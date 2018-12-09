package com.kh.moduhome;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("newTagService")
public class NewTagService {
	
	public List<Map<String,Object>> newTageServie(List<Map<String,Object>> goodsList, int DATE_DATE){
		
	
	//New 로직 시작
    Calendar newCal = Calendar.getInstance();
	Date newGoodsDate = new Date();
	
	//굿즈리스트 받음
	for(int i=0; i < goodsList.size(); i++) {
		Date goodsDate = (Date) goodsList.get(i).get("GOODS_DATE");
		newCal.setTime(goodsDate);// Data값 캘린더로 변경
		newCal.add(Calendar.DATE, DATE_DATE); //신상품 태그 날짜 조건

		newGoodsDate = new Date(newCal.getTimeInMillis());
		
		Calendar today = Calendar.getInstance();
		Date nowDate = new Date(today.getTimeInMillis());
		
		
		if(nowDate.compareTo(newGoodsDate) == -1) {
			// NOW가 크면1, 같으면0 작으면 -1 반환
			goodsList.get(i).put("GOODS_NEWDATE", 1);
			//jsp에서 1이면 뉴태그생성
		}else {
			goodsList.get(i).put("GOODS_NEWDATE", 0);
			//1이아니면 노생성
		}
	
	  }
	return goodsList;

	}
}


