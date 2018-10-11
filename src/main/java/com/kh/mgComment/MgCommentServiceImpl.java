package com.kh.mgComment;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import com.kh.moduhome.GoodsImageUtils;



@Service("mgcommentService")
public class MgCommentServiceImpl implements MgCommentService {


	@Resource(name = "mgcommentDAO")
	private MgCommentDAO mgcommentDAO;
	
	@Resource(name = "goodsImageUtils")
	private GoodsImageUtils goodsImageUtils;
	
	// 매거진 댓글 삭제
	@Override
	public void mgCommentDelete(Map<String, Object> map, HttpServletRequest request) throws Exception {
		
		mgcommentDAO.mgCommentDelete(map);
	}
	
	// 매거진 댓글 리스트
	@Override
	public List<Map<String, Object>> mgCommentList(Map<String, Object> map) throws Exception {
		return mgcommentDAO.mgCommentList(map);
	}

	// 매거진 댓글 등록
	@Override
	public void mgCommentInsert(Map<String, Object> map, HttpServletRequest request) throws Exception {
		System.out.println("셀렉키삽입전" + map.get("MG_NUMBER"));
		mgcommentDAO.mgCommentInsert(map);
		System.out.println("셀렉키삽입후" + map.get("MG_NUMBER"));

	}

	

}
