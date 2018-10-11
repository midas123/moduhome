package com.kh.search;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.moduhome.CommandMap;


@Controller
public class SearchController {


	private String isSearch;
	private String MEMBER_NUMBER;



	@Resource(name = "searchService")
	private SearchService searchService;

	// 검색 리스트
	@RequestMapping(value = "/totalSearch")
	public ModelAndView totalSearch(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		MEMBER_NUMBER = request.getParameter("MEMBER_NUMBER");
		
		if (MEMBER_NUMBER != null) {
		searchService.recentSearchInsert(commandMap.getMap(),request);
		};
		
		List<Map<String, Object>> noticeSearch = searchService.noticeSearch(commandMap.getMap());
		List<Map<String, Object>> faqSearch = searchService.faqSearch(commandMap.getMap());
		List<Map<String, Object>> snsSearch = searchService.snsSearch(commandMap.getMap());
		List<Map<String, Object>> memberSearch = searchService.memberSearch(commandMap.getMap());
		List<Map<String, Object>> mgSearch = searchService.mgSearch(commandMap.getMap());
		List<Map<String, Object>> goodsSearch = searchService.goodsSearch(commandMap.getMap());
		
		System.out.println("1");
		
		
		
		isSearch = request.getParameter("ISSEARCH");
		mv.addObject("isSearch", isSearch);
		mv.addObject("noticeSearch", noticeSearch);
		mv.addObject("faqSearch", faqSearch);
		mv.addObject("snsSearch", snsSearch);
		mv.addObject("memberSearch", memberSearch);
		mv.addObject("mgSearch", mgSearch);
		mv.addObject("goodsSearch", goodsSearch);
		System.out.println("2");
		mv.setViewName("search");
		return mv;

	}
	
	
	
	// 검색 폼
	@RequestMapping(value = "/searchForm")
	public ModelAndView searchForm(CommandMap commandMap, HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		/*MEMBER_NUMBER = session.getAttribute("MEMBER_NUMBER").toString();*/
		
		if(session.getAttribute("MEMBER_NUMBER") != null) {
			MEMBER_NUMBER = session.getAttribute("MEMBER_NUMBER").toString();
			mv.addObject("MEMBER_NUMBER", MEMBER_NUMBER);
		};
		
		List<Map<String, Object>> recentSearch = searchService.recentSearch(commandMap.getMap());
		
		
		mv.addObject("recentSearch", recentSearch);
		mv.setViewName("searchForm");
		return mv;
	}


}
