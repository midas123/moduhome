<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>통합검색</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Free Bootstrap Theme by uicookies.com">
    <meta name="keywords" content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">
    
    <link href="https://fonts.googleapis.com/css?family=Bellefair|Open+Sans" rel="stylesheet">
    <link rel="stylesheet" href="/ModuHome/style/css/styles-merged.css">
    <link rel="stylesheet" href="/ModuHome/style/css/style.min.css">

 <!--  <script src="/ModuHome/search/js/scripts.min.js"></script>
  <script src="/ModuHome/search/js/main.min.js"></script>
  <script src="/ModuHome/search/js/custom.js"></script> -->

</head>
<body>

<!-- <div class="col-md-12" style="background-color:#85C8DD; height: 80px; width: 100%; margin: 0px 0px 150px 0px;">
</div>
 -->

<section class="flexslider">
      <ul class="slides">
        <li style="background-image: url(/ModuHome/style/img/slider_1.jpg)" class="overlay">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
              
                <div class="probootstrap-slider-text text-center" style=" margin-top:300px;">
                  <h1 class="probootstrap-heading" style="font-size: 100px;"><span><strong>MAGAZINE</strong></span></h1>
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
          <div class="col-md-12 text-center section-heading probootstrap-animate" data-animate-effect="fadeIn">
            <div class="col-md-12 probootstrap-animate" style="background: url(/ModuHome/style/img/mymain.png) no-repeat left 100%; background-size: cover; width: 100%; height: 300px;">

                <div class="col-md-4"></div>
                <div class="col-md-4">
                  <img src="/ModuHome/style/img/img_sm_3.jpg" style="border-radius: 50%; width: 150px; height: 150px; margin-top: 20px; margin-bottom: 5px;">
                  <span style="color: #212121;"><br>${sessionScope.MEMBER_NUMBER}님</span>
                </div>
                <div class="col-md-4" align="right" style="">
                  <c:if test="${follow_exist == 0 }">
                  	<img src="/ModuHome/style/img/follow_btn.png" style="width: 100px; margin: 30px 0px 0px 0px;">
                  </c:if>
                  <c:if test="${follow_exist == 1 }">
                  	<img src="/ModuHome/style/img/follow_btn.png" style="width: 100px; margin: 30px 0px 0px 0px;">
                  </c:if>
                </div>
    
              <div class="col-md-12">
                <div class="mypage-box" align="center">
                  <li style="list-style: none;">
                    <div class="col-md-3"></div>
                    <div class="col-md-3">
                      <div style="font-size: 20px; color: #212121;">팔로우</div>
                      <div>
                        <a onclick="followModal('followingList');" id="follow_quantity" style='font-family:나눔고딕,san-serif;font-size:12px;color:#212121;text-decoration:none;' >${follow_quantity }</a>
                      </div>
                    </div>  
                    <div class="col-md-3">
                      <div style="font-size: 20px; color: #212121;">팔로잉</div>
                      <div>
                        <a onclick="followModal('followerList');" id="following_quantity" style='font-family:나눔고딕,san-serif;font-size:12px;color:#212121;text-decoration:none;' >${following_quantity }</a>
                      </div>
                    </div>
                  </li>
                </div>
              </div>
            </div>

            <div class="col-md-12" style="margin:20px 0px 80px 0px;">
              <div class="col-md-3 text-center ">
                <div style="font-size:20px;"><strong>주문내역<br/></strong></div>
              </div>
              <div class="col-md-3 text-center ">
                <div style="font-size:20px;"><strong>활동내역<br/></strong></div>
              </div>
              <div class="col-md-3 text-center ">
                <div style="font-size:20px;"><strong>회원정보<br/></strong></div>
              </div>
              <div class="col-md-3 text-center ">
                <div style="font-size:20px;"><strong>고객센터<br/></strong></div>
              </div>

              
              <div style="border-bottom: 1px solid #bcbcbc; margin-bottom: 10px;"><hr></div>
            
              <div class="col-md-3 text-center ">
                <a href="/ModuHome/MyOrderList">내 주문내역<br/></a>
              </div>
              <div class="col-md-3 text-center" >
                <a href="/ModuHome/cart/cartList">장바구니<br/></a>
                <a href="/ModuHome/myStory">내 스토리<br/></a>
                <a href="/ModuHome/myCollecting">보관함<br/></a>
              </div>
              <div class="col-md-3 text-center">
                <a href="/ModuHome/MemberAuthForm">회원정보수정<br/></a>
                <a href="/ModuHome/MemberdeleteForm">탈퇴<br/></a>
              </div>    
              <div class="col-md-3 text-center">
                <a href="/ModuHome/noticeList">공지사항<br/></a>
                <a href="/ModuHome/faqList">자주묻는질문<br/></a>
              </div>
            </div>  

          </div>
      </div>
    </div>
</section>

</body>

<script type="text/javascript">
var state = "style";
var handler = null;
var isLoading = false;
var contNum = 1;
var follow_target ="";
var load_mem_id = parseInt($(".load_mem_id").attr("id"));

function followModal(state){
	if(state == 'followerList'){
		loadfollowerData();
		$("#follow_sub").text("팔로워");
		$('#follow').modal('show');
	}
	if(state == 'followingList'){
		loadfollowingData();
		$("#follow_sub").text("팔로잉");
		$('#follow').modal('show');
	}
}

