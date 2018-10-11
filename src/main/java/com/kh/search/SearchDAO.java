package com.kh.search;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kh.moduhome.AbstractDAO;

@Repository("searchDAO")
public class SearchDAO extends AbstractDAO {

	// 최근 검색  등록
	public void recentSearchInsert(Map<String, Object> map) throws Exception {
		insert("search.RECENTSEARCHINSERT", map);
	}
	
	// 최근 검색 리스트
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> recentSearch(Map<String, Object> map) throws Exception {
	
		return selectList("search.RECENTSEARCH", map);
	}
	
	// 공지사항 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> noticeSearch(Map<String, Object> map) throws Exception {
	
		return selectList("search.NOTICESEARCH", map);
	}
	
	// FAQ 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> faqSearch(Map<String, Object> map) throws Exception {
	
		return selectList("search.FAQSEARCH", map);
	}
	
	// 스토리 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> snsSearch(Map<String, Object> map) throws Exception {
	
		return selectList("search.SNSSEARCH", map);
	}
	
	// 회원 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> memberSearch(Map<String, Object> map) throws Exception {
	
		return selectList("search.MEMBERSEARCH", map);
	}
	
	// 매거진 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> mgSearch(Map<String, Object> map) throws Exception {
	
		return selectList("search.MGSEARCH", map);
	}
	
	// 상품 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> goodsSearch(Map<String, Object> map) throws Exception {
	
		return selectList("search.GOODSSEARCH", map);
	}

}