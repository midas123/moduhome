package com.kh.follow;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service("followService")
public class FollowServiceImpl implements FollowService{

	@Resource(name = "followDAO")
	private FollowDAO followDAO;
	

	@Override
	public MemberModel selectIdMember(int MEMBER_NUMBER) throws Exception {
		// TODO Auto-generated method stub
		return followDAO.selectIdMember(MEMBER_NUMBER);
	}

	@Override
	public int followExist(int follow_id, Object following_id) throws Exception{
		// TODO Auto-generated method stub
		return followDAO.followExist(follow_id, following_id);
		
	}

	@Override
	public void followReg(FollowModel followModel) throws Exception {
		// TODO Auto-generated method stub
		followDAO.followReg(followModel);
	}

	@Override
	public void followDel(FollowModel followModel) throws Exception {
		// TODO Auto-generated method stub
		followDAO.followDel(followModel);
	}

	@Override
	public String selectfollowQuan(String following_id) throws Exception{
		return followDAO.selectfollowQuan(following_id);
	}
	
	@Override
	public String selectfollowingQuan(String follow_id) throws Exception{
		return followDAO.selectfollowingQuan(follow_id);
	}	
	
	@Override
	public List<FollowListModel> followerViewData(FollowListModel followListModel, int mem_id) throws Exception{
		return followDAO.followerViewData(followListModel, mem_id);
	}

	@Override
	public List<FollowListModel> followingViewData(FollowListModel followListModel, int mem_id) throws Exception{
		return followDAO.followingViewData(followListModel, mem_id);
	}

	@Override
	public List<String> followingAlarm(int follow) throws Exception {
		// TODO Auto-generated method stub
		return followDAO.followingAlarm(follow);
	}	

	
	
}
