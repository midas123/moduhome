<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/ModuHome/theme/aboki/power_review_custom.2.css">
<link rel="stylesheet" href="/ModuHome/theme/aboki/main.css">
</head>
<body>
	<div class="cboth p_review" id="changeReviewList"> 
                        <div id="powerReview">
                           <div class="hd-t">
                             <c:if test="${sessionScope.MEMBER_ID eq null}">
                                       <h2 style="margin-bottom: 0px; font-size: 20px;">��ǰ���� �ı�</h2>
                                       <div class="review-write-btn">
										<a href="#" data-toggle="modal" data-target="#myModal" onClick="alert('�α����� ���ּ���.'); return false;">�ı� �ۼ��ϱ�</a>
                                       </div>
                              </c:if> 
            				<c:if test="${sessionScope.MEMBER_ID ne null and checkBuy ne goodsBasic.GOODS_NUMBER}">
            				<h2 style="margin-bottom: 0px; font-size: 20px;">��ǰ���� �ı�</h2>
                    <div class="review-write-btn">
					<a href="/ModuHome/review/reviewForm?GOODS_NUMBER=${goodsBasic.GOODS_NUMBER}" data-toggle="modal" data-target="#myModal" onClick="alert('������ �ۼ� �����մϴ�.'); return false;">�ı� �ۼ��ϱ�</a></div>
            				</c:if> 
                              <c:if test="${sessionScope.MEMBER_ID ne null and checkBuy eq goodsBasic.GOODS_NUMBER}">
                                       <h2 style="margin-bottom: 0px; font-size: 20px;">��ǰ���� �ı�</h2>
                        	<div class="review-write-btn">
                           <a href="/ModuHome/review/reviewForm?GOODS_NUMBER=${goodsBasic.GOODS_NUMBER}" data-toggle="modal" data-target="#myModal">�ı� �ۼ��ϱ�</a>
                              </div>
                              </c:if>
                           </div>     
                           
                           <div id="listPowerReview" class="MS_power_review_list">
                           <c:forEach var="goodsReview" items="${reviewList}" varStatus="stat">
                           <c:if test="${reviewEndPagingNum >= stat.count}">
                           <c:if test="${reviewStartPagingNum < stat.count}">
                           <ul class="PR15N01-review-wrap">
                                 <li id="power_review_block20180711112229"
                                    class="power-review-list-box">
                                 <div class="hd-box">
                                    <ul class="desc">
                                    <li class="pr-list-writer">�ۼ��� : ${goodsReview.MEMBER_NAME}</li><br>
                                    <li class="pr-list-writer"><fmt:formatDate value="${goodsReview.REVIEW_REGDATE}" pattern="YYYY-MM-dd HH:mm" /></li>
                                    </ul>
                                   <div class="star-icon">
	                                    <span class="star">
	                                   	<c:if test="${goodsReview.REVIEW_SCORE == 20 }">
										<font color="#FFBF00" size="5">��</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 40 }">
										<font color="#FFBF00" size="5">�ڡ�</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 60 }">
										<font color="#FFBF00" size="5">�ڡڡ�</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 80 }">
										<font color="#FFBF00" size="5">�ڡڡڡ�</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 100 }">
										<font color="#FFBF00" size="5">�ڡڡڡڡ�</font>
										</c:if>
	                                    </span>
	                                    </div> 
                                 </div><!-- hd-box -->
                           <span class="pr-options" style="display: none;">${goodsReview.REVIEW_CONTENT}</span>   
                           <div class="PR15N01-hd">
                           <h2>���� : ${goodsReview.REVIEW_TITLE }</h2>
                           </div>
                           <div class="content">
                              <p class="content_p"><a class="more-options">���� : ${goodsReview.REVIEW_CONTENT }</a></p>
                           <div class="ctr">
                           <div class="photo-list">
                                 <ul>
                                    <li>
                                       <div><span></span>
                                       <c:if test="${goodsReview.REVIEW_IMAGE ne null }">
                                       <img src="/ModuHome/images/review/${goodsReview.REVIEW_IMAGE}">
                                       </c:if>
                                       </div>
                                    <div class="attach-preview"></div></li>                                    
                                 </ul>
                           </div>   
                                                   
                             <c:if test="${goodsReview.MEMBER_NUMBER eq sessionScope.MEMBER_NUMBER }">
												<c:url var="viewURL" value="/reviewDelete">
													<c:param name="REVIEW_NUMBER" value="${goodsReview.REVIEW_NUMBER}" />
													<c:param name="DETAIL" value="1" />
													<c:param name="GOODS_NUMBER" value="${goodsBasic.GOODS_NUMBER}" />
													<%-- <c:param name="REVIEW_IMAGE" value="${review.REVIEW_IMAGE }" /> --%>
							  </c:url>
							 <a href="${viewURL}" class="delete" onclick="javascript:return confirm('�����Ͻðڽ��ϱ�?');">&nbsp;&nbsp;[����]</a>
							  </c:if> 
                              
                           </div><!-- ctr -->
                           </div><!-- content -->

                                 </li><!-- power-review-list-box -->
                           </ul><!-- PR15N01-review-wrap -->
                           </c:if>
                           </c:if>
                           </c:forEach>
                           <c:if test="${reviewSize gt 5 }">
                           <div style="text-align:center;">
                           <c:if test="${reviewNowPage ne 1 }">
                           <a class="pg_prev" style="margin-top: -8px;" href="javascript:ajaxReviewPaging(1,${reviewEndPagingNum},${reviewStartPagingNum},${reviewNowPage});"></a>
                           </c:if>
                              <span class="pg_current">${reviewNowPage}</span>
                          <%--     <c:if test="${reviewNowPage ne 1 }">
                              <span class="pg_page">
                              ${reviewTotalPage}</span>   
                              </c:if> --%>
                              
                              <c:if test="${reviewNowPage ne 1 && reviewNowPage ne reviewTotalPag}">
						<span class="pg_page">
                              Of
                              </span>
						<span class="pg_page">${reviewTotalPage}</span>
					</c:if>
                              
                           <c:if test="${reviewNowPage ne reviewTotalPage }">
                           <a class="pg_next" style="margin-top: -8px;" href="javascript:ajaxReviewPaging(2,${reviewEndPagingNum},${reviewStartPagingNum},${reviewNowPage});"></a>
                           </c:if>
                           </div>
                           </c:if>
                           </div>
                           </div><!-- listPowerReview -->
					</div>


