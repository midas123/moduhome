//====================================================================
//마이페이지 이미지리스팅 부분 공통 스크립트
//===================================================================
var brandcodes      = document.getElementsByName('brandcodes[]');
var branduids       = document.getElementsByName('branduids[]');
var brchks          = document.getElementsByName('brchks[]');
var quantities      = document.getElementsByName('quantity[]');
var order_quantities= document.getElementsByName('order_quantity[]');





//전체선택
function all_brand_check() {
    for (var i = 0; i < brchks.length; i++) {
        brchks[i].checked = document.list_form.all_brchk.checked; 
    }
}

function todayGoodsDelete() {

	for(var i = 0; i< sessionStorage.getItem('todayGoods'); i++){
		var brchks = document.getElementsByName("brchks["+i+"]");
		console.log("brchks :" +brchks);
	}
	
/*	if ($('input[name="guestName"]').val().length == 0) {
        alert("보내는분의 성함을 입력하세요.");	       
        $('input[name="guestName"]').focus();
        return false;
    }  
 
 if ($('input[name="guestPhone2"]').val().length == 0 && $('input[name="guestPhone3"]').val().length == 0) {
        alert("보내는분의 연락처을 입력하세요.");	       
        $('input[name="guestPhone2"]').focus();
        return false;
    }  else {
    	$('#frmOrder').submit();
    }*/
 
}


//선택된 상품들을 장바구니에 담는다.
function multi_go_basket(language) {
    if (typeof language == 'undefined') {
        language = 'kor';
    }
    if (checkbox_validate(language) == false) {
        return;
    }
    for (var i = 0; i < brchks.length; i++) {
        if (brchks[i].checked == true) {
            alert("다중선택시 장바구니 담기 구현해야함");
        }
    }
}

//장바구니에 상품을 담는다.
function go_basket(idx, language) {
    if (typeof language == 'undefined') {
        language = 'kor';
    }
    if (false === quantity_validate(idx, language)) {
        return;
    }
    var f1 = document.form1;
    var param_obj = document.getElementById('param_'+idx);

    f1.amount.value = (typeof order_quantities[idx] != 'undefined') ? order_quantities[idx].value : 1;
    f1.brandcode.value = brandcodes[idx].value;
    f1.branduid.value = branduids[idx].value;
    f1.uid.value = branduids[idx].value;
    if(param_obj !=null){
       f1.spcode.value = param_obj.getAttribute('spcode');
       f1.spcode2.value = param_obj.getAttribute('spcode2');
       f1.optcode.value = param_obj.getAttribute('optcode');
       f1.optioncode.value = param_obj.getAttribute('optioncode')+",";
       f1.optiontype.value = param_obj.getAttribute('option_type');
       f1.optionlist.value = param_obj.getAttribute('optionvalue');
       f1.opts.value = param_obj.getAttribute('optcode')+",";
       f1.option_type.value = param_obj.getAttribute('option_type');
       if (f1.wish_id) {
           f1.wish_id.value = param_obj.getAttribute('wish_id');
       }
       f1.mode.value = "ins";
    }else {
       f1.spcode.value = 0;
       f1.spcode2.value = 0;
       f1.mode.value = "";
    }

    f1.action = "basket.html";
    f1.submit();

    alert((language == 'eng') ? "Add to cart." : "장바구니에 상품을 담습니다.");
}

function package_basket(uid,language,device) {

    if (typeof language == 'undefined') {
        language = 'kor';
    }
    if (typeof device == 'undefined') {
        device = 'pc';
    }
    if (language == 'eng') {
        alert("Products after confirming from the product detail page, if a set of products will be placed in the cart.");
    } else {
        alert((device == 'mobile') ? "세트 상품의 경우 상세페이지에서\n상품 변경유무 확인 후\n장바구니에 담아야 합니다." : "세트 상품의 경우 상세페이지에서 상품 변경유무 확인 후\n\n장바구니에 담아야 합니다.");
    }
    location.href = '/shop/shopdetail.html?branduid='+uid;
}

