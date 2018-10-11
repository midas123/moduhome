package com.kh.mgComment;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.kh.moduhome.CommandMap;

@Controller
public class MgCommentController {

	@Resource(name = "mgcommentService")
	private MgCommentService mgcommentService;
	
	// 매거진 댓글 삭제
	@RequestMapping(value = "/mgcommentdelete")
	public ModelAndView mgDelete(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/mgDetail?MG_NUMBER="+commandMap.get("MG_NUMBER")+"&MEMBER_NUMBER="+commandMap.get("MEMBER_NUMBER"));
		
		System.out.println("11" + commandMap.get("MG_CM_NUMBER"));

		mgcommentService.mgCommentDelete(commandMap.getMap(), request);

		return mv;
	}
	
	//매거진 댓글 등록
	@RequestMapping(value="/mgCommentInsert")
	public ModelAndView mgCommentInsert(CommandMap commandMap, HttpServletRequest request, HttpSession session) throws Exception{
		
		ModelAndView mv=new ModelAndView("redirect:/mgDetail?MG_NUMBER="+commandMap.get("MG_NUMBER")+"&MEMBER_NUMBER="+commandMap.get("MEMBER_NUMBER"));
		commandMap.getMap().put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER").toString());
		System.out.println("파람" + commandMap.getMap());
		mgcommentService.mgCommentInsert(commandMap.getMap(),request);
		
	
		return mv;
	}
	
	
}
