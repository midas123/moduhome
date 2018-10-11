package com.kh.mg;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kh.moduhome.AbstractDAO;

@Repository("mgDAO")
public class MgDAO extends AbstractDAO {
	
	// 매거진 상세보기
/*	@SuppressWarnings("unchecked")
	public Map<String, Object> mgDetail2(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("magazine.MGDETAIL2", map);
	}*/
	
	// 매거진 타이틀 수정폼
	public void mgModifyForm(Map<String, Object> map) throws Exception {
		update("magazine.MGMODIFYFORM", map);
		System.out.println("ok");
	}
	
	
	// 매거진 타이틀 수정
	public void mgModify(Map<String, Object> map) throws Exception {
		update("magazine.MGMODIFY", map);
	}
	
	// 매거진 타이틀 삭제
	public void mgDelete(Map<String, Object> map) throws Exception {
		delete("magazine.MGDELETE", map);
	}
	
	// 사진 게시판
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> glList(Map<String, Object> map) throws Exception {
		return selectList("magazine.GLLIST", map);
	}
	
	

	//매거진 내용 리스트
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> mgContentList(Map<String, Object> map) throws Exception {
		return selectList("magazine_con.MGCONTENTLIST", map);
	}
	
	//최근 매거진 추출
	@SuppressWarnings("unchecked")
	public Map<String, Object> mgNew(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("magazine.MGNEW", map);
	}
	
	
	// 매거진 내용 등록
	public void mgContentInsert(Map<String, Object> map) throws Exception {
		insert("magazine_con.MGCONTENTINSERT", map);
	}
	

	// 매거진 리스트
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> mgList(Map<String, Object> map) throws Exception {
		return selectList("magazine.MGLIST", map);
	}
	
	// ADMIN 매거진 리스트
		@SuppressWarnings("unchecked")
		public List<Map<String, Object>> adminMgList(Map<String, Object> map) throws Exception {
			return selectList("magazine.ADMINMGLIST", map);
		}
	
	
	// 매거진 상세보기
	@SuppressWarnings("unchecked")
	public Map<String, Object> mgDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("magazine.MGDETAIL", map);
	}
		
	// 조회수 증가
	public void updateHitCnt(Map<String, Object> map) throws Exception {
		update("magazine.UPDATEHITCNT", map);
	}
	
	// 매거진 타이틀 등록
	public void mgTitleInsert(Map<String, Object> map) throws Exception {
		insert("magazine.MGTITLEINSERT", map);
	}
	
	
	//매거진 메인이미지 등록
	public void mgMainImage(Map<String, Object> map) throws Exception {
	   update("magazine.MGMAINIMAGE", map);
	}
	
	//매거진 내용이미지 등록
	public void mgContentImage(Map<String, Object> map) throws Exception {
	   update("magazine.MGMAINIMAGE", map);
	}

	//내가 보관한 매거진의 리스트
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> mgMycollectList(int MEMBER_NUMBER) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> mgMycollectList = selectList("magazine.MGMYCOLLECTLIST", MEMBER_NUMBER);
		return mgMycollectList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> mgMoreList(Map<String, Object> map) throws Exception {
		System.out.println("d");
		return selectList("magazine.MGMORELIST", map);
	}
	

	// 매거진 검색(제목)
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> searchMgList0(Map<String, Object> map) throws Exception {
		return selectList("magazine.searchMgList0", map);
	}

	// 매거진 검색(내용)
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> searchMgList1(Map<String, Object> map) throws Exception {
		return selectList("magazine.searchMgList1", map);
	}
	
}