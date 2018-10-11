package com.kh.Notice;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kh.moduhome.AbstractDAO;


@Repository("noticeDAO")
public class NoticeDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectNoticeList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return selectList("notice.selectNoticeList", map);
	}

	public void insertNotice(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		insert("notice.insertNotice", map);
	}
	
	public void updateHitCnt(Map<String, Object> map) throws Exception {
		update("notice.updateHitCnt", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectNoticeDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("notice.selectNoticeDetail", map);
	}
	
	public void updateNotice(Map<String, Object> map) throws Exception {
		update("notice.updateNotice",map);
	}
	
	public void deleteNotice(Map<String, Object> map) throws Exception {
		update("notice.deleteNotice",map);
	}
	
}
