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
        <li style="background-image: url(/ModuHome/style/img/join.jpg)" class="overlay">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
              
                <div class="probootstrap-slider-text text-center" style=" margin-top:300px;">
                  <h1 class="probootstrap-heading" style="font-size: 60px;"><span><strong>회원가입</strong></span></h1>
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

          <div class="col-md-3 probootstrap-animate"></div>
           
          <div class="col-md-9 probootstrap-animate">
            <form  method="post" name="frm" enctype="multipart/form-data" class="probootstrap-form">

              <table>
                <tr>
                  <td width="100px"><label for="name">아이디</label></td>
                  <td><input type="text" class="form-control" id="MEMBER_ID" name="MEMBER_ID" value="" label="아이디" required></td>
                  <td class="alert alert-positive">&nbsp;　</td>
                </tr>
                
                <tr><td>　</td></tr>
                
                <tr>
                  <td width="100px"><label for="name">이름</label></td>
                  <td><input type="text" class="form-control" id="MEMBER_NAME" name="MEMBER_NAME" value="" label="이름" required></td>
                  <td class="alert alert-positive">&nbsp;　</td>
                </tr>                
                
                <tr><td>　</td></tr>
                  
                <tr>
                  <td width="100px"><label for="name">닉네임</label></td>
                  <td><input type="text" class="form-control" id="MEMBER_NICKNAME" name="MEMBER_NICKNAME" value="" label="닉네임" required></td>
                  <td class="alert alert-positive">&nbsp;　</td>
                </tr>               
                
                <tr><td>　</td></tr>
                 
                <tr>
                  <td width="100px"><label for="name">비밀번호</label></td>
                  <td><input type="password" class="form-control" id="MEMBER_PASSWORD" name="MEMBER_PASSWORD" value="" label="비밀번호" required></td>
                  <td class="al">　</td>
                </tr>
                
                <tr><td>　</td></tr>
                                
                <tr>
                  <td width="100px"><label for="name">비밀번호 확인</label></td>
                  <td><input type="password" class="form-control" id="MEMBER_PASSWORD_CH" name="MEMBER_PASSWORD_CH" value="" label="비밀번호체크" required></td>
                  <td class="alert alert-positive">&nbsp;　</td>
                </tr>
                
                <tr><td>　</td></tr>
                                
                <tr>
                  <td width="100px"><label for="name">생년월일</label></td>
                  <td><input type="text" class="form-control" id="MEMBER_JUMIN" name="MEMBER_JUMIN" value="" label="생년월일" placeholder="예)19000101" maxlength="8" required></td>
                  <td class="alert alert-positive">&nbsp;　</td>
                </tr>                              
                
                <tr><td>　</td></tr>
                
                <tr>
                  <td width="100px"><label for="name">연락처</label></td>
                  <td><input type="text" class="form-control" id="MEMBER_PHONE" name="MEMBER_PHONE" value="" label="연락처" required maxlength="11" placeholder="'-'없이 입력해주세요."></td>
                  <td class="alert alert-positive">&nbsp;　</td>
                </tr>             
                
                <tr><td>　</td></tr>
                
                <tr>
                  <td width="100px"><label for="name">이메일</label></td>
                  <td><input type="text" class="form-control" id="MEMBER_EMAIL" name="MEMBER_EMAIL" value="" label="이메일" required maxlength="20"></td>
                  <td><a href="javascript:email_code();">　인증번호받기</a></td>
                </tr>
                
          
                 
                <tr>
                  <td width="100px"><label for="name">인증코드</label></td>
                  <td><input type="text" class="form-control" id="auth" name="auth" value="" label="인증번호" required maxlength="7" placeholder="인증번호를 입력해주세요." disabled="disabled"></td>
                  <td><a href="javascript:member_send();" id="auth_ok">　인증확인</a></td>
                </tr>
               
               <tr><td>　</td></tr>
                 
                <tr>
                  <td width="100px"><label for="name">우편번호</label></td>
                  <td><input type="text" class="form-control" id="MEMBER_ZIPCODE" name="MEMBER_ZIPCODE" label="우편번호" value="" maxlength="6" required readonly=""></td>
                  <td><a href="javascript:sample6_execDaumPostcode();">　주소찾기</a></td>
                </tr>
                
                <tr>
                  <td width="100px"><label for="name">주소</label></td>
                  <td><input type="text" class="form-control" id="MEMBER_ADDRESS1" name="MEMBER_ADDRESS1" label="주소1" value="" maxlength="48" required readonly=""></td>
                </tr>
                <tr>
                  <td width="100px"><label for="name">　</label></td>
                  <td><input type="text" class="form-control" id="MEMBER_ADDRESS2" name="MEMBER_ADDRESS2" label="주소2" value=""  required ></td>
                </tr>
                     
                     <tr><td>　</td></tr>
                                 
                <tr>  
                  <td width="100px"><label for="name">프로필사진</label></td>
                  <td><input type="file"  id="MEMBER_PROP" name="MEMBER_PROP" accept="image/gif,image/jpeg,image/png" onchange="chk_file_type(this)" /></td>
                  <td><img src="/ModuHome/images/member/profile.jpg" alt="heart_img" height="100px" width="90px" id="proimg"></td>
                </tr>
                
                <tr><td>　</td></tr>

               <tr>
                 <td width="100px">　</td>
                 <td><input type="submit" class="btn btn-primary btn-lg" id="join" name="join" value="회원가입" onclick="javascript:checkSubmit()">　　　　<input type="submit" class="btn btn-primary btn-lg" id="cancle" name="cancle" value="가입취소" onclick=""></td>
               </tr>
              </table>
          
              
            </form>
          </div>

          <div class="col-md-2 probootstrap-animate"></div>
          
        </div>
      </div>
    </section>   

    <footer class="probootstrap-footer">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
              <ul class="probootstrap-footer-social">
                <li><a href="#"><i class="icon-twitter"></i></a></li>
                <li><a href="#"><i class="icon-facebook"></i></a></li>
                <li><a href="#"><i class="icon-github"></i></a></li>
                <li><a href="#"><i class="icon-dribbble"></i></a></li>
                <li><a href="#"><i class="icon-linkedin"></i></a></li>
                <li><a href="#"><i class="icon-youtube"></i></a></li>
              </ul>
          </div>
        </div>
        <!-- END row -->
        <div class="row">
          <div class="col-md-12 copyright text-center">
            <p>&copy; 2017 <a href="https://probootstrap.com/">uiCookies:FineOak</a>. All Rights Reserved. <br> Designed &amp; Developed with by <a href="https://probootstrap.com/">uicookies.com</a></p>
          </div>
        </div>
      </div>
    </footer>
    

    <script src="style/js/scripts.min.js"></script>
    <script src="style/js/custom.min.js"></script>
    
    <!-- 다음 주소 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script><script charset="UTF-8" type="text/javascript" src="http://s1.daumcdn.net/svc/attach/U03/cssjs/postcode/1484723365148/170118.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('MEMBER_ZIPCODE').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('MEMBER_ADDRESS1').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('MEMBER_ADDRESS2').focus();
            }
        }).open();
    }
