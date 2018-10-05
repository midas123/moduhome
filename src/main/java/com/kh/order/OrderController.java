package com.kh.order;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.adminOrder.AdminOrderService;
import com.kh.goods.GoodsService;
import com.kh.moduhome.CommandMap;


@Controller
public class OrderController {
	@Resource(name="orderService")
	private OrderService orderService;
	
	@Resource(name="goodsService")
	private GoodsService goodsService;

	@Resource(name = "adminOrderService")
	private AdminOrderService adminOrderService;
	//구매하기
	@RequestMapping(value="/order")
	public ModelAndView orderForm(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("orderForm");
		HttpSession session = request.getSession();
		String memn = session.getAttribute("MEMBER_NUMBER").toString();
		System.out.println("memn:"+memn);
		commandMap.getMap().put("MEMBER_NUMBER", memn);
		System.out.println("commandMap.getMap():"+commandMap.getMap());
		
		
		Map<String, Object> orderMember = orderService.orderMember(commandMap.getMap());
		System.out.println("orderMember:"+orderMember);
		mv.addObject("orderMember", orderMember);
		
		//상품옵션 및 수량정보
		String[] goods_kinds_number = request.getParameterValues("kinds[]"); 
		String[] ea = request.getParameterValues("ea[]");
		
		List<Map<String, Object>> goods = new ArrayList<Map<String, Object>>();

		
		for (int i = 0; i < goods_kinds_number.length; i++) {

			System.out.println("ea[" + i + "] = " + ea[i]);
			commandMap.put("GOODS_NUMBER", request.getParameter("goodsno"));
			commandMap.put("GOODS_KIND_NUMBER", goods_kinds_number[i]);
			commandMap.put("EA", ea[i]);
			System.out.println("CommandMap!! :" +commandMap.getMap());
			
			Map<String, Object> orderGoods = orderService.orderGoods(commandMap.getMap());
			
			System.out.println("orderGoods : " + orderGoods);

			orderGoods.put("EA", ea[i]);

			goods.add(orderGoods);

			System.out.println("goods : " + goods);
		}
		
		mv.addObject("GOODS_NUMBER", request.getParameter("goodsno"));
		mv.addObject("goods", goods);
		mv.addObject("goods_kind_number", goods_kinds_number);
		mv.addObject("ea", ea);
		
		return mv;
	
	}
	
	@RequestMapping(value="/orderEnd")
	public ModelAndView orderEnd(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("orderEnd");
		//멤버 넘버가 세션으로 넘어오도록
		//mv.setViewName("redirect:/orderEnd");
		System.out.println("orderendMap:"+commandMap.getMap());
		commandMap.put("MEMBER_NUMBER", request.getParameter("MEMBER_NUMBER"));
		
		Map<String, Object> orderMember = orderService.orderMember(commandMap.getMap());

		List<Map<String, Object>> goods = new ArrayList<Map<String, Object>>();

		String[] goods_kinds_number = request.getParameterValues("kinds[]");
		String[] ea = request.getParameterValues("ea[]");
		String[] goods_number = request.getParameterValues("GOODS_NUMBER");

		for (int i = 0; i < goods_kinds_number.length; i++) {

			commandMap.put("GOODS_KIND_NUMBER", goods_kinds_number[i]);
			commandMap.put("EA", ea[i]);
			commandMap.put("GOODS_NUMBER", goods_number[i]);

			Map<String, Object> orderGoods = orderService.orderGoods(commandMap.getMap());

			orderGoods.put("EA", ea[i]);

			goods.add(orderGoods);

			System.out.println("주문한 상품목록 : " + goods);
		}
		
		
		//주문코드 생성
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();

		for (int i = 0; i < 5; i++) {
			temp.append((char) ((rnd.nextInt(26)) + 65));
		}
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(d);
		String ORDER_CODE = ("S" + date + temp);
		System.out.println("ORDER_CODE:"+ORDER_CODE);

		
		//배송정보 저장
		commandMap.put("ORDER_CODE", ORDER_CODE);
		commandMap.put("BUYER_ZIPCODE", orderMember.get("MEMBER_ZIPCODE"));
		commandMap.put("BUYER_ADDRESS1", orderMember.get("MEMBER_ADDRESS1"));
		commandMap.put("BUYER_ADDRESS2", orderMember.get("MEMBER_ADDRESS2"));
		commandMap.put("BUYER_NAME", orderMember.get("MEMBER_NAME"));
		commandMap.put("BUYER_EMAIL", orderMember.get("MEMBER_EMAIL"));
		commandMap.put("BUYER_NUMBER", orderMember.get("MEMBER_PHONE"));
		
		if (request.getParameter("DELIVERY_MESSAGE").isEmpty()) {
			commandMap.put("DELIVERY_MESSAGE", " ");
		}

		commandMap.put("GOODS_NUMBER", request.getParameter("GOODS_NUMBER"));
		commandMap.put("RECEIVER_NAME", request.getParameter("RECEIVER_NAME"));
		commandMap.put("RECEIVER_ZIPCODE", request.getParameter("RECEIVER_ZIPCODE"));
		commandMap.put("RECEIVER_ADDRESS1", request.getParameter("RECEIVER_ADDRESS1"));
		commandMap.put("RECEIVER_ADDRESS2", request.getParameter("RECEIVER_ADDRESS2"));
		commandMap.put("RECEIVER_NUMBER", request.getParameter("RECEIVER_NUMBER"));
		commandMap.put("DELIVERY_MESSAGE", request.getParameter("DELIVERY_MESSAGE"));
		commandMap.put("RECEIVER_NUMBER", request.getParameter("RECEIVER_PHONE"));
		commandMap.put("TOTALPRICE", request.getParameter("TOTALPRICE"));
		
		//주문정보 저장
		for (int i = 0; i < goods_kinds_number.length; i++) {

			commandMap.put("GOODS_KIND_NUMBER", goods_kinds_number[i]);
			commandMap.put("ORDER_AMOUNT", ea[i]);
			commandMap.put("ORDER_TOTAL_PRICE", request.getParameter("TOTALPRICE"));
			commandMap.put("GOODS_NUMBER", goods_number[i]);

		}
		//상품 구매 트랜잭션 처리
		orderService.orderGoodsAction(commandMap.getMap());

		mv.addObject("orderMember", orderMember);
		mv.addObject("goods", goods);
		mv.addObject("goods_kind_number", goods_kinds_number);
		mv.addObject("ea", ea);
		
		//포인트
		if (commandMap.getMap().get("usePoint") != null) {
		int usePoint = Integer.parseInt((String) commandMap.getMap().get("usePoint"));
		mv.addObject("usePoint", usePoint);
		}
		
		mv.addObject("ORDER_CODE", ORDER_CODE);
		mv.addObject("BUYER_NUMBER", commandMap.get("BUYER_NUMBER"));
		mv.addObject("TOTALPRICE", commandMap.get("TOTALPRICE"));
		mv.addObject("RECEIVER_NAME", commandMap.get("RECEIVER_NAME"));
		mv.addObject("RECEIVER_ZIPCODE", commandMap.get("RECEIVER_ZIPCODE"));
		mv.addObject("RECEIVER_ADDRESS1", commandMap.get("RECEIVER_ADDRESS1"));
		mv.addObject("RECEIVER_ADDRESS2", commandMap.get("RECEIVER_ADDRESS2"));
		mv.addObject("DELIVERY_MESSAGE", commandMap.get("DELIVERY_MESSAGE"));
		mv.addObject("RECEIVER_PHONE", commandMap.get("RECEIVER_NUMBER"));
		
		return mv;
	}
	
