<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>

<title>매거진</title>
</head>
<body>

    <section class="flexslider">
      <ul class="slides">
        <li style="background-image: url(img/slider_1.jpg)" class="overlay">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
                <div class="probootstrap-slider-text text-center">
                  <h1 class="probootstrap-heading">매거진</h1>
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
          <div class="col-md-6 col-md-offset-3 text-center section-heading probootstrap-animate" data-animate-effect="fadeIn">
            <h2>What We Offer</h2>
          </div>
        </div>
        
        
   <form name="magazine" action="mgInsertForm" method="post">
      <button type="submit" class="button">
         <span>매거진등록</span>
      </button>
   </form>
        <!-- END row -->

        <div class="row" align="center" style="margin-bottom: 30px">

          <select>
            <option>50평형</option>
            <option>40평형</option>
          </select>
          <select>
            <option>50평형</option>
            <option>40평형</option>
          </select>
          <select>
            <option>50평형</option>
            <option>40평형</option>
          </select>
          <select>
            <option>50평형</option>
            <option>40평형</option>
          </select> 

          <input type="submit" name="" value="검색">         
        </div>


<!--  -----------  -->

        <div class="row">


    <c:forEach items="${mgList}" var="mgList">         
      <form>
      <c:url var="viewURL" value="mgDetail">
         <c:param name="MG_NUMBER" value="${mgList.MG_NUMBER}" />  
         <c:param name="MEMBER_NUMBER" value="${sessionScope.MEMBER_NUMBER}" />          
      </c:url>


          <div class="col-md-6">
            <div class="probootstrap-service-2 probootstrap-animate">
              <div class="image" style="width: 100%">
                  <img src="/ModuHome/style/img/${mgList.MG_TITLE_IMAGE}" style="width: 100%;" height="350px">
              </div>
              <div class="text" style="width: 100%">
                <a href="#this" name="MG_TITLE">${mgList.MG_TITLE }</a>
                <input type="hidden" id="MG_NUMBER" value="${mgList.MG_NUMBER }">
                <input type="hidden" id="MEMBER_NUMBER" value="${sessionScope.MEMBER_NUMBER }">
                <h6>#아이구</h6>
              </div>
            </div>
          </div>
      </form>
    </c:forEach>          

        </div>
        

   
   
<!--  -----------------  -->
   
      </div>
    </section>

<form id="commonForm" name="commonForm"></form>
   
   <script type="text/javascript">
      $(document).ready(function() {
         $("a[name='MG_TITLE']").on("click", function(e){
            e.preventDefault();
            fn_openBoardDetail($(this));
         });
      });

      function fn_openBoardDetail(obj) {
         var comSubmit = new ComSubmit();
         comSubmit.setUrl("<c:url value='/mgDetail'/>");
         comSubmit.addParam("MG_NUMBER", obj.parent().find("#MG_NUMBER").val());
         if((obj.parent().find("#MEMBER_NUMBER").val()) != "") {
            comSubmit.addParam("MEMBER_NUMBER", obj.parent().find("#MEMBER_NUMBER").val());
         }
         else {
            comSubmit.addParam("MEMBER_NUMBER", 0);
         }
         comSubmit.submit();
      }    

   </script>

</body>
</html>