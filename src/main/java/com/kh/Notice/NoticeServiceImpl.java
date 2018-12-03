package com.kh.Notice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="noticeDAO")
	private NoticeDAO noticeDAO;

	@Override
	public List<Map<String, Object>> selectNoticeList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.selectNoticeList(map);
	}

	@Override
	public void insertNotice(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		noticeDAO.insertNotice(map);
	}

	@Override
	public Map<String, Object> selectNoticeDetail(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		noticeDAO.updateHitCnt(map);
		Map<String, Object> resultMap = noticeDAO.selectNoticeDetail(map);
		return resultMap;
	}

	@Override
	public void updateNotice(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		noticeDAO.updateNotice(map);
	}

	@Override
	public void deleteNotice(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		noticeDAO.deleteNotice(map);
	}

}
