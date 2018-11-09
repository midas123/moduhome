package com.kh.mg;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kh.collecting.CollectingService;
import com.kh.member.MemberService;
import com.kh.mgComment.MgCommentService;
import com.kh.moduhome.CommandMap;
import com.kh.paging.Paging;



@Controller
public class MgController {
	
	private String filePath = "C:\\Users\\예영\\git\\moduhome\\src\\main\\webapp\\style\\img\\";


	// 검색, 페이징
		private int searchNum;
		private String isSearch;

		private int currentPage = 1;
		private int totalCount;
		private int blockCount = 10;
		private int blockPage = 5;
		private String pagingHtml;
		private Paging page;
		
	@Resource(name="memberService")
	private MemberService memberService;
		
	@Resource(name = "mgService")
	private MgService mgService;
	
	@Resource(name = "mgcommentService")
	private MgCommentService mgcommentService;	
	
	//보관하기 관련 서비스 등록
	@Resource(name="collectingService")
	private CollectingService collectingService;
	
	
	// 매거진 수정폼
	@RequestMapping(value = "/admin/mgModifyForm")
	public ModelAndView mgModifyForm(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("adminMgModifyForm");
		
		System.out.println(commandMap.get("MG_TITLE_IMAGE"));
		System.out.println("1");
		System.out.println(commandMap.get("MG_NUMBER"));
		
		if (commandMap.get("MG_TITLE_IMAGE") != null) {
			File removeFile = new File(filePath +commandMap.get("MG_TITLE_IMAGE"));
			System.out.println(removeFile);
			removeFile.delete();
		}
		
		
		mgService.mgModifyForm(commandMap.getMap(), request);
		
		Map<String, Object> mgDetail = mgService.mgDetail(commandMap.getMap());
		mv.addObject("mgDetail", mgDetail);
		
		
		Map<String, Object> mgNew = mgService.mgNew(commandMap.getMap());
		mv.addObject("mgNew", mgNew);
		
		/*List<Map<String, Object>> mgContentList = mgService.mgContentList(commandMap.getMap());
		mv.addObject("mgContentList", mgContentList);*/
		
		return mv;
	}
	
	
	// 매거진 수정
	@RequestMapping(value = "/admin/mgModify")
	public ModelAndView mgModify(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/mglist");

		mgService.mgModify(commandMap.getMap(), request);
		mv.addObject("MG_NUMBER", commandMap.get("MG_NUMBER"));

		return mv;
	}
	
	// 매거진 타이틀 삭제
	@RequestMapping(value = "/mgdelete")
	public ModelAndView mgDelete(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/mglist");
		
		System.out.println("number" + commandMap.get("MG_NUMBER"));

		Map<String, Object> mgDelete = mgService.mgDetail(commandMap.getMap());
		
		System.out.println(mgDelete.get("MG_TITLE_IMAGE"));
		
		if (mgDelete.get("MG_TITLE_IMAGE") != null) {
			File removeFile = new File(filePath +mgDelete.get("MG_TITLE_IMAGE"));
			System.out.println(removeFile);
			removeFile.delete();
		}

		mgService.mgDelete(commandMap.getMap());

		return mv;
	}
	
	//사진 게시판
	@RequestMapping(value = "/gllist")
	public ModelAndView glList(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("");

		List<Map<String, Object>> glList = mgService.glList(commandMap.getMap());
		
		
		
		mv.addObject("glList", glList);
		mv.setViewName("glList");
		
		return mv;
	}

	//매거진 리스트
	@RequestMapping(value = "/mglist")
	public ModelAndView mgList(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		List<Map<String, Object>> mgList = mgService.mgList(commandMap.getMap());
		
		mv.addObject("mgList", mgList);
		mv.setViewName("mgList");
		
		return mv;
		
	}
	
	//admin 매거진 리스트
	@RequestMapping(value = "/admin/mglist")
	public ModelAndView adminMgList(CommandMap commandMap, HttpServletRequest request) throws Exception{

		
		if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
				|| request.getParameter("currentPage").equals("0")) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		ModelAndView mv = new ModelAndView();
		List<Map<String, Object>> adminMgList = mgService.adminMgList(commandMap.getMap());

		
		Map<String, Object> isSearchMap = new HashMap<String, Object>();
		
		isSearch = request.getParameter("isSearch");

