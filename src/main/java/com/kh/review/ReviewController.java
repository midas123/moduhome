package com.kh.review;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.kh.moduhome.CommandMap;

@Controller
public class ReviewController {

	@Resource(name = "reviewService")
	private ReviewService reviewService;
	
	//리뷰 폼으로 이동
	@RequestMapping(value="/review/reviewForm")
	public ModelAndView reviewForm(CommandMap commandMap, HttpServletRequest request){
		ModelAndView mv=new ModelAndView("store/review/modal_reviewForm");
		return mv;
	}
	
	//리뷰 등록
	@RequestMapping(value="/reviewWrite")
	public ModelAndView reviewWrite(CommandMap commandMap, HttpServletRequest request, HttpSession session) throws Exception{
		System.out.println("ddddddd:"+session.getAttribute("MEMBER_NUMBER"));
		ModelAndView mv=new ModelAndView("redirect:goods/detail?GOODS_NUMBER="+commandMap.get("GOODS_NUMBER"));
		commandMap.getMap().put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER").toString());
		reviewService.reviewWrite(commandMap.getMap(),request);
		reviewService.reviewPoint(commandMap.getMap());
		return mv;
	}
	
	//마이페이지 리뷰 삭제
		@RequestMapping(value = "/reviewDelete")
		@ResponseBody
		public ModelAndView deleteReview(CommandMap commandMap, HttpServletRequest request) throws Exception {
			ModelAndView mv = new ModelAndView();
			reviewService.reviewDelete(commandMap.getMap());
			
			if(commandMap.getMap().get("DETAIL")!=null) {
				String temp = (String)commandMap.getMap().get("DETAIL");
				if(temp.equals("1")){
					String GOODS_NUMBER = (String)commandMap.getMap().get("GOODS_NUMBER");
					mv.setViewName("redirect:/goods/detail?GOODS_NUMBER="+GOODS_NUMBER);
				}
			}else {
			mv.setViewName("redirect:/mypage#review");
			}

			return mv;
		}
	
}