function loadfollowerData() {
  var load_mem_id = parseInt($(".load_mem_id").attr("id"));
  $.ajax({
		type : 'post', 
		url : 'followerViewData',
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : 'json',
		data : JSON.stringify({
			follow : load_mem_id
		}),
		success: setfollowerData
  });
};

function loadfollowingData() {
  var load_mem_id = parseInt($(".load_mem_id").attr("id"));
  $.ajax({
		type : 'post', 
		url : 'followingViewData',
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : 'json',
		data : JSON.stringify({
			following : load_mem_id
		}),
		success: setfollowingData
  });
};

function setfollowerData(data) {

  var html = '';
  if(data != null){
		$(data).each(
			function(){
				console.log(this);
					
				html+=	"<tr>"
				    +     	"<td style='height:40px;width:20%;vertical-align:middle;align:middle;text-align:middle;'>"
				    + 			"<img src='/style/resources/images/mem_prof/"+this.prof_img+"' alt='prof_img' style='width:33px;height:31px;' class='img-circle' />"
				    + 		"</td>"
				    + 		"<td style='height:40px;width:60%;vertical-align:top;align:left;text-align:left;'>"
				    + 			"<a  href='/style/myPageLikeCol.do?mem_id="+this.follow_id+"' style='font-size:12px;font-weight:bold;color:#555555;font-family:나눔고딕;'>"+this.nickname+"</a><br>"
				    + 			"<font style='font-size:11px;color:#555555;'>"+this.intro+"</font>"
				    + 		"</td>"
				    + 		"<td style='height:40px;width:20%;vertical-align:middle;align:center;text-align:center;'>"
				    +			"<a href='#' onclick='followM(\""+this.follow_id+"\");' id='list_follow_btn"+this.follow_id+"' >"
					if(this.follow_to != 0){
					html+=			"<img src='/style/resources/images/main/followList_del.png' alt='followList_Del' width='40px'>"
					}
					if(this.follow_to == 0){
					html+=			"<img src='/style/resources/images/main/followList_reg.png' alt='followList_Reg' width='40px'>"
					}
				    html+=		"</a>"	
		   		 	+		"</td>"
				    + 	"</tr>";
			}	
		);
		  $('#follow_table').html(html);
	}
};

function setfollowingData(data) {
  var html = '';
  if(data != null){
		$(data).each(
			function(){
				console.log(this);
					
				html+=	"<tr>"
				    +     	"<td style='height:40px;width:20%;vertical-align:middle;align:middle;text-align:middle;'>"
				    + 			"<img src='/style/resources/images/mem_prof/"+this.prof_img+"' alt='prof_img' style='width:33px;height:31px;' class='img-circle' />"
				    + 		"</td>"
				    + 		"<td style='height:40px;width:60%;vertical-align:top;align:left;text-align:left;'>"
				    + 			"<a  href='/style/myPageLikeCol.do?mem_id="+this.following_id+"' style='font-size:12px;font-weight:bold;color:#555555;font-family:나눔고딕;'>"+this.nickname+"</a><br>"
				    + 			"<font style='font-size:11px;color:#555555;'>"+this.intro+"</font>"
				    + 		"</td>"
				    + 		"<td style='height:40px;width:20%;vertical-align:middle;align:center;text-align:center;'>"
				    +			"<a href='#' onclick='followM(\""+this.following_id+"\");' id='list_follow_btn"+this.following_id+"' >"
					if(this.follow_to != 0){
					html+=			"<img src='/style/resources/images/main/followList_del.png' alt='followList_Del' width='40px'>"
					}
					if(this.follow_to == 0){
					html+=			"<img src='/style/resources/images/main/followList_reg.png' alt='followList_Reg' width='40px'>"
					}
				    html+=		"</a>"	
		   		 	+		"</td>"
				    + 	"</tr>";
			}	
		);
		  $('#follow_table').html(html);
	}
};


function followM(follow_id){
  var mem_id = $(".mem_id").attr("id");
  follow_target = follow;
  $.ajax({
	  	type : 'post', 
		url : 'followDo.do',
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : 'json',
		data : JSON.stringify({
			follow : mem_id,
			following : follow
		}),
		success: followM_ok
  });
};

function followM_ok(data){
	var html = "";
	if(data == 1){
		html=	"<img src='/style/resources/images/main/followList_del.png' alt='followList_Del' width='40px'>"
	}
	if(data == 0){
		html=	"<img src='/style/resources/images/main/followList_reg.png' alt='followList_Reg' width='40px'>"
	}
	$('#list_follow_btn'+follow_target).html(html);
}

 function follow(load_mem_id){
	var mem_id = parseInt($(".mem_id").attr("id"))
	$.ajax({
		type : 'post', 
		url : 'followDo',
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : 'json',
		data : JSON.stringify({
			follow : load_mem_id,
			following : mem_id
		}),
		success: follow_ok
	});
};


function follow_ok(data){
	var html = "";
	var dual = "";
	if(data == 1){
		dual = parseInt($('#following_quantity').text())+1;
		html=	"<img src='/ModuHome/style/img/following_btn.png' alt='following_btn' style='width:100px;' />"
	}
	if(data == 0){
		dual = parseInt($('#following_quantity').text())-1;
		html=	"<img src='/ModuHome/style/img/follow_btn.png' alt='follow_btn' style='width:100px;' />"
	}

	$('#following_quantity').text(dual);
	$('#mini_follow_quantity').text(dual);
	$('#follow_btn').html(html);
}
	

</script>

</html>