<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>매거진타이틀등록</title>
</head>
<body>
	<form name="mgInsert" action="mgTitleInsert" method="post" enctype="multipart/form-data">
		<!-- <input type="hidden" name="mode" value="login"> 
	<input type="hidden" name="viewName" value="${viewName}"> -->

		<div class="input">
			
			<div class="mg_title">
				<input type="text" id="mg_title" name="MG_TITLE" required="" class="xx-control" placeholder="내용">제목
			</div>
			<div class="mg_content">
				<input type="text" id="mg_content" name="MG_CONTENT" required="" class="xx-control" placeholder="내용">서브내용
			</div>
			<div class="member_number">
				<input type="hidden" id="member_number" name="MEMBER_NUMBER" value="${MEMBER_NUMBER}"> 
			</div>
			<div class="mg_hashtag">
				<input type="text" id="mg_hashtag" name="MG_HASHTAG" required="" class="xx-control" placeholder=해쉬태그">해쉬태그
			</div>
			<div class="mg_title_image">
				<input type="file" id="mg_title_image" name="MG_TITLE_IMAGE">메인사진
			</div>
			
		</div>

		<td>
		   <SELECT id="mg_type" name="MG_TYPE" size=1>
                   <OPTION value=''>주거형태</OPTION>
                   <OPTION value='아파트'>아파트</OPTION>
                   <OPTION value='주택'>주택</OPTION>
                   <OPTION value='빌라'>빌라</OPTION>
                   <OPTION value='원룸'>원룸</OPTION>
                   <OPTION value='이색공간'>이색공간</OPTION>
                   <OPTION value='오피스텔'>오피스텔</OPTION>
           </SELECT>
        </td>
        <td>           
           <SELECT id="mg_style" name="MG_STYLE" size=1>
                   <OPTION value=''>스타일</OPTION>
                   <OPTION value='러블리'>러블리</OPTION>
                   <OPTION value='유니크'>유니크</OPTION>
                   <OPTION value='네추럴'>네추럴</OPTION>
                   <OPTION value='DIY'>DIY</OPTION>
                   <OPTION value='북유럽'>북유럽</OPTION>
                   <OPTION value='빈티지'>빈티지</OPTION>
                   <OPTION value='모던'>모던</OPTION>
                   <OPTION value='럭셔리'>럭셔리</OPTION>
                   <OPTION value='프렌치'>프렌치</OPTION>
                   <OPTION value='인더스트리얼'>인더스트리얼</OPTION>
                   <OPTION value='미니멀라이즈'>미니멀라이즈</OPTION>
           </SELECT>
       </td>
          <td>           
           <SELECT id="mg_space" name="MG_SPACE" size=1>
                   <OPTION value=''>공간</OPTION>
                   <OPTION value='거실'>거실</OPTION>
                   <OPTION value='주방'>주방</OPTION>
                   <OPTION value='침실'>침실</OPTION>
                   <OPTION value='서재'>서재</OPTION>
                   <OPTION value='베란다'>베란다</OPTION>
                   <OPTION value='현관'>현관</OPTION>
                   <OPTION value='욕실'>욕실</OPTION>
                   <OPTION value='드레스룸'>드레스룸</OPTION>
                   <OPTION value='원룸'>원룸</OPTION>
            </SELECT>
       </td>
            <td>           
           <SELECT id="mg_average" name="MG_AVERAGE" size=1>
                   <OPTION value=''>평수</OPTION>
                   <OPTION value='10평미만'>10평미만</OPTION>
                   <OPTION value='10평대'>10평대</OPTION>
                   <OPTION value='20평대'>20평대</OPTION>
                   <OPTION value='30평대'>30평대</OPTION>
                   <OPTION value='40평대'>40평대</OPTION>
                   <OPTION value='50평대'>50평이상</OPTION>
            </SELECT>
       </td>
<!-- 	MG_TYPE  주거형태/아파트/주택/빌라/원룸/이색공간/오피스텔
  	MG_STYLE  스타일/ 러블리/ 유니크/ 네추럴/ DIY/ 북유럽/빈티지/모던/ 럭셔리/ 프렌치/ 인더스트리얼/ 미니멀라이즈
  	MG_SPACE 공간/ 거실/ 주방/ 침실/ 서재/ 베란다/ 현관/ 욕실/ 드레스룸/ 원룸
  	MG_AVERAGE 평수/ 10평미만/ 20평대/ 30평대/ 40평대/ 50평이상 -->
		<button type="submit" class="button">
			<span>글쓰기</span>
		</button>
	</form>
		
		<table>
		<tbody>
				
                  <%--  <c:forEach items="${snsList}" var="snsList">
                   
                   
                   <table>
                   <form>
                     <tr>
                        <td>${snsList.SNS_NUMBER }</td>
                        <td>${snsList.MEMBER_NUMBER }</td>
                        <td>${snsList.SNS_TITLE }</td>
                       	<td>
                           <fmt:formatDate value="${snsList.SNS_REGDATE}" pattern="yyyy.MM.dd" />
                        </td>
                   		<td>
                   		<div>
                   		<c:if test="${snsList.SNS_IMAGE eq null}">
                   		<td>${snsList.SNS_NUMBER}</td>
                   		</c:if>
                   		<c:if test="${snsList.SNS_IMAGE ne null}">
                   		   <img src="/test/file/snsFile/${snsList.SNS_IMAGE}" width="60" height="60" >${snsList.SNS_NUMBER}</td>
                   		</c:if>
                   		</td>
                   		</div>
                        <td>${snsList.SNS_CONTENT}</td>
                        <td><a href="/MODA/login/findForm">좋아요</a></td>
                     </tr>
                     </form>
                     </div>
                    </table>
		
					<table>
					<td colspan="3">
   					 <form name="cm" action="snsboardCM" method="post">
        				<input type="hidden" id="sns_number" name="SNS_NUMBER" value="${snsList.SNS_NUMBER}">
        				<!-- 세션 아이디 --> 
       					<input type="hidden" id="member_number" name="MEMBER_NUMBER" value="4"> 
        			<input type="text" id="sns_cm_content" name="SNS_CM_CONTENT" >
        			<button type="submit">댓글등록</button>
        			
        				<c:forEach items="${reviewList2}" var="reviewList2" >
        					<c:if test="${snsList.SNS_NUMBER eq reviewList2.SNS_NUMBER}">
                   				<table>
                   					<td>${reviewList2.MEMBER_NUMBER}</td>
                   					<td>${reviewList2.SNS_CM_CONTENT}</td>
                   					<td>${reviewList2.SNS_CM_REGDATE}</td>
                   				</table>
                   			</c:if>
                   		</c:forEach>
   					</form>
   					</td>
					</table>
                  </c:forEach> --%>
                  
               </tbody> 
               </table>
</body>
</html>