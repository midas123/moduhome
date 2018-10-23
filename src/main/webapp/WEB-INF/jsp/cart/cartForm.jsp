<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/ModuHome/dist/jquery/jquery-1.11.0.min.js"></script>
<script src="/ModuHome/dist/jquery/jquery-ui.js"></script>
<script src="/ModuHome/dist/jquery/jquery-migrate-1.2.1.min.js"></script>
<script>
var sum = 0;
var disSum= 0;
var totSum = 0;
var delfee =0;

//체크된 상품 합계금액 계산
function checkedRows(index){
	    var index = index;
	    console.log("주문요약인덱스:"+index);
	    var tagName = "#checkbox"+index;
	    var price = $(".price").eq(index).attr("value"); //상품 가격
	    console.log("price:"+price);
	    var disprice = $(".disprice").eq(index).attr("value2"); //할인 금액
	    console.log("총disprice:"+disprice);
	    var totprice = rm_comma($("#pricesum"+index).html()); //상품 합계가격
	    console.log("총totprice:"+totprice);
	    price = parseInt(price, 10);
	    disprice = parseInt(disprice, 10);
	    totprice = parseInt(totprice, 10);
      		if($(tagName).is(":checked")){
      			   sum = sum + price; 
      			   disSum = disSum + disprice;	//할인 금액합계
      			   totSum = totSum + totprice;  //최종 결제 금액
      	   		
      			//배송비
      			if(totSum < 30000){
      				 delfee= 2500;
      				 $("#delfee").html(comma(delfee));
      			   } else {
      				 delfee= 0;
      				$("#delfee").html(comma(delfee));
      			   } 
      			   
	        	   $("#realtotalPrice").html(comma(totSum));
	        	   $("#disCountPirce").html(comma(disSum));
	        	}else{
	        	    sum = sum-price;
	        	    disSum = disSum - disprice;
	        	    totSum = totSum - totprice;
	        		$("#realtotalPrice").html(comma(totSum));
	        		$("#disCountPirce").html(comma(disSum));
	        		
	        		if(totSum >= 30000 || totSum == 0){
	        			 delfee= 0;
	       				$("#delfee").html(comma(delfee));
	        		} else {
	        			delfee= 2500;
	      				 $("#delfee").html(comma(delfee));
	        		}
	        }
	};
</script>
<style>
.order-shoppingBag{
	background: #fff;
	margin-top: 70px;
}
.table-order-list{
	background: #fff;
	margin-top: -20px;
	margin-left: 20px;
}

.cal-result{
	background: #fff;
	margin-top: 20px;
	margin-left: 20px;
	background: #f2f2f2;
	width: 1000px;
}
.button-wrap{
	margin-left: 20px;
}
.button-wrap #btn-checked-all, #btn-unchecked-all, #btn-checked-one{
	background: #85C8DD;
	float:left;
	text-align: center;
	font-size: 14px;
	border: none;
	color: white;
	width:100px; 
	/* height:30px; */
	/* padding: 1px; */
	/* border-radius: 12px; */
	border-radius: 30px;
 	padding-top:5px;
 	padding-bottom:5px;
 	/* line-height: 1.5; */
 	margin-right : 10px;
 	text-transform: uppercase;
}

.button-wrap #btn-checked-one{
	background: #b2b2b2;
}


.button-group{
	margin-top: 20px;
	margin-bottom: 30px;
}

.button-group #buy-button, #cancel-button{
	text-align: center;
	font-size: 16px;
	border: none;
	color: white;
	width:230px; 
	height:40px;
	 background: #85C8DD;
	 margin-top: 40px;
	 background-position: center;
}

.button-group #cancel-button{
	background: #b2b2b2;
}


</style>

