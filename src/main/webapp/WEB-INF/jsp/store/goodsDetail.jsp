<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>

<c:set var="checkBuy" value="600"/> 

<!-- CSS Offset -->
<link type="text/css" rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet"
	href="https://fonts.googleapis.com/earlyaccess/nanumgothic.css" />
<script type="text/javascript"
	src="//cdn.ggumim.co.kr/resource/house_interior_web/ggumim.stack-1.2.05.min.js"></script>
	
<link type="text/css" rel="stylesheet" href="/ModuHome/css/store/ggumim-1.2.04.min.css"/>
<link rel="stylesheet" href="/ModuHome/theme/aboki/power_review_custom.2.css">
<link rel="stylesheet" href="/ModuHome/theme/aboki/main.css">

<script src="/ModuHome/dist/jquery/jquery-1.11.0.min.js"></script>
<script src="/ModuHome/dist/jquery/jquery-ui.js"></script>
<script src="/ModuHome/dist/jquery/jquery-migrate-1.2.1.min.js"></script>	
	
<script>
	var loginCheck = '${sessionScope.MEMBER_ID}';

	function _exec(mode) {
		if (mode == 'restock') {
			document.location.href = "./restock";
		} else if (mode == "buy") {
			if (document.getElementsByName("optno[]").length == 0) {
				alert("옵션을 선택해주세요");
				return;
			}
			if(loginCheck == null || loginCheck==''){
				console.log("상품상세페이지세션쳌:"+loginCheck);
				alert("로그인 후 구매가능 합니다.")
				return false;
			}
			
			var fm = document.fmOrder;
			fm.mode.value = mode;
			fm.target = "_self";
			fm.action = "/ModuHome/order";
			//if (mode=="order") fm.action = "../order";
			fm.submit();
		} else if (mode != "wishlist") {
			if (document.getElementsByName("optno[]").length == 0) {
				alert("옵션을 선택해주세요");
				return;
			}
			var fm = document.fmOrder;
			fm.mode.value = mode;
			fm.target = "_self";
			fm.action = "/ModuHome/cart/cartAdd";
			//if (mode=="wishlist") fm.action = "../mypage/wishlist";
			fm.submit();
		}
	}
</script>

