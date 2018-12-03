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
	<form name="mgmodify" action="mgModify" method="post" enctype="multipart/form-data">
		<!-- <input type="hidden" name="mode" value="login"> 
	<input type="hidden" name="viewName" value="${viewName}"> -->

		<div class="input">
			
			<div class="mg_title">
				<input type="text" id="mg_title" name="MG_TITLE" required="" class="xx-control" placeholder="${mgDetail.MG_TITLE }">제목
			</div>
			<div class="mg_content">
				<input type="text" id="mg_content" name="MG_CONTENT" required="" class="xx-control" placeholder="${mgDetail.MG_CONTENT }">서브내용
			</div>
			<div class="member_number">
				<input type="text" id="member_number" name="MEMBER_NUMBER" required="" class="xx-control" placeholder="회원">회원
			</div>
			<div class="mg_hashtag">
				<input type="text" id="mg_hashtag" name="MG_HASHTAG" required="" class="xx-control" placeholder=${mgDetail.MG_HASHTAG }">해쉬태그
			</div>
			<div class="mg_title_image">
				<input type="file" id="mg_title_image" name="MG_TITLE_IMAGE">
				메인사진
			</div>
			       	<input type="hidden" id="MG_NUMBER" name="MG_NUMBER" value="${mgDetail.MG_NUMBER}">
                   	
			
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

		<button type="submit" class="button">
			<span>수정하기</span>
		</button>
	</form>
		

</body>
</html>