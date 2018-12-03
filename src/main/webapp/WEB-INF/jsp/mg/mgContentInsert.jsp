<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>매거진내용등록</title>
</head>
<body>
	<form name="mgInsert" action="mgContentInsert" method="post" enctype="multipart/form-data">
		<!-- <input type="hidden" name="mode" value="login"> 
	<input type="hidden" name="viewName" value="${viewName}"> -->
		
	
		
		<div class="input">
			
			<div class="mg_number">
				<input type="hidden" id="mg_number" name="MG_NUMBER" value="${mgNew.MG_NUMBER}">
			</div>

			<div class="mg_image">
				<input type="file" id="mg_image" name="MG_IMAGE">사진
			</div>
			
			<div class="mg_image_content">
				<input type="text" id="mg_image_content" name="MG_IMAGE_CONTENT" required="" class="xx-control" placeholder="회원">내용
			</div>
			
		</div>

		<button type="submit" class="button">
			<span>글쓰기</span>
		</button>
	</form>
	
		 <c:forEach items="${mgContentList}" var="mgContentList" >
        					<c:if test="${mgNew.MG_NUMBER eq mgContentList.MG_NUMBER}">
                   				<table>
                   					<td>${mgContentList.MG_IMAGE_CONTENT}</td>
                   					<td><img src="/ModuHome/images/mgContent/${mgContentList.MG_IMAGE}" width="60" height="60" ></td>
                   			   	</table>
                   			</c:if>
           	</c:forEach>

</body>
</html>