//주문수량을 체크한다.
function quantity_validate(idx, language) {
    if (typeof language == 'undefined') {
        language = 'kor';
    }
    if (quantities[idx].value == 0) {
        alert((language == 'eng') ? "The selected product is out of stock. You can not order this product." : "선택하신 상품의 재고가 없어 주문하실 수 없습니다.");
        if (typeof order_quantities[idx] != 'undefined') {
            order_quantities[idx].focus();
        }
        return false;
    }

    if (typeof order_quantities[idx] != 'undefined') {
        if (isNaN(order_quantities[idx].value)) {
            alert((language == 'eng') ? "Input numbers in quantity field." : "주문수량을 숫자로 입력하세요.");
            order_quantities[idx].focus();
            return false;
        }
        if (order_quantities[idx].value.length == 0) {
            alert((language == 'eng') ? "Input quantity." : "주문수량을 입력하세요.");
            order_quantities[idx].focus();
            return false;
        }
        if (parseInt(quantities[idx].value) < parseInt(order_quantities[idx].value)) {
            alert((language == 'eng') ? "The order quantity exceeds the remaining stock." : "주문수량이 재고 수량보다 많습니다.");
            order_quantities[idx].focus();
            return false;
        }
    }
}

function checkbox_validate(language) {
    if (typeof language == 'undefined') {
        language = 'kor';
    }
    var c = 0;
    for (var i = 0; i < brchks.length; i++) {
        if (brchks[i].checked === true) {
            c++;
        }
    }
    if (c == 0) {
        alert((language == 'eng') ? "Please select product." : "상품을 선택하세요.");
        return false;
    }
}

//====================================================================
//최근본상품관련 스크립트
//===================================================================

//선택된 최근 본 상품들을 삭제한다.
function multi_del_viewproduct(language) {
    if (typeof language == 'undefined') {
        language = 'kor';
    }
    if (checkbox_validate(language) == false) {
        return;
    }
    if (confirm((language == 'eng') ? "Delete selected products from list of recently viewed products." : "선택하신 최근 본 상품을 삭제합니다.")) {
        brcookie = get_viewproduct_cookie();
        for (var i = 0; i < brchks.length; i++) {
            if (brchks[i].checked == true) {
                brcookie = set_viewproduct(brchks[i].value, brcookie); 
            }
        }
        set_cookie_viewproduct(brcookie);
        document.location.reload();
    }
}

//최근본 상품을 삭제한다.
function del_viewproduct(brandcode,language) {
    if (typeof language == 'undefined') {
        language = 'kor';
    }
    if (confirm((language == 'eng') ? "Delete from list of recently viewed products." : "최근 본 상품에서 삭제합니다.")) {
        brcookie = get_viewproduct_cookie();
        set_cookie_viewproduct(set_viewproduct(brandcode, brcookie));
        document.location.reload();
    }
}

function all_del_viewproduct(language) {
    if (typeof language == 'undefined') {
        language = 'kor';
    }
    if (confirm((language == 'eng') ? "Delete all products from list of recently viewed products." : "최근 본 상품을 전체 삭제합니다.")) {
        set_cookie_viewproduct('');
        document.location.reload();
    }
}

//최근 본 상품 쿠키로 삭제 요청 상품을 없애고 재구성  
function set_viewproduct(brandcode, brcookie) {
    brand_arr = brcookie.split(',');
    var set_brands = "";
    for (var i = 1; i < brand_arr.length - 1; i++) {
        if (brandcode !== brand_arr[i]) {
            set_brands += "," + brand_arr[i];
        }
    }
    if (set_brands.length > 0) {
        set_brands += ",";
    }
    return set_brands;
}

//최근 본 상품 쿠키를 굽는다.
function set_cookie_viewproduct(set_brands) {
    var hostname = window.location.hostname;
    if (hostname.substr(0,4) === "www.") {
        var hostlen = hostname.length;
        hostname = hostname.substring(3, hostlen);
    }
    document.cookie = 'viewproduct=' + escape(set_brands) +'; path=/;domain=' + hostname + ';';
}

//쿠키를 가져온다
function get_viewproduct_cookie() {        
    return getCookiefss("viewproduct"); 
}

//====================================================================
//wish list 관련 스크립트
//===================================================================

