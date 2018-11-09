<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>


<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Free Bootstrap Theme by uicookies.com">
<meta name="keywords"
	content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">
<link href="https://fonts.googleapis.com/css?family=Bellefair|Open+Sans"
	rel="stylesheet">
<link rel="stylesheet" href="/ModuHome/style/css/styles-merged.css">
<link rel="stylesheet" href="/ModuHome/style/css/style.min.css">
<script>
function logincheck(){
	var loginCheck= "${sessionScope.MEMBER_ID}";
	
	if(loginCheck == null || loginCheck==''){
		alert("로그인 후 보실 수 있습니다.");
		return false;
	}
}
</script>

</head>
<body>


 <section class="flexslider">
      <ul class="slides">
        <li style="background-image: url(/ModuHome/style/img/image03.jpg)" class="overlay">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
                <div class="probootstrap-slider-text text-center">
                  <h1 class="probootstrap-heading probootstrap-animate">Welcome to my home!</h1>
                </div>
              </div>
            </div>
          </div>
        </li>
        <li style="background-image: url(/ModuHome/style/img/notice_detail.jpg)" class="overlay">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
                <div class="probootstrap-slider-text text-center">
                  <h1 class="probootstrap-heading probootstrap-animate"></h1>
                </div>
              </div>
            </div>
          </div>
          
        </li>
        <li style="background-image: url(/ModuHome/style/img/main3.jpg)" class="overlay">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
                <div class="probootstrap-slider-text text-center">
                  <h1 class="probootstrap-heading probootstrap-animate"></h1>
                </div>
              </div>
            </div>
          </div>
        </li>
      </ul>
    </section>

	<section class="probootstrap-section probootstrap-bg-white">
	<div class="container">
		<div class="row">
			<div
				class="col-md-12 text-center section-heading probootstrap-animate"
				data-animate-effect="fadeIn">
				<div class="col-md-12" style="font-size: 50px; color: #212121; text-align:left; "><font size="7" face='Serif'>신상품</font></div>
				
				<div class="col-md-12 "><hr style="border:solid 1px #212121; "></div>
				
				<div class="col-md-12" align="right">
					<input type="button" onclick="location.href='goods'"
						class="btn btn-primary btn-lg" id="write" value="더보기">
				</div>

			</div>

		</div>

		<div class="row" style="width:80%; margin:0 auto;" >
			<c:forEach items="${newItem}" var="newItem" begin="0" end="5"
				step="1" varStatus="status">
				<c:url var="goodsUrl"
					value="/goods/detail?GOODS_NUMBER=${newItem.GOODS_NUMBER}" />
				<div class="col-md-4 probootstrap-animate">
					<a href="${goodsUrl}"><img
						src="/ModuHome/images/goods/${newItem.GOODS_THUMBNAIL}"
						alt="Free Bootstrap Template by uicookies.com"
						class="img-responsive"></a>
					<div class="name">
						<a href="${goodsUrl}">상품명 ${newItem.GOODS_NAME}</a>
					</div>
					<div>
						<del>
							<span class="price_original"><fmt:formatNumber
									value="${newItem.GOODS_PRICE}" /></span>
						</del>
						원
					</div>
					<span class="price_discount"><fmt:formatNumber
							value="${newItem.GOODS_DISPRICE}" /></span> <span class="unit">원</span><span>(<fmt:formatNumber
							value="${(newItem.GOODS_PRICE - newItem.GOODS_DISPRICE)*100 / newItem.GOODS_PRICE}"
							type="number" />%)
					</span>
				</div>
			</c:forEach>
		</div>
	</div>
	</section>



	<section class="probootstrap-section probootstrap-bg-white">
	<div class="container">
		<div class="row">
			<div
				class="col-md-12 text-center section-heading probootstrap-animate"
				data-animate-effect="fadeIn">
				<div class="col-md-12" style="font-size: 50px; color: #212121; text-align:left; "><font face='Serif'>매거진</font></div>
				
				<div class="col-md-12 "><hr style="border:solid 1px #212121; "></div>

				<div class="col-md-12" align="right">
					<input type="button" onclick="location.href='mglist'"
						class="btn btn-primary btn-lg" id="write" value="더보기">
				</div>

			</div>


		</div>


		<div class="row">
			<c:forEach items="${mgList}" var="mgList" begin="0" end="3" step="1"
				varStatus="status">

				<c:url var="viewURL" value="mgDetail">
					<c:param name="MG_NUMBER" value="${mgList.MG_NUMBER}" />
					<c:param name="MEMBER_NUMBER" value="${sessionScope.MEMBER_NUMBER}" />
				</c:url>
				<a href="${viewURL}" onclick="return logincheck();">

					<div class="col-md-6">
						<div class="probootstrap-service-2 probootstrap-animate" style="height:600px">
							<div class="image" style="width: 100%">
								<img src="/ModuHome/images/mgMain/${mgList.MG_TITLE_IMAGE}"
									style="width: 100%;" height="400px">
							</div>
							<div class="text" style="width: 100%">
								<input type="hidden" id="MG_NUMBER" value="${mgList.MG_NUMBER }">
								<input type="hidden" id="MEMBER_NUMBER"
									value="${sessionScope.MEMBER_NUMBER }">
									
           						<div class="col-md-12" style="color:#000000; margin-top:20px; font-size:20px;">${mgList.MG_CONTENT}</div>

								<div class="col-md-12" style="color:#85c8dd; text-align:left;">${mgList.MG_TYPE}&nbsp;
									${mgList.MG_STYLE}&nbsp; ${mgList.MG_SPACE}&nbsp;
									${mgList.MG_AVERAGE}</div>
							</div>
						</div>
					</div> 
				</a>
			</c:forEach>
		</div>
	</div>
	</section>

	<section class="probootstrap-section probootstrap-bg-white">
	<div class="container">
		<div class="row">
			<div
				class="col-md-12 text-center section-heading probootstrap-animate"
				data-animate-effect="fadeIn">
				<div class="col-md-12" style="font-size: 50px; color: #212121; text-align:left; "><font face='Serif'>갤러리</font></div>
				
				<div class="col-md-12 "><hr style="border:solid 1px #212121; "></div>

				<div class="col-md-12" align="right">
					<input type="button" onclick="location.href='gllist'"
						class="btn btn-primary btn-lg"  id="write" value="더보기">
						<!-- <a href="#this" name="MG_TITLE" style="font-size: 15px; color: #212121;"><font face='Serif'>전체보기</font></a> -->
				</div>

			</div>


		</div>




		<div class="row">

			<c:forEach items="${glList}" var="glList" begin="0" end="5" step="1"
				varStatus="status">
				<div class="col-md-4  col-sm-4">

					<div class="probootstrap-card probootstrap-listing">
						<div class="probootstrap-card-media">

							<figure itemprop="associatedMedia" itemscope
								itemtype="http://schema.org/ImageObject"
								class="grid-item probootstrap-animate"> <img
								src="/ModuHome/images/mgMain/${glList.MG_TITLE_IMAGE}"
								itemprop="thumbnail" style="width: 400px; height: 300px; margin-bottom:30px;" /> </figure>

						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>
	</section>







</body>
</html>