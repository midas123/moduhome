package com.kh.follow;

import java.util.List;
import com.kh.follow.FollowModel;


public interface FollowService {

	
	//개인의 상세정보 불러오기
		MemberModel selectIdMember(int MEMBER_NUMBER) throws Exception;

		//int followExist(int follow_id, int following_id) throws Exception;
		
		int followExist(int follow_id, Object following_id) throws Exception;
		
		void followReg(FollowModel followModel) throws Exception;

		void followDel(FollowModel followModel) throws Exception;
		
		String selectfollowQuan(String following_id) throws Exception;
		
		String selectfollowingQuan(String follow_id) throws Exception;
		
		List<FollowListModel> followerViewData(FollowListModel followListModel, int mem_id) throws Exception;

		List<FollowListModel> followingViewData(FollowListModel followListModel, int mem_id) throws Exception;

		List<String> followingAlarm(int follow) throws Exception;
}
