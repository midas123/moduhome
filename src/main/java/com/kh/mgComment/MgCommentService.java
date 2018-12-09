package com.kh.mgComment;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MgCommentService {
	
	// 매거진 댓글 삭제
	public void mgCommentDelete(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	// 매거진 댓글 등록
	public void mgCommentInsert(Map<String, Object> map, HttpServletRequest request) throws Exception;
		
	// 매거진 댓글 리스트
	public List<Map<String, Object>> mgCommentList(Map<String, Object> map) throws Exception;

}
