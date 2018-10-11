
package com.kh.mg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import com.kh.moduhome.GoodsImageUtils;

@Service("mgService")
public class MgServiceImpl implements MgService {

	@Resource(name = "mgDAO")
	private MgDAO mgDAO;
	
	@Resource(name = "goodsImageUtils")
	private GoodsImageUtils goodsImageUtils;
	
	// 매거진 수정폼
	@Override
	public void mgModifyForm(Map<String, Object> map, HttpServletRequest request) throws Exception {
		mgDAO.mgModifyForm(map);

	}
	
	// 매거진 수정
	@Override
	public void mgModify(Map<String, Object> map, HttpServletRequest request) throws Exception {
		mgDAO.mgModify(map);
		
	      map = goodsImageUtils.mgMainImage(map, request);
	      mgDAO.mgMainImage(map);
	      
	      
	}
	
	// 매거진 타이틀 삭제
	@Override
	public void mgDelete(Map<String, Object> map) throws Exception {
		Map<String, Object> image = new HashMap<String, Object>();
		image = mgDAO.mgDetail(map);
		goodsImageUtils.mgTitleImageDelete(image);
		
		mgDAO.mgDelete(map);
	}
	
	// 매거진 상세보기
	/*@Override
	public Map<String, Object> mgDetail2(Map<String, Object> map) throws Exception {
	
		Map<String, Object> mgDetail = mgDAO.mgDetail(map);
		return mgDetail;
	}*/
	
	// 사진 게시판
	@Override
	public List<Map<String, Object>> glList(Map<String, Object> map) throws Exception {
		
		return mgDAO.glList(map);
	}
	
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
	
	
	// ADMIN 매거진 리스트
	@Override
	public List<Map<String, Object>> adminMgList(Map<String, Object> map) throws Exception {
		
		return mgDAO.adminMgList(map);
	}
	
	// 매거진 상세보기
	@Override
	public Map<String, Object> mgDetail(Map<String, Object> map) throws Exception {
		mgDAO.updateHitCnt(map);
		Map<String, Object> mgDetail = mgDAO.mgDetail(map);
		return mgDetail;
	}

	//내가 보관한 매거진의 리스트
	@Override
	public List<Map<String, Object>> mgMycollectList(int MEMBER_NUMBER) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> mgMycollectList = mgDAO.mgMycollectList(MEMBER_NUMBER);
		return mgMycollectList;
	}
	
	@Override
	public List<Map<String, Object>> mgMoreList(Map<String, Object> map)throws Exception {
		System.out.println("s");
		return mgDAO.mgMoreList(map);
		
	}
	
	
	// 리뷰 검색
		@Override
		public List<Map<String, Object>> searchMgList0(Map<String, Object> map) throws Exception {
			return mgDAO.searchMgList0(map);
		}

		// 리뷰 검색
		@Override
		public List<Map<String, Object>> searchMgList1(Map<String, Object> map) throws Exception {
			return mgDAO.searchMgList1(map);
		}

}