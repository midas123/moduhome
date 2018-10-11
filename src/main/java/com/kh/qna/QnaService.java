package com.kh.qna;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface QnaService {
	//qna 작성
	void insertModalQna(Map<String, Object> map, HttpServletRequest request) throws Exception;

	// qna 삭제(Admin Page)
	void qnaDelete(Map<String, Object> map) throws Exception;
}