<script>
	//콤마 추가
	function comma(num) {
		var len, point, str;
		num = num + "";
		point = num.length % 3;
		len = num.length;
		str = num.substring(0, point);
		while (point < len) {
			if (str != "")
				str += ",";
			str += num.substring(point, point + 3);
			point += 3;
		}
		return str;
	}
	//콤마 삭제
	function rm_comma(num) {
		var number = num + "";
		return number.replace(",", "");
	}
	//수량 변경 함수
	function change_ea(obj, idx) {
		var ea = parseInt($("input.input_ea", $(obj).parent().parent()).val(),
				10)
				+ idx;
		if (ea < 1) {
			alert("1개 이상을 주문하셔야 합니다");
			return;
		}
		$("input.input_ea", $(obj).parent().parent()).val(ea);
	}
	function chkSoldout(obj) {
		if (obj.options[obj.selectedIndex].stock == "0") {
			alert("선택한 항목은 품절된 옵션입니다");
			obj.selectedIndex = 0;
			return false;
		}
		return true;
	}
	function setOption(obj) {
		$(document).ready(
			function() {
				var totprice = 0;
				if ($("#option option:selected").attr("disabled") == "disabled") {
					alert("선택한 옵션은 품절된 상태입니다");
					$("#option").get(0).selectedIndex = 0;
					return;
				}
				var optno = $("#option option:selected").val();
				if (!optno)
					return;
				//상품 옵션 추가 html
				var li = "<li class='MK_li_1_1'><span class='MK_p-name'>" 
						+ $("#option option:selected").attr("value")
						+ "</span><input type='hidden' name='optno[]' value='" + optno + 
								"'><input type='hidden' name='kinds[]' value='"
						+ $("option:selected", $(obj)).attr("kinds")
						+ "'><input type='hidden' class='mstock' value='"
						+ $("option:selected", $(obj)).attr("stock")
						+ "'><div class='MK_qty-ctrl' style='height:50px'>"
						+ "<input type='text' name='ea[]' value='1' class='input_ea' size='2' maxlength='3' readonly>"
						+ "<span class='ea'><a class='MK_btn-up'><img src='/ModuHome/images/storeMain/btn_num_up.gif' alt='' />"
						+ "</a><a class='MK_btn-dw'><img src='/ModuHome/images/storeMain/btn_num_down.gif' alt='' />"
						+ "</a></span></div><span class='MK_price' data-price='"
						+ $("option:selected", $(obj)).attr("price") + "'>"
						+ comma($("option:selected", $(obj)).attr("price"))
						+ "원</span><a href='#' optno='" + optno +"'class='MK_btn-del'><img src='/ModuHome/images/storeMain/btn_close.gif' alt='' /></a></li>";
				//페이지에 html 코드 삽입
				$("#MK_innerOpt_01").append(li);
				//상품 추가할때 합계가격 갱신
		 		var thisIdx = $(".input_ea").attr("value");
				var inputEa = parseInt(thisIdx, 10);
				var price = parseInt(rm_comma($("option:selected",
						$('#option')).attr("price")), 10);
				price = price * inputEa;
				price = parseInt(price, 10);
				totprice = parseInt(rm_comma($("#MK_txt-won")
						.html()), 10);
			
				totprice = totprice + price;
				$("#MK_txt-won").data("price", totprice);
				$("#MK_txt-won").html(comma(totprice) + "원"); 
			});
	}
	//상품옵션 삭제
	$(document).ready(function() {
		$("#MK_innerOpt_01").on("click", ".MK_btn-del", function() {
			var ritem = $(this).attr("optno");
			console.log("ritem:"+ritem);
			var thisIdx = $(".MK_btn-del").index(this);
			var price = parseInt($(".MK_price").eq(thisIdx).data("price"), 10);
			var totprice = parseInt($("#MK_txt-won").data("price"), 10);
			totprice = parseInt(totprice - price);
			$("#MK_txt-won").data("price", totprice);
			$("#MK_txt-won").html(comma(totprice) + "원");
			//삭제
			$(".MK_li_1_1").eq(thisIdx).remove();
			//기본 선택 위치로
			$("option:eq(0)").prop("selected", true);
		});
	});
	//수량증가
	$(document).ready(
			function() {
				$("#MK_innerOpt_01").on("click","li a.MK_btn-up",
						function(e) {
							//현재 옵션의 리스트 인덱스							
							var thisIdx = parseInt($(".MK_btn-up").index(this),10);
							//수량 변경 함수
							change_ea(this, 1);
							//입력 수량
							var inputEa = parseInt($(".input_ea").eq(thisIdx).val(), 10);
							//상품 재고 수량
							var mStock = parseInt($(".mstock").eq(thisIdx).val(), 10);
							//단품 가격
							var price = parseInt($('.MK_price').eq(thisIdx).attr("data-price"), 10);
							//합계 가격
							var totprice = parseInt($("#MK_txt-won").data("price"), 10);
							//재고 수량  체크
							if (inputEa > mStock) {
								alert(mStock + "개 이상 주문하실 수 없습니다.");
								$(".input_ea").eq(thisIdx).val(mStock);
								return false;
							}
							//합계 금액 계산							
							totprice = totprice + price;
							//계산 결과 삽입
							$(".MK_price").eq(thisIdx).data("price",
									(price * inputEa));
							$(".MK_price").eq(thisIdx).html(
									comma(price * inputEa) + "원");
							$("#MK_txt-won").data("price", totprice);
							$("#MK_txt-won").html(comma(totprice) + "원");
						});
				//수량 감소
				$("#MK_innerOpt_01").on(
						"click",
						"li a.MK_btn-dw",
						function(e) {
							var thisIdx = $(".MK_btn-dw").index(this);
							var inputEa = parseInt($(".input_ea").eq(thisIdx)
									.val(), 10);
							if (inputEa == 1) {
								alert("1개 이상 주문하셔야 합니다.");
								$(".input_ea").eq(thisIdx).val() == 1;
								return false;
							}
							change_ea(this, -1);
							inputEa = parseInt(
									$(".input_ea").eq(thisIdx).val(), 10);
							var price = parseInt(
									$('.MK_price').eq(thisIdx).attr("data-price"), 10);
							
							$(".MK_price").eq(thisIdx).data("price",
									(price * inputEa));
							var total = $(".MK_price").eq(thisIdx).html(
									comma(price * inputEa) + "원");
							var totprice = parseInt($("#MK_txt-won").data(
									"price"), 10);
							totprice = totprice - price;
							$("#MK_txt-won").data("price", totprice);
							$("#MK_txt-won").html(comma(totprice) + "원");
							return false;
						});
			});
			//엔터키 submit방지	
			$(document).on("keypress", ":input:not(textarea)", function(event) {
			    return event.keyCode != 13;
			});		
				
</script>

<script>
function loginCheckOrder(){
	alert('로그인을 해주세요.'); 
	return false;
}

