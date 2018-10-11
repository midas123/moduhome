package com.kh.qna;

import java.util.*;

import org.springframework.stereotype.Repository;
import com.kh.moduhome.AbstractDAO;


@Repository("qnaDAO")
public class QnaDAO extends AbstractDAO{
	
	//qna 작성
	public void insertModalQna(Map<String, Object> map) throws Exception {	
		insert("qna.insertModalQna", map);
	}
	
	// qna 삭제(Admin Page)
	public void qnaDelete(Map<String, Object> map) throws Exception {
		delete("qna.qnaDelete", map);
	}
	
	// 전체 리스트(Admin Page)
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> qnaList(Map<String, Object> map) throws Exception {
		return selectList("qna.qnaList", map);
	}
	
	// qna 이미지 등록
	public void qnaImageWrite(Map<String, Object> map) throws Exception {
		update("qna.insertQnaImage", map);
	}
}
