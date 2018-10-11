   <link rel="stylesheet" href="/MODA/css/memberDelete.css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
    


<div class="modal-header">
        <div class="modal-title" style="height:20px;">
           <div class="container-fluid">
              <div class="col-xs-6" style="align:left; text-align:left;"><h5 style="font:맑은고딕;"><strong>QNA작성하기</strong></h5></div>
              <div class="col-xs-6" style="align:right; text-align:right;">                                         
              </div>
           </div>
        </div>
     </div>
<form method="post" action="/ModuHome/qna/modalqnaWrite" class="form-horizontal" enctype="multipart/form-data">
      <div class="modal-body">
         <div class="container-fluid">
          <div class="row">
              <div class="col-xs-12" style="text-align:left;align:left;" > 
                    <div style="min-height:200px;">
                        <div class="col-xs-12">  
                         <input type="hidden" name="QNA_CATEGORY" value="상품문의">
                         <input type="hidden" name="GOODS_NUMBER" value="${param.GOODS_NUMBER}">
                         <input type="hidden" name="MEMBER_NUMBER" value="${sessionScope.MEMBER_NUMBER}">
					제목:<input type="text" id="is-title" name="QNA_TITLE" class="form-control" maxlength="20" required=""><br />
					문의 내용: <textarea name="QNA_CONTENT" id="is-contents" rows="4" maxlength="100" class="form-control" required=""></textarea>
				    이미지 첨부: <input type="file" id="is-file" name="QNA_IMAGE" class="xx-control" >		
	                    </div>
	                 </div>
	             </div>
	          </div>  
	       </div>
	   </div>
   <div class="modal-footer">
         <div class="form-group">
         <div class="col-xs-12" style="text-align:right;">
           <button type="submit" class="btn btn btn-warning" style="background-color:#85c8dd;" >
			<span class="button-label">확인</span>
		     </button>
		     <button class="btn btn btn-warning" style="background-color:#85c8dd;"  data-dismiss="modal" onclick="window.location.reload()">
			<span class="button-label">닫기</span>
		</button>
		     </div>
    </div>
	</div>
	</form>

