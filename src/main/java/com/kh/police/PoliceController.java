package com.kh.police;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.moduhome.CommandMap;
import com.kh.sns.SnsService;

@Controller
public class PoliceController {

	
	
	@Resource(name="policeService")
	private PoliceService policeService;
	
	@Resource(name="snsService")
	private SnsService snsService;
	
	@RequestMapping(value="/police")  //후에 스토리view에 추가해줘야됨 신고작성할때 필요한 내용 솔직히 다 있는내용 구지 안해도될듯
	public ModelAndView police(HttpSession session,CommandMap map, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();		
		String mem_id = session.getAttribute("MEMBER_NUMBER").toString();  //세션에서 아이디가져오기		
		String article_seq = "542";  //게시물번호 설정
		
		String url = request.getRequestURL().toString();
        mv.addObject("mem_id", mem_id);
        mv.addObject("article_seq", article_seq);
        mv.addObject("url", url);  //지금 현재 url 보내줌
		mv.setViewName("police"); 
		return mv;
	}
	
	@RequestMapping(value="/policeReg",method= RequestMethod.POST)
	public ModelAndView policeReg(HttpServletRequest request, CommandMap Map)throws Exception{
		ModelAndView mav = new ModelAndView();
		
		policeService.policeReg(Map.getMap());   //신고내용 넣기
		
		String url = Map.get("url").toString(); //돌아가기 위한 url
		
		mav.addObject("url", url); // alert 확인을 누르고 돌아갈 경로
		mav.setViewName("policeRegSuc"); //신고완료 alert를 띄어줄 jsp
		
		return mav;
		
	}
	
	@RequestMapping(value="/policeList")
	public ModelAndView policeList(HttpSession session, CommandMap map, HttpServletRequest request)throws Exception{
		String mem_id = session.getAttribute("MEMBER_NUMBER").toString();
		
		if(mem_id != "0") {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("NOTEnterPoliceList");			
		}
		
		List <Map<String,Object>> list = policeService.policelist();
		
		ModelAndView mv = new ModelAndView("adminPoliceList");

		
		mv.addObject("list", list);
		return mv;
		
	}
	
	/*신고목록삭제*/
	@RequestMapping(value="/policedelete", method= RequestMethod.POST)
	public @ResponseBody String deletePolice(HttpServletRequest request, CommandMap Map) throws Exception {
		
		
		System.out.println("체크넘 : " +Map.get("checkNum").toString());
		
		String[] arrIdx = Map.get("checkNum").toString().split(",");
		for (int i=0; i<arrIdx.length; i++) {
		    policeService.Policedelete(arrIdx[i]);
		}
     return "ok";
		
	}
	/*신고된 게시물 숨기기*/
	@RequestMapping(value="/policeHide", method= RequestMethod.POST)
	public @ResponseBody String HideSNS(HttpServletRequest request, CommandMap Map) throws Exception {
		
		
		System.out.println("체크넘 : " +Map.get("checkNum").toString());
		
		String[] arrIdx = Map.get("checkNum").toString().split(",");
		String sns_num = "";
		
		for (int i=0; i<arrIdx.length; i++) {
		   sns_num = policeService.selectSNSnum(arrIdx[i]);
		     policeService.policeUpdateHide(sns_num); //police_hide값을 1로 바꿈     
		     snsService.snsHide(sns_num);//sns숨기기 sns_hide 를 1로 바꾸기
		}
		
		System.out.println("sns_num : "+sns_num );
		
		/*for(int i=0; i < sns_num.length; i++) {
		System.out.println("sns넘버 : "+sns_num[i]);
		}*/
        return "ok";
		
	}
	
	/*숨기기취소*/
	@RequestMapping(value="/policeHideCancle", method= RequestMethod.POST)
	public @ResponseBody String CancleHideSNS(HttpServletRequest request, CommandMap Map) throws Exception {
			
		System.out.println("체크넘 : " +Map.get("checkNum").toString());
		
		String[] arrIdx = Map.get("checkNum").toString().split(",");
		String sns_num = "";
		
		for (int i=0; i<arrIdx.length; i++) {
		   sns_num = policeService.selectSNSnum(arrIdx[i]);
		     policeService.UpdateHideCancle(sns_num);  //police_hide=0 해당 sns_num 숨기기취소     
		     snsService.snsHideCancel(sns_num);//sns숨기기
		}
		
		System.out.println("sns_num : "+sns_num );
		
		/*for(int i=0; i < sns_num.length; i++) {
		System.out.println("sns넘버 : "+sns_num[i]);
		}*/
        return "ok";
		
	}

	
}
