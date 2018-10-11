package com.kh.snsComment;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;





@Service("snscommentService")
public class SnsCommentServiceImpl implements SnsCommentService {


	@Resource(name = "snscommentDAO")
	private SnsCommentDAO snscommentDAO;


	//스토리 댓글 등록
	@Override
	public void snsCommentInsert(Map<String, Object> map, HttpServletRequest request) throws Exception {
		System.out.println("셀렉키삽입전" + map.get("SNS_NUMBER"));
		snscommentDAO.snsCommentInsert(map);
		System.out.println("셀렉키삽입후" + map.get("SNS_NUMBER"));

	}
	

	//스토리 댓글 리스트
	@Override
	public List<Map<String, Object>> snsCommentList(Map<String, Object> map) throws Exception {
		return snscommentDAO.snsCommentList(map);
	}

    //하나의 스토리 댓글 가져오기
	@Override
	public Map<String, Object> snsCommentListOne(Map<String, Object> map) throws Exception {
		 
		return snscommentDAO.snsCommentListOne(map);
	}


	@Override
	public void snsCommentDelete(Map<String, Object> map) throws Exception {
		snscommentDAO.snsCommentDelete(map);
	}


   



}
