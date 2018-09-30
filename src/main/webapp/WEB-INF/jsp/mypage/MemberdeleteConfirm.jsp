<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<title>회원탈퇴</title>
</head>

<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	var check = $(".check").attr("id"); 
	if(check == "1"){
	   if(confirm("정말 회원탈퇴를 하시겠습니까?")){
		 location.href = "/ModuHome/MemberDelete";
	   }else{
	     	 location.href = "/ModuHome/";
	  }
	}else{
		alert("비밀번호를 확인해주세요.");
		location.href = "/ModuHome/MemberdeleteForm";
	}
	
	
});
</script>
<body>
<div style="display:none;" class="check" id="${check}" ></div>
</body>
</html>