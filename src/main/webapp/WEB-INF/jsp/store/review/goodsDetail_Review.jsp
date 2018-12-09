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
                                       <h2 style="margin-bottom: 0px; font-size: 20px;">상품 후기</h2>
                                       <div class="review-write-btn" style="border: 1px solid black; background: #fff; color: black; text-align: center;">

										<a href="#"  data-size="md" data-label="구매 후기 작성"
                                    onClick="alert('로그인을 해주세요.'); return false;">
                                       <div>후기 작성하기</div>
                                 </a>
                                       </div>
                              </c:if> 
            				<c:if test="${sessionScope.MEMBER_ID ne null and checkBuy ne goodsBasic.GOODS_NUMBER}">
            				<h2 style="margin-bottom: 0px; font-size: 20px;">상품 후기</h2>
                    <div class="review-write-btn" style="border: 1px solid black; background: #fff; color: black; text-align: center;">

					<a href="#"  data-size="md" data-label="구매 후기 작성"
                                    onClick="alert('구매후 작성 가능합니다.'); return false;">
                                       <div>후기 작성하기</div>
                                 
                                 </a></div>
            				</c:if> 
                              <c:if test="${sessionScope.MEMBER_ID ne null and checkBuy eq goodsBasic.GOODS_NUMBER}">
                                       <h2 style="margin-bottom: 0px; font-size: 20px;">상품 후기</h2>
                        	<div class="review-write-btn" style="border: 1px solid black; background: #fff; color: black; text-align: center;">

                           <a href="/ModuHome/review/reviewForm?GOODS_NUMBER=${goodsBasic.GOODS_NUMBER}" data-toggle="modal" data-target="#myModal">후기 작성하기</a>
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
                                    <li class="pr-list-writer">${goodsReview.MEMBER_NAME}</li><br>
                                    <li class="pr-list-writer"><fmt:formatDate value="${goodsReview.REVIEW_REGDATE}" pattern="YYYY-MM-dd" /></li>
                                    </ul>
                                   <div class="star-icon">
	                                    <span class="star">
	                                   	<c:if test="${goodsReview.REVIEW_SCORE == 20 }">
										<font color="#FFBF00" size="5">★</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 40 }">
										<font color="#FFBF00" size="5">★★</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 60 }">
										<font color="#FFBF00" size="5">★★★</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 80 }">
										<font color="#FFBF00" size="5">★★★★</font>
										</c:if>
										<c:if test="${goodsReview.REVIEW_SCORE == 100 }">
										<font color="#FFBF00" size="5">★★★★★</font>
										</c:if>
	                                    </span>
	                                    </div> 
                                 </div><!-- hd-box -->
                           <div class="PR15N01-hd">
                           <h2>${goodsReview.REVIEW_TITLE }</h2>
                           </div>
                           <div class="content">
                              <p class="content_p"><a class="more-options">${goodsReview.REVIEW_CONTENT }</a></p><br>
                              <c:if test="${goodsReview.REVIEW_IMAGE ne null }">                       
								<img class="imgScale" src="/ModuHome/images/review/${goodsReview.REVIEW_IMAGE}" width="100" height="100" onclick="detailView(this)">
                              </c:if>
                           <div>                 
                             <c:if test="${goodsReview.MEMBER_NUMBER eq sessionScope.MEMBER_NUMBER }">
												<c:url var="viewURL" value="/reviewDelete">
													<c:param name="REVIEW_NUMBER" value="${goodsReview.REVIEW_NUMBER}" />
													<c:param name="DETAIL" value="1" />
													<c:param name="GOODS_NUMBER" value="${goodsBasic.GOODS_NUMBER}" />
							  					</c:url>
							 <a href="${viewURL}" class="delete" onclick="delchk();" style="float:right">&nbsp;&nbsp;[삭제]</a>
							  </c:if> 
                           </div><!-- ctr -->
                           </div>
                                 </li>
                           </ul>
                           </c:if>
                           </c:if>
                           </c:forEach>
                           </div>
                           </div>
                           
                           <c:if test="${reviewSize gt 5 }">
                           <div style="text-align:center; margin-top:20px;">
                           <c:if test="${reviewNowPage ne 1 }">
                           <a class="pg_prev" style="margin-top: -8px;" href="javascript:ajaxReviewPaging(1,${reviewEndPagingNum},${reviewStartPagingNum},${reviewNowPage});">prev</a>
                           </c:if>
                              <span class="pg_current">${reviewNowPage}</span>
                              <c:if test="${reviewNowPage ne 1 && reviewNowPage ne reviewTotalPag}">
								<span class="pg_page">
                              Of
                              </span>
						<span class="pg_page">${reviewTotalPage}</span>
							</c:if>
                              
                           <c:if test="${reviewNowPage ne reviewTotalPage }">
                           <a class="pg_next" style="margin-top: -8px;" href="javascript:ajaxReviewPaging(2,${reviewEndPagingNum},${reviewStartPagingNum},${reviewNowPage});">next</a>
                           </c:if>
                           </div>
                           </c:if>
					</div>
</body>
</html>