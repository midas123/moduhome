<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>매거진상세보기</title>
</head>
<body>
	
		<table>
		<tbody>
              
                   <table>
             
                    
                     <tr>
                    	 <td>
                           <fmt:formatDate value="${mgDetail.MG_REGDATE}" pattern="yyyy.MM.dd" />
                        </td>
                     </tr>
                     <tr>
                        <td>제목 : ${mgDetail.MG_TITLE}</td>
                     </tr>
                     
                     <tr>
                        <td>해쉬태그 : ${mgDetail.MG_HASHTAG}</td>
                     </tr>
                     
                     <tr>
                        <td>보관하기 : ${mgDetail.MG_COLLECTING}</td>
                     </tr>
                     
                     <tr>
                        <td>조회수 : ${mgDetail.MG_HITCOUNT}</td>
                     </tr>
                     
                     <tr>
                        <td>서브내용 : ${mgDetail.MG_CONTENT}</td>
                     </tr>
                    
                 </table>
                 
                 <table>
                     
                     <c:forEach items="${mgContentList}" var="mgContentList"  >
                     <c:if test="${mgDetail.MG_NUMBER eq mgContentList.MG_NUMBER}">
                     
                     
                     <tr><td><img src="/ModuHome/images/mgContent/${mgContentList.MG_IMAGE}" width="60" height="60" ></td></tr>
                     <tr><td>${mgContentList.MG_IMAGE_CONTENT}</td></tr>
                     </c:if>
                     </c:forEach>

                    </table>
		
		
					<table>
					<td colspan="3">
   					 <form name="magazinecm" action="mgCommentInsert" method="post">
        				<input type="hidden" id="mg_number" name="MG_NUMBER" value="${mgDetail.MG_NUMBER}">
       					<input type="hidden" id="member_number" name="MEMBER_NUMBER" value="${MEMBER_NUMBER}"> 
        			<input type="text" id="mg_cm_content" name="MG_CM_CONTENT" >
        			<button type="submit">댓글등록</button>
        				<c:forEach items="${mgCommentList}" var="mgCommentList" >
        					<c:if test="${mgDetail.MG_NUMBER eq mgCommentList.MG_NUMBER}">
                   				<table>
                   					<td>${mgCommentList.MEMBER_NUMBER}</td>
                   					<td>${mgCommentList.MG_CM_CONTENT}</td>
                   					<td>${mgCommentList.MG_CM_REGDATE}</td>
                   				</table>
                   			</c:if>
                   		</c:forEach>
   					</form>
   					</td>
					</table>
                 
                  
               </tbody> 
               </table>
</body>
</html>