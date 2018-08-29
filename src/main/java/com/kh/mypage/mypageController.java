package com.kh.mypage;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kh.follow.FollowService;
import com.kh.follow.MemberModel;
import com.kh.mg.MgService;
import com.kh.moduhome.CommandMap;

import jdk.nashorn.internal.ir.RuntimeNode.Request;


@Controller
public class mypageController {

	@Resource(name="followService")
	private FollowService followService;
	
	@Resource(name = "mgService")
	private MgService mgService;	
	   
	@RequestMapping(value="/myHome", method=RequestMethod.GET)
	public ModelAndView myHome(@ModelAttribute("MemberModel") MemberModel memberModel, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		
		//회원넘버 얻기
		String mem_id;
		int MEMBER_NUMBER;
		
		//남의 마이페이지에 들어갔을때에 동작
		if((request.getParameter("MEMBER_NUMBER") != null)) {
			mem_id = request.getParameter("MEMBER_NUMBER");
			MEMBER_NUMBER =  Integer.parseInt(mem_id);
		}
		//내 마이페이지에 들어갔을때에 동작
		else {
			mem_id = session.getAttribute("MEMBER_NUMBER").toString();
			MEMBER_NUMBER = Integer.parseInt(mem_id);
		}
		
		//회원에 대한 정보 가져오기
		memberModel = followService.selectIdMember(MEMBER_NUMBER);
		
		if(session.getAttribute("MEMBER_NUMBER")!=null){
			int follow_exist = followService.followExist(MEMBER_NUMBER, session.getAttribute("MEMBER_NUMBER"));
			mv.addObject("follow_exist", follow_exist);
		}		
		
		//팔로우, 팔로잉에 대한 정보 가져오기
		String follow_quantity = followService.selectfollowQuan(mem_id);
		String following_quantity = followService.selectfollowingQuan(mem_id);

		mv.addObject("follow_quantity", follow_quantity);
		mv.addObject("following_quantity", following_quantity);	
		
		mv.addObject("memberModel", memberModel);
		mv.setViewName("myHome");
		
		return mv;
	}
	
	@RequestMapping(value="/myCollecting" ,method=RequestMethod.GET)
	public ModelAndView myCollecting(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		int MEMBER_NUMBER;
		HttpSession session = request.getSession();
		
		//남의 마이페이지의 보관함에 들어갔을때에 동작 
		if((request.getParameter("MEMBER_NUMBER") != null)) {
			MEMBER_NUMBER =  Integer.parseInt(request.getParameter("MEMBER_NUMBER"));
		}
		//내 마이페이지의 보관함을 들어갔을때에 동작
		else {
			MEMBER_NUMBER = Integer.parseInt(session.getAttribute("MEMBER_NUMBER").toString());
		}
		
		List<Map<String, Object>> mgMycollectList = mgService.mgMycollectList(MEMBER_NUMBER);
		mv.addObject("mgMycollectList", mgMycollectList);

		mv.setViewName("myCollecting");
		return mv;		
	}
	
	@RequestMapping(value="/myStory" ,method=RequestMethod.GET)
	public ModelAndView myStory( HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		int MEMBER_NUMBER ;
		
		//남의 마이페이지의 스토리에 들어갔을때에 동작
		if((request.getParameter("MEMBER_NUMBER") != null)) {
			MEMBER_NUMBER =  Integer.parseInt(request.getParameter("MEMBER_NUMBER"));
			System.out.println("파람~");
		}
		//내 마이페이지의 내가쓴 스토리를 들어갔을때에 동작
		else {
			MEMBER_NUMBER = Integer.parseInt(session.getAttribute("MEMBER_NUMBER").toString());
			System.out.println("세션세션");
		}
		
		//snsboard에서 내 아이디에 해당하는 스토리를 꺼내오는 로직 추가
		//SELECT * FROM SNSBOARD WHERE MEMBER_NUMBER=61;
		mv.setViewName("myStory");
		return mv;
	}
}