function simpleLightbox(imageUrl, bgColor, maxWidth){
    if(typeof bgColor === 'undefined'){
        bgColor = '#000';
    }
    if(typeof maxWidth === 'undefined'){
        maxWidth = '400px';
    }
    window.open('', 'simpleLightbox').document.write('<html><head><meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, minimum-scale=1.0, width=device-width" /></head><body style="margin:0;'+bgColor+';height:100%;" onclick="javascript:window.close(\'simpleLightbox\');"><table border="0" width="100%" height="100%"><tr><td valign="middle" align="center"><img style="position:relative;z-index:2;width:100%;max-width:'+maxWidth+';" src="'+imageUrl+'"/></td></tr></table></body></html>');
}


$('.imgScale').on('click', function(){
    simpleLightbox($(this).attr('src'));
});

</script>


<style>
body {
   font-family: 'Nanum Gothic';
}

.btn-just-buy, .review-write-btn {
	border: 1px solid #85C8DD;
    background: #85C8DD;
    color: #fff;
}
.btn-cart {
	border: 1px solid #b2b2b2;
    background: #b2b2b2;
    color: #fff;
}
.container {
	width: 1000px;
}
.furniture-view{
	margin-top: 100px;
	width: 1000px;
}

.furniture-view .row .furniture-view-infomation {
	float:right;
	margin-right: 80px;
	padding-top: 50px;
	padding-bottom: 50px;
}
.furniture-view-infomation div {
	margin-left: 50px;
}
.furniture-delivery div {
	margin-left: 1px;
}
.furniture-view-option div {
	margin-left: 1px;
}
.furniture-view-option .option-set select {
	margin-top: -30px;
}

.furniture-view .furniture-view-image .furniture-view-image-wrapper {
	width: 400px;
	margin-left: 50px;
	padding-top: 50px;
}

.MS_power_review_list .hd-box ul li {
	display:inline-block;
	margin-left: 15px;
    height:40px;
}

.qna-list ul li{
	display:inline-block;
	margin-left: 15px;
}

</style>

<style type="text/css">
    /* Customized Paging */
.pg_wrap { clear:both; }
.pg_page { /* �⺻������ */

}
.pg_page:hover {
    background:#f2f2f2;
}
.pg_current { /* ���������� */
    width:30px !important;
    height:30px !important;
    font-size:13px;
    color:#fff;
    font-weight:normal;
    line-height:33px;
    margin:0;
    padding:0;
    background:#333;
    border:0;
    border-radius:2px;
    -moz-border-radius:2px;
    -webkit-border-radius:2px;
    text-align:center;
    display:inline-block;
    font-size: 16px;
}
.pg_start { /* ó�� */
    position:relative;
    top:50%;
    width:28px !important;
    height:28px !important;
    background:#fff;
    border:1px solid #ccc;
    border-radius:2px;
    -moz-border-radius:2px;
    -webkit-border-radius:2px;
    text-indent:-9999px;
    display:inline-block;
}
.pg_start:after {
    position:absolute;
    top:10px;
    left:10px;
    content:"";
    width:6px;
    height:6px;
    border-top:1px solid transparent;
    border-right:1px solid transparent;
    border-bottom:1px solid #777;
    border-left:1px solid #777;
    transform:rotate(45deg);
}
.pg_start:before {
    position:absolute;
    top:10px;
    left:16px;
    content:"";
    width:6px;
    height:6px;
    border-top: 1px solid transparent;
    border-right: 1px solid transparent;
    border-bottom: 1px solid #777;
    border-left: 1px solid #777;
    transform:rotate(45deg);
}
.pg_start:hover {
    background:#fff;
    border:1px solid #333;
}
.pg_prev { /* ���� */
    position:relative;
    top:50%;
    width:28px !important;
    height:28px !important;
    background:#fff;
    border:1px solid #ccc;
    border-radius:2px;
    -moz-border-radius:2px;
    -webkit-border-radius:2px;
    text-indent:-9999px;
    display:inline-block;
    margin-top: -8px !important;
}
.pg_prev:after {
    position:absolute;
    top:10px;
    left:13px;
    content:"";
    width:6px;
    height:6px;
    border-top:1px solid transparent;
    border-right:1px solid transparent;
    border-bottom:1px solid #777;
    border-left:1px solid #777;
    transform:rotate(45deg);
}
.pg_prev:hover {
    background:#fff;
    border:1px solid #333;
}
.pg_next { /* ���� */
    position:relative;
    width:28px !important;
    height:28px !important;
    background:#fff;
    border:1px solid #ccc;
    border-radius:2px;
    -moz-border-radius:2px;
    -webkit-border-radius:2px;
    text-indent:-9999px;
    display:inline-block;
    margin-top: -8px !important;
}
.pg_next:after {
    position:absolute;
    top:10px;
    right:13px;
    content:"";
    width:6px;
    height:6px;
    border-top:1px solid #777;
    border-right:1px solid #777;
    border-bottom:1px solid transparent;
    border-left:1px solid transparent;
    transform:rotate(45deg);
}
.pg_next:hover {
    background:#fff;
    border:1px solid #333;
}
.pg_end { /* �ǳ� */
    position:relative;
    top:50%;
    width:28px !important;
    height:28px !important;
    background:#fff;
    border:1px solid #ccc;
    border-radius:2px;
    -moz-border-radius:2px;
    -webkit-border-radius:2px;
    text-indent:-9999px;
    display:inline-block;
}
.pg_end:after {
    position:absolute;
    top:10px;
    right:10px;
    content:"";
    width:6px;
    height:6px;
    border-top:1px solid #777;
    border-right:1px solid #777;
    border-bottom:1px solid transparent;
    border-left:1px solid transparent;
    transform:rotate(45deg);
}
.pg_end:before {
    position:absolute;
    top:10px;
    right:16px;
    content:"";
    width:6px;
    height:6px;
    border-top:1px solid #777;
    border-right:1px solid #777;
    border-bottom:1px solid transparent;
    border-left:1px solid transparent;
    transform:rotate(45deg);
}
.pg_end:hover {
    background:#fff;
    border:1px solid #333;
}


