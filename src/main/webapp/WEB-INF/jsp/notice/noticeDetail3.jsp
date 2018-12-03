<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  


<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/ui.css'/>" />

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>
</head>
<body>

	<table class="board_view">
		<colgroup>
			<col width="15%">
			<col width="35%">
			<col width="15%">
			<col width="35%">
		</colgroup>	
		<caption>게시글 상세</caption>
		
		<tbody>
			<tr>
				<th scope="row">글 번호</th>
				<td>${map.NOTICE_NUMBER }</td>
				<th scope="row">조회수</th>
				<td>${map.NOTICE_HITCOUNT }</td>
			</tr>
			<tr>
				<th scope="row">작성자</th>
				<td>관리자</td>
				<th scope="row">작성시간</th>
				<td>${map.NOTICE_REGDATE  }</td>
			</tr>
			<tr>
				<th scope="row">제목</th>
				<td colspan="3">${map.NOTICE_TITLE }</td>
			</tr>
			<tr>
				<td colspan="4">${map.NOTICE_CONTENT }</td>
			</tr>
		</tbody>
	</table>
	
	<a href="#this" class="btn" id="list">목록으로</a>
	<a href="#this" class="btn" id="update">수정하기</a>


<form id="commonForm" name="commonForm"></form>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#list").on("click", function(e) {
				e.preventDefault();
				fn_openBoardList();
			});
			
			$("#update").on("click", function(e) {
				e.preventDefault();
				fn_openBoardUpdate();
			});
		});
		
		function fn_openBoardList() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/noticeList'/>");
			comSubmit.submit();
		}
		
		function fn_openBoardUpdate() {
			var NOTICE_NUMBER= "${map.NOTICE_NUMBER}";
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/noticeModifyForm'/>");
			comSubmit.addParam("NOTICE_NUMBER", NOTICE_NUMBER);
			comSubmit.submit();
		}
	
	</script>
	
	
	

</body>
</html>