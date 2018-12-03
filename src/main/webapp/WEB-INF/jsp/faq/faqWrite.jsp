<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  


<!DOCTYPE html>
<html lang="ko">
<head>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>


<style type="text/css">
	.a   {
		background-color: #85c8dd; color:#fff;
		text-align: center;
	}
	.b{
		background-color: #ebebeb; 
		text-align: center;
	}
</style>

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
	
    <!-- 글쓰기 폼-->
    <section class="probootstrap-section probootstrap-bg-white">
      <div class="container">
        <div class="row">
          <div class="col-md-2 probootstrap-animate"></div>

          <div class="col-md-8 probootstrap-animate">
            <form id="frm">
              <table width="100%">
                <tr>
                 	<td width="10%" class="a">제목</td>
                	<td width="15%">
	                	<select name="FAQ_CATEGORY" class="form-control">
	                		<option value='1' selected>주문/결제</option>
	                		<option value='2'>변경/취소</option>
	                		<option value='3'>상품</option>
	                		<option value='4'>배송</option>
	                		<option value='5'>교환/반품</option>
	                		<option value='6'>환불</option>
	                		<option value='7'>회원관련</option>
	                		<option value='8'>기타</option>
	                	</select>
               		</td>
                 	<td width="75%" class="b"><input type="text" class="form-control" id="FAQ_TITLE" name="FAQ_TITLE"></td>
                </tr>
                <tr>
                  <td colspan="3">
                    <textarea cols="30" rows="10" class="form-control" id="FAQ_CONTENT" name="FAQ_CONTENT" style="margin: 30px 0px 30px 0px; "></textarea>
                  </td>
                </tr>
              </table>
              <table align="center">
                <tr>
                  <td><input type="submit" class="btn btn-primary btn-lg" id="write" name="#this" value="작성하기"></td>
                  <td>&nbsp;&nbsp;</td>
                  <td><input type="button" class="btn btn-primary btn-lg" id="list" name="#this" value="목록으로"></td>
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
			$("#list").on("click", function(e) {
				e.preventDefault(); //이벤트 전파를 막기위한 코드
				fn_openBoardList();
			});
			
			$("#write").on("click", function(e) {
				e.preventDefault(); //이벤트 전파를 막기위한 코드
				fn_insertBoard();
			});
		});
		
		function fn_openBoardList() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/faqList' />");
			comSubmit.submit();
		}
		
		function fn_insertBoard() {
			var comSubmit = new ComSubmit('frm');
			comSubmit.setUrl("<c:url value='/faqWrite' />");
			comSubmit.submit();
		}
	
	</script>

</body>
</html>