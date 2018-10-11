package com.kh.search;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;


@Service("searchService")
public class SearchServiceImpl implements SearchService {

	@Resource(name = "searchDAO")
	private SearchDAO searchDAO;
	
	
	// 최근 검색 등록
	@Override
	public void recentSearchInsert(Map<String, Object> map, HttpServletRequest request) throws Exception {
		
		  searchDAO.recentSearchInsert(map);
	}
	
	// 최근 검색 리스트
	@Override
	public List<Map<String, Object>> recentSearch(Map<String, Object> map) throws Exception {
		System.out.println("recent");
		return searchDAO.recentSearch(map);
	}
	
	// 공지사항 검색
	@Override
	public List<Map<String, Object>> noticeSearch(Map<String, Object> map) throws Exception {
		System.out.println("notice");
		return searchDAO.noticeSearch(map);
	}
	
	// faq 검색
	@Override
	public List<Map<String, Object>> faqSearch(Map<String, Object> map) throws Exception {
		System.out.println("faq");
		return searchDAO.faqSearch(map);
	}
	
	// 스토리 검색
	@Override
	public List<Map<String, Object>> snsSearch(Map<String, Object> map) throws Exception {
		System.out.println("sns");
		return searchDAO.snsSearch(map);
	}
	
	// 회원 검색
	@Override
	public List<Map<String, Object>> memberSearch(Map<String, Object> map) throws Exception {
		System.out.println("member");
		return searchDAO.memberSearch(map);
	}
	
	// 매거진 검색 
	@Override
	public List<Map<String, Object>> mgSearch(Map<String, Object> map) throws Exception {
		System.out.println("mg");
		return searchDAO.mgSearch(map);
	}
	
	// 상품 검색
	@Override
	public List<Map<String, Object>> goodsSearch(Map<String, Object> map) throws Exception {
		System.out.println("goods");
		return searchDAO.goodsSearch(map);
	}
	

}