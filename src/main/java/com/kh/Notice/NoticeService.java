package com.kh.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {
	
	List<Map<String, Object>> selectNoticeList(Map<String, Object> map) throws Exception;
	
	void insertNotice(Map<String, Object> map) throws Exception;
	
	Map<String, Object> selectNoticeDetail(Map<String, Object> map) throws Exception;
	
	void updateNotice(Map<String, Object> map) throws Exception;
	
	void deleteNotice(Map<String, Object> map) throws Exception;

}
