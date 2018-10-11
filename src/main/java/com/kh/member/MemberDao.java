package com.kh.member;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kh.moduhome.AbstractDAO;

@Repository("memberDao")
public class MemberDao extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> memberList(Map<String, Object> map) throws Exception {
		System.out.println("memberok");
		return selectList("member.memberList", map);
	}
	
	@SuppressWarnings("unchecked")
	Map<String, Object> memberInfo(String member_number)throws Exception {
		
		 return (Map<String,Object>)selectOne("member.memberInfo", member_number);
	}

	public void ModifyMember(Map<String, Object> map)throws Exception {
		       
		    update("member.ModifyMember", map);
		
	}

	public void memberDelete(String MEMBER_NUMBER)throws Exception {
		     
		delete("member.MemberDelete",MEMBER_NUMBER);
	}
    
	/*회원탈퇴시 본인 확인*/
	public String checkPassword(Map<String, Object> map)throws Exception {
	     
		    return (String)selectOne("member.checkPassword",map);
	}
}