</script>


<script>
/* 스크립터의 전역변수 */
var id_ok ="";
var password_ok ="";
var email_ok ="";
var name_ok="";
var phone_ok="";
var email_wr_ok="";
var jumin_ok="";
var nick_ok="";
var auth_email="";

/* 아이디 중복확인 */
$("input[name=MEMBER_ID]").blur(function(){
	var MEMBER_ID = $(this).val();
	var s = $(this).parent();
	var p = s.parent();
	
	
	$.post("/ModuHome/checkId",{MEMBER_ID:MEMBER_ID},function(data){
		if (!MEMBER_ID){
			$(".alert",p).removeClass("alert-positive").addClass("alert-negative").html("아이디를 입력해주세요.");
			//$("input[name=chkid]").val("");
		} else if (data==1){
			$(".alert",p).removeClass("alert-positive").addClass("alert-negative").html("이미 등록된 아이디입니다.");
			//$("input[name=chkid]").val("");
		} else {
			$(".alert",p).removeClass("alert-negative").addClass("alert-positive").html("사용 가능한 아이디입니다.");
			//$("input[name=chkid]").val("on");
			id_ok="ok";
		}
	});		
});

/* 닉네임 중복확인 */
$("input[name=MEMBER_NICKNAME]").blur(function(){
	var mem_nickname = $(this).val();
	var s = $(this).parent();
	var p = s.parent();
	
        if(mem_nickname == ""){
        	$(".alert",p).removeClass("alert-positive").addClass("alert-negative").html("닉네임을 입력해주세요.");
        	nick_ok ="";
        }else {
			$(".alert",p).removeClass("alert-negative").addClass("alert-positive").html("");
			nick_ok="ok";
		}
      
});