</style>
</head>
<div class="col-md-12" style="background-color:#85C8DD; height: 90px; width: 100%; margin-top: 0px;">
</div>
<body style="background: #fff">
	<form name="fmOrder">
			<input type="hidden" name="mode"> <input type="hidden" name="goodsno" value="${goodsBasic.GOODS_NUMBER }"> 
				<input type="hidden" name="GOODS_NAME" value="${goodsBasic.GOODS_NAME}">
	<div class="container" style="margin: auto;">
		<div class="furniture-view">
			<div class="row">
				<div class="col-xs-6 furniture-view-image monday-slick">
						<div class="furniture-view-image-wrapper">
									<c:forEach var="goodsImage" items="${goodsImage}" varStatus="stat" begin="0" end="1">
									<div class="furniture-image">
									<img src="/ModuHome/images/goods/${goodsImage.IMAGE}" onerror="this.src='/ModuHome/images/noimg_130.gif'" />
									</div>
									</c:forEach>
							</div > 
				</div>
				<!--/.left menu end-->
				<div class="col-xs-6 furniture-view-infomation">
							<div class="furniture-view-brand">${goodsBasic.GOODS_BRNAME}</div>
							<div class="furniture-view-name">${goodsBasic.GOODS_NAME}</div>
							<div class="furniture-view-cost">
								<c:if test="${goodsBasic.GOODS_PRICE eq goodsBasic.GOODS_DISPRICE}">
								<span>
									<fmt:formatNumber value="${goodsBasic.GOODS_PRICE}" type="number" />원
								</span><br/> 
						        </c:if>
								<c:if test="${goodsBasic.GOODS_PRICE ne goodsBasic.GOODS_DISPRICE}">
										<span class="original slashit"><fmt:formatNumber
												value="${goodsBasic.GOODS_PRICE}" type="number" />원</span><br/> 
										<span class="discount"><fmt:formatNumber
										value="${goodsBasic.GOODS_DISPRICE}" type="number" /></span>원 
										<span class="discount_rate">(<fmt:formatNumber
											value="${(goodsBasic.GOODS_PRICE - goodsBasic.GOODS_DISPRICE)*100 / goodsBasic.GOODS_PRICE}"
											type="number" />%할인)</span>
								</c:if>
							</div>
						<div class="furniture-delivery">
							<div class="row">
								<div>배송비</div>
								<div>
									<div style="float: left; font-size: 12px;">2,500 원 (
										30,000원 이상 무료 )</div>
									<div style="float: left; font-size: 12px;">(선결제)</div>
									<input type="hidden" name="delivery-payment" value="0">
								</div>
							</div>
						</div>
					<!--/.right top menu end-->
					<div class="furniture-view-option ">
						<div>상품옵션</div>
						<div class="option-set">
						<select id="option" onchange="setOption(this)"
							style="width: 225px">
							<option selected value="">-옵션 선택-</option>
							<c:forEach var="goodsDetail" items="${goodsDetail}"
								varStatus="stat">
							<c:choose>
							<c:when test="${goodsDetail.GOODS_AMOUNT eq 0}">
							품절
							</c:when>
							<c:when test="${goodsDetail.GOODS_AMOUNT > 0}">
								<c:if test="${goodsDetail.GOODS_AMOUNT > 0}">
									<c:if
										test="${goodsBasic.GOODS_DISPRICE ne goodsBasic.GOODS_PRICE }">
										<option
											value="${goodsDetail.GOODS_OPTION1}-${goodsDetail.GOODS_OPTION2 }"
											data-optnm1="${goodsDetail.GOODS_OPTION1 }"
											data-optnm2="${goodsDetail.GOODS_OPTION2 }"
											stock="${goodsDetail.GOODS_AMOUNT }"
											price="${goodsBasic.GOODS_DISPRICE }"
											kinds="${goodsDetail.GOODS_KIND_NUMBER }">${goodsDetail.GOODS_OPTION1}-${goodsDetail.GOODS_OPTION2 }
										</option>
										<c:if test="${goodsBasic.GOODS_DISPRICE eq goodsBasic.GOODS_PRICE }">
										</c:if>
									</c:if>
								</c:if>
								</c:when>
								</c:choose>
								</c:forEach>
						</select>
						</div>	
						<div>
							<ul class="MK_inner-opt-cm" id="MK_innerOpt_01"></ul>
						</div>
						<div id="MK_innerOptTotal" class="">
							<p class="totalRight">
								<span class="MK_txt-total">합계</span> <span id="MK_txt-won"
									data-price="">0원</span>
							</p>
						</div>	
					</div>
					<!--/.furniture-view-option-->
					<div class="row" style="margin-right: -100px; margin-left: -1px;">
							<div class="col-xs-5 btn-cart" id="cartBtn"
								onclick="javascript:_exec('cart');" style="width:230px; height:40px; text-align: center;">장바구니 담기</div>
							<div class="col-xs-5 btn-just-buy"
								onclick="javascript:_exec('buy');" style="margin-left:0px; width:230px; height:40px; ">바로구매</div>
					</div>
				</div>
				<!--/.right bottom menu-->
			</div>
			<!--/right menu-->
		</div>
		</div>
		<div class="container">
			<div class="commerce-title">
				<h2>제품 설명</h2>
			</div>
		<div class="furniture-view-body" style="width: 1000px;">
			<c:forEach var="goodsImage" items="${goodsImage}" varStatus="stat" begin="2">
			<img src="/ModuHome/images/goods/${goodsImage.IMAGE}" width="1000">
			<br>
			</c:forEach>
		</div> 
		<!--/.furniture-view-body-->
		
		<div class="cboth p_review" id="changeReviewList"> 
                        <div id="powerReview">
                           <div class="hd-t">
                        <c:if test="${sessionScope.MEMBER_ID eq null}">
                                  <h2 style="margin-bottom: 0px; font-size: 20px;">상품 후기</h2>
                                  <div class="review-write-btn" style="border: 1px solid black; background: #fff; color: black; text-align: center;">
				
									<a href="#"  data-size="md" data-label="구매 후기 작성"
				                               onClick="alert('로그인을 해주세요.'); return false;">후기 작성하기</a>
				                                  </div>
                         </c:if> 
           				<c:if test="${sessionScope.MEMBER_ID ne null and checkBuy ne goodsBasic.GOODS_NUMBER}">
            				<h2 style="margin-bottom: 0px; font-size: 20px;">상품 후기</h2>
		                    <div class="review-write-btn" style="border: 1px solid black; background: #fff; color: black; text-align: center;">
		
							<a href="#"  data-size="md" data-label="구매 후기 작성"
		                                    onClick="alert('구매후 작성 가능합니다.'); return false;">후기 작성하기</a></div>
           				</c:if> 
                              <c:if test="${sessionScope.MEMBER_ID ne null and checkBuy eq goodsBasic.GOODS_NUMBER}">
                                       <h2 style="margin-bottom: 0px; font-size: 20px;">상품 후기</h2>
                                       
                        	<div class="review-write-btn" style="border: 1px solid black; background: #fff; color: black; text-align: center;">
                           <a href="/ModuHome/review/reviewForm?GOODS_NUMBER=${goodsBasic.GOODS_NUMBER}" 
                           data-toggle="modal" data-target="#myModal">후기 작성하기</a>
                            </div>
                              </c:if> 
                              
                           </div>     
                           
                           <div id="listPowerReview" class="MS_power_review_list">
                           <c:forEach var="goodsReview" items="${reviewList}" varStatus="stat">
                           <c:if test="${reviewEndPagingNum >= stat.count}">
                           <c:if test="${reviewStartPagingNum < stat.count}">
                           <ul class="PR15N01-review-wrap">
                                 <li id="power_review_block20180711112229"
                                    class="power-review-list-box">
                                 <div class="hd-box">
                                    <ul class="desc">
                                    <li class="pr-list-writer">${goodsReview.MEMBER_NAME}</li><br>
                                    <li class="pr-list-writer"><fmt:formatDate value="${goodsReview.REVIEW_REGDATE}" pattern="YYYY-MM-dd HH:mm" /></li>
                                    </ul>
                                   <div class="star-icon">
	                                    <span class="star">
	                                   	<c:if test="${goodsReview.REVIEW_SCORE == 20 }">
										<font color="#FFBF00" size="5">★</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 40 }">
										<font color="#FFBF00" size="5">★★</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 60 }">
										<font color="#FFBF00" size="5">★★★</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 80 }">
										<font color="#FFBF00" size="5">★★★★</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 100 }">
										<font color="#FFBF00" size="5">★★★★★</font>
										</c:if>
	                                    </span>
	                                    </div> 
                                 </div><!-- hd-box -->
                           <span class="pr-options" style="display: none;">${goodsReview.REVIEW_CONTENT}</span>   
                           <div class="PR15N01-hd">
                           <h2>${goodsReview.REVIEW_TITLE }</h2>
                           </div>
                           <div class="content">
                              <p class="content_p"><a class="more-options">${goodsReview.REVIEW_CONTENT }</a></p><br>
                              <c:if test="${goodsReview.REVIEW_IMAGE ne null }">
                                       <img class="imgScale" src="/ModuHome/images/review/${goodsReview.REVIEW_IMAGE}" width="100" height="100">
                                       </c:if>
                           <div>
                                                   
                             <c:if test="${goodsReview.MEMBER_NUMBER eq sessionScope.MEMBER_NUMBER }">
												<c:url var="viewURL" value="/reviewDelete">
													<c:param name="REVIEW_NUMBER" value="${goodsReview.REVIEW_NUMBER}" />
													<c:param name="DETAIL" value="1" />
													<c:param name="GOODS_NUMBER" value="${goodsBasic.GOODS_NUMBER}" />
													<%-- <c:param name="REVIEW_IMAGE" value="${review.REVIEW_IMAGE }" /> --%>
							  					</c:url>
							 <a href="${viewURL}" class="delete" onclick="javascript:return confirm('삭제하시겠습니까?');" style="float:right">&nbsp;&nbsp;[삭제]</a>
							  </c:if> 
                           </div><!-- ctr -->
                           </div>
                                 </li>
                           </ul>
                           </c:if>
                           </c:if>
                           </c:forEach>
                           
                           </div>
                           </div>
                           
                           
                           <c:if test="${reviewSize gt 5 }">
                           <div style="text-align:center; padding:20px;">
	                           <c:if test="${reviewNowPage ne 1 }">
	                           <a class="pg_prev" style="margin-top: -8px;" href="javascript:ajaxReviewPaging(1,${reviewEndPagingNum},${reviewStartPagingNum},${reviewNowPage});">prev</a>
	                           </c:if>
                              <span class="pg_current" >${reviewNowPage}</span>
                              <c:if test="${reviewNowPage ne 1 }">
                              <span class="pg_page" >${reviewTotalPage}</span>   
                              </c:if>
	                           <c:if test="${reviewNowPage ne reviewTotalPage }">
	                           <a class="pg_next" style="margin-top: -8px;" href="javascript:ajaxReviewPaging(2,${reviewEndPagingNum},${reviewStartPagingNum},${reviewNowPage});">next</a>
	                           </c:if>
                           </div>
                           </c:if>
					</div>
		<!-- qna 상품문의 -->
		<div class="product-view-qna-list section box-shadow" id="changeQnaList" style="width:100%;">
			<div class="furniture-qna1">
		 	<div class="commerce-title1" style="padding:5px;"> 
		 			<h2 style="margin-bottom: 0px;">Q&A</h2>
		 			
					<div class="qna-wrapper">
						<c:if test="${sessionScope.MEMBER_ID eq null}">
                                       <div class="review-write-btn" style="float:right; border: 1px solid black;">
                                 <a href="#"  data-size="md" data-label="상품 문의 작성"
                                    onClick="alert('로그인을 해주세요.'); return false;">QNA 작성하기
                                 </a></div>
                              </c:if> 
                               <c:if test="${sessionScope.MEMBER_ID ne null }">
                                 <div class="review-write-btn" style="float:right; border: 1px solid black;">
                                <a href="/ModuHome/qna/modal_qnaForm?GOODS_NUMBER=${goodsBasic.GOODS_NUMBER}" data-toggle="modal" data-target="#myModal">QNA 작성하기</a>
                                  </div>
                              </c:if> 
					</div>
				</div>
		 			<hr style="color:#99999; width:100%; padding:5px;">
			</div>
               <div class="section-body">
                  <ul class="list-dropdown">
                     <c:forEach var="goodsQna" items="${qnaList}" varStatus="stat">
                     <c:if test="${qnaEndPagingNum >= stat.count}">
                     <li>
                         <ul style="float:right; display:inline-block; list-style:none;">
                         <li class="author">${goodsQna.MEMBER_NAME}</li>
                         <li class="date">${goodsQna.QNA_REGDATE}</li>
                         <li>
                         		<c:if test="${goodsQna.MEMBER_NUMBER eq sessionScope.MEMBER_NUMBER }">
									<c:url var="viewURL" value="/qnaDelete">
										<c:param name="QNA_NUMBER" value="${goodsQna.QNA_NUMBER}" />
										<c:param name="DETAIL" value="1" />
										<c:param name="GOODS_NUMBER" value="${goodsBasic.GOODS_NUMBER}" />
									</c:url>
								    <p style="float:right;"><a href="${viewURL}" style="float:right;" class="delete" onclick="return delchk()">[삭제]</a></p>
					  			</c:if> 
                         </li>
                         </ul>
                        <!-- 질문자 제목 -->
                        	<div>
                           <strong class="title">${goodsQna.QNA_TITLE}</strong>
                        	</div>
                        <!-- 질문자내용 -->
                        <div>
                                 <p style="width:60%;">${goodsQna.QNA_CONTENT}</p>
                        </div>
                             <%--   
	                              <div>
	                                  <c:if test="${goodsQna.QNA_IMAGE ne null }">
	                                       <img src="/ModuHome/images/qna/${goodsQna.QNA_IMAGE }" width="100" height="100">
	                                       </c:if>
	                              </div> --%>
                        <!-- 답변내용 -->
                        <c:if test="${goodsQna.QNA_REPCONTENT ne null}">
                           <div class="answer" style="margin-left: 30px;">
                              <p> <strong>
                              ┗관리자:
                              </strong>${goodsQna.QNA_REPCONTENT}</p>
                              <div class="info">
                                 <p class="date">${goodsQna.QNA_REPDATE}</p>
                              </div>
                           </div> 
                           <hr>
                        </c:if>
                  
                     </li>
                     </c:if>
                     </c:forEach> 
                  </ul>
                        </div>
                        
                        
				<c:if test ="${qnaSize gt 5}">
                  <div style="text-align:center;">
                     <c:if test="${qnaNowPage ne 1 }">
                     <a class="pg_prev" style="margin-top: -8px;" href="javascript:ajaxQnaPaging(1,${qnaEndPagingNum},${qnaStartPagingNum},${qnaNowPage});">-</a>
                     </c:if>   
                        <span class="pg_current">${qnaNowPage}</span>
                        <c:if test="${qnaNowPage ne 1 }">
                        <span class="pg_page">
                        ${qnaTotalPage}   
                        </span>
                        </c:if>   
                     <c:if test="${qnaNowPage ne qnaTotalPage}">   
                     <a class="pg_next" style="margin-top: -8px;" href="javascript:ajaxQnaPaging(2,${qnaEndPagingNum},${qnaStartPagingNum},${qnaNowPage});" >-</a>
                     </c:if>
                     </div>
                  </c:if>  
        	 </div>  
			<c:if test="${not empty relatedGoods}">
			<div class="furniture-together" style="width: 100%;">
				<div class="commerce-title">
					<h2>추천 상품</h2>
				</div>
				<div id="furniture-together-list"
					class="row furniture-item-group-3 furniture-related-wrapper" style="width: 100%;">
					<c:forEach items="${relatedGoods}" var="relatedGoods">
					<div class="furniture-slick-item">
						<a href="/ModuHome/goods/detail?GOODS_NUMBER=${relatedGoods.GOODS_NUMBER }"> <img
							src="/ModuHome/images/goods/${relatedGoods.GOODS_THUMBNAIL}" />
						</a>
						<div class="description">
							<div class="name"><a href="/ModuHome/goods/detail?GOODS_NUMBER=${relatedGoods.GOODS_NUMBER }">${relatedGoods.GOODS_NAME }</a></div>
							<div class="price_original">
								<s><fmt:formatNumber value="${relatedGoods.GOODS_PRICE }" type="number"/>원</s>
							</div>
							<div class="price_discount">
								<span><fmt:formatNumber value="${relatedGoods.GOODS_DISPRICE }" type="number"/></span>원 
								(<fmt:formatNumber value="${(relatedGoods.GOODS_PRICE - relatedGoods.GOODS_DISPRICE)*100 / relatedGoods.GOODS_PRICE}" type="number"/>%)
							</div>
						</div>
					</div>
					</c:forEach>
					</div>
				</div>
				</c:if>
