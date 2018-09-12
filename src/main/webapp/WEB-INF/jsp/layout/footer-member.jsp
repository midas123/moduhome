<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>uiCookies:FineOak &mdash; Free Bootstrap Theme, Free Responsive Bootstrap Website Template</title>
    <meta name="description" content="Free Bootstrap Theme by uicookies.com">
    <meta name="keywords" content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">
    
    <link href="https://fonts.googleapis.com/css?family=Bellefair|Open+Sans" rel="stylesheet">
    <link rel="stylesheet" href="/ModuHome/style/css/styles-merged.css">
    <link rel="stylesheet" href="/ModuHome/style/css/style.min.css">
	<style type="text/css">
		.bank_select {
		color: #000;
		}

		.bank_select option {
		color: #000;
		}
	</style>
</head>
<body>
 
    <footer class="probootstrap-footer">
      <div class="container">
        <div class="row">
          <div class="col-md-4">
            <table>
              <tr>
                <td width="290px"><span>공지사항</span></td>
                <td><a href="/ModuHome/noticeList">더보기</a></td>
              </tr>
            </table>
            <hr/>
            <table>
              <tr><td><a href="http://localhost:8080/ModuHome/noticeDetail?NOTICE_NUMBER=125">03. 모두의집 교환/반품 기준 안내</a></td></tr>
              <tr><td><a href="http://localhost:8080/ModuHome/noticeDetail?NOTICE_NUMBER=122">02. 평일 오전 9시 당일 발송 마감 이후 변경/취소 불가합니다.</a></td></tr>
              <tr><td><a href="http://localhost:8080/ModuHome/noticeDetail?NOTICE_NUMBER=121">01. 당일 발송 마감은 평일 오전 9시입니다.</a></td></tr>
              <tr><td><a href="http://localhost:8080/ModuHome/noticeDetail?NOTICE_NUMBER=120">2018년 추석 연휴 배송안내</a></td></tr>
            </table>
          </div>
          <div class="col-md-4">
            <table>
              <tr>
                <td width="290px"><span>고객센터</span></td>
              </tr>
            </table>
            <hr/>
            <table>
              <tr><td rowspan="2"><span style="font-size: 40px;">1224-1224</span></td><td>평일 10:00~17:00</td></tr>
              <tr><td>주말 및 공휴일 휴무</td></tr>

            </table>
            <table>
              <tr>&nbsp;</tr>
              <tr>
                <td style="border:1px solid;" width="20%" align="center"><a href="#">1:1문의</a></td>
                <td style="border:1px solid;" width="20%" align="center"><a href="#">상품후기</a></td>
                <td style="border:1px solid;" width="20%" align="center"><a href="/ModuHome/faqList">FAQ</a></td>
              </tr>
            </table>

          </div>

          <div class="col-md-4">
            <table>
              <tr>
                <td width="290px"><span>BANK ACCOUNT</span></td>
              </tr>
            </table>
            <hr/>
            <table>
              <tr><td>농협&nbsp;&nbsp;&nbsp;1001-222-1111</td></tr>
              <tr><td>신한&nbsp;&nbsp;&nbsp;110-221-441278</td></tr>
              <tr><td>우리&nbsp;&nbsp;&nbsp;1004-8282-9292</td></tr>
              <tr><td>예금주&nbsp;&nbsp;&nbsp;(주)모두의집</td></tr>
              <tr><td>
                <select style="width: 200px;" class="bank_select" onchange = 'window.open(this.options[selectedIndex].value, "_new" , "")'>
                  <option selected >인터넷 뱅킹 바로가기</option>
                  <option value="https://www.kbstar.com">국민은행</option>
                  <option value="http://www.ibk.co.kr">기업은행</option>
                  <option value="https://www.shinhan.com">신한은행</option>
                  <option value="https://www.wooribank.com">우리은행</option>
                  <option value="https://www.kebhana.com">하나은행</option>
                  <option value="https://banking.nonghyup.com">농협</option>
                </select>
              </td></tr>
            </table>
          </div>

        </div>
        <!-- END row -->
        <div class="row">
          <div class="col-md-12 copyright text-center">
            <p><hr/>
              <a href="https://uicookies.com/">홈 |</a>
              <a href="https://uicookies.com/">회사소개 |</a>
              <a href="https://uicookies.com/">이용약관 |</a>
              <a href="https://uicookies.com/">개인정보 취급방침 |</a> COPYRIGHTⓒ2018 (주)잘풀리는집  ALL RIGHTS RESERVED  <i class="icon icon-heart"></i> by <a href="https://uicookies.com/">KH10004</a></p>
          </div>
        </div>
        
        
        <div class="row">
          <div class="col-md-12 copyright text-center" align="center">
          	<img style="width: 40px; border-radius: 50%;" src="/ModuHome/style/img/sns_fac2.png">
          	<img style="width: 40px; border-radius: 50%; margin-left: 15px;" src="/ModuHome/style/img/sns_ins2.png">
          	<img style="width: 40px; border-radius: 50%; margin-left: 15px;" src="/ModuHome/style/img/sns_twi2.png">
          </div>
        </div>
        
        
      </div>
    </footer>

    <script src="/ModuHome/style/js/scripts.min.js"></script>
    <script src="/ModuHome/style/js/custom.min.js"></script>
 
 
<script>
	function bank_open(url, name, option) {
	window.open(url, name, option);
	}
</script>
 
 
</body>
</html>