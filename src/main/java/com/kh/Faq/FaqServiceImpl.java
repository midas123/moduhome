package com.kh.Faq;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("faqService")
public class FaqServiceImpl implements FaqService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="faqDAO")
	private FaqDAO faqDAO;

	@Override
	public List<Map<String, Object>> selectFaqList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return faqDAO.selectFaqList(map);
	}

	@Override
	public void insertFaq(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		faqDAO.insertFaq(map);
	}

	@Override
	public Map<String, Object> selectFaqDetail(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = faqDAO.selectFaqDetail(map);
		return resultMap;
	}

	@Override
	public void updateFaq(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		faqDAO.updateFaq(map);
	}

	@Override
	public void deleteFaq(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		faqDAO.deleteFaq(map);
	}

}
