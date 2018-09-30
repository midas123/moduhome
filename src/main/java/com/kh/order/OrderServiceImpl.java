package com.kh.order;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Resource(name = "orderDAO")
	private OrderDAO orderDAO;

	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@Override
	public Map<String, Object> orderMember(Map<String, Object> map) throws Exception {
		return orderDAO.orderMember(map);
	}

	@Override
	public Map<String, Object> orderGoods(Map<String, Object> map) throws Exception {
		return orderDAO.orderGoods(map);
	}
	
	@Override
	public Map<String, Object> orderGoods2(Map<String, Object> map) throws Exception {
		return orderDAO.orderGoods2(map);
	}
	

	@Override
	public Object orderGoodsAction(Map<String, Object> map) throws Exception {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("GoodsOrderTx");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		 
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
		//배송 정보
		orderDAO.createDeliveryList(map);
		//주문 정보
		orderDAO.createOrderList(map);
		//상품 재고 수량 빼기
		orderDAO.goodsCountDown(map);
		//포인트 적립
		if (map.get("usePoint") != null) {
			if(!((String)map.get("usePoint")).isEmpty()){
				int usePoint = Integer.parseInt((String)map.get("usePoint"));
				if (usePoint != 0) {
					int POINT_POINT = -(usePoint);
					System.out.println("POINT_POINT" + POINT_POINT);
					map.put("POINT_POINT", POINT_POINT);
					map.put("POINT_CONTENT", "상품 구매");
					orderDAO.insertPoint(map);
				}
			  }
		}
		
		}catch (Exception ex) {
			transactionManager.rollback(status);
			System.out.println("#####주문 처리 중 에러발생");
			  throw ex;
			}
		transactionManager.commit(status);
		return null;
	}

	@Override
	public Object createDeliveryList(Map<String, Object> map) throws Exception {
		
		return orderDAO.createDeliveryList(map);
	}

	@Override
	public Object createOrderList(Map<String, Object> map) throws Exception {
		return orderDAO.createOrderList(map);
	}

	@Override
	public Object goodsCountDown(Map<String, Object> map) throws Exception {
		return orderDAO.goodsCountDown(map);
	}

	@Override
	public Object insertPoint(Map<String, Object> map) throws Exception {
		return orderDAO.insertPoint(map);
	}

	@Override
	public List<Map<String, Object>> selectOrderList2(Map<String, Object> map) throws Exception {
		return orderDAO.selectOrderList2(map);
	}



}
