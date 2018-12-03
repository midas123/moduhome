<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
function pay_update(){
 if(confirm("정말 주문취소 하시겠습니까?") == true){
	 var form = document.getElementById('myOrderfrm')
	 form.action = "/ModuHome/payUpdate"
	 form.submit();
   } else {
       return;
    }
 }
function pay_update2(){
if(confirm("구매확정 하시겠습니까?") == true){
	 var form = document.getElementById('myOrderfrm')
	 form.action = "/ModuHome/payUpdate"
	 form.submit();
}else{
   return;
}
}

</script>
<title>Insert title here</title>
</head>
<body>
${myOrderList}
========================================================================
<!-- foreach 시작-->
<br/>
<c:forEach items="${myOrderList}" var="myOrderList">

<!-- 주문내역 리스트 시작-->
상품명: ${myOrderList.GOODS_NAME}







<!-- 주문내역 리스트 끝-->
<form id="myOrderfrm" method="post">
<input type="hidden" value="멤버 넘버"/>
<input type="hidden" name="ORDER_CODE" value="${myOrderList.ORDER_CODE}"/>
<input type="hidden" name="ORDER_TOTAL_PRICE" value="${myOrderList.ORDER_TOTAL_PRICE}"/>
</form>
</c:forEach>
<!-- foreach 종료-->


<button name="flag" type="submit" value="구매확정" onclick="javascript:pay_update2();">구매확정</button>
<button name="flag" type="submit" value="구매취소" onclick="javascript:pay_update();">구매취소</button>



</body>
</html>