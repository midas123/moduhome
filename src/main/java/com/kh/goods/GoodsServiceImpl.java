package com.kh.goods;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService{
	
	@Resource(name="goodsDAO")
	private GoodsDAO goodsDAO;
	
	//스토어 메인, 상품 판매순 정렬
	@Override
	public List<Map<String, Object>> bestSellAll(Map<String, Object> map) throws Exception {
		return goodsDAO.bestSellAll(map);
	}
	//스토어 메인, 상품 판매순 정렬
	@Override
	public List<Map<String, Object>> newItemAll(Map<String, Object> map) throws Exception {
		return goodsDAO.newItemAll(map);
	}
	
	//스토어 세부 카테고리
	@Override
	public List<Map<String, Object>> goodsCategory(Map<String, Object> map) throws Exception {
		return goodsDAO.goodsCategory(map);
	}

	@Override
	public List<Map<String, Object>> sortGoodsCategory(Map<String, Object> map) throws Exception {		
		return goodsDAO.sortGoodsCategory(map);
	}

	/*@Override
	public List<Map<String, Object>> colorToLower(List<Map<String, Object>> goodsList) throws Exception {
		
		for(int i=0; i<goodsList.size();i++) {
			//System.out.println("컬러 변환전 = " + goodsList.get(i).get("COLORKIND"));
			String tempColor = ((String)goodsList.get(i).get("COLORKIND")).toLowerCase();
			goodsList.get(i).remove("COLORKIND"); //기존값삭제
			String[] colorKind = tempColor.split(","); //템프이용해서 (,)로 스플릿
			goodsList.get(i).put("COLORKIND",colorKind); //스트링배열로 다시추가
			//System.out.println("변환 후 = " + ((String[])goodsList.get(i).get("COLORKIND")).length);
		}
		return goodsList;
	}*/
	
	@Override
	public List<Map<String, Object>> goodsSubCategory(Map<String, Object> map) throws Exception {
	    return goodsDAO.goodsSubCategory(map);
	}
        
	@Override
    public List<Map<String, Object>> BestgoodsSubCategory(Map<String, Object> map) throws Exception {
	    return goodsDAO.BestgoodsSubCategory(map);
	}

	@Override
	public int goodsCountUp(Map<String, Object> map) throws Exception {
		return goodsDAO.goodsCountUp(map);
	}

	@Override
	public List<Map<String, Object>> selectOneGood(Map<String, Object> map) throws Exception {
		return goodsDAO.selectOneGood(map);
	}

	@Override
	public List<Map<String, Object>> selectImage(Map<String, Object> map) throws Exception {
		return goodsDAO.selectImage(map);
	}

	@Override
	public int checkBuy(Map<String, Object> map) throws Exception {
		return goodsDAO.checkBuy(map);
	}

	@Override
	public List<Map<String, Object>> selectRelatedGoods(Map<String, Object> map) throws Exception {
		
	      List<Map<String, Object>> relatedGoodsList = new ArrayList<Map<String, Object>>();
	      Map<String, Object> goodsNum = new HashMap<String, Object>();

	         if (map.get("GOODS_RELEVANT") != null) {
	            String coordi = (String) map.get("GOODS_RELEVANT");
	              String[] coordis = coordi.split(",");
	              System.out.println("GOODS_RELEVANT :" +coordis);

	              for(String a : coordis) {
	                 goodsNum.put("GOODS_NUMBER", a);
	                 System.out.println("goodsNum.GOODS_NUMBER :" +goodsNum.get("GOODS_NUMBER"));
	                 Map<String, Object> related = goodsDAO.selectRelatedGoods(goodsNum);
	                 if (related != null) {
	                   relatedGoodsList.add(related);
	                }
	                 
	              }
	         }
	         
	         System.out.println("relatedGoodsList :" +relatedGoodsList);
	           return relatedGoodsList;
	}
	
	@Override
	public List<Map<String, Object>> selectOrderList(String memberNum) throws Exception {
		return goodsDAO.selectOrderList(memberNum);
	}
		
	@Override
	public List<Map<String, Object>> selectQNA(Map<String, Object> map) throws Exception {
		return goodsDAO.selectQNA(map);
	}

	@Override
	public List<Map<String, Object>> selectReview(Map<String, Object> map) throws Exception {
		return goodsDAO.selectReview(map);
	}

	@Override
	public int reviewScore(Map<String, Object> map) throws Exception {
		try {
		return goodsDAO.reviewScore(map);
		
		}catch(Exception e) { //평점없으면 
			return 0;
		}
	}
	
	@Override
	public List<Map<String, Object>> goodsSearchList(Map<String, Object> map) throws Exception {
	   return goodsDAO.goodsSearchList(map);
	}
	
	/*@Override
	public List<Map<String, Object>> goodsColorSearchList(Map<String, Object> map) throws Exception {
	   return goodsDAO.goodsColorSearchList(map);
	}
	
	@Override
	public List<Map<String, Object>> goodsSizeSearchList(Map<String, Object> map) throws Exception {
	   return goodsDAO.goodsSizeSearchList(map);
	}*/
	
	@Override
	public List<Map<String,Object>> selectCategoryCount(String category1) throws Exception{
	    return goodsDAO.selectCategoryCount(category1);
    }
	
	@Override
	public void cancel_order(Map<String, Object> map) throws Exception {
		goodsDAO.cancle_order(map);
	}
	
	@Override
	public void confirm_order(Map<String, Object> map) throws Exception {
		goodsDAO.confirm_order(map);	
	}
	@Override
	public void OrderStateModi(Map<String, Object> map) throws Exception {
		goodsDAO.Modify_Order(map);
		
	}
	

}