</head>
<div class="col-md-12" style="background-color:#85C8DD; height: 100px; width: 100%; margin-top: -100px;">
</div>
<body style="background-color:#fff; !important;">
<div class="order-shoppingBag" style="margin-top:100px; background-color:#fff;" align="center">
		<div class="section-head left border" style="padding: 30px; margin-top:20px; margin-left:50px; background-color:#fff; text-align: center;">
			<span style="font-size: 28px; color:black;">장바구니</span>
		</div>
		<hr style="color:#999999; width:90%; padding:20px;">
		<div id="changeCartGoodlist" class="section-body col-md-12" style="background-color:#fff; !important;">
			<form name="fmCart" action="/ModuHome/cart/cartDelete">
			<div class="container">
			<div class="table-order-list" align="center">
				<table class="table" >
	         		<col width="10px">
	  				<col width="12.5px">
	  				<col width="15px">
	  				<col width="12.5px">
	  				<col width="12.5px">   
	  				<col width="12.5px">   
	  				<col width="12.5px">   
				<thead>
				 	<tr>
						<th scope="col" ></th>
						<th scope="col" class="info-img">상품 정보</th>
						<th scope="col" class="info-caption">&nbsp;</th>
						<th scope="col" class="payment">상품 가격</th>
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
						&nbsp;&nbsp;
						<input type="checkbox" id="checkbox${stat.index}" name="GOODS_KIND_NUMBER" value="${cartList.GOODS_KIND_NUMBER}" onclick="javascript:checkedRows('${stat.index}');">
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
									<input type="hidden" name="kinds[]" value="${cartList.GOODS_KIND_NUMBER }">
									<input type="hidden" name="goodsno[]" value="${cartList.GOODS_NUMBER }">
									<input type="hidden" class="mstock" value="${cartList.GOODS_AMOUNT }">
									<span id="mstock${stat.index}" value="${cartList.GOODS_AMOUNT }"></span>
									<input type="text" name="ea[]" value="${cartList.CART_AMOUNT }" class="input_ea${stat.index}" size="2" readonly>
									
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
						<span class="button-label-delete" value="${cartList.GOODS_KIND_NUMBER}">삭제</span>
						</td> 
					</tr>
					</c:forEach>
					</c:if>
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
			-할인 금액&nbsp;&nbsp;&nbsp;<strong id="disCountPirce">0</strong>원
		</div>
		<div class="sum">
			총 주문금액&nbsp;&nbsp;&nbsp;<strong id="realtotalPrice">0</strong>원
		</div>
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
		<button type="button" id="cancel-button" onclick="javascript:history.back();">이전 페이지로</button>
</div>
</td>
</tr>
</table>
</form>
</div>
</div>

<script>
function cartBuy(){
		var fm = document.fmCart;
		if($("input:checkbox[name='GOODS_KIND_NUMBER']").is(":checked") == false) {
			alert("상품을 선택해 주세요.");
			return false;
		};
		
		if('${sessionScope.MEMBER_ID}' == null || '${sessionScope.MEMBER_ID}' =='' ){
			alert("로그인 후 구매해주세요.")
			return false;
		}
		
		//fm.mode.value = "cart";
		fm.target = "_self";
		fm.action = "/ModuHome/order";
		fm.submit();
	}
</script>
<script>
$(document).ready(function(){
$("#btn-checked-all").click(function(){
	$(".order-shoppingBag input[name='GOODS_KIND_NUMBER']").not(":checked").trigger("click");
	return false;
});

$("#btn-unchecked-all").click(function(){
	$(".order-shoppingBag input[name='GOODS_KIND_NUMBER']:checked").trigger("click");
	return false;
});

$("#btn-checked-one").click(function(){
	if (!$(".order-shoppingBag input[name='GOODS_KIND_NUMBER']").is(":checked")){
		alert("삭제하실 상품을 선택해주세요");
		return false;
	} else {
	
	if(confirm("정말로 상품을 삭제하시겠습니까?")){
	//선택상품 삭제로직
	var goodsNum = new Array();
	$(".order-shoppingBag input[name='GOODS_KIND_NUMBER']:checked").each(function() {
		goodN = $(this).attr("value");
		goodsNum.push(goodN);
	});
	$.ajax({
	     url: "/ModuHome/cart/cartDelete",
	       type : "post",
	       data: {"GOODS_KIND_NUMBER":goodsNum},
	       success:function(data){
	    	  $("#changeCartGoodlist").html(data);
	       }
	    });  
	}
	}
	return false;
	
});

$("span.button-label-delete").click(function(){
		var goodsNum = $(this).attr("value");
		$.ajax({
		     url: "/ModuHome/cart/cartDelete",
		       type : "post",
		       data: {"GOODS_KIND_NUMBER":goodsNum},
		       success:function(data){
		    	  $("#changeCartGoodlist").html(data);
		       }
		    });  
	});
});
</script>
<script>
function ajaxChangeEa(cartNum, index, idx) {
	var idx = idx; //상품 수량
	var index = index;//장바구니 목록 중 상품의 인덱스
	var ea = parseInt($(".input_ea"+index).val(), 10);
	$.ajax({
		url: "/ModuHome/cart/modifyEa",
		data: {"CART_NUMBER": cartNum, "CART_AMOUNT":idx},
		dataType: "json",
		success:function(data){
			//수량 변경
			$(".input_ea"+index).val(idx+ea);
		}
	});
}

