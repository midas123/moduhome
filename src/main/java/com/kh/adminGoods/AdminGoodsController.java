package com.kh.adminGoods;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.moduhome.CommandMap;
import com.kh.paging.Paging;

@Controller
public class AdminGoodsController {

	@Resource(name = "adminGoodsService")
	private AdminGoodsService adminGoodsService;
	
	//페이징 변수
	private int searchNum;
	private String isSearch;
	
	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 20;
	private int blockPage = 10;
	private String pagingHtml;
	private Paging page;

	// 상품 목록 불러오기
	@RequestMapping(value = "/admin/goodsList")
	public ModelAndView adminGoodsList(CommandMap commandMap, HttpServletRequest request) throws Exception {

		if(request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
				|| request.getParameter("currentPage").equals("0")) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		ModelAndView mv = new ModelAndView("goodsList");

		List<Map<String, Object>> goodsList = adminGoodsService.goodsList(commandMap.getMap());
		
		if(request.getParameter("isSearch") != null) {
			isSearch = request.getParameter("isSearch");
			searchNum = Integer.parseInt(request.getParameter("searchNum"));
			
			if(searchNum == 0) //상품이름
				goodsList = adminGoodsService.adminGoodsSearch0(isSearch);
			else if(searchNum == 1) // 글번호
				goodsList = adminGoodsService.adminGoodsSearch1(isSearch);
			else if(searchNum == 2) // 카테고리
				goodsList = adminGoodsService.adminGoodsSearch2(isSearch);
			else if(searchNum == 3) // 판매 활성화 or 비활성화
				goodsList = adminGoodsService.adminGoodsSearch3(isSearch);
			else if(searchNum == 4) // 재고 0인 상품
				goodsList = adminGoodsService.adminGoodsSearch4(isSearch);
			else if(searchNum == 5) // 판매량 많은 순 정렬
				goodsList = adminGoodsService.adminGoodsSearch5(isSearch);
			else if(searchNum == 6) // 조회수 많은 순 정렬
				goodsList = adminGoodsService.adminGoodsSearch6(isSearch);
			else if(searchNum == 7) // 조회수 많은 순 정렬
				goodsList = adminGoodsService.adminGoodsSearch7(isSearch);
			else
				goodsList = adminGoodsService.goodsList(commandMap.getMap());
			
			totalCount = goodsList.size();
			page = new Paging(currentPage, totalCount, blockCount, blockPage, "goodsList", searchNum, isSearch);
			pagingHtml = page.getPagingHtml().toString();
			
			int lastCount = totalCount;
			
			if(page.getEndCount() < totalCount)
				lastCount = page.getEndCount() + 1;
			
			goodsList = goodsList.subList(page.getStartCount(), lastCount);
			
			mv.addObject("isSearch", isSearch);
			mv.addObject("searchNum", searchNum);
			mv.addObject("totalCount", totalCount);
			mv.addObject("pagingHtml", pagingHtml);
			mv.addObject("currentPage", currentPage);
			mv.addObject("goodsList", goodsList);
			mv.setViewName("goodsList");
			return mv;
		}
		
		totalCount = goodsList.size();
		
		page = new Paging(currentPage, totalCount, blockCount, blockPage, "goodsList");
		pagingHtml = page.getPagingHtml().toString();
		
		int lastCount = totalCount;
		
		if(page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;
		
		goodsList = goodsList.subList(page.getStartCount(), lastCount);
		
		mv.addObject("totalCount", totalCount);
		mv.addObject("pagingHtml", pagingHtml);
		mv.addObject("currentPage", currentPage);
		
		Calendar today = Calendar.getInstance();
		Date d = new Date(today.getTimeInMillis());

		for (int i = 0; i < goodsList.size(); i++) {
			if (goodsList.get(i).get("GOODS_SALEDATE") != null && goodsList.get(i).get("GOODS_DISPRICE") != null) {
				Date dDay = (Date) goodsList.get(i).get("GOODS_SALEDATE");
				if (dDay.getTime() < d.getTime()) {
					goodsList.get(i).remove("GOODS_SALEDATE");
					goodsList.get(i).remove("GOODS_DISPRICE");
				}
			}
		}

		mv.addObject("goodsList", goodsList);
		Integer count = adminGoodsService.goodsCount();
		int a = count;
		mv.addObject("count", a);

		return mv;
	}

	// 상품 등록폼으로 이동
	@RequestMapping(value = "/admin/goodsInsertForm")
	public ModelAndView goodsInsertForm() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("goodsForm");

		return mv;
	}

	// 상품 등록
	@RequestMapping(value = "/admin/goodsInsert")
	public ModelAndView goodsInsert(CommandMap commandMap, HttpServletRequest request) throws Exception {

		 ModelAndView mv = new ModelAndView("redirect:goodsList");
	      if(((String)commandMap.getMap().get("GOODS_DISPRICE")).equals("")) {
	         System.out.println("들어와짐?");
	         commandMap.getMap().put("GOODS_DISPRICE", commandMap.getMap().get("GOODS_PRICE"));
	      }
	      adminGoodsService.insertGoods(commandMap.getMap(), request);

	      return mv;

	}

	// 상품 수정폼으로 이동
	@RequestMapping(value = "/admin/goodsModifyForm")
	public ModelAndView goodsModifyForm(CommandMap commandMap) throws Exception {

		ModelAndView mv = new ModelAndView("goodsModifyForm");
		System.out.println("수정폼");
		List<Map<String, Object>> goodsDetail = adminGoodsService.modifyGoodsForm(commandMap.getMap());
		mv.addObject("goodsDetail", goodsDetail);
		Map<String, Object> goodsBasic = goodsDetail.get(0);

		mv.addObject("goodsBasic", goodsBasic);
		
		if (goodsBasic.get("GOODS_SALEDATE") != null) {
			Date from = new Date();
			from = (Date) goodsBasic.get("GOODS_SALEDATE");
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			String to = transFormat.format(from);
			mv.addObject("SALEDATE", to);
		}

		List<Map<String, Object>> goodsImage = adminGoodsService.modifyGoodsFormImage(commandMap.getMap());
		mv.addObject("goodsImage", goodsImage);

		return mv;
	}

	// 상품 수정
	@RequestMapping(value = "/admin/goodsModify")
	public ModelAndView goodsModify(CommandMap commandMap, HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("redirect:goodsList");

		adminGoodsService.modifyGoods(commandMap.getMap(), request);

		return mv;
	}

	// 상품 삭제
	@RequestMapping(value = "/admin/goodsDelete")
	public ModelAndView goodsDelete(CommandMap commandMap) throws Exception {

		ModelAndView mv = new ModelAndView("redirect:goodsList");

		adminGoodsService.deleteGoods(commandMap.getMap());

		return mv;
	}
}
