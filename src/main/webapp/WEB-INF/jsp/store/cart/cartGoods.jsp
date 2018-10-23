<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
			<form name="fmCart" action="/ModuHome/cart/cartDelete">
			<input type="hidden" name="mode" value="del"><!-- 
			<div class="container" style="margin: 0px 0px 0px 0px; padding:0px;">	 -->
			<div class="container">
			<div class="table-order-list" align="center">
				<table class="table" >
	         		<col width="10px">
	  				<col width="12.5px">
	  				<col width="15px">
	  				<col width="12.5px">
	  				<%-- <col width="12.5px"> --%>
	  				<col width="12.5px">   
	  				<col width="12.5px">   
	  				<col width="12.5px">   
				<thead>
				 	<tr>
						<th scope="col" ></th>
						<th scope="col" class="info-img">상품 정보</th>
						<th scope="col" class="info-caption">&nbsp;</th>
						<th scope="col" class="payment">상품 가격</th>
						<!-- <th scope="col" class="sale">할인 금액</th> -->
						<th scope="col" class="delivery">합계</th>
						<th scope="col" class="delivery">배송비</th>
						<th scope="col" class="delete">비고</th>
					</tr> 
				</thead>
				<tbody>
				
						<c:if test="${!empty cartList}">
						<c:forEach var="cartList" items="${cartList}" varStatus="stat">
						<tr>
						<td>
						&nbsp;&nbsp;<c:if test="${!empty sessionScope.MEMBER_ID}">
						<input type="checkbox" id="checkbox${stat.index}" name="GOODS_KIND_NUMBER" value="${cartList.GOODS_KIND_NUMBER}" onclick="javascript:checkedRows(${stat.index});">
						</c:if>
						&nbsp;&nbsp;<c:if test="${empty sessionScope.MEMBER_ID}">
						<%-- <input type="checkbox" id="checkbox${stat.index}" name="GOODS_KIND_NUMBER" value="${cartList.GOODS_KIND_NUMBER},${cartList.CART_AMOUNT}" onclick="javascript:checkedRows('${stat.index}');"> --%>
						<input type="checkbox" id="checkbox${stat.index}" name="GOODS_KIND_NUMBER" value="${cartList.GOODS_KIND_NUMBER}" onclick="javascript:checkedRows('${stat.index}');">
						</c:if>
						</td>
						<td class="info-img">
						<a href="/ModuHome/goodsDetail?GOODS_NUMBER=${cartList.GOODS_NUMBER }">
						<img img_layer="/ModuHome/images/goods/${cartList.GOODS_THUMBNAIL}" goodsno="${cartList.GOODS_NUMBER }"
								src="/ModuHome/images/goods/${cartList.GOODS_THUMBNAIL}"
								width="167" class="img-responsive"></a></td>
						<td class="info-caption">
							<em class="name">${cartList.GOODS_NAME}/${cartList.GOODS_KIND_NUMBER}</em>
							<div class="option">
						<!-- 옵션박스 -->
						<div class="optionbox col-xs-24">
							<ul id="optionbox">
								<li id="oplist">
									<b>${cartList.GOODS_OPTION1} / ${cartList.GOODS_OPTION2}</b>
									<input type="hidden" name="cart" value="${cartList.CART_NUMBER }">
									<span id="cartNum${stat.index}" value="${cartList.CART_NUMBER }"></span>
									<input type="hidden" name="kinds" value="${cartList.GOODS_KIND_NUMBER }">
									<input type="hidden" class="mstock" value="${cartList.GOODS_AMOUNT }">
									<span id="mstock${stat.index}" value="${cartList.GOODS_AMOUNT }"></span>
									<input type="text" name="ea" value="${cartList.CART_AMOUNT }" class="input_ea${stat.index}" size="2" readonly>
									
									<span class="ea">
										<a class="btn-ea-up${stat.index}" onclick="javascript:eaUp(${stat.index});">
											<img src="/ModuHome/images/storeMain/btn_num_up.gif" alt="">
										</a>
										<a class="btn-ea-dn${stat.index}" onclick="javascript:eaDown(${stat.index});">
											<img src="/ModuHome/images/storeMain/btn_num_down.gif" alt="">
										</a>
									</span>
								</li>
							</ul>
						</div>
						</div></td>
						<!-- 상품가격 -->
						<!-- 기본 가격 -->
						<c:if test="${cartList.GOODS_DISPRICE eq cartList.GOODS_PRICE}">
						<td class="payment">
						<span class="price" id="priceid${stat.index}" value="${cartList.GOODS_PRICE}"><fmt:formatNumber value="${cartList.GOODS_PRICE}"/></span>원</td>
						<c:set var="TOTALPRICE" value="${cartList.GOODS_PRICE * cartList.CART_AMOUNT}" />
						<span class="totalprice${stat.index}" value="${TOTALPRICE}"></span>
						</c:if>
						
						<!-- 할인 가격 -->
						<c:if test="${cartList.GOODS_DISPRICE ne cartList.GOODS_PRICE}">
						<td class="payment">
						<del id="orgprice${stat.index}"><fmt:formatNumber value="${cartList.GOODS_PRICE}"/></del>원
						<br/>
						<span  class="oriPriceforDis" value="${cartList.GOODS_PRICE}"></span>
						<span  class="price" id="priceid${stat.index}" value="${cartList.GOODS_DISPRICE}"><fmt:formatNumber value="${cartList.GOODS_DISPRICE}"/></span>원
						<c:set var="TOTALPRICE" value="${cartList.GOODS_DISPRICE* cartList.CART_AMOUNT}" />
						<span class="totalprice${stat.index}" value="${TOTALPRICE}"></span>
						<span class="orgprice${stat.index}" value="${cartList.GOODS_PRICE}"></span>
						</td> 
						</c:if>
						
						<c:if test="${TOTALPRICE >= 30000 }">
						<c:set var="DELIVERYFEE" value="0"/>
						</c:if>
						<c:if test="${TOTALPRICE < 30000 }">
						<c:set var="DELIVERYFEE" value="2500"/>
						</c:if> 
						<!-- 할인금액 -->
						<c:if test="${cartList.GOODS_DISPRICE ne cartList.GOODS_PRICE}">
				 		<span class="disprice" id="disprice${stat.index}" value="${cartList.GOODS_PRICE-cartList.GOODS_DISPRICE}" value2="${cartList.GOODS_PRICE* cartList.CART_AMOUNT-cartList.GOODS_DISPRICE* cartList.CART_AMOUNT}">
						<%-- <fmt:formatNumber value="${cartList.GOODS_PRICE* cartList.CART_AMOUNT-cartList.GOODS_DISPRICE* cartList.CART_AMOUNT}"/> --%>
				 		</span>
						</c:if>
						
						<!-- 합계 -->
						<td><span id="pricesum${stat.index}">
						<fmt:formatNumber value="${TOTALPRICE}"/>
						</span>
						원
						</td>
						<!--배송비  -->
						<td><span id="delivery${stat.index}"><fmt:formatNumber value="${DELIVERYFEE}"/></span>원 
						</td> 
						<!-- 삭제버튼 -->
						<td class="delete">
						<c:if test="${!empty sessionScope.MEMBER_ID}">
						<a href="/ModuHome/cart/cartDelete?GOODS_KIND_NUMBER=${cartList.GOODS_KIND_NUMBER}" onClick='return confirm("정말로 장바구니를 삭제하시겠습니까?");'> <span class="button-label">삭제</span>
						</a>
						</c:if>
						<c:if test="${empty sessionScope.MEMBER_ID}">
						<a href="/ModuHome/cart/cartDelete?GOODS_KIND_NUMBER=${cartList.GOODS_KIND_NUMBER}"> <span class="button-label">삭제</span>
						</a>
						</c:if>
					
						</td> 
					</tr>
					
					</c:forEach>
					</c:if>
					
					<c:set var="doneLoop" value="false"/>
					<c:set var="index" value="1" /> 
					
						<c:if test="${empty cartList }">
						<c:if test="${empty sessionScope.CartSession}">
							<tr>
								<td colspan="7" style="padding:30px 0;" align="center">
									장바구니에 주문하실 상품을 담아주세요<br>
								</td>
							</tr>
						</c:if>
					</c:if>	
					</tbody>
	</table>
