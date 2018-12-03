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

	<form id="frm">
		<table class="board_view">
			<colgroup>
				<col width="15%"/>
				<col width="35%"/>
				<col width="15%"/>
				<col width="35%"/>
			</colgroup>
			<caption>게시글 상세</caption>
			
			<tbody>
				<tr>
					<th scope="row">글 번호</th>
					<td>
						${map.NOTICE_NUMBER }
						<input type="hidden" id="NOTICE_NUMBER" name="NOTICE_NUMBER" value="${map.NOTICE_NUMBER }">
					</td>
					<th scope="row">조회수</th>
					<td>${map.NOTICE_HITCOUNT }</td>
				</tr>
				<tr>
					<th scope="row">작성자</th>
					<td>관리자</td>
					<th scope="row">작성시간</th>
					<td>${map.NOTICE_REGDATE }</td>
				</tr>
				<tr>
					<th scope="row">제목</th>
					<td colspan="3">
						<input type="text" id="NOTICE_TITLE" name="NOTICE_TITLE" class="wdb_90" value="${map.NOTICE_TITLE }"/>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<textarea rows="20" cols="100" title="내용" id="NOTICE_CONTENT" name="NOTICE_CONTENT">${map.NOTICE_CONTENT }</textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	
	<a href="#this" class="btn" id="list">목록으로</a>
	<a href="#this" class="btn" id="update">저장하기</a>
	<a href="#this" class="btn" id="delete">삭제하기</a>


<form id="commonForm" name="commonForm"></form>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#list").on("click", function(e){
				e.preventDefault();
				fn_openBoardList();
			});
			
			$("#update").on("click", function(e){
				e.preventDefault();
				fn_updateBoard();
			});
			
			$("#delete").on("click", function(e){
				e.preventDefault();
				fn_deleteBoard();
			});
			
		});
		
		function fn_openBoardList() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/noticeList'/>");
			comSubmit.submit();
		}
		
		function fn_updateBoard() {
			var comSubmit = new ComSubmit("frm");
			comSubmit.setUrl("<c:url value='/noticeModify'/>");
			comSubmit.submit();
		}
		
		function fn_deleteBoard() {
			var comSubmit = new ComSubmit();
			var index = confirm("정말로 삭제하시겠습니까?");
			if(index == true) {
				comSubmit.setUrl("<c:url value='/noticeDelete'/>");
				comSubmit.addParam("NOTICE_NUMBER",$("#NOTICE_NUMBER").val());
				comSubmit.submit();				
			}
			else {
				comSubmit.setUrl("<c:url value='/noticeDetail'/>");
				comSubmit.addParam("NOTICE_NUMBER",$("#NOTICE_NUMBER").val());
				comSubmit.submit();				
			}

		}
	</script>	

</body>
</html>