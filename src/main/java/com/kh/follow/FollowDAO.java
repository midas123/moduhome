package com.kh.follow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.moduhome.AbstractDAO;

@Repository("followDAO")
public class FollowDAO extends AbstractDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//개인에 대한 상세정보 -> 마이페이지로 이동예정
	public MemberModel selectIdMember(int MEMBER_NUMBER) {
		return (MemberModel) selectOne("follow.selectIdMember", MEMBER_NUMBER);
	}
	
	public int followExist(int follow, Object following) {
		// TODO Auto-generated method stub
		Map param = new HashMap();
		param.put("follow", follow);
		param.put("following", following);
		
		return (Integer) selectOne("follow.followExist", param);
	}

	public void followReg(FollowModel followModel){
		insert("follow.followReg", followModel);
	}

	public void followDel(FollowModel followModel){
		delete("follow.followDel", followModel);
	}
	
	public String selectfollowQuan(String following_id){
		String follow_quan = (String) selectOne("follow.selectfollowQuan", following_id);
		
		int dual = Integer.parseInt(follow_quan);
		if(dual >= 1000){
			dual = dual/100;
			follow_quan = String.valueOf(dual).substring(0, String.valueOf(dual).length()-1)+"."+String.valueOf(dual).substring(String.valueOf(dual).length()-1, String.valueOf(dual).length());
		}
		
		return follow_quan;
	}
	
	public String selectfollowingQuan(String follow_id){
		String following_quan = (String) selectOne("follow.selectfollowingQuan", follow_id);
		
		int dual = Integer.parseInt(following_quan);
		if(dual >= 1000){
			dual = dual/100;
			following_quan = String.valueOf(dual).substring(0, String.valueOf(dual).length()-1)+"."+String.valueOf(dual).substring(String.valueOf(dual).length()-1, String.valueOf(dual).length());
		}
		
		return following_quan;
	}	
	
	public List<FollowListModel> followerViewData(FollowListModel followListModel, int mem_id){
		
		List<FollowListModel> followerList = selectList("follow.followerViewData", mem_id);
		System.out.println("followList의 사이즈" + followerList.size());
		
		if(followerList.size() != 0){
			for(int i = 0; i<followerList.size(); i++){
				Map param = new HashMap();
				param.put("MEMBER_NUMBER",followerList.get(i).getFollowing());
				
				followerList.get(i).setNickname((String) selectOne("member.findNickName", param));
				followerList.get(i).setProf_img((String) selectOne("member.findProfImg", param));
				System.out.println("멤버프로필사진은?" + (String) selectOne("member.findProfImg", param));
				
				param.put("follow", mem_id);
				param.put("following", followerList.get(i).getFollowing());
				
				followerList.get(i).setFollow_to((Integer) selectOne("follow.followExist", param));
				/*1이면 존재/0이면 존재X*/
			}	
		}	
		
		return followerList;
	}
	
	public List<FollowListModel> followingViewData(FollowListModel followListModel, int mem_id){
		
		List<FollowListModel> followingList = selectList("follow.followingViewData", mem_id);
		
		if(followingList.size() != 0){
			for(int i = 0; i<followingList.size(); i++){
				Map param = new HashMap();
				param.put("MEMBER_NUMBER", followingList.get(i).getFollow());
				
				followingList.get(i).setNickname((String) selectOne("member.findNickName", param));
				followingList.get(i).setProf_img((String) selectOne("member.findProfImg", param));

				System.out.println("멤버프로필사진은?" + (String) selectOne("member.findProfImg", param));

				param.put("follow", followingList.get(i).getFollow());
				param.put("following", mem_id);
				
				followingList.get(i).setFollow_to((Integer) selectOne("follow.followExist", param));
				/*1이면 존재/0이면 존재X*/
			}	
		}	
		
		return followingList;
	}

	public List<String> followingAlarm(int follow) {
		// TODO Auto-generated method stub
		List<String> followinglist = selectList("follow.followingAlarm", follow);
		
		return followinglist;
	}	
	
}
