<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>first</title>


</head>
<body>

  <section class="flexslider">
      <ul class="slides">
        <li style="background-image: url(/ModuHome/style/img/slider_1.jpg)" class="overlay">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
                <div class="probootstrap-slider-text text-center">
                  <h1 class="probootstrap-heading">회원탈퇴</h1>
                </div>
              </div>
            </div>
          </div>
        </li>
      </ul>
    </section>
    
    <section class="probootstrap-section probootstrap-bg-white">
      <div class="container">
        <div class="row">

          <div class="col-md-4 probootstrap-animate"></div>
           
          <div class="col-md-4 probootstrap-animate">
            <form  method="post" name="frm" class="probootstrap-form">
              
              <div class="form-group">
                <label for="id">아이디</label>
                <input type="text" class="form-control" id="MEMBER_ID" name="MEMBER_ID" value="${MEMBER_ID}" readonly="readonly">
              </div>
              <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" class="form-control" id="MEMBER_PASSWORD" name="MEMBER_PASSWORD">
              </div>
              

              <div class="form-group" align="center">
                <input type="submit" class="btn btn-primary btn-lg" id="memDelete" name="memDelete" value="회원탈퇴" onclick="javascript:checkSubmit()">
                <input type="button" class="btn btn-primary btn-lg" id="goMain" name="goMain" value="메인으로" onclick="location.href='/ModuHome/'">
              </div>
			  
			 
            </form>
          </div>

          <div class="col-md-4 probootstrap-animate"></div>
          
        </div>
      </div>
    </section>   



</body>

<script>

function checkSubmit(){
	var frm = document.frm;
    if(frm.MEMBER_ID ==""){
	    alert("이름을 입력해주세요.");
    }
	else{
		document.frm.action="/ModuHome/memDeleteComfirm";
		document.frm.submit();
	}
	
}
</script>


</html>