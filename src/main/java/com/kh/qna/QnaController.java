package com.kh.qna;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.kh.moduhome.CommandMap;

@Controller
public class QnaController {

	@Resource(name="qnaService")
	private QnaService qnaService;
	
	// 검색, 페이징
	private int searchNum;
	private int categoryNum;
	private String isSearch;

	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 15;
	private int blockPage = 15;
	private String pagingHtml;


	private int ref;
	
	

	@RequestMapping(value = "/qna/modal_qnaForm")
	public ModelAndView modal_qanFormRepAop(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("store/qna/modal_qnaForm");
		System.out.println("qna컨트롤러");
		return mv;
	}

	@RequestMapping(value = "/qna/modalqnaWrite")
	public ModelAndView modal_qnaWriteLoginAop(CommandMap commandMap, HttpSession session, HttpServletRequest request) throws Exception {
		//commandMap.getMap().put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER").toString());
		System.out.println("qna쓰기:"+commandMap.getMap());
		qnaService.insertModalQna(commandMap.getMap(), request);
		
		ModelAndView mv = new ModelAndView();
		System.out.println("qna Goods_Number : " +commandMap.get("GOODS_NUMBER").toString());
		mv.setViewName("redirect:/goods/detail?GOODS_NUMBER=" + commandMap.get("GOODS_NUMBER").toString());
		return mv;
	}
	
