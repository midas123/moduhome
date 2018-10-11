package com.kh.sns;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import com.kh.moduhome.GoodsImageUtils;

@Service("snsService")
public class SnsServiceImpl implements SnsService {

	@Resource(name = "snsDAO")
	private SnsDAO snsDAO;
	
	@Resource(name = "goodsImageUtils")
	private GoodsImageUtils goodsImageUtils;
	
	// 매거진 타이틀 삭제
	@Override
	public void snsDelete(Map<String, Object> map) throws Exception {
		Map<String, Object> image = new HashMap<String, Object>();
		;
		goodsImageUtils.snsImageDelete(map);
		
		snsDAO.snsDelete(map);
	}
	
	
	// 스토리 등록
	@Override
	public void snsInsert(Map<String, Object> map, HttpServletRequest request) throws Exception {
		
		  snsDAO.snsInsert(map);

	      map = goodsImageUtils.snsMainImage(map, request);
	      snsDAO.snsMainImage(map);
	      System.out.println("사진ok");
			
	}
	
	/*// 스토리 리스트
	@Override
	public List<Map<String, Object>> snsList(Map<String, Object> map) throws Exception {
		
		return snsDAO.snsList(map);
	}*/
    
    //스토리 숨기기
	@Override
	public void snsHide(String sns_num) throws Exception {
		  snsDAO.snsHide(sns_num);
		
	}
	
	@Override
	public void snsHideCancel(String sns_num) throws Exception {
		  snsDAO.snsHideCancle(sns_num);
		
	}
    //좋아요수 추가
	@Override
	public void updateLike(Map<String, Object> map) throws Exception {
		snsDAO.snsUpdateLike(map);
		
	}

	@Override
	public List<Map<String, Object>> snsList(String MEMBER_NUMBER) throws Exception {
	  
		  return snsDAO.snsList(MEMBER_NUMBER);
	}

	@Override
	public void downLike(Map<String, Object> map) throws Exception {
		snsDAO.snsDownLike(map);
		
	}

	@Override
	public int snsWriter(String sns_number) throws Exception {
		// TODO Auto-generated method stub
		
		return snsDAO.snsWriter(sns_number);
	}

	@Override
	public List<Map<String, Object>> snsMyList(int mEMBER_NUMBER) throws Exception {
		// TODO Auto-generated method stub
		return snsDAO.snsMyList(mEMBER_NUMBER);
	}
	

}