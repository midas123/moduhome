
package com.kh.mg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kh.moduhome.GoodsImageUtils;

@Service("mgService")
public class MgServiceImpl implements MgService {

	@Resource(name = "mgDAO")
	private MgDAO mgDAO;
	
	@Resource(name = "goodsImageUtils")
	private GoodsImageUtils goodsImageUtils;
	
	// 매거진 타이틀 등록
	@Override
	public void mgTitleInsert(Map<String, Object> map, HttpServletRequest request) throws Exception {
		
		  mgDAO.mgTitleInsert(map);

	      map = goodsImageUtils.mgMainImage(map, request);
	      mgDAO.mgMainImage(map);
			
	}

	//매거진 내용 등록
	@Override
	public void mgContentInsert(Map<String, Object> map, HttpServletRequest request) throws Exception {
		System.out.println("셀렉키삽입전" + map.get("MG_NUMBER"));
		   map = goodsImageUtils.mgContentImage(map, request);
		      mgDAO.mgContentInsert(map);
		System.out.println("셀렉키삽입후" + map.get("MG_NUMBER"));
		


	}
	
	//매거진 내용 리스트
	@Override
	public List<Map<String, Object>> mgContentList(Map<String, Object> map) throws Exception {
		
		return mgDAO.mgContentList(map);
	}
	
	//최근 매거진 추출
	@Override
	public Map<String, Object> mgNew(Map<String, Object> map) throws Exception {
		Map<String, Object> mgNew = mgDAO.mgNew(map);
		return mgNew;
	}
	
	// 매거진 리스트
	@Override
	public List<Map<String, Object>> mgList(Map<String, Object> map) throws Exception {
		
		return mgDAO.mgList(map);
	}
	
	// 매거진 상세보기
	@Override
	public Map<String, Object> mgDetail(Map<String, Object> map) throws Exception {
		mgDAO.updateHitCnt(map);
		Map<String, Object> mgDetail = mgDAO.mgDetail(map);
		return mgDetail;
	}
	


}