	// 삭제
	@RequestMapping(value = "/qnaDelete")
	@ResponseBody
	public ModelAndView deleteReview(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();

		qnaService.qnaDelete(commandMap.getMap());
		
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

	/*
	// Q&A 전체 글 목록 불러오기
	@RequestMapping(value = "/qna/qnaList")
	public ModelAndView qnaList(CommandMap commandMap, HttpServletRequest request) throws Exception {

		if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
				|| request.getParameter("currentPage").equals("0")) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		ModelAndView mv = new ModelAndView();
		List<Map<String, Object>> list = qnaService.qnaList(commandMap.getMap());

		isSearch = request.getParameter("isSearch");
		if (isSearch != null) {

			categoryNum = Integer.parseInt(request.getParameter("categoryNum"));
			searchNum = Integer.parseInt(request.getParameter("searchNum"));

			String s = request.getParameter("isSearch");
			Map<String, Object> isSearchMap=new HashMap<String, Object>();
			isSearchMap.put("isSearch", new String(s.getBytes("iso-8859-1"),"utf-8"));
			
			if (searchNum == 0) {
				commandMap.put("MEMBER_ID", isSearch);
				if (categoryNum == 0) { //아이디 검색
					commandMap.put("QNA_CATEGORY", "상품문의");
					list = qnaService.qnaSearchMemberList(commandMap.getMap());
				}
				else if (categoryNum == 1) { // 상품에 해당하는 Q&A 목록 불러오기

					commandMap.put("QNA_CATEGORY", "상품문의");
					list = qnaService.qnaMemberList(commandMap.getMap());
				}
				else if (categoryNum == 2) { // 배송에 해당하는 Q&A 목록 불러오기
					commandMap.put("QNA_CATEGORY", "배송문의");
					list = qnaService.qnaMemberList(commandMap.getMap());
				}
				else if (categoryNum == 3) { // 입금에 해당하는 Q&A 목록 불러오기
					commandMap.put("QNA_CATEGORY", "입금문의");
					list = qnaService.qnaMemberList(commandMap.getMap());
				}
				else if (categoryNum == 4) { // 교환&반품에 해당하는 Q&A 목록 불러오기
					commandMap.put("QNA_CATEGORY", "교환&반품문의");
					list = qnaService.qnaMemberList(commandMap.getMap());
				}
				else if (categoryNum == 5) { // 기타에 해당하는 Q&A 목록 불러오기
					commandMap.put("QNA_CATEGORY", "기타문의");
					list = qnaService.qnaMemberList(commandMap.getMap());
				}
				else if (categoryNum == 6) { // 기타에 해당하는 Q&A 목록 불러오기
					list = qnaService.qnaRepState(isSearchMap);
				}
				

			}
			if (searchNum == 1) {
				commandMap.put("QNA_TITLE", isSearch);
				if (categoryNum == 0) { // 제목 검색
					commandMap.put("QNA_CATEGORY", "상품문의");
					list = qnaService.qnaSearchTitleList(commandMap.getMap());
				}
				if (categoryNum == 1) { // 상품에 해당하는 Q&A 목록 불러오기
					commandMap.put("QNA_CATEGORY", "상품문의");
					list = qnaService.qnaTitleList(commandMap.getMap());
				}
				if (categoryNum == 2) { // 배송에 해당하는 Q&A 목록 불러오기
					commandMap.put("QNA_CATEGORY", "배송문의");
					list = qnaService.qnaTitleList(commandMap.getMap());
				}
				if (categoryNum == 3) { // 입금에 해당하는 Q&A 목록 불러오기
					commandMap.put("QNA_CATEGORY", "입금문의");
					list = qnaService.qnaTitleList(commandMap.getMap());
				}
				if (categoryNum == 4) { // 교환&반품에 해당하는 Q&A 목록 불러오기
					commandMap.put("QNA_CATEGORY", "교환&반품문의");
					list = qnaService.qnaTitleList(commandMap.getMap());
				}
				if (categoryNum == 5) { // 기타에 해당하는 Q&A 목록 불러오기
					commandMap.put("QNA_CATEGORY", "기타문의");
					list = qnaService.qnaTitleList(commandMap.getMap());
				}
			}
			totalCount = list.size();
			page = new qnaPaging(currentPage, totalCount, blockCount, blockPage, "qnaList", categoryNum, searchNum,
					isSearch);
			pagingHtml = page.getPagingHtml().toString();

			int lastCount = totalCount;

			if (page.getEndCount() < totalCount)
				lastCount = page.getEndCount() + 1;

			list = list.subList(page.getStartCount(), lastCount);

			mv.addObject("categoryNum", categoryNum);
			mv.addObject("isSearch", isSearch);
			mv.addObject("searchNum", searchNum);
			mv.addObject("totalCount", totalCount);
			mv.addObject("pagingHtml", pagingHtml);
			mv.addObject("currentPage", currentPage);
			mv.addObject("list", list);
			mv.setViewName("qnaList");

			return mv;

		} else {
			totalCount = list.size();

			page = new qnaPaging(currentPage, totalCount, blockCount, blockPage, "qnaList", categoryNum, searchNum);
			pagingHtml = page.getPagingHtml().toString();

			int lastCount = totalCount;

			if (page.getEndCount() < totalCount)
				lastCount = page.getEndCount() + 1;

			list = list.subList(page.getStartCount(), lastCount);

			mv.addObject("totalCount", totalCount);
			mv.addObject("pagingHtml", pagingHtml);
			mv.addObject("currentPage", currentPage);

			mv.addObject("list", list);
			mv.setViewName("qnaList");

			return mv;
		}
	}

	// Q&A 상세보기
	@RequestMapping(value = "/qna/openQnaDetail")
	public ModelAndView qnaDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView();

		System.out.println("qnaDetail : " + commandMap.getMap());

		qnaService.selectQnaCount(commandMap.getMap());

		Map<String, Object> qna = qnaService.selectQnaDetail(commandMap.getMap());

		System.out.println(qna);

		mv.addObject("qna", qna);
		mv.setViewName("qnaDetail");

		return mv;
	}

	// Q&A 등록하기 폼으로 이동
	@RequestMapping(value = "/qna/qnaForm")
	public ModelAndView qnaForm(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("form : "+commandMap.getMap());
		if (request.getParameter("QNA_NUMBER") != null) {

			Map<String, Object> qna = qnaService.selectQnaDetail(commandMap.getMap());

			mv.addObject("qna", qna);
			System.out.println("qna" + qna);
		}
		mv.setViewName("qnaForm");
		return mv;
	}

	// Q&A 답변 쓰기
	@RequestMapping(value = "/qna/qnaWrite")
	public ModelAndView qnaReplyWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView();

		System.out.println("adWrite : " + commandMap.getMap());

		qnaService.insertQnaReply(commandMap.getMap());

		mv.setViewName("redirect:/qna/openQnaDetail?QNA_NUMBER="+commandMap.get("QNA_NUMBER"));

		return mv;
	}

	// Q&A 삭제
	@RequestMapping(value = "/qna/qnaDelete")
	public ModelAndView adDelete(CommandMap commandMap, HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView();

		String filePath = "C:\\Java\\App\\SIRORAGI\\src\\main\\webapp\\file\\qnaFile\\";
		String image1 = null;
		String image2 = null;

		boolean a;
		boolean b;
		boolean c;

		a = request.getParameter("QNA_IMAGE1") != null && request.getParameter("QNA_IMAGE2").isEmpty();
		b = request.getParameter("QNA_IMAGE2") != null && request.getParameter("QNA_IMAGE1").isEmpty();
		c = request.getParameter("QNA_IMAGE2") != null && request.getParameter("QNA_IMAGE1") != null;

		System.out.println(a);
		System.out.println(b);
		System.out.println(c);

		if (request.getParameter("QNA_IMAGE1") != null && request.getParameter("QNA_IMAGE2").isEmpty()) {
			image1 = request.getParameter("QNA_IMAGE1");
		}
		if (request.getParameter("QNA_IMAGE2") != null && request.getParameter("QNA_IMAGE1").isEmpty()) {
			image1 = request.getParameter("QNA_IMAGE2");
		}
		if (request.getParameter("QNA_IMAGE2") != null && request.getParameter("QNA_IMAGE1") != null) {
			image1 = request.getParameter("QNA_IMAGE1");
			image2 = request.getParameter("QNA_IMAGE2");

			File imageFile1 = new File(filePath + image1);
			File imageFile2 = new File(filePath + image2);

			System.out.println(imageFile1.isFile());
			System.out.println(imageFile2.isFile());

			if (imageFile1.isFile()) {
				imageFile1.delete();
			}
			if (imageFile2.isFile()) {
				imageFile2.delete();
			}
		}

		if (request.getParameter("categoryNum").isEmpty()) {
			categoryNum = 0;
		} else {
			categoryNum = Integer.parseInt(request.getParameter("categoryNum"));
		}
		isSearch = request.getParameter("isSearch");

		System.out.println("deleteQNA : " + commandMap.getMap());
		
		qnaService.qnaDelete(commandMap.getMap());
		
		String s = request.getParameter("isSearch");
		Map<String, Object> isSearchMap=new HashMap<String, Object>();
		isSearchMap.put("isSearch", new String(s.getBytes("iso-8859-1"),"utf-8"));
		
		if (categoryNum == 1) {
			mv.setViewName("redirect:/qna/qnaList?categoryNum=1&searchNum=0&isSearch=");
		} else if (categoryNum == 2) {
			mv.setViewName("redirect:/qna/qnaList?categoryNum=2&searchNum=0&isSearch=");
		} else if (categoryNum == 3) {
			mv.setViewName("redirect:/qna/qnaList?categoryNum=3&searchNum=0&isSearch=");
		} else if (categoryNum == 4) {
			mv.setViewName("redirect:/qna/qnaList?categoryNum=4&searchNum=0&isSearch=");
		} else if (categoryNum == 5) {
			mv.setViewName("redirect:/qna/qnaList?categoryNum=5&searchNum=0&isSearch=");
		} else if (categoryNum == 6) {
			mv.setViewName("redirect:/qna/qnaList?categoryNum=6&searchNum=0&isSearch="+isSearchMap.get("isSearch"));
		}else {
			mv.setViewName("redirect:/qna/qnaList");
		}
		return mv;

	}

	// Q&A 답변 삭제
	@RequestMapping(value = "/qna/qnaReplyDelete")
	public ModelAndView qnaReplyDelete(CommandMap commandMap, HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView();

		System.out.println("qnaReplyDelete : " + commandMap.getMap());

		qnaService.qnaReplyDelete(commandMap.getMap());

		mv.setViewName("redirect:/goodsDetail?GOODS_NUMBER=" + commandMap.get("GOODS_NUMBER").toString());
			
		return mv;

	}*/

}
