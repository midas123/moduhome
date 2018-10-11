package com.kh.police;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.kh.moduhome.AbstractDAO;

@Repository("policeDao")
public class PoliceDao extends AbstractDAO{
	
	public void policeReg(Map<String, Object> map)throws Exception{
		insert("police.policeReg",map);
	}
    
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> policeList() {
		return selectList("police.policeList");
	}

	public void PoliceDelete(String police_seq) {
		delete("police.policeDelete",police_seq);
		
	}

	public String selectSNSnum(String police_num) {
		
		return selectOne("police.selectSNSnum",police_num).toString();
	}

	public void updateHide(String sns_num) {
		   update("police.updateHide", sns_num);
		
	}

	public void updateHideCancle(String sns_num) {
		update("police.updateHideCancle", sns_num);
		
	}



	
}
