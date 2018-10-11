package com.kh.cart;

import java.util.ArrayList;
import java.util.Arrays;
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
		//Date d = new Date(today.getTimeInMillis());
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
	public ModelAndView cartDelete(CommandMap commandMap, HttpServletRequest request, 
			@RequestParam(value="GOODS_KIND_NUMBER[]", required = false) String[] arrayParams) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		List<Map<String, Object>> cartSession = new ArrayList<Map<String, Object>>();
		Map<String, Object> cartMap = new HashMap<String, Object>();
		//상품 선택삭제로 넘어온 상품번호 처리
		List<String> GKN = new ArrayList<String>();
		String[] cart_number= arrayParams;
		if(cart_number != null) {
		if(cart_number.length == 1) {
			String a = cart_number[0];
			String[] total;
			total =a.split(",");
			GKN.add(total[0]);
		}
		else if(cart_number.length > 1) {
		for(int i=0; i<cart_number.length; i++) {
			GKN.add(cart_number[i]);
			}
		}
		}//상품 삭제버튼으로 넘어온 상품번호 처리
		if(commandMap.get("GOODS_KIND_NUMBER") instanceof String) {
			System.out.println("카트 선택 삭제2");
			String a =   (String) commandMap.get("GOODS_KIND_NUMBER");
			String[] total;
			total =a.split(",");
			GKN.add(total[0]);
		}
		//상품 삭제 시작
		if (session.getAttribute("MEMBER_NUMBER") != null) {//회원
	         if (commandMap.get("GOODS_KIND_NUMBER") instanceof String) {
	            cartMap = new HashMap<String, Object>();
	            cartMap.put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER"));
	            cartMap.put("GOODS_KIND_NUMBER", commandMap.get("GOODS_KIND_NUMBER"));
	            cartService.deleteMyCart(cartMap);
	            
    			commandMap.put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER"));
    			commandMap.put("MEMBER_NUMBER", session.getAttribute("MEMBER_NUMBER"));
    			List<Map<String, Object>> cartList = new ArrayList<Map<String, Object>>();
    			cartList = cartService.cartList(commandMap.getMap());
	            mv.addObject("cartList", cartList);
	         } else { // 장바구니 여러개 선택 삭제
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
	            }
	         }
		} else {  //비회원
			cartSession = (List<Map<String, Object>>) session.getAttribute("cartSession");
			
			if (GKN.size()==1) {
				cartSession = (List<Map<String, Object>>) session.getAttribute("cartSession");
				for (int i = 0; i < cartSession.size(); i++) {
					if (cartSession.get(i).get("GOODS_KIND_NUMBER").equals(GKN.get(0))) {
						cartSession.remove(i);
					}
				}
			} else {//여러 상품 삭제
				for (int j = 0; j < GKN.size(); j++) {
					cartSession = (List<Map<String, Object>>) session.getAttribute("cartSession");
					for (int i = 0; i < cartSession.size(); i++) {
						if (cartSession.get(i).get("GOODS_KIND_NUMBER").equals(GKN.get(j))) {
							cartSession.remove(i);}
					}
				}}}
		mv.setViewName("store/cartGoods");
		return mv;
	    }
	
	@RequestMapping(value="/cart/modifyEa")
	@ResponseBody
	public Map<String, Object> modifyEa(CommandMap commandMap) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		cartService.updateCarts(commandMap.getMap());
		return param;
	}

}
