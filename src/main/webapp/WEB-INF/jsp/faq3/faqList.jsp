<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  


<!DOCTYPE html>
<html lang="ko">
<head>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>
</head>
<body>

    <section class="flexslider">
      <ul class="slides">
        <li style="background-image: url(/ModuHome/style/img/slider_1.jpg)" class="overlay">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
                <div class="probootstrap-slider-text text-center">
                  <h1 class="probootstrap-heading">Contact Us</h1>
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
          <div class="col-md-2 probootstrap-animate"></div>

          <div class="col-md-8 probootstrap-animate">
            <form action="#" method="post" class="probootstrap-form">


              <div class="faq">
                <ul>
                  <li id="faq" class="list db_select">
                    <div class="db_title">
                      <span>변경/취소&nbsp;&nbsp;</span>
                      주문을 여러번 했는데, 묶음으로 받고싶어요
                    </div>
                    <div class="db_detail" style="display: block;">
                      <p>데일리라이크는 평일 오전 9시에 당일 출고가 마감됩니다. 이미 배송 작업이 진행된 이후에는 고객님께서 요청을 주셔도 묶음배송이 불가하오니, 이 점 깊은 양해 부탁드립니다.</p>
                      <p>니다. 이미 배송 작업이 진행된 이후에는 고객님께서 요청을 주셔도 묶음배송이 불가하오니, 이 점 깊은 양해 부탁드립니다.</p>
                    </div>
                  </li>

                  <li id="faq" class="list db_select">
                    <div class="db_title">
                      <span>변경/취소&nbsp;&nbsp;</span>
                      주문을 여러번 했는데, 묶음으로 받고싶어요
                    </div>
                    <div class="db_detail" style="display: none;">
                      <p>데일리라이크는 평일 오전 9시에 당일 출고가 마감됩니다. 이미 배송 작업이 진행된 이후에는 고객님께서 요청을 주셔도 묶음배송이 불가하오니, 이 점 깊은 양해 부탁드립니다.</p>
                      <p>니다. 이미 배송 작업이 진행된 이후에는 고객님께서 요청을 주셔도 묶음배송이 불가하오니, 이 점 깊은 양해 부탁드립니다.</p>
                    </div>
                  </li>

                  <li id="faq" class="list db_select">
                    <div class="db_title">
                      <span>변경/취소&nbsp;&nbsp;</span>
                      주문을 여러번 했는데, 묶음으로 받고싶어요
                    </div>
                    <div class="db_detail" style="display: none;">
                      <p>데일리라이크는 평일 오전 9시에 당일 출고가 마감됩니다. 이미 배송 작업이 진행된 이후에는 고객님께서 요청을 주셔도 묶음배송이 불가하오니, 이 점 깊은 양해 부탁드립니다.</p>
                      <p>니다. 이미 배송 작업이 진행된 이후에는 고객님께서 요청을 주셔도 묶음배송이 불가하오니, 이 점 깊은 양해 부탁드립니다.</p>
                    </div>
                  </li>


                </ul>
              </div>

              <table align="center">
                <tr>
                  <td><input type="button" class="btn btn-primary btn-lg" id="write" name="#this" value="글쓰기"></td>
                </tr>
              </table>
            </form>
          </div>

          <div class="col-md-2 probootstrap-animate"></div>

        </div>
      </div>
    </section> 
	
	
	
<form id="commonForm" name="commonForm"></form>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#write").on("click", function(e){
				e.preventDefault();
				fn_openBoardWrite();
			});
			
			$("a[name='NOTICE_TITLE']").on("click", function(e){
				e.preventDefault();
				fn_openBoardDetail($(this));
			});
		});
		
		function fn_openBoardWrite() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/noticeWriteForm'/>");
			comSubmit.submit();
		}
		
		function fn_openBoardDetail(obj) {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/noticeDetail'/>");
			comSubmit.addParam("NOTICE_NUMBER", obj.parent().find("#NOTICE_NUMBER").val());
			comSubmit.submit();
		}
	
	</script>
	
</body>
</html>