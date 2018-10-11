package com.kh.sns;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kh.moduhome.AbstractDAO;

@Repository("snsDAO")
public class SnsDAO extends AbstractDAO {
	
	
	// 매거진 타이틀 삭제
	public void snsDelete(Map<String, Object> map) throws Exception {
		delete("snsboard.SNSDELETE", map);
	}
	
	//스토리 메인 이미지 등록
	public void snsMainImage(Map<String, Object> map) throws Exception {
	   update("snsboard.SNSMAINIMAGE", map);
	}

	/*// 스토리 리스트
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> snsList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("snsboard.SNSLIST", map);
	}*/

	
	// 스토리 등록
	public void snsInsert(Map<String, Object> map) throws Exception {
		insert("snsboard.SNSINSERT", map);
	}
    
	//스토리 숨기기
	public void snsHide(String sns_num)throws Exception {
	    update("snsboard.SNSHIDE", sns_num);
	}
	
	  //스토리 숨기기 취소
     public void snsHideCancle(String sns_num) {
			 update("snsboard.SNSHIDECancel", sns_num);
			
		}
     /* 좋아요 증가*/
	 public void snsUpdateLike(Map<String, Object> map) {
			update("snsboard.SNSLIKEupdate", map);
			
		}
	 
	  /*로그인한 회원의 like리스트*/
	 @SuppressWarnings("unchecked")  
	public List<Map<String, Object>> snsList(String MEMBER_NUMBER) {
		return selectList("snsboard.SNSLIST", MEMBER_NUMBER);
	}

	public void snsDownLike(Map<String, Object> map) {
		update("snsboard.SNSDownLike", map);
		
	}
	
	public int snsWriter(String sns_number) {
		return Integer.parseInt(selectOne("snsboard.SNSWriter", sns_number).toString());
	}

	public List<Map<String, Object>> snsMyList(int MEMBER_NUMBER) {
		return selectList("snsboard.SNSMYLIST", MEMBER_NUMBER);
	}
	
}