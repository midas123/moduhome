package com.kh.adminMember;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kh.moduhome.AbstractDAO;

@Repository("adminMemberDAO")
public class AdminMemberDAO extends AbstractDAO{
	
	public List<Map<String, Object>> adminMemberList(Map<String, Object> map) throws Exception{
		return selectList("adminMember.adminMemberList", map);
	}

	
}
