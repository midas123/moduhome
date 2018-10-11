package com.kh.Faq;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kh.moduhome.AbstractDAO;


@Repository("faqDAO")
public class FaqDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFaqList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return selectList("faq.selectFaqList", map);
	}

	public void insertFaq(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		insert("faq.insertFaq", map);
	}
	
	public void updateHitCnt(Map<String, Object> map) throws Exception {
		update("faq.updateHitCnt", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectFaqDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("faq.selectFaqDetail", map);
	}
	
	public void updateFaq(Map<String, Object> map) throws Exception {
		update("faq.updateFaq",map);
	}
	
	public void deleteFaq(Map<String, Object> map) throws Exception {
		update("faq.deleteFaq",map);
	}
	
}
