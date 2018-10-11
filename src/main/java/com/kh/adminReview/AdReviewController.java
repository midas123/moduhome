package com.kh.adminReview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.moduhome.CommandMap;
import com.kh.paging.Paging;
import com.kh.review.ReviewService;

@Controller
public class AdReviewController {

	// 검색, 페이징
	private int searchNum;
	private String isSearch;

	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 10;
	private int blockPage = 5;
	private String pagingHtml;
	private Paging page;

	@Resource(name = "reviewService")
	private ReviewService reviewService;

	@RequestMapping(value = "/admin/adminPage")
	public String list() {
		return "adminForm";
	}

	// 리뷰 관리자 리스트
		@RequestMapping(value = "/admin/reviewAdmin")
		public ModelAndView reviewList(CommandMap commandMap, HttpServletRequest request) throws Exception {

			if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
					|| request.getParameter("currentPage").equals("0")) {
				currentPage = 1;
			} else {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}

			ModelAndView mv = new ModelAndView();
			List<Map<String, Object>> list = reviewService.reviewList(commandMap.getMap());

			
			Map<String, Object> isSearchMap = new HashMap<String, Object>();
			
			isSearch = request.getParameter("isSearch");

			if (isSearch != null) {
			
				searchNum = Integer.parseInt(request.getParameter("searchNum"));
				isSearchMap.put("isSearch", isSearch);

				if (searchNum == 0) { // 회원ID
					list = reviewService.searchReviewList0(isSearchMap);
				} else if (searchNum == 1) { // 상품이름
					list = reviewService.searchReviewList1(isSearchMap);
				} else if (searchNum == 2) { // 상품번호
					list = reviewService.searchReviewList2(isSearchMap);
				}

				totalCount = list.size();
				page = new Paging(currentPage, totalCount, blockCount, blockPage, "reviewAdmin", searchNum, isSearch);
				pagingHtml = page.getPagingHtml().toString();

				int lastCount = totalCount;

				if (page.getEndCount() < totalCount)
					lastCount = page.getEndCount() + 1;

				list = list.subList(page.getStartCount(), lastCount);

				mv.addObject("isSearch", isSearch);
				mv.addObject("searchNum", searchNum);
				mv.addObject("totalCount", totalCount);
				mv.addObject("pagingHtml", pagingHtml);
				mv.addObject("currentPage", currentPage);
				mv.addObject("list", list);
				mv.setViewName("reviewAdmin");

				return mv;

			} else {
				totalCount = list.size();

				page = new Paging(currentPage, totalCount, blockCount, blockPage, "reviewAdmin");
				pagingHtml = page.getPagingHtml().toString();

				int lastCount = totalCount;

				if (page.getEndCount() < totalCount)
					lastCount = page.getEndCount() + 1;

				list = list.subList(page.getStartCount(), lastCount);

				mv.addObject("totalCount", totalCount);
				mv.addObject("pagingHtml", pagingHtml);
				mv.addObject("currentPage", currentPage);

				mv.addObject("list", list);
				mv.setViewName("reviewAdmin");

				return mv;
			}
		}

		// 리뷰 삭제
		@RequestMapping(value = "/admin/reviewAdminDelete")
		public ModelAndView reviewDelete(CommandMap commandMap) throws Exception {

			ModelAndView mv = new ModelAndView();

			reviewService.reviewDelete(commandMap.getMap());
			mv.setViewName("redirect:/admin/reviewAdmin");

			return mv;
		}

}
