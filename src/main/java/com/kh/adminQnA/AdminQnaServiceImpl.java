package com.kh.adminQnA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.moduhome.GoodsImageUtils;

@Service("adminQnaService")
public class AdminQnaServiceImpl implements AdminQnaService {

	@Resource(name = "adminQnaDAO")
	private AdminQnaDAO adminQnaDAO;
	
	@Resource(name = "goodsImageUtils")
	private GoodsImageUtils goodsImageUtils;
	
	// Q&A 목록
	@Override
	public List<Map<String, Object>> qnaList(Map<String, Object> map) throws Exception {
		return adminQnaDAO.qnaList(map);
	}
	
	// Q&A 상세보기
	@Override
	public Map<String, Object> qnaDetail(Map<String, Object> map) throws Exception {
		return adminQnaDAO.qnaDetail(map);
	}
	
	// 관리자 페이지 답변달기
	@Override
	public void updateReplyStep(Map<String, Object> map) throws Exception {
		adminQnaDAO.updateReplyStep(map);
	}
	
	// 관리자 Q&A 강제삭제
	@Override
	public void adminQnaDelete(Map<String, Object> map) throws Exception {
		
		//Map<String, Object> image = new HashMap<String, Object>();
		//image = adminQnaDAO.qnaDetail(map);
		//goodsImageUtils.qnaImageDelete(image);
		adminQnaDAO.adminQnaDelete(map);
	}
	
	// 답변 삭제
	@Override
	public void qnaReplyDelete(Map<String, Object> map) throws Exception {
		adminQnaDAO.qnaReplyDelete(map);
	}
	
	// 제목 & 카테고리 검색
	@Override
	public List<Map<String, Object>> qnaTitleList(Map<String, Object> map) throws Exception {
		return adminQnaDAO.qnaSearchTitleList(map);
	}
		
	// 관리자 Q&A 카테고리 검색
	@Override
	public List<Map<String, Object>> qnaCategoryList(Map<String, Object> map) throws Exception {
		return adminQnaDAO.qnaCategoryList(map);
	}
		
	// 관리자 Q&A 아이디 검색
	@Override
	public List<Map<String, Object>> qnaSearchMemberList(Map<String, Object> map) throws Exception {
		return adminQnaDAO.qnaSearchMemberList(map);
	}
	
	// 관리자 Q&A 제목 검색
	@Override
	public List<Map<String, Object>> qnaSearchTitleList(Map<String, Object> map) throws Exception {
		return adminQnaDAO.qnaSearchTitleList(map);
	}
		
	// 관리자 Q&A 답변 상태 검색
	@Override
	public List<Map<String, Object>> qnaRepState(Map<String, Object> map) throws Exception {
		return adminQnaDAO.qnaRepState(map);
	}
}