</div>
	<br />
	<br />
<!-- modal trigger 삭제금지 -->
<div class="modal fade bs-example-modal-sm mymodal" id="myModal" tabindex="-1" role="dialog" 
aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm" style="width:500px;">
	
		<div class="modal-content">
		</div>
	</div>
</div>
<!-- modal trigger 삭제금지 -->

<script>
	Stack
			.add(function() {
				FurnitureView.init("29976");
				FurnitureView
						.setUnit('{"56885-":{"unitIdx":219575,"price":20800,"name":"\ub9c8\ud638\uac00\ub2c8","count":241,"count_type":0,"status":1,"sold":1},"56886-":{"unitIdx":219576,"price":20800,"name":"\uc5d0\ubcf4\ub2c8","count":241,"count_type":0,"status":1,"sold":1}}');
				FurnitureView.setExtraUnit('[]');
				FurnitureView.setOptionStatus('{"56886-":1,"56885-":1}');
				FurnitureView
						.setOption('{"56885":{"idx":56885,"parent_idx":56884,"type":2,"name":"\ub9c8\ud638\uac00\ub2c8","price":0,"url":"","map":"","color":"#FFFFFF","parent_name":"\uceec\ub7ec"},"56886":{"idx":56886,"parent_idx":56884,"type":2,"name":"\uc5d0\ubcf4\ub2c8","price":0,"url":"","map":"","color":"#FFFFFF","parent_name":"\uceec\ub7ec"}}');
				FurnitureView.setOptionSet('[[56885,56886]]');
				FurnitureView.vars.dept = 1;
				FurnitureView.vars.outsideLink = '';
				var option_count = $(".option-item").length;

				if (option_count > 4) {
					$(".furniture-view-extra-option").css("margin-top",
							"100px");
				}
				FurnitureView.visibility = '1';
				FurnitureView.naverPay.stock = "Y";
			});

