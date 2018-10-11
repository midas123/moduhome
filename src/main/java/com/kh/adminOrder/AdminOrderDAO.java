package com.kh.adminOrder;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kh.moduhome.AbstractDAO;

@Repository("adminOrderDAO")
public class AdminOrderDAO extends AbstractDAO {

	// 전체 주문리스트
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allOrderList(Map<String, Object> map) throws Exception {
		return selectList("adminOrder.allOrderList", map);
	}

	// 결제 상태 변경
	public void updateGoodsPayState(Map<String, Object> map) throws Exception {
		update("adminOrder.updateGoodsPayState", map);
	}

	// 상품 구매확정/주문취소/교환, 반품 상태변경
	public void updateGoodsState(Map<String, Object> map) throws Exception {
		update("adminOrder.updateGoodsState", map);
	}

	// 배송 상태 변경
	public void updateDeliveryState(Map<String, Object> map) throws Exception {
		update("adminOrder.updateDeliveryState", map);
	}

	// 주문 삭제
	public void orderDelete(Map<String, Object> map) throws Exception {
		delete("adminOrder.orderDelete", map);
	}

	// 주문내역 상세보기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> orderDetail(Map<String, Object> map) throws Exception {
		return selectList("adminOrder.orderDetail", map);
	}

	// 결제 상태로 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> adminOrderSearch0(Map<String, Object> map) throws Exception {
		return selectList("adminOrder.adminOrderSearch0", map);
	}

	// 주문 상태로 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> adminOrderSearch1(Map<String, Object> map) throws Exception {
		return selectList("adminOrder.adminOrderSearch1", map);
	}

	// 배송 상태로 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> adminOrderSearch2(Map<String, Object> map) throws Exception {
		return selectList("adminOrder.adminOrderSearch2", map);
	}

	// 주문코드로 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> adminOrderSearch3(Map<String, Object> map) throws Exception {
		return selectList("adminOrder.adminOrderSearch3", map);
	}

	// 상품 이름으로 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> adminOrderSearch4(Map<String, Object> map) throws Exception {
		return selectList("adminOrder.adminOrderSearch4", map);
	}

	// 회원ID로 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> adminOrderSearch5(Map<String, Object> map) throws Exception {
		return selectList("adminOrder.adminOrderSearch5", map);
	}
	
	// 브랜드 이름으로 검색
		@SuppressWarnings("unchecked")
		public List<Map<String, Object>> adminOrderSearch6(Map<String, Object> map) throws Exception {
			return selectList("adminOrder.adminOrderSearch6", map);
		}

	// 해당 주문에 대해 취소가 있나 확인
	@SuppressWarnings("unchecked")
	public Map<String, Object> confirmCancel(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("adminOrderCancel.confirmCancel", map);
	}

	// 주문취소 목록
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allCancelList(Map<String, Object> map) throws Exception {
		return selectList("adminOrderCancel.allCancelList", map);
	}

	// 주문반품/교환목록
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allExchangeList(Map<String, Object> map) throws Exception {
		return selectList("adminOrderCancel.allExchangeList", map);
	}

	// 배송상태로 주문취소 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allCancelSearch1(Map<String, Object> map) throws Exception {
		return selectList("adminOrderCancel.allCancelSearch1", map);
	}

	// 주문코드로 주문취소 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allCancelSearch2(Map<String, Object> map) throws Exception {
		return selectList("adminOrderCancel.allCancelSearch2", map);
	}

	// 상품이름으로 주문취소 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allCancelSearch3(Map<String, Object> map) throws Exception {
		return selectList("adminOrderCancel.allCancelSearch3", map);
	}
	
	// 브랜드 이름으로 주문취소 검색
		@SuppressWarnings("unchecked")
		public List<Map<String, Object>> allCancelSearch5(Map<String, Object> map) throws Exception {
			return selectList("adminOrderCancel.allCancelSearch5", map);
		}

	// 회원ID로 주문취소 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allCancelSearch4(Map<String, Object> map) throws Exception {
		return selectList("adminOrderCancel.allCancelSearch4", map);
	}

	//주문상태로 교환/반품 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allExchangeSearch1(Map<String, Object> map) throws Exception {
		return selectList("adminOrderCancel.allExchangeSearch1", map);
	}

	//배송상태로 교환/반품 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allExchangeSearch2(Map<String, Object> map) throws Exception {
		return selectList("adminOrderCancel.allExchangeSearch2", map);
	}

	//주문코드로 교환/반품 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allExchangeSearch3(Map<String, Object> map) throws Exception {
		return selectList("adminOrderCancel.allExchangeSearch3", map);
	}

	//상품이름으로 교환/반품 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allExchangeSearch4(Map<String, Object> map) throws Exception {
		return selectList("adminOrderCancel.allExchangeSearch4", map);
	}

	//브랜드 이름으로 교환/반품 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allExchangeSearch6(Map<String, Object> map) throws Exception {
		return selectList("adminOrderCancel.allExchangeSearch6", map);
	}
	
	//회원ID로 교환/반품 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allExchangeSearch5(Map<String, Object> map) throws Exception {
		return selectList("adminOrderCancel.allExchangeSearch5", map);
	}
}
