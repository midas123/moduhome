package com.kh.cart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.moduhome.CommandMap;
import com.kh.order.OrderService;


@Controller
public class CartController {
	
	@Resource(name="cartService")
	private CartService cartService;
	
	@Resource(name="orderService")
	private OrderService orderService;
	
	List<Map<String, Object>> cartSession = new ArrayList<Map<String, Object>>();
	
	//장바구니 담기
	@SuppressWarnings("unused")
	@RequestMapping(value="/cart/cartAdd")
	public ModelAndView cartAdd(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/cart/cartList");
		HttpSession session = request.getSession();
		System.out.println("MEMBER_NUMBER:"+session.getAttribute("MEMBER_NUMBER"));
		System.out.println("장바구니commandMap:"+commandMap.getMap());
		
		//비회원용 세션 카트
		Map<String, Object> cartMap = new HashMap<String, Object>();
		
		if(session.getAttribute("MEMBER_NUMBER") != null) {
			commandMap.put("GOODS_NUMBER", commandMap.get("goodsno"));
			commandMap.put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER"));
			commandMap.put("CART_AMOUNT", commandMap.get("ea[]"));
			cartService.cartInsert(commandMap.getMap());
		} else {
			commandMap.put("GOODS_NUMBER", commandMap.get("goodsno"));
			
			if (commandMap.get("optno[]") instanceof String) { // 하나의 정보만 전송되면
				// System.out.println("여기 if문 들어오는건지");
				String b = (String) commandMap.get("ea[]"); // 수량
				String c = (String) commandMap.get("kinds[]"); // 종류
				Integer e = Integer.parseInt(commandMap.get("GOODS_NUMBER").toString());

				int dup = 0; // 중복데이터 있으면 1, 없으면 0유지

				// 중복 상품 제외
				if (cartSession.size() != 0) {
					for (int i = 0; i < cartSession.size(); i++) {
						if (cartSession.get(i).get("GOODS_KIND_NUMBER").equals(c)) {
							dup = 1;
						}
					}
					if (dup == 0) {
						cartMap = new HashMap<String, Object>();
						cartMap.put("GOODS_KIND_NUMBER", c);
						cartMap.put("CART_AMOUNT", b);
						cartMap.put("GOODS_NUMBER", e);
						cartSession.add(cartMap);
					}
				} else {
					cartSession = new ArrayList<Map<String, Object>>();
					cartMap = new HashMap<String, Object>();
					cartMap.put("GOODS_KIND_NUMBER", c);
					cartMap.put("CART_AMOUNT", b);
					cartMap.put("GOODS_NUMBER", e);
					cartSession.add(cartMap);
				}

			} else { // 로그인정보도 없고, 전송된 데이터가 여러개면
				String a[] = (String[]) commandMap.get("kinds[]");
				String b[] = (String[]) commandMap.get("ea[]");
				Integer e = Integer.parseInt(commandMap.get("GOODS_NUMBER").toString());
				int dup = 0;
				System.out.println("cartSession:"+cartSession);
				if (cartSession.size() != 0) {
					System.out.println("장바구니가 이미 존재할때 추가 등록하는 경우");
					for (int i = 0; i < a.length; i++) {
						dup = 0; // 0이면 중복 없음. 1이면 중복있음
						for (int j = 0; j < cartSession.size(); j++) {
							if (a[i].equals(cartSession.get(j).get("GOODS_KIND_NUMBER"))) {
								dup = 1;
							}
						}
						if (dup == 0) {
							cartMap = new HashMap<String, Object>();
							cartMap.put("GOODS_KIND_NUMBER", a[i]);
							cartMap.put("CART_AMOUNT", b[i]);
							cartMap.put("GOODS_NUMBER", e);
							cartSession.add(cartMap);
						}
					}
				} else {
					cartSession = new ArrayList<Map<String, Object>>();
					for (int i = 0; i < a.length; i++) {
						System.out.println("장바구니가 비었을때 등록하는 경우");
						cartMap = new HashMap<String, Object>();
						cartMap.put("GOODS_KIND_NUMBER", a[i]);
						cartMap.put("CART_AMOUNT", b[i]);
						cartMap.put("GOODS_NUMBER", e);
						cartSession.add(i, cartMap);
					}
				}
			}
		}
		session.setAttribute("cartSession", cartSession);
		System.out.println("최종 cartSession : " + session.getAttribute("cartSession"));
		
		return mv;
	}
	
	// 장바구니 리스트 불러오기
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cart/cartList")
	public ModelAndView cartList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cartList");

		List<Map<String, Object>> cartList = new ArrayList<Map<String, Object>>();

