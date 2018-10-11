package com.kh.like;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.alarm.AlarmService;
import com.kh.moduhome.CommandMap;
import com.kh.sns.SnsService;

@Controller
public class LikeController {
    
	
	
	@Resource(name="likeService")
	private LikeService likeService;
	
	@Resource(name="alarmService")
	private AlarmService alarmService;
	
	@Resource(name="snsService")
	private SnsService snsService;
	
	
	//팔로우 폼 예딱님 환영합니다~
	@RequestMapping(value="/like")
	public ModelAndView likeForm(HttpSession session,CommandMap map) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		/*int MEMBER_NUMBER = (int)session.getAttribute("MEMBER_NUMBER");*/
		String mem_id = session.getAttribute("MEMBER_NUMBER").toString();
		System.out.println("MEMBER_NUMBER : "+mem_id);
		/*memberModel = likeService.selectMember*/
		
		String article_seq = "124";
		
		String like_count = likeService.snsLikeCount(article_seq); //한 게시물의 like의 양
		System.out.println("like_count : "+like_count);
		String like_exist="";
		
		map.getMap().put("SNS_NUMBER", article_seq);
		map.getMap().put("MEMBER_NUMBER", mem_id);
		
		
		if(session.getAttribute("MEMBER_ID") != null) {
		     like_exist = likeService.snsLikeExist(map.getMap());
	   }else {
		   like_exist = "0";
	   }
		System.out.println("/like");
		System.out.println("article_seq : "+article_seq);
		System.out.println("like_exist : "+like_exist);
		System.out.println("like_count : "+like_count);
		
		mv.addObject("mem_id", mem_id);
		mv.addObject("article_seq", article_seq);
		mv.addObject("like_count", like_count);
		mv.addObject("like_exist", like_exist);
		
		
		mv.setViewName("mylike"); 
		
		return mv;
	}
	
	
	
	@RequestMapping(value="/likeSNSReg", method= RequestMethod.POST)
	public @ResponseBody String likeSNSReg(HttpServletRequest request, CommandMap Map)throws Exception{
		
		likeService.likeSNSReg(Map.getMap());		
		String sns_number = Map.getMap().get("SNS_NUMBER").toString();
		int like_count = Integer.parseInt(likeService.snsLikeCount(sns_number));
		Map.put("like_count", like_count);
		snsService.updateLike(Map.getMap());
		
		
		/*알람*/
		/*System.out.println("" + Map.get("MEMBER_NUMBER"));
		int article_writer = snsService.snsWriter(sns_number);
		int reg_id = Integer.parseInt((String) Map.getMap().get("MEMBER_NUMBER")); 
		int article_num = Integer.parseInt((String) Map.getMap().get("SNS_NUMBER")); //알람발생 sns_number
		alarmService.regAlarm(article_writer, reg_id, article_num, 1); //좋아요알람등록
*/		
		return "1";
		
	}
	
	@RequestMapping(value="/likeSNSDel", method = RequestMethod.POST)
	public @ResponseBody String likeSNSDel(HttpServletRequest request, CommandMap Map) throws Exception{

		likeService.likeSNSDel(Map.getMap()); //좋아요 취소
		String sns_number = Map.getMap().get("SNS_NUMBER").toString();
		int like_count = Integer.parseInt(likeService.snsLikeCount(sns_number));
		Map.put("like_count", like_count);
		snsService.downLike(Map.getMap());
		
		return "2";
	}
	
}
