package com.kh.goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.moduhome.CommandMap;

import com.kh.paging.GoodsPaging;


@Controller
public class GoodsController {

	@Resource(name="goodsService")
	private GoodsService goodsService;
	
	 public static final int pagingSet = 5;
	 private int currentPage = 1;
	 private int totalCount;
	 private int blockCount = 9;
	 private int blockPage = 5;
	 private String pagingHtml;
	 private GoodsPaging page;
	
	
	@RequestMapping(value = "/goods")
	public ModelAndView goodsMain(HttpServletResponse response, HttpServletRequest request, CommandMap Map) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goodsMain");
		//신상품, 인기상품 정렬
		List<Map<String, Object>> sellBestItem = goodsService.bestSellAll(Map.getMap());
		List<Map<String, Object>> newItem = goodsService.newItemAll(Map.getMap());
		
		mv.addObject("sellBestItem", sellBestItem);
		mv.addObject("newItem", newItem);
		return mv;
		
	}
	
	@RequestMapping(value = "/goods/category")
	public ModelAndView goodsCategory(HttpServletResponse response, HttpServletRequest request, CommandMap Map) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goodsCategory");
		//대분류 카테고리
		String categoryName = (String) Map.getMap().get("CATEGORY");
		//소분류 카테고리
		String subCategoryName = (String) Map.getMap().get("SUBCATEGORY");
		//상품 정렬 순서
		String sort = (String) Map.getMap().get("sort");
		//상품 정렬순서 값
		if(sort != null) {
			//ajax로 전송되는 상품 정렬 페이지 설정
			mv.setViewName("store/goodsSort");
			Map.getMap().put("sort", sort);
		} else {
			//상품 정렬 기본 값(최신순)
			sort = "1";
			Map.getMap().put("sort", sort);
		}
		//요청으로 넘어온 소분류 카테고리가 있을 경우
		if(subCategoryName == null || subCategoryName == "") {
			subCategoryName = null;
			Map.getMap().put("SUBCATEGORY", subCategoryName);
		}
		if(categoryName.equals("전체")) {
			System.out.println("전체출력");
			Map.getMap().put("CATEGORY", null);
		}
		//상품 게시판에서 메뉴로 출력되는 소분류 카테고리 목록
		List<String> goodsCategory = new ArrayList<>();
		 if (categoryName.equals("가구")) {
	         goodsCategory.add("침실가구");
	         goodsCategory.add("거실가구");
	         goodsCategory.add("주방가구");
	         goodsCategory.add("홈오피스");
	         goodsCategory.add("테이블");
	         goodsCategory.add("체어");
		 }
		 if (categoryName.equals("가전")) {
	         goodsCategory.add("생활가전");
	         goodsCategory.add("주방가전");
	         goodsCategory.add("시즌가전");
		 }
		 if (categoryName.equals("패브릭")) {
	         goodsCategory.add("커튼/블라인드");
	         goodsCategory.add("매트·러그");
	         goodsCategory.add("패브릭소품");
		 }
		 if (categoryName.equals("주방")) {
	         goodsCategory.add("주방용품");
	         goodsCategory.add("주방수납");
	         goodsCategory.add("주방소품");
		 }
		 if (categoryName.equals("생활·수납")) {
	         goodsCategory.add("홈케어");
	         goodsCategory.add("욕실용품");
	         goodsCategory.add("생활용품");
		 }
		 //카테고리명, 상품 정렬순서 값이 담긴 Map객체로 DB검색 실행
		if(Map.getMap() !=null) {
		List<Map<String, Object>> goodsCategoryList = goodsService.goodsCategory(Map.getMap());
		 //상품 게시판 페이징
	      if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
	            || request.getParameter("currentPage").equals("0")) {
	         currentPage = 1;
	      } else {
	         currentPage = Integer.parseInt(request.getParameter("currentPage"));
	      }
	      totalCount = goodsCategoryList.size();
	      page = new GoodsPaging(currentPage, totalCount, blockCount, blockPage);
	      pagingHtml = page.getPagingHtml().toString();
	      int lastCount = totalCount;
	      if (page.getEndCount() < totalCount)
	         lastCount = page.getEndCount() + 1;
	      goodsCategoryList = goodsCategoryList.subList(page.getStartCount(), lastCount);
	      mv.addObject("totalCount", totalCount);
	      mv.addObject("pagingHtml", pagingHtml);
		  mv.addObject("categoryName", categoryName);
		  mv.addObject("subCategory", goodsCategory);
		  mv.addObject("subCategoryOne", subCategoryName);
		  mv.addObject("goodsCategoryList", goodsCategoryList);
		}
		return mv;
	}
	
	@RequestMapping(value = "/goods/detail")
	public ModelAndView goodsDetail(HttpServletResponse response, HttpServletRequest request, CommandMap Map, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		//상품페이지 첫 요청시 (ajax 요청이 아닐 경우)
		if(Map.getMap().get("pagingReviewOnOff") == null && Map.getMap().get("pagingQnaOnOff") == null) {
			System.out.println("상품페이지 첫 요청");
			mv.setViewName("goodsDetail");
			int checkBuy;
			
			goodsService.goodsCountUp(Map.getMap());
			System.out.println("goodsdetailMap:"+Map.getMap());
			List<Map<String, Object>> goodsDetail = goodsService.selectOneGood(Map.getMap());
		    List<Map<String, Object>> goodsImage = goodsService.selectImage(Map.getMap());
		    
		    Map<String, Object> goodsBasic = goodsDetail.get(0);
		    System.out.println("goodsBasic:"+goodsBasic);
		    
		    mv.addObject("goodsBasic", goodsBasic);
		    mv.addObject("GOODS_NUMBER", goodsDetail.get(0).get("GOODS_NUMBER"));
		
		    
		    List<Map<String, Object>> relatedGoods = goodsService.selectRelatedGoods(goodsBasic);
		    
		    if (session.getAttribute("MEMBER_NUMBER") != null) {
		         String mem_num = session.getAttribute("MEMBER_NUMBER").toString();
		         Map.put("MEMBER_NUMBER", mem_num);
		         
		         try { 
	
		            checkBuy = goodsService.checkBuy(Map.getMap());
		            mv.addObject("checkBuy", checkBuy);
		            
		         } catch (Exception e) { 
		            checkBuy = 0;
		            mv.addObject("checkBuy", checkBuy);
		         }
		         System.out.println("checkBuy:"+checkBuy);
	         
	      }
		     mv.addObject("goodsDetail", goodsDetail);
		     mv.addObject("relatedGoods", relatedGoods);
			 mv.addObject("goodsImage", goodsImage);
		}
		
	    //상품후기 리스트
	    List<Map<String, Object>> reviewList = goodsService.selectReview(Map.getMap());
	      int pagingSet = 5;
	      int reviewEndPagingNum = pagingSet;
	      int reviewStartPagingNum = 0;
	      int reviewNowPage = 1;
	      
	      String pagingReviewOnOff = (String) Map.getMap().get("pagingReviewOnOff");
	      
	      //상품 후기 ajax 페이징
	      if (pagingReviewOnOff != null) {//ajax 페이징 on 
	         String pagingCheck = (String) Map.getMap().get("pagingCheck");//next/prev 구분
	         reviewEndPagingNum = Integer.parseInt((String) Map.getMap().get("reviewEndPagingNum"));//마지막 게시글
	         reviewStartPagingNum = Integer.parseInt((String) Map.getMap().get("reviewStartPagingNum"));//처음 게시글
	         reviewNowPage = Integer.parseInt((String) Map.getMap().get("reviewNowPage"));//현재 페이지 번호
	         
	         if (pagingCheck.equals("1"))// prev 클릭
	         {
	            if (reviewEndPagingNum != pagingSet) {
	               reviewStartPagingNum = reviewStartPagingNum - pagingSet;
	               reviewEndPagingNum = reviewEndPagingNum - pagingSet;
	               reviewNowPage = reviewNowPage - 1;
	            }
	         } else if (pagingCheck.equals("2")) // next 클릭
	         {
	            if (reviewEndPagingNum < reviewList.size()) {
	               reviewStartPagingNum = reviewStartPagingNum + pagingSet;
	               reviewEndPagingNum = reviewEndPagingNum + pagingSet;
	               reviewNowPage = reviewNowPage + 1;
	            } 
	         }
	         mv.setViewName("store/review/goodsDetail_Review");
	      }
	      mv.addObject("reviewEndPagingNum", reviewEndPagingNum);
	      mv.addObject("reviewStartPagingNum", reviewStartPagingNum);
	      mv.addObject("reviewNowPage", reviewNowPage);
	      mv.addObject("reviewSize", reviewList.size());
	      mv.addObject("reviewTotalPage", (int) Math.ceil((double) reviewList.size() / pagingSet));
	    
	    
	    //QnA 리스트
	    List<Map<String, Object>> qnaList = goodsService.selectQNA(Map.getMap());
	    System.out.println("qnaList:"+qnaList);
	    System.out.println("qnaListSIze:"+qnaList.size());
	    
	    //QNA 페이징
	    int qnaEndPagingNum = pagingSet; // 5
        int qnaStartPagingNum = 0;
        int qnaNowPage = 1;

	      String pagingQnaOnOff = (String) Map.getMap().get("pagingQnaOnOff");
	      System.out.println("상품문의pagingQnaOnOff:"+pagingQnaOnOff);
	      System.out.println("pagingQnaOnOff: " + (String) Map.getMap().get("pagingQnaOnOff"));
	      if (pagingQnaOnOff != null) {
	         String pagingCheck = (String) Map.getMap().get("pagingCheck");
	         qnaEndPagingNum = Integer.parseInt((String) Map.getMap().get("qnaEndPagingNum"));
	         qnaStartPagingNum = Integer.parseInt((String) Map.getMap().get("qnaStartPagingNum"));
	         qnaNowPage = Integer.parseInt((String) Map.getMap().get("qnaNowPage"));
	         if (pagingCheck.equals("1"))// prev 클릭
	         {
	            if (qnaEndPagingNum != pagingSet) {
	               qnaStartPagingNum = qnaStartPagingNum - pagingSet;
	               qnaEndPagingNum = qnaEndPagingNum - pagingSet;
	               qnaNowPage = qnaNowPage - 1;
	            }
	         } else if (pagingCheck.equals("2")) // next 클릭
	         {
	            if (qnaEndPagingNum < qnaList.size()) {
	               qnaStartPagingNum = qnaStartPagingNum + pagingSet;
	               qnaEndPagingNum = qnaEndPagingNum + pagingSet;
	               qnaNowPage = qnaNowPage + 1;
	            }

	         }
	         mv.setViewName("store/qna/goodsDetail_Qna");
	      }

	      int qnaTotalPage = (int) Math.ceil((double) qnaList.size() / pagingSet);
	      mv.addObject("qnaEndPagingNum", qnaEndPagingNum);
	      mv.addObject("qnaStartPagingNum", qnaStartPagingNum);
	      mv.addObject("qnaNowPage", qnaNowPage);
	      mv.addObject("qnaSize", qnaList.size());
	      mv.addObject("qnaTotalPage", qnaTotalPage);
	     mv.addObject("qnaList", qnaList);
	     mv.addObject("reviewList", reviewList);
	     mv.addObject("GOODS_NUMBER", Map.getMap().get("GOODS_NUMBER"));
		
		return mv;
	}
	
	@RequestMapping(value = "/goods/search", produces = "application/json; charset=utf8")
	public ModelAndView goodsSearch(HttpServletResponse response, HttpServletRequest request, CommandMap Map) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goodsSearch");

		List<Map<String, Object>> goodsSearchList = goodsService.goodsSearchList(Map.getMap());
	    String searchKeyWord = (String) Map.getMap().get("isSearch");

	     //페이징
	      if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
	            || request.getParameter("currentPage").equals("0")) {
	         currentPage = 1;
	      } else {
	         currentPage = Integer.parseInt(request.getParameter("currentPage"));
	         System.out.println("CurrentPage :" + currentPage);
	      }
	      System.out.println("CurrentPage :" + currentPage);

	      totalCount = goodsSearchList.size();

	      page = new GoodsPaging(currentPage, totalCount, blockCount, blockPage);
	      pagingHtml = page.getPagingHtml().toString();

	      int lastCount = totalCount;

	      if (page.getEndCount() < totalCount)
	         lastCount = page.getEndCount() + 1;

	      goodsSearchList = goodsSearchList.subList(page.getStartCount(), lastCount);
		
	      
	      mv.addObject("goodsSearchList", goodsSearchList);
	      mv.addObject("searchKeyWord", searchKeyWord);
	      mv.addObject("totalCount", totalCount);
	      mv.addObject("pagingHtml", pagingHtml);
	      mv.addObject("currentPage", currentPage);
	      
		return mv;
		
	}
}
