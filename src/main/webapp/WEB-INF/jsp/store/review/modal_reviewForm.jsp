   <link rel="stylesheet" href="/ModuHome/css/memberDelete.css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
.star-input>.input,
.star-input>.input>label:hover,
.star-input>.input>input:focus+label,
.star-input>.input>input:checked+label{display: inline-block;vertical-align:middle;background:url('http://localhost:8080/ModuHome/images/grade_img.png')no-repeat;}
.star-input{display:inline-block; white-space:nowrap;width:225px;height:40px;padding:25px;line-height:30px;}
.star-input>.input{display:inline-block;width:150px;background-size:150px;height:28px;white-space:nowrap;overflow:hidden;position: relative;}
.star-input>.input>input{position:absolute;width:1px;height:1px;opacity:0;}
star-input>.input.focus{outline:1px dotted #ddd;}
.star-input>.input>label{width:30px;height:0;padding:28px 0 0 0;overflow: hidden;float:left;cursor: pointer;position: absolute;top: 0;left: 0;}
.star-input>.input>label:hover,
.star-input>.input>input:focus+label,
.star-input>.input>input:checked+label{background-size: 150px;background-position: 0 bottom;}
.star-input>.input>label:hover~label{background-image: none;}
.star-input>.input>label[for="p1"]{width:30px;z-index:5;}
.star-input>.input>label[for="p2"]{width:60px;z-index:4;}
.star-input>.input>label[for="p3"]{width:90px;z-index:3;}
.star-input>.input>label[for="p4"]{width:120px;z-index:2;}
.star-input>.input>label[for="p5"]{width:150px;z-index:1;}
.star-input>output{display:inline-block;width:60px; font-size:18px;text-align:right; vertical-align:middle;}
</style>
<div class="modal-header">
        <div class="modal-title" style="height:20px;">
           <div class="container-fluid">
              <div class="col-xs-6" style="align:left; text-align:left;"><h5 style="font:맑은고딕;">
              <strong>상품 후기</strong></h5></div>
              <div class="col-xs-6" style="align:right; text-align:right;">                                         
              </div></div></div></div>
<form method="post" class="form-horizontal" action="/ModuHome/reviewWrite" enctype="multipart/form-data">
     <div class="modal-body">
         <div class="container-fluid">
          <div class="row">
              <div class="col-xs-12" style="text-align:left;align:left;" > 
              <div style="min-height:200px;">
                  <div class="col-xs-12">  
                  <input type="hidden" name="mode" value="cs">
                  <input type="hidden" name="GOODS_NUMBER" value="${param.GOODS_NUMBER}">
                         제목:<input type="text" id="is-title" name="REVIEW_TITLE" class="form-control" maxlength="20" required="">
			    작성자: <input type="text" id="user" name="user" class="form-control" maxlength="20" required="" 
			    readonly="readonly" value="${MEMBER_NAME}">
			  <input type="hidden" id="is-title" name="MEMBER_NUMBER" class="xx-control" value="${MEMBER_NUMBER }">
               평가하기 :
	<span class="star-input">
	<span class="input">
    	<input type="radio" name="REVIEW_SCORE" value="1" id="p1">
    	<label for="p1">1</label>
    	<input type="radio" name="REVIEW_SCORE" value="2" id="p2">
    	<label for="p2">2</label>
    	<input type="radio" name="REVIEW_SCORE" value="3" id="p3" checked="checked">
    	<label for="p3">3</label>
    	<input type="radio" name="REVIEW_SCORE" value="4" id="p4">
    	<label for="p4">4</label>
    	<input type="radio" name="REVIEW_SCORE" value="5" id="p5">
    	<label for="p5">5</label>
  	</span>
  	<!-- <output for="star-input"><b>0</b>점</output>  -->						
</span><br />
        구매후기 <textarea name="REVIEW_CONTENT" id="is-contents" rows="8" class="form-control" maxlength="100" required="">
        </textarea>
     이미지첨부 <input type="file" id="is-file" name="REVIEW_IMAGE" class="xx-control" >
     </div></div></div></div></div></div>
   <div class="modal-footer">
    <div class="form-group">
    <div class="col-xs-12" style="text-align:right;">
      <button type="submit" class="btn btn btn-warning" style="background-color:#85c8dd;" >
	<span class="button-label">확인</span>
     </button>
     <button class="btn btn btn-warning" style="background-color:#85c8dd;"  data-dismiss="modal" 
     onclick="window.location.reload()">
	<span class="button-label">닫기</span>
    </button>
     </div>
    </div>
	</div>
</form>

