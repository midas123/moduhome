package com.kh.adminQnA;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kh.moduhome.AbstractDAO;

@Repository("adminQnaDAO")
public class AdminQnaDAO extends AbstractDAO {

	// Q&A 목록
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> qnaList(Map<String, Object> map) throws Exception {
		return selectList("qna.qnaList", map);
	}
	
	// Q&A 상세보기
	@SuppressWarnings("unchecked")
	public Map<String, Object> qnaDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("qna.selectQnaDetail", map);
	}
	
	// 관리자 페이지 답변달기
	public void updateReplyStep(Map<String, Object> map) throws Exception {
		update("qna.updateReplyStep", map);
	}
	
	// 관리자 Q&A 강제 삭제
	public void adminQnaDelete(Map<String, Object> map) throws Exception {
		delete("qna.qnaDelete", map);
	}
	
	// 답변 삭제
	public void qnaReplyDelete(Map<String, Object> map) throws Exception {
		delete("qna.qnaReplyDelete", map);
	}
	
	// 제목 & 카테고리 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> qnaTitleList(Map<String, Object> map) throws Exception {
		return selectList("qna.qnaTitleList", map);
	}
		
	// 아이디 & 카테고리 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> qnaCategoryList(Map<String, Object> map) throws Exception {
		return selectList("qna.qnaCategoryList", map);
	}
		
	// 아이디 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> qnaSearchMemberList(Map<String, Object> map) throws Exception {
		return selectList("qna.qnaSearchMemberList", map);
	}
		
	// 제목 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> qnaSearchTitleList(Map<String, Object> map) throws Exception {
		return selectList("qna.qnaSearchTitleList", map);
	}
		
	// 답변 상태 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> qnaRepState(Map<String, Object> map) throws Exception {
		return selectList("qna.qnaRepState", map);
	}
}