/* 비밀번호 유효성검사 */ 
$("input[name=MEMBER_PASSWORD]").blur(function(){
	var password= $(this).val();
	var password_ch= document.frm.MEMBER_PASSWORD_CH.value;
	var s = $("input[name=MEMBER_PASSWORD_CH]").parent();
	var p = s.parent();
        if(password_ch == ""){
        	$(".alert",p).removeClass("alert-positive").addClass("alert-negative").html("　");
        	password_ok = "";
        }else if (password != password_ch){
			$(".alert",p).removeClass("alert-positive").addClass("alert-negative").html("비밀번호를 확인해주세요.");
			password_ok = "";
		}else if (password == password_ch){
			$(".alert",p).removeClass("alert-negative").addClass("alert-positive").html("　");
			password_ok = "ok";
		}
      
});

$("input[name=MEMBER_PASSWORD_CH]").blur(function(){
	var password_ch= $(this).val();
	var password = document.frm.MEMBER_PASSWORD.value;
	var s = $(this).parent();
	var p = s.parent();
	
        if(password_ch == ""){
        	$(".alert",p).removeClass("alert-positive").addClass("alert-negative").html("비밀번호를 확인해주세요.");
        	password_ok = "";
        }else if (password != password_ch){
			$(".alert",p).removeClass("alert-positive").addClass("alert-negative").html("비밀번호가 다릅니다.");
			password_ok = "";
		}else if (password == password_ch){
			$(".alert",p).removeClass("alert-negative").addClass("alert-positive").html("");
			password_ok = "ok";
			
		}
      
});
/* 이름 유효성 */
$("input[name=MEMBER_NAME]").blur(function(){
	var mem_name = $(this).val();
	var s = $(this).parent();
	var p = s.parent();
	
        if(mem_name == ""){
        	$(".alert",p).removeClass("alert-positive").addClass("alert-negative").html("이름을 입력해주세요.");
        	name_ok ="";
        }else {
			$(".alert",p).removeClass("alert-negative").addClass("alert-positive").html("");
			name_ok="ok";
		}
      
});
/* 연락처 유효성 */
$("input[name=MEMBER_PHONE]").blur(function(){
	var mem_phone = $(this).val();
	var s = $(this).parent();
	var p = s.parent();
	
        if(mem_phone == ""){
        	$(".alert",p).removeClass("alert-positive").addClass("alert-negative").html("연락처를 입력해주세요.");
        	phone_ok ="";
        }else {
			$(".alert",p).removeClass("alert-negative").addClass("alert-positive").html("");
			phone_ok ="ok";
		}
      
});
/* 이메일 유효성 */
$("input[name=MEMBER_EMAIL]").blur(function(){
	var mem_email = $(this).val();
	var s = $(this).parent();
	var p = s.parent();
	
        if(mem_email == ""){
        	$(".alert",p).removeClass("alert-positive").addClass("alert-negative").html("이메일을 입력해주세요.");
        	email_wr_ok ="";
        }else {
			$(".alert",p).removeClass("alert-negative").addClass("alert-positive").html("");
			email_wr_ok ="ok";
		}
      
});
/* 생년월일 */
$("input[name=MEMBER_JUMIN]").blur(function(){
	var mem_jumin = $(this).val();
	var s = $(this).parent();
	var p = s.parent();
	
        if(mem_jumin == ""){
        	$(".alert",p).removeClass("alert-positive").addClass("alert-negative").html("생년월일을 입력해주세요");
        	jumin_ok ="";
        }else if(frm.MEMBER_JUMIN.value.length != 8){
			$(".alert",p).removeClass("alert-positive").addClass("alert-negative").html("생년월일을 정확히 입력해주세요.");
			jumin_ok ="";
		}else{
			$(".alert",p).removeClass("alert-negative").addClass("alert-positive").html("");
			jumin_ok = "ok";
		}
      
});

/* 이메일 */
function email_code(){
	
	   var f = document.frm;
	   auth_email = f.MEMBER_EMAIL.value; //회원가입누를때 이메일 바꿔 내는것을 방지 ||인증을 받은 이메일 자바스크립트의 전역변수에 넣기
	   var email = f.MEMBER_EMAIL.value;

	 	  if(email == '') {
		      alert("이메일을 입력해주세요");
		      return false;
		  }else{
	    	  $.ajax({
	    	        type: "POST",
	    	        url: "/ModuHome/email_auth",
	    	        data: ({mode:"email_code", email:email}),
	    	        //contentType: "text/plain; charset=euc-kr",
	    	        success: function(data) {
	    	        	console.log(data);
	    	        	if(data != 0){
	    	        		alert("이미 가입된 이메일입니다.다른이메일을 입력해주세요");
	    	        	}else{ 
	    	        		alert("인증번호를 요청하신 이메일로 발송했습니다.");
	    	        		var auth2 = document.getElementById("auth"); /* 이메일인증번호를 보낼 시 인증번호input칸이 풀린다 이유: 이메일값과 인증번호값을 넣지않고 회원가입을 하는것을 막기위해 */
	    	        		   auth2.removeAttribute("disabled"); /* 인증번호 input칸의 disabled속성을 삭제 */
	    	        	}
	    	        	
	    	        	if(data != null)    {
	    	            	console.log("로그 내용2"+data);
	    	            }
	    	        },
	    	        error: function(e){
	    	         alert("알맞은 이메일로 인증해주세요.");
	    	        }
	    	    });
	      }
	   
	}
	
