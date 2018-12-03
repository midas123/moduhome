<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<% request.setCharacterEncoding("utf-8"); %>

<% response.setContentType("text/html; charset=utf-8"); %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/* function ajaxList(num){
	var searchKeyWord = '${searchKeyWord}';
	var page = 'on';
	var url = window.location.pathname;
	console.log("상품전체 검색 페이징");
    console.log(num);
    console.log(searchKeyWord);
    console.log(url);
    console.log(page);
     
   if(num != null){
      var currentPage = num;
      console.log('currentPage :' +currentPage);   
   }else{
      var currentPage = 1;
      console.log('currentPagezz :' +currentPage);
   }

	 $.ajax({
            url: url,
            type : "post",
            data: {"currentPage":currentPage, "isSearch":searchKeyWord, "page":page, "searchColor":searchKeyWord, "searchSize":searchKeyWord},
            success:function(data){
            	
            	
               $("#changeList").html(decodeURIComponent(data));
            }
         });  
   }   */
</script>


</head>
<body>
<section id="changeList">
<div class="cboth main_section03">
      <div class="cboth main_title">"${searchKeyWord}"검색 PRODUCT LIST</div>
   <br>
	
	
      <div class="cboth prd-list">
         <table summary="상품이미지, 상품 설명, 가격">
         <colgroup>
            <col width="25%">
            <col width="25%">
            <col width="25%">
            <col width="25%">
         </colgroup>
         
         <tbody>
         <tr class="nopadding"><td></td><td></td><td></td><td></td></tr>
         <c:forEach items="${goodsSearchList}" var="goodsList" begin="0" varStatus="status" end="${fn:length(goodsSearchList)}">
       
            <td>
            <div class="tb-center">
            <div class="box">
            <div class="thumb salebox">
                        <a href="/ModuHome/goodsDetail?GOODS_NUMBER=${goodsList.GOODS_NUMBER}"><img class="MS_prod_img_s" src="/ModuHome/images/goods/${goodsList.GOODS_THUMBNAIL}" alt=""></a>
                        <input type="hidden" name="custom_price" value="${goodsList.GOODS_PRICE}">
                        <input type="hidden" name="product_price" value="${goodsList.GOODS_DISPRICE}">
           				<input type="hidden" id="currentPage" value="${currentPage}"/>
           				
                        <!-- 조건태그시작 -->
                        <c:if test="${goodsList.GOODS_DISPRICE != goodsList.GOODS_PRICE}">
                        <span class="sale_text" style="display: block;">
                        -<fmt:formatNumber value="${(goodsList.GOODS_PRICE - goodsList.GOODS_DISPRICE)*100 / goodsList.GOODS_PRICE}" type="number" />%
                        </span>
                        </c:if>
                    
						
                      	
                     </div>
                     <ul class="info">
                        <li class="dsc"> <a href="/ModuHome/goodsDetail?GOODS_NUMBER=${goodsList.GOODS_NUMBER}">${goodsList.GOODS_NAME}</a></li>
<%--                         <li class="subname">${goodsList.GOODS_SHORTDESC}<br>(${goodsList.COLORCOUNT}color)
 --%>                        </li>
                     <c:if test="${goodsList.GOODS_DISPRICE eq goodsList.GOODS_PRICE}">
                     <li class="price"><fmt:formatNumber value="${goodsList.GOODS_PRICE}" type="number"/>원</li>
                     </c:if>   
                     <c:if test="${goodsList.GOODS_DISPRICE ne goodsList.GOODS_PRICE }">
                     <li class="consumer"><fmt:formatNumber value="${goodsList.GOODS_PRICE}" type="number"/>원</li><li class="price"><fmt:formatNumber value="${goodsList.GOODS_DISPRICE}" type="number"/>원</li>
                     </c:if>
                     <li class="icon"><span class="MK-product-icons"></span></li>
                     </ul>
                  </div>
                  </div>
                  
               </td>
         <c:if test="${status.index == 3}">
      		<tr>
         </c:if>
         <c:if test="${status.index > 4 && (status.index+1) % 4 == 0}">
      			<tr>
         </c:if>
            </c:forEach>               
            <tr>
            </tr>
      </tbody>
         
      </table>
       <center>
               ${pagingHtml}
           
        </center>
      </div><!-- //prd-list -->
   </div><!-- //main_section03 -->
</section>

</body>
</html>