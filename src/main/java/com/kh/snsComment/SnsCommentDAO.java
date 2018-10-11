package com.kh.snsComment;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kh.moduhome.AbstractDAO;

@Repository("snscommentDAO")
public class SnsCommentDAO extends AbstractDAO {
	


	// 스토리 댓글 등록
	public void snsCommentInsert(Map<String, Object> map) throws Exception {
		insert("snsboard_cm.SNSCOMMENTINSERT", map);
	}
	

	// 스토리 댓글 리스트
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> snsCommentList(Map<String, Object> map) throws Exception {
		return selectList("snsboard_cm.SNSCOMMENTLIST", map);
	}

     //하나의 스토리 댓글 가져오기
	@SuppressWarnings("unchecked")
	public Map<String, Object> snsCommentListOne(Map<String, Object> map) {
		
		return (Map<String, Object>) selectOne("snsboard_cm.SNSCOMMENTLISTONE", map);
	}


	public void snsCommentDelete(Map<String, Object> map) {
		delete("snsboard_cm.SNSCOMMENTDELETE", map);
	}


}


