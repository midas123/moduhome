package com.kh.collecting;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service("collectingService")
public class CollectingServiceImpl implements CollectingService {
	
	@Resource(name = "collectingDAO")
	private CollectingDAO collectingDAO;
	
	@Override
	public int collectingExist(int MG_NUMBER, int MEMBER_NUMBER) throws Exception{
		/*			int exist = collectingDAO.collectingExist(MG_NUMBER, MEMBER_NUMBER);
		if(exist != 0){
			return 1;
		}else{
			return 0;
		}*/
		
		return collectingDAO.collectingExist(MG_NUMBER, MEMBER_NUMBER);
	}	
	
	@Override
	public int collectingExist2(int MG_NUMBER, int MEMBER_NUMBER) throws Exception{
		CollectingModel collectingModel = collectingDAO.collectingExist2(MG_NUMBER, MEMBER_NUMBER);
		System.out.println("exi:"+collectingModel);
		if(collectingModel==null){
			return 1;
		}else{
			return 0;
		}
		
	/*	INT로 바꾼 코드. 지울예정?
	 * int exist = collectingDAO.collectingExist(MG_NUMBER, MEMBER_NUMBER);
		if(exist != 0){
			return 1;
		}else{
			return 0;
		}*/
	}
	
	@Override
	public void collectingReg(int MG_NUMBER, int MEMBER_NUMBER) throws Exception{
		collectingDAO.collectingReg(MG_NUMBER, MEMBER_NUMBER);
	}
	
	@Override
	public String collectingQuan(int MG_NUMBER) throws Exception{
		return collectingDAO.collectingQuan(MG_NUMBER);
	}
	
	@Override
	public int collectingDel(int MG_NUMBER, int MEMBER_NUMBER) throws Exception{
		return collectingDAO.collectingDel(MG_NUMBER, MEMBER_NUMBER);
	}
	
}
