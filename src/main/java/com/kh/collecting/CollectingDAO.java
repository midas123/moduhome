package com.kh.collecting;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.kh.moduhome.AbstractDAO;

@Repository("collectingDAO")
public class CollectingDAO extends AbstractDAO{
	
	
	public int collectingExist(int MG_NUMBER, int MEMBER_NUMBER){
		Map param = new HashMap();
		param.put("MG_NUMBER", MG_NUMBER);
		param.put("MEMBER_NUMBER", MEMBER_NUMBER);
		
		return (Integer) selectOne("collecting.collectingExist", param);
	}		

	public CollectingModel collectingExist2(int MG_NUMBER, int MEMBER_NUMBER){
		Map param = new HashMap();
		param.put("MG_NUMBER", MG_NUMBER);
		param.put("MEMBER_NUMBER", MEMBER_NUMBER);
		
		return (CollectingModel) selectOne("collecting.collectingExist2", param);
	}	
	
	public void collectingReg(int MG_NUMBER, int MEMBER_NUMBER){
		Map param = new HashMap();
		param.put("MG_NUMBER", MG_NUMBER);
		param.put("MEMBER_NUMBER", MEMBER_NUMBER);
		
		insert("collecting.collectingReg", param);
	}
	
	public String collectingQuan(int MG_NUMBER){
		Map param = new HashMap();
		param.put("MG_NUMBER", MG_NUMBER);

		String collecting_quan = (String) selectOne("collecting.collectingQuan", MG_NUMBER);
		
		int dual = Integer.parseInt(collecting_quan);
		if(dual >= 1000){
			dual = dual/100;
			collecting_quan = String.valueOf(dual).substring(0, String.valueOf(dual).length()-1)+"."+String.valueOf(dual).substring(String.valueOf(dual).length()-1, String.valueOf(dual).length());
		}
		
		return collecting_quan;
	}
	
	
	public int collectingDel(int MG_NUMBER, int MEMBER_NUMBER){
		Map param = new HashMap();
		param.put("MG_NUMBER", MG_NUMBER);
		param.put("MEMBER_NUMBER", MEMBER_NUMBER);
		
		delete("collecting.collectingDel", param);
		
		return 1;
	}	
	
}