<%-- 	<div class="space15 mt10 cboth p_review" id="changeReviewList">
		<div id="powerReview">
			<div class="furniture-review">
				<div class="mt18"></div>
				<div class="furniture-review-title">
					<c:if test="${sessionScope.MEMBER_ID eq null}">
					<h2 style="margin-bottom: 0px; font-size: 20px;">��ǰ���� �ı�</h2>
						<div class="review-write-btn">
							<a href="#" data-size="md" data-label="���� �ı� �ۼ�"
								onClick="alert('�α����� ���ּ���.'); return false;">�ı� �ۼ��ϱ�</a> <a
								href="#" data-toggle="modal" data-target="#myModal">�����ı�
								�ۼ��ϱ�</a>
						</div>
					</c:if>
					<c:if
						test="${sessionScope.MEMBER_ID ne null and checkBuy ne GOODS_NUMBER}">
						<h2 style="margin-bottom: 0px; font-size: 20px;">��ǰ���� �ı�</h2>
						<div class="review-write-btn">
							<a href="#" data-size="md" data-label="���� �ı� �ۼ�"
								onClick="alert('������ �ۼ� �����մϴ�.'); return false;">�ı� �ۼ��ϱ�</a>
						</div>
						<a
							href="/ModuHome/review/reviewForm?GOODS_NUMBER=${GOODS_NUMBER}"
							data-toggle="modal" data-target="#myModal">�ı� �ۼ��ϱ�</a>
					</c:if>
					<c:if
						test="${sessionScope.MEMBER_ID ne null and checkBuy eq GOODS_NUMBER}">
						<h2 style="margin-bottom: 0px; font-size: 20px;">��ǰ���� �ı�</h2>
						<div class="review-write-btn">
							<a
								href="/ModuHome/review/reviewForm?GOODS_NUMBER=${GOODS_NUMBER}"
								data-toggle="modal" data-target="#myModal">�ı� �ۼ��ϱ�</a>

						</div>
					</c:if>
				</div>
			</div>
		</div>

		<div id="listPowerReview" class="MS_power_review_list">
			  <c:forEach var="goodsReview" items="${reviewList}" varStatus="stat">
                           <c:if test="${reviewEndPagingNum >= stat.count}">
                           <c:if test="${reviewStartPagingNum < stat.count}">
                           <ul class="PR15N01-review-wrap">
                                 <li id="power_review_block20180711112229"
                                    class="power-review-list-box">
                                 <div class="hd-box">
                                    <ul class="desc">
                                    <li class="pr-list-writer">�ۼ��� : ${goodsReview.MEMBER_NAME}</li><br>
                                    <li class="pr-list-writer"><fmt:formatDate value="${goodsReview.REVIEW_REGDATE}" pattern="YYYY-MM-dd HH:mm" /></li>
                                    </ul>
                                   <div class="star-icon">
	                                    <span class="star">
	                                   	<c:if test="${goodsReview.REVIEW_SCORE == 20 }">
										<font color="#FFBF00" size="5">��</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 40 }">
										<font color="#FFBF00" size="5">�ڡ�</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 60 }">
										<font color="#FFBF00" size="5">�ڡڡ�</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 80 }">
										<font color="#FFBF00" size="5">�ڡڡڡ�</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 100 }">
										<font color="#FFBF00" size="5">�ڡڡڡڡ�</font>
										</c:if>
	                                    </span>
	                                    </div> 
                                 </div><!-- hd-box -->
                           <span class="pr-options" style="display: none;">${goodsReview.REVIEW_CONTENT}</span>   
                           <div class="PR15N01-hd">
                           <h2>���� : ${goodsReview.REVIEW_TITLE }</h2>
                           </div>
                           <div class="content">
                              <p class="content_p"><a class="more-options">���� : ${goodsReview.REVIEW_CONTENT }</a></p>
                           <div class="ctr">
                           <div class="photo-list">
                                 <ul>
                                    <li>
                                       <div><span></span>
                                       <c:if test="${goodsReview.REVIEW_IMAGE ne null }">
                                       <img src="/ModuHome/images/review/${goodsReview.REVIEW_IMAGE}">
                                       </c:if>
                                       </div>
                                    <div class="attach-preview"></div></li>                                    
                                 </ul>
                           </div>   
                                                   
                             <c:if test="${goodsReview.MEMBER_NUMBER eq sessionScope.MEMBER_NUMBER }">
												<c:url var="viewURL" value="/reviewDelete">
													<c:param name="REVIEW_NUMBER" value="${goodsReview.REVIEW_NUMBER}" />
													<c:param name="DETAIL" value="1" />
													<c:param name="GOODS_NUMBER" value="${GOODS_NUMBER}" />
													<c:param name="REVIEW_IMAGE" value="${review.REVIEW_IMAGE }" />
							  </c:url>
							 <a href="${viewURL}" class="delete" onclick="javascript:return confirm('�����Ͻðڽ��ϱ�?');">&nbsp;&nbsp;[����]</a>
							  </c:if> 
                              
                           </div><!-- ctr -->
                           </div><!-- content -->

                                 </li><!-- power-review-list-box -->
                           </ul><!-- PR15N01-review-wrap -->
                           </c:if>
                           </c:if>
                           </c:forEach> --%>

			<!-- ���� ����¡ -->
		<%-- 	<c:if test="${reviewSize gt 5 }">
				<div style="text-align: center;">
					<c:if test="${reviewNowPage ne 1 }">
						<a class="pg_prev" style="margin-top: -8px;"
							href="javascript:ajaxReviewPaging(1,${reviewEndPagingNum},${reviewStartPagingNum},${reviewNowPage});"></a>
					</c:if>
					<span class="pg_current">${reviewNowPage}</span>
					<c:if test="${reviewNowPage ne 1 && reviewNowPage ne reviewTotalPag}">
						<span class="pg_page">
                              Of
                              </span>
						<span class="pg_page">${reviewTotalPage}</span>
					</c:if>
					<c:if test="${reviewNowPage ne reviewTotalPage }">
						<a class="pg_next" style="margin-top: -8px;"
							href="javascript:ajaxReviewPaging(2,${reviewEndPagingNum},${reviewStartPagingNum},${reviewNowPage});"></a>
					</c:if>
				</div>
			</c:if>
		</div>
	</div> --%>
</body>
</html>