		Calendar today = Calendar.getInstance();
		Date d = new Date(today.getTimeInMillis());

		HttpSession session = request.getSession();
		List<Map<String, Object>> cartSession = new ArrayList<Map<String, Object>>();
		Map<String, Object> cartMap = new HashMap<String, Object>();

		if (session.getAttribute("MEMBER_NUMBER") != null) {
			commandMap.put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER"));
			cartList = cartService.cartList(commandMap.getMap());
		} else {
			if (session.getAttribute("cartSession") != null) {
				cartSession = (List<Map<String, Object>>) session.getAttribute("cartSession");
				for (int i = 0; i < cartSession.size(); i++) {
					cartMap = new HashMap<String, Object>();
					cartMap.put("GOODS_KIND_NUMBER", cartSession.get(i).get("GOODS_KIND_NUMBER"));
					cartMap.put("GOODS_NUMBER", cartSession.get(i).get("GOODS_NUMBER"));

					cartMap = cartService.sessionCartList(cartMap);
					cartMap.put("CART_AMOUNT", cartSession.get(i).get("CART_AMOUNT"));

					cartList.add(cartMap);
				}
			}
		}
		mv.addObject("cartList", cartList);
		System.out.println("CARTLIST :" + cartList);

	
	return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cart/cartDelete")
	public ModelAndView cartDelete(CommandMap commandMap, HttpServletRequest request, @RequestParam(value="GOODS_KIND_NUMBER[]", required = false) String[] arrayParams) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/cart/cartList");
		HttpSession session = request.getSession();
		System.out.println("선택삭제"+commandMap.get("GOODS_KIND_NUMBER"));
		
		List<Map<String, Object>> cartSession = new ArrayList<Map<String, Object>>();
		Map<String, Object> cartMap = new HashMap<String, Object>();
		
		//상품 선택삭제로 넘어온 상품번호 처리
		List<String> GKN = new ArrayList<String>();
		String[] cart_number= (String[]) arrayParams;
		
		if(cart_number != null) {
		if(cart_number.length == 1) {
			System.out.println("단품 삭제");
			//String a =   (String) cart_number.get("GOODS_KIND_NUMBER");
			String a = cart_number[0];
			String[] total;
			total =a.split(",");
			GKN.add(total[0]);
			System.out.println("GKN단품:"+GKN);
		}
		else if(cart_number.length > 1) {
			System.out.println("어러개 삭제");
		System.out.println("cart_number비회원 여러개:"+cart_number);
		for(int i=0; i<cart_number.length; i++) {
		
			GKN.add(cart_number[i]);
			
			}
		System.out.println("GKN여러개:"+GKN);
		}
		}
		
