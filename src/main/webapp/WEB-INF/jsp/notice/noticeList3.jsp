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

	<h2>게시판 목록</h2>
	<table class="board_list">
		<colgroup>
			<col width="10%"/>
			<col width="*"/>
			<col width="15%"/>
			<col width="20%"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">글번호</th>
				<th scope="col">제목</th>
				<th scope="col">조회수</th>
				<th scope="col">작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(noticeList) > 0 }">
					<c:forEach items="${noticeList }" var="noticeList">
						<tr>
							<td>${noticeList.NOTICE_NUMBER}</td>
							<td class="title">
								<a href="#this" name="NOTICE_TITLE">${noticeList.NOTICE_TITLE }</a>
								<input type="hidden" id="NOTICE_NUMBER" value="${noticeList.NOTICE_NUMBER }">
							</td>
							<td>${noticeList.NOTICE_HITCNT }</td>
							<td>${noticeList.NOTICE_REGDATE }</td>	
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">조회된 결과가 없습니다</td>
					</tr>
				</c:otherwise>			
			</c:choose>
		</tbody>
	</table>
	<br/>
	
	<a href="#this" class="btn" id="write">글쓰기</a>
	

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