</script>

<script type="text/javascript" async
	src="//cdn-aitg.widerplanet.com/js/wp_astg_4.0.js"></script>
<script type="text/javascript"
	src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript"
	src="https://pay.naver.com/customer/js/naverPayButton.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="//cdn.ggumim.co.kr/resource/house_interior_web/ggumim-1.2.05.min.js"></script>
<script>
	var api_url = "https://api.ggumim.co.kr";
	//  쌍였던 애들 처리
	Stack.process();

	// 너무 많이 페이지 내러가면, 업 버튼 생기기
	Scrolltop.begin();

	// 갤러리!
	Naruto.begin();
</script>
<script>
function ajaxReviewPaging(pagingCheck,reviewEndPagingNum,reviewStartPagingNum,reviewNowPage) {
 var pagingReviewOnOff="ON";
 var GOODS_NUMBER='${GOODS_NUMBER}';
  $.ajax({
     url: "/ModuHome/goods/detail",
       type : "post",
       data: {"reviewNowPage":reviewNowPage,"reviewStartPagingNum":reviewStartPagingNum,
    	   "reviewEndPagingNum":reviewEndPagingNum,"pagingReviewOnOff":pagingReviewOnOff,
    	   "pagingCheck":pagingCheck,"GOODS_NUMBER":GOODS_NUMBER},
       success:function(data){
          $("#changeReviewList").html(data);
       }
    });     
}

function ajaxQnaPaging(pagingCheck,qnaEndPagingNum,qnaStartPagingNum,qnaNowPage) {
 var pagingQnaOnOff="ON";
 var GOODS_NUMBER='${GOODS_NUMBER}';
  $.ajax({
       url: "/ModuHome/goods/detail",
       type : "post",
       data: {"qnaNowPage":qnaNowPage,"qnaStartPagingNum":qnaStartPagingNum,"qnaEndPagingNum":qnaEndPagingNum,"pagingQnaOnOff":pagingQnaOnOff,"pagingCheck":pagingCheck,"GOODS_NUMBER":GOODS_NUMBER},
       success:function(data){
          $("#changeQnaList").html(data);
       }
    });     
} 

</script>   
	
</form>
</body>
</html>
