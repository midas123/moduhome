package com.kh.search;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SearchService {
	
	// 최근 검색 등록
	public void recentSearchInsert(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	// 최근 검색 리스트
	public List<Map<String, Object>> recentSearch(Map<String, Object> map) throws Exception;
	
	// notice 검색
	public List<Map<String, Object>> noticeSearch(Map<String, Object> map) throws Exception;
	
	// faq 검색
	public List<Map<String, Object>> faqSearch(Map<String, Object> map) throws Exception;
	
	// 스토리 검색
	public List<Map<String, Object>> snsSearch(Map<String, Object> map) throws Exception;
	
	// 회원 검색
	public List<Map<String, Object>> memberSearch(Map<String, Object> map) throws Exception;
	
	// 매거진 검색
	public List<Map<String, Object>> mgSearch(Map<String, Object> map) throws Exception;
	
	// 상품 검색
	public List<Map<String, Object>> goodsSearch(Map<String, Object> map) throws Exception;

	
}