		if (isSearch != null) {
		
			searchNum = Integer.parseInt(request.getParameter("searchNum"));
			isSearchMap.put("isSearch", isSearch);

			if (searchNum == 0) { // 회원ID
				adminMgList = mgService.searchMgList0(isSearchMap);
			} else if (searchNum == 1) { // 상품이름
				adminMgList = mgService.searchMgList1(isSearchMap);
			}

			totalCount = adminMgList.size();
			page = new Paging(currentPage, totalCount, blockCount, blockPage, "mglist", searchNum, isSearch);
			pagingHtml = page.getPagingHtml().toString();

			int lastCount = totalCount;

			if (page.getEndCount() < totalCount)
				lastCount = page.getEndCount() + 1;

			adminMgList = adminMgList.subList(page.getStartCount(), lastCount);

			mv.addObject("isSearch", isSearch);
			mv.addObject("searchNum", searchNum);
			mv.addObject("totalCount", totalCount);
			mv.addObject("pagingHtml", pagingHtml);
			mv.addObject("currentPage", currentPage);
			mv.addObject("adminMgList", adminMgList);
			
			mv.setViewName("adminMgList");

			return mv;

		} else {
			totalCount = adminMgList.size();

			page = new Paging(currentPage, totalCount, blockCount, blockPage, "mglist");
			pagingHtml = page.getPagingHtml().toString();

			int lastCount = totalCount;

			if (page.getEndCount() < totalCount)
				lastCount = page.getEndCount() + 1;

			adminMgList = adminMgList.subList(page.getStartCount(), lastCount);

			mv.addObject("totalCount", totalCount);
			mv.addObject("pagingHtml", pagingHtml);
			mv.addObject("currentPage", currentPage);

			mv.addObject("adminMgList", adminMgList);
			
			mv.setViewName("adminMgList");

			return mv;
		}
	}
	
	//매거진 상세보기
	@RequestMapping(value = "/mgDetail")
	public ModelAndView mgDetail(HttpSession session, CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		int MEMBER_NUMBER =Integer.parseInt(session.getAttribute("MEMBER_NUMBER").toString());
		System.out.println("새 파라미터로 넘어온 멤버넘버는?"+MEMBER_NUMBER);
		
		List<Map<String, Object>> memberList = memberService.memberList(commandMap.getMap());
		Map<String, Object> mgDetail = mgService.mgDetail(commandMap.getMap());
		List<Map<String, Object>> mgCommentList = mgcommentService.mgCommentList(commandMap.getMap());
		List<Map<String, Object>> mgContentList = mgService.mgContentList(commandMap.getMap());
		
		System.out.println("mgnumber의 값은 뭘까용 ??" + mgDetail.get("MG_NUMBER"));
		
		//보관되어 있는 수를 가져오는 동작로직
		int MG_NUMBER = Integer.parseInt(commandMap.get("MG_NUMBER").toString());
		
		String collecting_quan = collectingService.collectingQuan(MG_NUMBER);
		
		int collecting_exist = collectingService.collectingExist(MG_NUMBER, MEMBER_NUMBER);
		System.out.println("mgdetail의 exist의 값은"+collecting_exist );
		if (memberList == null) {
			System.out.println("membernull");
		} else {
			System.out.println("membernotnull");
		}
		mv.addObject("collecting_exist", collecting_exist);
		mv.addObject("mgContentList", mgContentList);
		mv.addObject("mgCommentList", mgCommentList);
		mv.addObject("memberList", memberList);
		mv.addObject("mgDetail", mgDetail);
		mv.addObject("collecting_quan", collecting_quan);
		mv.setViewName("mgDetail");
		
		return mv;
	}
	
	
	//매거진 등록 폼
	@RequestMapping(value = "/admin/mgInsertForm")
	public ModelAndView mgInsertForm(CommandMap commandMap, HttpServletRequest request, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		String MEMBER_NUMBER = session.getAttribute("MEMBER_NUMBER").toString();
		
		mv.addObject("MEMBER_NUMBER", MEMBER_NUMBER);
		mv.setViewName("adminMgInsertForm");

		return mv;
	}
	
	// 매거진 타이틀 등록
	@RequestMapping(value = "/admin/mgTitleInsert")
	public ModelAndView mgTitleInsert(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		System.out.println("0");
		mgService.mgTitleInsert(commandMap.getMap(), request);
		System.out.println("1");
		Map<String, Object> mgNew = mgService.mgNew(commandMap.getMap());
		System.out.println("2");
		mv.addObject("mgNew", mgNew);
		System.out.println("파람" + commandMap.getMap());
		mv.setViewName("adminMgInsertForm2");
		
		return mv;
	}
	

	
	// 매거진 내용 등록
	@RequestMapping(value = "/admin/mgContentInsert")
	public ModelAndView mgContentInsert(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/mgContentInsert?MG_NUMBER="+commandMap.get("MG_NUMBER"));

		/*commandMap.getMap().put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER").toString());*/
		System.out.println("파람" + commandMap.getMap());
		mgService.mgContentInsert(commandMap.getMap(),request);
		
		List<Map<String, Object>> mgContentList = mgService.mgContentList(commandMap.getMap());
		Map<String, Object> mgNew = mgService.mgNew(commandMap.getMap());
		
		mv.addObject("mgContentList", mgContentList);
		mv.addObject("mgNew", mgNew);
		
	
		mv.setViewName("adminMgInsertForm2");
		
		
		return mv;
	}
	
	//매거진 리스트
	@RequestMapping(value = "/mglist2")
	public ModelAndView mgList2(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		List<Map<String, Object>> mgList = mgService.mgList(commandMap.getMap());
		
		mv.addObject("mgList", mgList);
		mv.setViewName("mgList2");
		
		return mv;
	}
	
	@RequestMapping(value = "/mgmorelist.ajax", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView mgmoreList(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		System.out.println("1");
		System.out.println(commandMap.getMap());
		
		
		List<Map<String, Object>> mgMoreList = mgService.mgMoreList(commandMap.getMap());
		
		if(mgMoreList != null) {
			System.out.println("ok");
		} else {
			System.out.println("no");
		}
		for(int i=0; i<mgMoreList.size(); i++) {
			System.out.println(mgMoreList.get(i).get("MG_NUMBER"));
		}
	
	
		mv.addObject("mgMoreList", mgMoreList);
		mv.setViewName("jsonView");
		
		return mv;
	}
	
   

	
	
}
