package com.kh.mgComment;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kh.moduhome.AbstractDAO;

@Repository("mgcommentDAO")
public class MgCommentDAO extends AbstractDAO {
	
	// 매거진 댓글 삭제
	public void mgCommentDelete(Map<String, Object> map) throws Exception {
		delete("magazine_cm.MGCOMMENTDELETE", map);
	}
	
	
	// 매거진 댓글 리스트
		@SuppressWarnings("unchecked")
	public List<Map<String, Object>> mgCommentList(Map<String, Object> map) throws Exception {
		return selectList("magazine_cm.MGCOMMENTLIST", map);
	}


	// 매거진 댓글 등록
	public void mgCommentInsert(Map<String, Object> map) throws Exception {
		insert("magazine_cm.MGCOMMENTINSERT", map);
	}


}