//wishlist 상품을 삭제한다
function del_wishlist(branduid,idx) {
    var f = document.form1;
    var param_obj = document.getElementById('param_'+idx);

    f.spcode.value = param_obj.getAttribute('spcode');
    f.spcode2.value = param_obj.getAttribute('spcode2');
    f.optcode.value = param_obj.getAttribute('optcode');
    f.optioncode.value = param_obj.getAttribute('optioncode');
    if (f.wish_id) {
        f.wish_id.value = param_obj.getAttribute('wish_id');
    }

    f.action = "mypage.html?mypage_type=mywishlist";
    f.mode.value = "del";
    f.branduid.value = branduid;
    f.submit();
    f.mode.value = "";
}
function multi_del_wishlist(language) {
    if (typeof language == 'undefined') {
        language = 'kor';
    }
    var f = document.form1;
    f.action = "mypage.html?mypage_type=mywishlist";
    f.mode.value = "del";
    var brandcode_val = check_brand(',','code','',language);
    if (brandcode_val == false) {
        return;
    } else {
        f.branduid.value = brandcode_val;
        f.spcode.value = check_brand(',','code','spcode',language);
        f.spcode2.value = check_brand(',','code','spcode2',language);
        f.optcode.value = check_brand(',','code','optcode',language);
        f.optioncode.value = check_brand(',','code','optioncode',language);
        if (f.wish_id) {
            f.wish_id.value = check_brand(',','code','wish_id',language);
        }
    }
    f.submit();
    f.mode.value = "";
}
//모바일에서만 사용. 위시리스트 전체 삭제
function all_del_wishlist() {
    var f = document.form1;
    f.action = "mypage.html?mypage_type=mywishlist";
    f.mode.value = "all_del";

    if(confirm('위시리스트를 비우시겠습니까?')) {
        f.submit();
    }
    f.mode.value = "";
}
function importune_pop(temp) {
    win = window.open('importune_pop.html?brandcode=' + temp, '조르기', 'width=507, height=700,scrollbars=auto');
    win.focus();
}
function check_brand(division,type,obj,language){
    if (typeof language == 'undefined') {
        language = 'kor';
    }
    var temp_r = "";
    var temp_brandcodes = "";
    if (checkbox_validate(language) == false) {
        return false;
    }
    var alertflag = false;
    var cnt = 0 ;
    for (var i = 0; i < brchks.length; i++) {
        if (brchks[i].checked == true) {
            if (type == 'uid') {
                if ( branduids[i].value.length == 0 ) {
                    alertflag = true;
                } else {
                    if(!obj){
                        temp_brandcodes += branduids[i].value + division; 
                    }else{
                        temp_r += document.getElementById('param_'+i).getAttribute(obj) + "()"; 
                    }
                }
            } else {
                if(!obj){
                    temp_brandcodes += branduids[i].value + division; 
                }else{
                    temp_r += document.getElementById('param_'+i).getAttribute(obj) + "()"; 
                }
            }
        }
    }
    if ( type == 'uid' && alertflag == true ){
        // 품절 상품 포함했을 경우
        if (temp_brandcodes.length > 0 ){
            alert((language == 'eng') ? "Out-of-stock products are excluded." : "품절 상품은 제외됩니다.");
        } else {
            alert((language == 'eng') ? "You can not use out-of-stock products." : "품절 상품은 사용할 수 없습니다.");
        }
    }

    if(!obj){
        return temp_brandcodes.substring(0, temp_brandcodes.length - 1);
    }else{
        return temp_r.substring(0, temp_r.length - 2);
    }
}
function importune_select(language){
    if (typeof language == 'undefined') {
        language = 'kor';
    }
    var obj = document.getElementById('allimportune');
    var senddata = check_brand('|','uid','',language);
    if( senddata != false){
        win = window.open('about:blank', 'importune_pop', 'width=507, height=700,scrollbars=auto');
        win.focus();
        obj.brandcode.value = senddata + "|";
        obj.target = 'importune_pop';
        obj.submit();
    }
}
function importune_all(language){
    if (typeof language == 'undefined') {
        language = 'kor';
    }
    var obj = document.getElementById('allimportune');
    if (obj.total_cnt.value > obj.alluid.value.split('|').length - 1 ) {
        alert((language == 'eng') ? "Out-of-stock products and products not available for request are excluded." : "품절 상품 또는 조르기 설정이 안된 상품은 제외됩니다.");
    }
    win = window.open('about:blank', 'importune_pop', 'width=507, height=700,scrollbars=auto');
    win.focus();
    obj.brandcode.value = obj.alluid.value;
    obj.target = 'importune_pop';
    obj.submit();
}
