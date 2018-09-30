<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>first</title>

</head>
<body>

<div style="display:none;" class="Session_mem_id" id="${mem_id}">
</div>

    <section class="flexslider">
      <ul class="slides">
        <li style="background-image: url(/ModuHome/style/img/slider_1.jpg)" class="overlay">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
                <div class="probootstrap-slider-text text-center">
                  <h1 class="probootstrap-heading">좋아요테스트</h1>
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
          <div class="col-md-6 col-md-offset-3 text-center section-heading probootstrap-animate">

            <div class="col-md-12 probootstrap-animate">

							<div class="btn-group">
							  
							<a href="#" class="#" data-toggle="dropdown" aria-expanded="false">
							  	<span class="my_menu">
							  		<img src="/ModuHome/style/img/arlam_img.png" alt="arlam_img" style="width:33px;height:31px;" class="img-circle" />
							  	</span>
							  	<span class="badge">
								  	<c:if test="${fn:length(sessionScope.session_mem_alarm) > 99}">
								  		99+
								  	</c:if>
								  	<c:if test="${fn:length(sessionScope.session_mem_alarm) <= 99}">
							  			${fn:length(sessionScope.session_mem_alarm)}
								  	</c:if>
							  	</span>
							</a>
							  <ul class="dropdown-menu pull-right " style="position:absolute; top:120%;max-height:200px;width:180px;overflow-y:auto;">
								  <c:forEach items="${sessionScope.session_mem_alarm }" var="alarm">
								    <li>
								    	<table style="width:100%"><tr><td style="padding:5px;border-bottom:solid 1px #e3e3e3;">
								    	
									    	<c:if test="${alarm.alarm_index_no == 1 }">
									    		<a href="alarmRead?alarm_seq=${alarm.alarm_seq }" style="text-decoration:none;color:#555555;font-size:10px;">작성한 스토리에 <strong>${alarm.alarm_reg_id }</strong> 님이 좋아요를 눌렀습니다.</a>
									    	</c:if>
									    	
									    	<c:if test="${alarm.alarm_index_no == 2 }">
									    		<a href="alarmRead?alarm_seq=${alarm.alarm_seq }" style="text-decoration:none;color:#555555;font-size:10px;"><strong>${alarm.alarm_reg_id }</strong> 님이 회원님을 팔로우 했습니다.</a>
									    	</c:if>
									    	
									    	<c:if test="${alarm.alarm_index_no == 3 }">
									    		<a href="alarmRead?alarm_seq=${alarm.alarm_seq }" style="text-decoration:none;color:#555555;font-size:10px;">작성한 스토리에 <strong>${alarm.alarm_reg_id }</strong> 님이 댓글을 달았습니다.</a>
									    	</c:if>
									    	
									    	<c:if test="${alarm.alarm_index_no == 4 }">
									    		<a href="alarmRead?alarm_seq=${alarm.alarm_seq }" style="text-decoration:none;color:#555555;font-size:10px;">내가 팔로우 한 사람이 <strong>${alarm.alarm_reg_id }</strong> 새 스토리를썼다</a>
									    	</c:if>
									    	
								    	</td></tr></table>
								  	</li>
								  </c:forEach> 
							  </ul>
							</div>
			</div>

              <a href="/ModuHome/" >메인으로</a>
          </div>
        </div>
        <!-- END row -->
        <div class="row mb40">
          <div class="col-md-6 probootstrap-animate">
            <p></p>
          </div>
          <div class="col-md-6 probootstrap-animate">
            <p></p>
          </div>
        </div>
        <!-- END row -->

      </div>
    </section>


</html>