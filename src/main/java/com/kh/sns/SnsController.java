package com.kh.sns;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import com.kh.moduhome.CommandMap;
import com.kh.snsComment.SnsCommentService;
import com.kh.alarm.AlarmService;
import com.kh.follow.FollowListModel;
import com.kh.follow.FollowService;


@Controller
public class SnsController {

	@Resource(name = "snsService")
	private SnsService snsService;
	
	@Resource(name = "snscommentService")
	private SnsCommentService snscommentService;
	
	@Resource(name="followService")
	private FollowService followService;
	
	@Resource(name="alarmService")
	private AlarmService alarmService;	

/*	//스토리 리스트
	@RequestMapping(value = "/snslist")
	public ModelAndView snsList(HttpSession session,CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		String MEMBER_NUMBER = session.getAttribute("MEMBER_NUMBER").toString();
		
		List<Map<String, Object>> snsList = snsService.snsList(commandMap.getMap());
		List<Map<String, Object>> snsCommentList = snscommentService.snsCommentList(commandMap.getMap());
		List<Map<String, Object>> snsList2 = snsService.snsList2(MEMBER_NUMBER);
		
		String url = request.getRequestURL().toString(); //신고하기폼에서 x를 누를떄 , 신고하기를 성공하면 되돌아갈 페이지
		
		//프로젝트옮길때 리뷰리스트 코맨트패키지로 옮기기	
		mv.addObject("url", url);
		mv.addObject("MEMBER_NUMBER", MEMBER_NUMBER);
		mv.addObject("snsCommentList", snsCommentList);
		mv.addObject("snsList", snsList);
		mv.addObject("snsList2", snsList2);
		mv.setViewName("/sns/snsList");
		
		return mv;
	}*/
	
	// 매거진 타이틀 삭제
	@RequestMapping(value = "/snsdelete")
	public ModelAndView snsDelete(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/snslist");
		
		System.out.println("number" + commandMap.get("SNS_NUMBER"));
		System.out.println("image" + commandMap.get("SNS_IMAGE"));

		snsService.snsDelete(commandMap.getMap());
		
		System.out.println("imageok");
		return mv;

	}
	
	//스토리 리스트
	@RequestMapping(value = "/snslist")
	public ModelAndView snsList(HttpSession session, CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		String MEMBER_NUMBER = "";
		if(session.getAttribute("MEMBER_NUMBER") == null) {
			 MEMBER_NUMBER = "0";
		}else {
		     MEMBER_NUMBER = session.getAttribute("MEMBER_NUMBER").toString();
		}
		//List<Map<String, Object>> snsList = snsService.snsList(commandMap.getMap());
		List<Map<String, Object>> snsList = snsService.snsList(MEMBER_NUMBER);
		List<Map<String, Object>> snsCommentList = snscommentService.snsCommentList(commandMap.getMap());
		
		String url = request.getRequestURL().toString(); //신고하기폼에서 x를 누를떄 , 신고하기를 성공하면 되돌아갈 페이지
		mv.addObject("MEMBER_NUMBER", MEMBER_NUMBER);
		mv.addObject("snsCommentList", snsCommentList);
		mv.addObject("url", url);
		mv.addObject("snsList", snsList);
		mv.setViewName("snsList");
		
		return mv;
	}
	

	// 스토리 등록
	@RequestMapping(value = "/snsinsert", method = RequestMethod.POST)
	public ModelAndView snsInsert(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/snslist");

		System.out.println("member_number의 값은?" + commandMap.get("MEMBER_NUMBER"));
		System.out.println("sns쓰기가 실행~!");
		
		
/*		f
		for() {
		alarm*/
		int member_number = Integer.parseInt(commandMap.get("MEMBER_NUMBER").toString());
		FollowListModel followListModel = new FollowListModel();
		List<String> list = followService.followingAlarm(member_number);
		
		snsService.snsInsert(commandMap.getMap(), request);
		
		//글 쓴 후에 나를 팔로잉 한 사람에게 알람 전송하기
		for(int i=0; i < list.size() ;i++) {
			int article_writer = Integer.parseInt(list.get(i));
			//System.out.println("겟팔로워"+list.get(i));
			alarmService.regAlarm(article_writer, member_number, 0, 4); //좋아요알람등록
		}
		
		return mv;
	}

	
}
