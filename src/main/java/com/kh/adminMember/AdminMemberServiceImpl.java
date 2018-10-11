package com.kh.adminMember;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("adminMemberService")
public class AdminMemberServiceImpl implements AdminMemberService{

	@Resource(name="adminMemberDAO")
	private AdminMemberDAO adminMemberDAO;
	
	@Override
	public List<Map<String, Object>> adminMemberList(Map<String, Object> map) throws Exception {
		
		return adminMemberDAO.adminMemberList(map);
	}
}