</div>
</div>
<div>
<table>
<tr>
<td>
<c:if test="${!empty cartList}">
	<div class="button-wrap">
		<button type="button" id="btn-checked-all">
			<span class="button-label">전체선택</span>
		</button>
		<button type="button" id="btn-unchecked-all"> 
			<span class="button-label">전체해제</span>
		</button>
		<button type="button" id="btn-checked-one">
			<span class="button-label">선택삭제</span>
		</button>
	</div>
</c:if>
</td>
</tr>
<tr>
<td>
	<c:if test="${empty cartList }">
		<c:if test="${empty sessionScope.cartKinds0}">
		<div class="button-wrap">
			<button class="button" id="btn-checked-all">
				<span class="button-label">전체선택</span>
			</button>
			<button class="button" id="btn-unchecked-all">
				<span class="button-label">전체해제</span>
			</button>
			<button class="button" id="btn-checked-one">
				<span class="button-label">선택삭제</span>
			</button>
		</div>
		</c:if>
	</c:if>
</td>
</tr>
<tr>
<td>
<div class="cal-result">
	<div class="price-order" style="padding: 10px;">
		<div class="sum">
			주문금액&nbsp;&nbsp;&nbsp;<strong id="realtotalPrice">0</strong>원
		</div>
		<div class="sum">
			할인 금액&nbsp;&nbsp;&nbsp;<strong id="disCountPirce">0</strong>원
		</div>
		<!-- <div class="sum-order">
			총 금액 합계&nbsp;&nbsp;&nbsp;<strong id="totalPrice">0</strong>원
		</div> -->
		<div class="item-label">
			배송비&nbsp;&nbsp;&nbsp;<strong id="delfee">0</strong>원
		</div>
	</div>
</div>


</td>
</tr>
<tr>
<td align="center">
<div class="button-group">
		<button type="button" id="buy-button" onclick="cartBuy();">구매하기</button>
		<!-- <button type="button" id="cancel-button" onclick="location.href='/ModuHome/goods'">쇼핑 계속</button> -->
		<button type="button" id="cancel-button" onclick="javascript:history.back();">이전 페이지로</button>
</div>
</td>
</tr>
</table>
</form>