		//상품 삭제버튼으로 넘어온 상품번호 처리
		if(commandMap.get("GOODS_KIND_NUMBER") instanceof String) {
			System.out.println("카트 선택 삭제2");
			String a =   (String) commandMap.get("GOODS_KIND_NUMBER");
			String[] total;
			total =a.split(",");
			GKN.add(total[0]);
			System.out.println("GKN:"+GKN);
		}

		
		//상품 삭제 시작
		//회원
		if (session.getAttribute("MEMBER_NUMBER") != null) {
	         if (commandMap.get("GOODS_KIND_NUMBER") instanceof String) {
	        	 System.out.println("장바구니 한개 선택");
	            cartMap = new HashMap<String, Object>();
	            cartMap.put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER"));
	            cartMap.put("GOODS_KIND_NUMBER", commandMap.get("GOODS_KIND_NUMBER"));
	            System.out.println("MEMBER_NUMBER:"+session.getAttribute("MEMBER_NUMBER"));
	            System.out.println("GOODS_KIND_NUMBER:"+commandMap.get("GOODS_KIND_NUMBER"));
	            cartService.deleteMyCart(cartMap);
	            
    			commandMap.put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER"));
    			List<Map<String, Object>> cartList = new ArrayList<Map<String, Object>>();
    			commandMap.put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER"));
    			cartList = cartService.cartList(commandMap.getMap());
	            mv.addObject("cartList", cartList);
	            mv.setViewName("store/cartGoods");
	            
	         } else { // 장바구니 여러개 선택 삭제
	        	 System.out.println("장바구니 여러개 선택");
	        	 System.out.println("arrayParams:"+cart_number);
	            
	            for (int j = 0; j < cart_number.length; j++) {
	            	System.out.println(cart_number[j]);
	               cartMap = new HashMap<String, Object>();
	               cartMap.put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER"));
	               cartMap.put("GOODS_KIND_NUMBER", cart_number[j]);
	               cartService.deleteMyCart(cartMap);
		           List<Map<String, Object>> cartList = new ArrayList<Map<String, Object>>();
		           commandMap.put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER"));
		           cartList = cartService.cartList(commandMap.getMap());
		           mv.addObject("cartList", cartList);
		           mv.setViewName("store/cartGoods");
	            }
	         }
	         //비회원
		} else { 
			cartSession = (List<Map<String, Object>>) session.getAttribute("cartSession");
			System.out.println("cartSession2:"+cartSession);
			
			if (GKN.size()==1) {
				System.out.println(" 비회원 장바구니 삭제"+GKN);
				cartSession = (List<Map<String, Object>>) session.getAttribute("cartSession");
				for (int i = 0; i < cartSession.size(); i++) {
					System.out.println(" 비회원 장바구니 삭제:"+cartSession.get(i).get("GOODS_KIND_NUMBER"));
					System.out.println("GKN.get(0):"+ GKN.get(0));
					if (cartSession.get(i).get("GOODS_KIND_NUMBER").equals(GKN.get(0))) {
						cartSession.remove(i);
					}
				}
			} else {
				System.out.println(" 비회원 장바구니 어러개 삭제"+GKN);
				for (int j = 0; j < GKN.size(); j++) {
					cartSession = (List<Map<String, Object>>) session.getAttribute("cartSession");
					for (int i = 0; i < cartSession.size(); i++) {
						System.out.println(" 비회원 장바구니 어러개 삭제:"+cartSession.get(i).get("GOODS_KIND_NUMBER"));
						System.out.println("GKN.get(j):"+GKN.get(j));
						if (cartSession.get(i).get("GOODS_KIND_NUMBER").equals(GKN.get(j))) {
							cartSession.remove(i);
						}
					}
				}
			}
			System.out.println("cartSession삭제후:"+cartSession);
		}
		return mv;
	}
	
	@RequestMapping(value="/cart/modifyEa")
	@ResponseBody
	public Map<String, Object> modifyEa(CommandMap commandMap, HttpServletRequest request) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		System.out.println("에이작스 장바구니 수량:"+commandMap.getMap());
		cartService.updateCarts(commandMap.getMap());
		
		return param;
	}
	
	
	@RequestMapping(value = "/cart/cartOrder")
	public ModelAndView cartOrder(CommandMap commandMap, HttpServletRequest request) throws Exception {
	ModelAndView mv = new ModelAndView("orderForm");
	HttpSession session = request.getSession();
	

	if (session.getAttribute("MEMBER_NUMBER") != null) {

		System.out.println("장바구니 구매");

		commandMap.put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER"));

		Map<String, Object> orderMember = orderService.orderMember(commandMap.getMap());
		mv.addObject("orderMember", orderMember);
		
		String[] kk = request.getParameterValues("GOODS_KIND_NUMBER");
		
		List<String> goods_kinds_number= new ArrayList<String>();
		
		for(int i=0; i<kk.length; i++) {
			String[] total;
			total =kk[i].split(",");
			System.out.println(total[0]);
			
			goods_kinds_number.add(total[0]);
			
		}
		
		System.out.println("goods_kinds_number:"+goods_kinds_number);
		
		List<String> ea = new ArrayList<String>();

		List<Map<String, Object>> goods1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> goods = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < goods_kinds_number.size(); i++) {

			commandMap.put("GOODS_KIND_NUMBER", goods_kinds_number.get(i));
			
			Map<String, Object> cartList = cartService.buyInCart(commandMap.getMap());
			
			//상품 수량
			String e = cartList.get("CART_AMOUNT").toString();
			cartList.put("EA", e);

			ea.add(e);

			System.out.println("e : " + e);
			System.out.println("ea : " + ea);

			goods1.add(cartList);

			System.out.println("goods : " + goods1);

			mv.addObject("GOODS_NUMBER", goods1.get(i).get("GOODS_NUMBER"));
		}

		String[] sArrays = ea.toArray(new String[ea.size()]);

		for (String s : sArrays) {
			System.out.println(s);
		}

		for (int i = 0; i < sArrays.length; i++) {
			commandMap.put("GOODS_KIND_NUMBER", goods_kinds_number.get(i));
			commandMap.put("EA", sArrays[i]);
			commandMap.put("GOODS_NUMBER", goods1.get(i).get("GOODS_NUMBER"));

			Map<String, Object> orderGoods = orderService.orderGoods(commandMap.getMap());

			orderGoods.put("EA", sArrays[i]);

			goods.add(orderGoods);

			System.out.println("goods : " + goods);

			mv.addObject("ea", sArrays[i]);

		}

		mv.addObject("goods_kinds_number", goods_kinds_number);
		mv.addObject("goods", goods);
	}
	return mv;
}
}
