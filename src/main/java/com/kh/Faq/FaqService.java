package com.kh.Faq;

import java.util.List;
import java.util.Map;

public interface FaqService {
	
	List<Map<String, Object>> selectFaqList(Map<String, Object> map) throws Exception;
	
	void insertFaq(Map<String, Object> map) throws Exception;
	
	Map<String, Object> selectFaqDetail(Map<String, Object> map) throws Exception;
	
	void updateFaq(Map<String, Object> map) throws Exception;
	
	void deleteFaq(Map<String, Object> map) throws Exception;

}