function member_send(){
	   var f = document.frm;
	   var email = f.MEMBER_EMAIL.value;
	   var auth = f.auth.value;
   		$.ajax({
      	 type: "POST",
       	 url: "./email_auth_success",
       	 data: ({email:email, auth:auth}),
      	  //data: ({Id:$("#Id").val(), Pwd:$("#Pwd").val()}),
      	  //contentType: "text/plain; charset=euc-kr",
      	  success: function(data) {
        		 /* alert("auth값받음"+data); */
   	     	 console.log("로그 내용1");     
           	 if(data == 1)    {
            		alert("인증완료");
            		email_ok="ok";
            		var auth2 = document.getElementById("auth"); /* 이메일인증번호를 보낼 시 인증번호input칸이 풀린다 이유: 이메일값과 인증번호값을 넣지않고 회원가입을 하는것을 막기위해 */
	        		   auth2.setAttribute("disabled", "disabled"); /* 인증완료 후 인증창 닫힘 */
            		   /* 인증완료 후 auth값 ok */
         	   } else {
         		  
          	  	alert("인증번호가 맞지 않습니다.");
          	  	f.auth.value ="";
          	  	console.log("로그 내용3");
       	     }
     	   },
     	   error: function(e){
     	    alert('error' + e);
   	     }
   	 });
	}

function chk_file_type(obj) { /*이미지 파일만 올릴수 있게 설정 */
	 var file_kind = obj.value.lastIndexOf('.');
	 var file_name = obj.value.substring(file_kind+1,obj.length);
	 var file_type = file_name.toLowerCase();

	 var check_file_type = new Array();

	 check_file_type=['jpg','gif','png','jpeg','bmp'];



	 if(check_file_type.indexOf(file_type)==-1){
	  alert('이미지 파일만 선택할 수 있습니다.');
	  var parent_Obj=obj.parentNode
	  var node=parent_Obj.replaceChild(obj.cloneNode(true),obj);
	  return false;
	 }
	}

			        
					    
/*  이미지 미리보기  */
 $(document).ready(function(){
function readURL(input) {
  if (input.files && input.files[0]) {
 var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
	reader.onload = function (e) {
	//파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
		 $('#proimg').attr('src', e.target.result);
		//이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
		 //(아래 코드에서 읽어들인 dataURL형식)
			}                   
			  reader.readAsDataURL(input.files[0]);
			 //File내용을 읽어 dataURL형식의 문자열로 저장
				  }
				  }//readURL()--
				   
			 //file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드
				  $("#MEMBER_PROP").change(function(){
		    //alert(this.value); //선택한 이미지 경로 표시
			    readURL(this);
		 });
		   });
					
					 /* 회원가입버튼 누를경우 */
						function checkSubmit(){
							var frm = document.frm;
							if(id_ok == ""){
								alert('아이디를 확인해주세요.');
							}else if(password_ok ==""){
								alert('비밀번호를 다시 확인해주세요.');	
							}else if(frm.MEMBER_JUMIN.value.length != 8){
								alert('생년월일을 확인해주세요.');								
							}else if(email_ok == ""){
								alert("이메일인증을 완료해주세요.");
							}else if(email_wr_ok == ""){
							   alert("이메일인증을 완료해주세요.");
						    }else if(name_ok ==""){
						    	 alert("이름을 확인해주세요.");
						    }else if(phone_ok ==""){
						    	alert("전화번호를 확인해주세요.");
						    }else if(jumin_ok==""){
						    	alert("생년월일을 확인해주세요.");
						    }else if(nick_ok==""){
						    	alert("닉네임을 확인해주세요.");
						    }else if(auth_email != document.frm.MEMBER_EMAIL.value){
						    	alert("인증된 이메일을 입력해주세요.");
						    }
							else{
								document.frm.action="/ModuHome/joinComplete";
								document.frm.submit();
							}
							
						}
					
					 
					</script>

</body>
</html>