//콤마 추가
function comma(num){
    var len, point, str; 
       
    num = num + ""; 
    point = num.length % 3 ;
    len = num.length; 
   
    str = num.substring(0, point); 
    while (point < len) { 
        if (str != "") str += ","; 
        str += num.substring(point, point + 3); 
        point += 3; 
    } 
    return str;
} 
//콤마 삭제
function rm_comma(num){
   var number = num + "";
   return number.replace(",","");
}

function change_ea(index,idx){
	var ea = parseInt($(".input_ea"+index).val(), 10) + idx;
	$(".input_ea"+index).val(ea);
} 

function eaUp(index){
var index = index;

//수량 증가
$(document).off().on("click", ".btn-ea-up"+index, function(e) {
    var inputEa = parseInt($("input.input_ea"+index).val(), 10); //입력 수량
    var mStock = parseInt($("#mstock"+index).attr("value"), 10); //재고수량
    var price = parseInt($("#priceid"+index).attr("value"), 10); //상품 단가
    var disprice = parseInt($("#disprice"+index).attr("value"), 10); //할인금액
    var total = parseInt(rm_comma($("#pricesum"+index).html()), 10); //합계
    var cartNum = $("#cartNum"+index).attr("value"); //카트번호
    
	if($("#checkbox"+index).is(":checked")){
		alert("수량변경은 선택해제 후 가능합니다.");		
		return false;
	}
	
    if(inputEa > mStock-1) {
        alert("재고가 부족하여 더 이상 주문하실 수 없습니다.");
        return false;
    }
    
	if('${sessionScope.MEMBER_ID}' == null || '${sessionScope.MEMBER_ID}' == ''){
		change_ea(index,1); 
	} else {
    ajaxChangeEa(cartNum, index, 1);
	}
	
	inputEa = inputEa + 1
    total = price + total;
    
  //배송비
  	var delfee = 0;
	if(total < 30000){
			 delfee= 2500;
			 $("#delivery"+index).html(comma(delfee));
		   } else {
			 delfee= 0;
			$("#delivery"+index).html(comma(delfee));
		   } 
    
   $("#disprice"+index).attr("value2", inputEa*disprice); //할인금액
    $("#pricesum"+index).html(comma(total)); //합계
    
});
}

 
//수량감소
function eaDown(index){
var index = index;

$(document).off().on("click", "li a.btn-ea-dn"+index, function(e) {
	var eaup = ".input_ea"+index;
	var ea = parseInt($(eaup).val());
    var cartNum = $("#cartNum"+index).attr("value");
    var inputEa = parseInt($("input.input_ea"+index).val(), 10);
    var mStock = parseInt($("#mstock"+index).attr("value"), 10); 
    var price = parseInt($("#priceid"+index).attr("value"), 10); //상품 단가
    var disprice = parseInt($("#disprice"+index).attr("value"), 10); //할인금액
    var total = parseInt(rm_comma($("#pricesum"+index).html()), 10); //합계
    var orgprice = parseInt($(".orgprice"+index).attr("value"), 10);

	if($("#checkbox"+index).is(":checked")){
		alert("이미 선택한 상품입니다.");		
		return false;
	}
	if(ea <= 1){
		alert("1개 이상을 주문하셔야 합니다");
		return false;
	}
	if('${sessionScope.MEMBER_ID}' == null || '${sessionScope.MEMBER_ID}' == ''){
		change_ea(index,-1); 
	} else {
		ajaxChangeEa(cartNum, index, -1);
	}
    total = total - price;
    inputEa = inputEa - 1
  //배송비
  	var delfee = 0;
	if(total < 30000){
			 delfee= 2500;
			 $("#delivery"+index).html(comma(delfee));
		   } else {
			 delfee= 0;
			$("#delivery"+index).html(comma(delfee));
		   } 
    $("#disprice"+index).attr("value2", inputEa*disprice);
    $("#pricesum"+index).html(comma(total)); //합계
});
}
</script>
</body>
</html>