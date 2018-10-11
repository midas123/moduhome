<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
 	<div class="product-view-qna-list section box-shadow" id="changeQnaList" style="width:100%;">
			<div class="furniture-qna">
		 	<div class="commerce-title" style="padding:40px;"> 
		 			<h2 style="margin-bottom: 0px;">Q&A</h2> 
					<div class="qna-wrapper">
						<c:if test="${sessionScope.MEMBER_ID eq null}">
                                       <div class="review-write-btn" style="float:right; border: 1px solid black;">
                                 <a href="#"  data-size="md" data-label="상품 문의 작성"
                                    onClick="alert('로그인을 해주세요.'); return false;">QNA 작성하기
                                 </a></div>
                              </c:if> 
                               <c:if test="${sessionScope.MEMBER_ID ne null }">
                                 <div class="review-write-btn" style="float:right; border: 1px solid black;">
                                <a href="/ModuHome/qna/modal_qnaForm?GOODS_NUMBER=${goodsBasic.GOODS_NUMBER}" data-toggle="modal" data-target="#myModal">QNA 작성하기</a>
                                  </div>
                              </c:if> 
					</div>
				</div>
			</div>
              <div id="ajax_qna_list">
               <div class="section-body">
                  <ul class="list-dropdown">
                     <c:forEach var="goodsQna" items="${qnaList}" varStatus="stat">
                     <c:if test="${qnaEndPagingNum >= stat.count}">
                     <c:if test="${qnaStartPagingNum < stat.count}">
                     <li>
                         <ul style="float:right; display:inline-block; list-style:none;">
                         <li class="author">${goodsQna.MEMBER_NAME}</li>
                         <li class="date">${goodsQna.QNA_REGDATE}</li>
                         <li>
                         		<c:if test="${goodsQna.MEMBER_NUMBER eq sessionScope.MEMBER_NUMBER }">
									<c:url var="viewURL" value="/qnaDelete">
										<c:param name="QNA_NUMBER" value="${goodsQna.QNA_NUMBER}" />
										<c:param name="DETAIL" value="1" />
										<c:param name="GOODS_NUMBER" value="${goodsBasic.GOODS_NUMBER}" />
									</c:url>
								    <p style="float:right;"><a href="${viewURL}" style="float:right;" class="delete" onclick="return delchk()">[삭제]</a></p>
					  			</c:if> 
                         </li>
                         </ul>
                        <!-- 질문자 제목 -->
                        	<div>
                           <strong class="title">${goodsQna.QNA_TITLE}</strong>
                        	</div>
                        <!-- 질문자내용 -->
                        <div>
                                 <p style="width:60%;">${goodsQna.QNA_CONTENT}</p>
                        </div>
                             <%--   
	                              <div>
	                                  <c:if test="${goodsQna.QNA_IMAGE ne null }">
	                                       <img src="/ModuHome/images/qna/${goodsQna.QNA_IMAGE }" width="100" height="100">
	                                       </c:if>
	                              </div> --%>
                        <!-- 답변내용 -->
                        <c:if test="${goodsQna.QNA_REPCONTENT ne null}">
                           <div class="answer" style="margin-left: 30px;">
                              <p> <strong>
                              ┗관리자:
                              </strong>${goodsQna.QNA_REPCONTENT}</p>
                              <div class="info">
                                 <p class="date">${goodsQna.QNA_REPDATE}</p>
                              </div>
                           </div> 
                           <hr>
                        </c:if>
                  
                     </li>
                     </c:if>
                     </c:if>
                     </c:forEach> 
                  </ul>
                        </div>
                        
                  
                  
                  
				<c:if test ="${qnaSize gt 5}">
                  <div style="text-align:center;">
                     <c:if test="${qnaNowPage ne 1 }">
                     <a class="pg_prev" style="margin-top: -8px;" href="javascript:ajaxQnaPaging(1,${qnaEndPagingNum},${qnaStartPagingNum},${qnaNowPage});">-</a>
                     </c:if>   
                        <span class="pg_current">${qnaNowPage}</span>
                       <c:if test="${qnaNowPage ne 1 && qnaNowPage ne qnaTotalPage}">
								<span class="pg_page">
                              Of
                              </span>
                        <span class="pg_page">${qnaTotalPage}</span>
                        </c:if>   
                     <c:if test="${qnaNowPage ne qnaTotalPage}">   
                     <a class="pg_next" style="margin-top: -8px;" href="javascript:ajaxQnaPaging(2,${qnaEndPagingNum},${qnaStartPagingNum},${qnaNowPage});" >-</a>
                        
                     </c:if>
                     </div>
                  </c:if>  
                  
                  
            </div>
         </div>
         </div>
</body>
</html>