	@RequestMapping(value="/MyOrderList")
	public ModelAndView MyOrderList(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("myorder");
		
		//String memberNum = (String)request.getAttribute("MEMBER_NUMBER");
		String memberNum = session.getAttribute("MEMBER_NUMBER").toString();

	List<Map<String, Object>> myOrderList = goodsService.selectOrderList(memberNum);
		mv.addObject("myOrderList", myOrderList);	
		
		return mv;
	}
	
	@RequestMapping(value="/payUpdate")
	public @ResponseBody String myOrderpayUpdate(CommandMap commandMap, HttpServletRequest request) throws Exception {
		System.out.println("오더넘 : "+commandMap.get("ORDER_NUMBER"));
		System.out.println("오더스테이트 : "+commandMap.get("ORDER_STATE"));	
		goodsService.OrderStateModi(commandMap.getMap());
	     return "1";
	}
	
	@RequestMapping(value="/myOrderDetail")
	public ModelAndView myOrderDetail(CommandMap commandMap, HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		String memberNum = session.getAttribute("MEMBER_NUMBER").toString();
		List<Map<String, Object>> myOrderList = goodsService.selectOrderList(memberNum);
		
		System.out.println(("order code = "+ (myOrderList.get(0)).get("ORDER_CODE")));
		mv.addObject("myOrderList", myOrderList);
		

		Map<String, Object> orderCode = new HashMap<String, Object>();
		orderCode.put("ORDER_CODE", (myOrderList.get(0)).get("ORDER_CODE"));
		System.out.println(orderCode);
		//Object orderCode= commandMap.get("ORDER_CODE").toString();
		
		
		System.out.println("오더코드"+commandMap.get("ORDER_CODE"));
		
		List<Map<String, Object>> myOrderDetail = new ArrayList<Map<String, Object>>();
		myOrderDetail = adminOrderService.orderDetail(orderCode);
		//OrderDetail = adminOrderService.orderDetail(commandMap.getMap());
		
		int total_price = 0;
		int total_amount = 0;

		for(int i=0;i<myOrderList.size(); i++) {
			total_price= Integer.parseInt((((myOrderDetail.get(i)).get("ORDER_TOTAL_PRICE")).toString()));
			String total = (((myOrderDetail.get(i)).get("ORDER_TOTAL_PRICE")).toString());
			
			total_amount = Integer.parseInt((((myOrderDetail.get(i)).get("ORDER_AMOUNT")).toString()));
			String amount = (((myOrderDetail.get(i)).get("ORDER_AMOUNT")).toString());
			
			total_price += Integer.parseInt(total) * Integer.parseInt(amount) ;
			System.out.println(total_price);
		}
		
		mv.addObject("total_price", total_price);
		
		Map<String, Object> myOrderDetail2 =  myOrderDetail.get(0);
		mv.addObject("myOrderDetail2", myOrderDetail2);
		System.out.println(myOrderDetail2);
		
		mv.setViewName("/mypage/myOrderDetail");
		
		return mv;
		
	}
		
	
}
