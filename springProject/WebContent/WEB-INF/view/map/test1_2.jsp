<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String food = request.getParameter("food");
	System.out.println("food >>> : " + food);

	String mid="";
	if(request.getAttribute("seName")!=null){
		mid=request.getAttribute("seName").toString();
	}
	String addr="";
	if(request.getAttribute("memAddr")!=null){
		addr=request.getAttribute("memAddr").toString();
	}
	System.out.println("addr >>>>>>>>>> : " + addr);
	
%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/emotionpage.css" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>GOGODA</title>
    <style>
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative;width:100%;height:800px;}
/* 팝업 레이어 */
/*.popup-layer{position:absolute;top:0;left:260px;z-index: 1;padding:5px;margin:10px 0 30px 10px;width:500px;height:700px;display:block;  background:#fff;}*/
 .popup-layer{position:absolute;top:160px;left:300px;z-index: 1;padding:5px;margin:30px 0 30px 10px;width:430px;height:755px;display:block;  background:rgba(255, 255, 255, 1);overflow-y: auto;border-radius: 10px;}
.popup-layer2{position:absolute;top:160px;left:730px;bottom:0;z-index: 1;padding:5px;margin:30px 0 30px 10px;width:470px;height:280px;display:block;  background:rgba(255, 255, 255, 1);overflow-y: auto;border-radius: 10px;}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:300px;margin:10px 0 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 1);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}
#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .info .jibun {padding-left:26px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .info .tel {color:#009900;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
#pagination {margin:10px auto;text-align: center;}
#pagination a {display:inline-block;margin-right:10px;}
#pagination .on {font-weight: bold; cursor: default;color:#777;}
li { list-style-type : none } /* 아무 표시 없음 */

#insert_review_tb{
    font-family: 'Malgun Gothic',dotum,'돋움',sans-serif;
    font-size: 12px;    
}
#insert_review_td1{
	width: 50px;
	height: 20px;
	text-align: center;
	font-weight: bold;
	background-color: #f5f5f5;
}
#renickname{
	width: 150px;
    padding: 5px;
    margin: 3px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
    outline: none;
}

#newgeo,#nowgeo,#defaultgeo{
    margin-bottom:14px;
    margin-left:5px;
	text-align:right;
    font-size: 10px; 
    background-color: #aaaaaa;
    color: white;
    border: 2px solid #aaaaaa;
	border-radius: 4px;
	padding: 6px 10px;
	transition-duration: 0.4s;
}
#newgeo:hover, #nowgeo:hover, #defaultgeo:hover {

}

#reBtn{
   text-align:right;
   font-size: 10px;
   background-color: #64AAFF;
   color: white;
   border: 2px solid #64AAFF;
   border-radius: 4px;
   padding: 6px 10px;
   transition-duration: 0.4s;
}
#reBtn:hover {
  background-color: white;
  color: black;
}

#popupClose{
   margin:10px; 
   text-align:right;
   font-size: 10px;
   background-color: #555555;
   color: white;
   border: 2px solid #555555;
   border-radius: 4px;
   padding: 6px 10px;
   transition-duration: 0.4s;
   position: absolute;right:0;
}
#popupClose:hover {
  background-color: white;
  color: black;
}

#updateBtn, #deleteBtn, #insertBtn{

   text-align:right;
   font-size: 10px;
   background-color: #555555;
   color: white;
   border: 2px solid #555555;
   border-radius: 4px;
   padding: 6px 10px;
   transition-duration: 0.4s;
}
#updateBtn:hover, #deleteBtn:hover, #insertBtn:hover {
  background-color: white;
  color: black;
}

#insertForm{
   margin:10px; 
   text-align:right;
   font-size: 10px;
   background-color: #008CBA;
   color: white;
   border: 2px solid #008CBA;
   border-radius: 4px;
   padding: 6px 10px;
   transition-duration: 0.4s;
}
#insertForm:hover {
  background-color: white;
  color: black;
}


#fileSelect{
   margin:3px; 
   text-align:right;
   font-size: 10px;
   background-color: #9696FF;
   color: white;
   border: 2px solid #9696FF;
   border-radius: 4px;
   padding: 6px 10px;
   transition-duration: 0.4s;
}
#fileSelect:hover {
  background-color: white;
  color: black;
}

#rephoto{float:right;margin-bottom:10px;margin-right:10px;width:100px; height:100px;}
.recontent{
	margin-top:5px;
	display:block;
}
#delLi{
	float:left;
	margin-top:20px;
	width:400px;
	margin-top: 10px;
	margin-left:10px;
	font-family: 'Malgun Gothic',dotum,'돋움',sans-serif;
	font-size: 13px;
	border-bottom: 1px solid Gainsboro;
	padding: 0px;} 
#restar{width:30px;height:30px; background:rgba(255, 255, 255, 0.7);}

span.star-prototype, span.star-prototype > * {
    height: 17px; 
    background: url("http://i.imgur.com/YsyS5y8.png") 0 -16px repeat-x;
    width: 80px;
    display: block;
    
    margin-bottom:7px;
    
}
 
span.star-prototype > * {
    background-position: 0 0;
    max-width:80px; 
}
.info > span > div{
	display:inline;
}

#starOne{
	
	filter: invert(45%) sepia(63%) saturate(1946%) hue-rotate(342deg) brightness(95%) contrast(101%);
	width:15px;
}

.blind {
  position: absolute;
  overflow: hidden;
  margin: -1px;
  padding: 0;
  width: 1px;
  height: 1px;
  border: none;
  clip: rect(0, 0, 0, 0);
}

.startRadio {
  display: inline-block;
  overflow: hidden;
  height: 20px;
  margin: 3px;
}
.startRadio:after {
  content: "";
  display: block;
  position: relative;
  z-index: 5;
  height: 20px;
  background: url("data:image/png;base64,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") repeat-x 0 0;
  background-size: contain;
  pointer-events: none;
}
.startRadio__box {
  position: relative;
  z-index: 1;
  float: left;
  width: 10px;
  height: 20px;
  cursor: pointer;
}
.startRadio__box input {
  opacity: 0 !important;
  height: 0 !important;
  width: 0 !important;
  position: absolute !important;
}
.startRadio__box input:checked + .startRadio__img {
  background-color: #ffdd00;
}
.startRadio__img {
  display: block;
  position: absolute;
  right: 0;
  width: 250px;
  height: 20px;
  pointer-events: none;
}

#popupClose2{
	float:right;
	margin-right: 9px;
    margin-top: 14px;
	text-align:right;
	font-size: 10px;
	background-color: #555555;
	color: white;
	border: 2px solid #555555;
	border-radius: 4px;
	padding: 6px 10px;
	transition-duration: 0.4s;
}
#popupClose2:hover {
  background-color: white;
  color: black;
}

#reviewForm{margin-top:15px;margin-left:20px;}
#insertBtn{float:right;margin-top:10px;}
#recontent{
	margin-left:3px;
	border:none;
	resize:none;
}

.popup-layer > p {margin-top:300px; margin-left:70px;}

#starOne{margin-left: 80px;}

#cartoon{
	background-image:url(/backImages/watercolour1.png);
}

</style>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=979d0cc4b8c4a11d3bd7378770fd582f&libraries=services"></script>
</head>
<body>
<form name="loginForm" id="loginForm">
<div class="w3-top">
	<div class="w3-bar w3-white w3-wide w3-padding w3-card">
		<a href="../../emotion/mainpage.ggd">
			<img src="/logo/GOGODA-logo.png" style="max-width:12%; max-height:12%">
		</a>
	<div class="w3-right w3-hide-small" id="memlogin">
		<a href="#로그인" class="w3-bar-item w3-button" id="loginbtn">로그인</a>
		<a href="#회원 가입" class="w3-bar-item w3-button" id="meminsert">회원가입</a>
    </div>
	</div>
</div>
</form>
<br><br><br><br><br><br>
<div class="" id="cartoon">
	<input type="text" id="newText" style="margin-bottom:10px; margin-left:10px">
	<input type="button" id="newgeo" value="검색" >
	<input type="button" id="nowgeo" value="현재위치">
	<input type="button" id="defaultgeo" value="기본위치">
	
	<img src="/backImages/cartoon3.png" id="move-img" style="width:5%; height:5%">
</div>
<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>

    <span id="menu_wrap" class="bg_white">
        <div class="option">
            <div>
                <form onsubmit="searchPlaces(); return false;">
       <!--              키워드 : <input type="text" value="" id="keyword" size="15"> 
                    <button type="submit">검색하기</button> --> 
                     <input type="hidden" value="" id="keyword" size="15">
                </form>
            </div>
        </div>
        <hr>
        <ul id="placesList"></ul>
        <div id="pagination"></div>
    </span>
    <span id="line2">
		
	</span>
</div>

<script src="${pageContext.request.contextPath}/include/js/jquery-1.12.4.min.js"></script>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=979d0cc4b8c4a11d3bd7378770fd582f"></script>
<script src="${pageContext.request.contextPath}/include/js/reviewEvent.js"></script>
<script src="${pageContext.request.contextPath}/include/js/kakaoMap.js"></script>
<script>
// 마커를 담을 배열입니다
var xx = 0.0;
var yy = 0.0;
var mid = "<%=mid%>";
var geocoder = new kakao.maps.services.Geocoder();


 
$(document).ready(function(){
	function delay(item) { return new Promise(resolve => setTimeout(() => { console.log(item); resolve(); }, 10) ); }
	
	$(window).load(async function(){
		var max_move=1374;
		var left=0;
		var right=11;
		var switchImage=true;
		var jump=0;
		for(var i=0;true;i++){
			await delay(1);
			if(switchImage){
				$("#move-img").css('margin-left',i+'px');
			}
			//if(i>=655){
				//$("#move-img").css('margin-top',jump+'px');
				//$("#move-img").css('margin-bottom',jump+'px');Z
				//jump=jump+5;
			//}
			if(i>=max_move){
				$("#move-img").attr("id","move-img2");
				switchImage=false;
				left=i;
				i=0;
			}
			if(switchImage!=true){
				left=left-1;
				right=left;
				$("#move-img2").css('margin-left',right+'px');
			}
			if(right<=10){		
				$("#move-img2").attr("id","move-img");
				switchImage=true;
				right=11;
				i=0;
			}
			//661px
			
			//if(right)
			
			//console.log(i);
		}
	});
		
	if("${seName}"!=''){
		$("#loginbtn").remove();
		$("#meminsert").remove();
		$("#memlogin").append("<a href='../../mem/memberSelect.ggd' style='color:black' id='' class='comlogin'>${seName}</a>님 환영합니다. &nbsp;&nbsp;&nbsp;");
		$("#memlogin").append("<button type='button' class='comlogin' id='memlogout' onclick='logoutBtn()'>로그아웃</button>");
	}
	
	
	
	var circle = null;
	
	defaultgeo();
	
	function defaultgeo(){
		geocoder.addressSearch("<%=addr%>", function(result, status) {

		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {

		        yy = result[0].x;
		        xx = result[0].y;
		        
		        searchPlaces();
		        center();
		        setCenter();

		    } 
		});
	
	}


	

	
	$(document).on("click","#defaultgeo",function(){
		geocoder.addressSearch("<%=addr%>", function(result, status) {

		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {

		        yy = result[0].x;
		        xx = result[0].y;
		        
		        searchPlaces();
		        removeCircle();
		        center();
		        setCenter();
		    } 
		});

	});
	
	

	
	var placeId = "";
	// qwer = reBtn의 data
	var qwer = "";
	

	
});

$(document).on("click","#newgeo",function(){
	geocoder.addressSearch($('#newText').val(), function(result, status) {

	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {

	        yy = result[0].x;
	        xx = result[0].y;
	        
	        searchPlaces();
	        removeCircle();
	        center();
	        setCenter();
	    } 
	});   

});


$(document).on("click","#nowgeo",function(){
	if(navigator.geolocation){
		navigator.geolocation.getCurrentPosition(function(pos){
			// 위도
			xx = pos.coords.latitude;
			console.log(xx);
			// 경도
			yy = pos.coords.longitude;
			console.log(yy);		
			// 키워드로 장소를 검색합니다
			searchPlaces();
			//circle = null;
			//circle.setMap(map);
			removeCircle();

			center();
			setCenter();
		});
	}else{
		alert("해당 브라우저에서 현재 위치를 찾을 수 없습니다.");
	}
});





$(document).on("click","#delLi",function(){
	$(".popup-layer2").remove();
	//alert($(this).children(2).children(".renickname").text());
	var renickname_s = $(this).find(".renickname").text().slice(0,-1);
	console.log(renickname_s);
	var bool = false;
	bool = renickname_s == mid;
	console.log
	if(bool){

		
		var popupClose = $("<input>");
		popupClose.attr({"type":"button","id":"popupClose2", "value":"닫기"});
		
		//var alink = $("<input>");
		//alink.attr({"type":"button","id":"reviewInsBtn","value":"리뷰 등록"});
		console.log($(this));
		$("<div/>").addClass("popup-layer2").appendTo("body").fadeIn();
		$(".popup-layer2").append(popupClose);
		insertForm();
		$("#renickname").val("<%=mid%>");
		$("#renickname").prop("readonly",true);
		$('#reviewForm').append('<input type="button" id="updateBtn" style="margin-top:10px;"value="수정하기">');
		$('#reviewForm').append('<input type="button" id="deleteBtn" style="margin-left:10px; margin-top:10px;" value="삭제하기">');
		$("#");
		
		let selectURL = "reviewSelect.ggd";
		console.log(typeof(placeId));
		let renum = $(this).find("#renum").val()
		var form = new FormData($("#reviewForm")[0]);
		form.append("kakaoid",placeId);
		form.append("renum",renum);
		console.log($(this).find("#renum").val());
		$.ajax({
			url:selectURL,
			type:"POST",
			data:form,
			enctype: 'application/x-www-form-urlencoded',
			cache:false,
			processData: false,
	        contentType: false,	
			success:whenSuccess,
			error:whenError
		});
		function whenSuccess(resData){
			console.log(resData);
			var str = resData.split(",");
			console.log(str[2]);
			$("#renickname").val(str[0]);
			$("input:radio[name='rerating']:radio[value='"+str[2]+"']").attr('checked', true);	
			$("#recontent").val(str[1]);
			$("#renumVal").val(str[3]);
		}
		function whenError(e){
			console.log()
		}
		

	}
});


function center(){
	// 지도에 표시할 원을 생성합니다
	//	var circle = new kakao.maps.Circle({
	circle = new kakao.maps.Circle({
	    center : new kakao.maps.LatLng(xx, yy),  // 원의 중심좌표 입니다 
	    radius: 10, // 미터 단위의 원의 반지름입니다 
	    strokeWeight: 1, // 선의 두께입니다 
	    strokeColor: '#ff0000', // 선의 색깔입니다
	    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
	    strokeStyle: 'dashed', // 선의 스타일 입니다
	    fillColor: '#ff0000', // 채우기 색깔입니다
	    fillOpacity: 1  // 채우기 불투명도 입니다   
	}); 

	// 지도에 원을 표시합니다 
	circle.setMap(map); 	
}
	
function removeCircle() {
	
	console.log(">>> : " + circle);
    circle.setMap(null);
}



var markers = [];

//var x = 37.473083599999995;
//var y = 126.8788276;
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(xx, yy), // 지도의 중심좌표
        level: 4 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();  

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});


function setCenter() {            
    // 이동할 위도 경도 위치를 생성합니다 
    console.log(xx, yy);
    var moveLatLon = new kakao.maps.LatLng(xx, yy);
    
    // 지도 중심을 이동 시킵니다
    map.setCenter(moveLatLon);
    map.setLevel(4);
}

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {
	document.getElementById('keyword').value = "<%=food%>";
    var keyword = document.getElementById('keyword').value;

    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
    }
    console.log(xx, yy)
    
	var options = {
			location : new kakao.maps.LatLng(xx, yy),
			radius : 1000
	}
    
    
    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch( keyword, placesSearchCB, options); 
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
		console.log(data);
        $('#menu_wrap').show();
        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);

        // 페이지 번호를 표출합니다
        displayPagination(pagination);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        $('#menu_wrap').hide();
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {
	// console.log("콩")
    var listEl = document.getElementById('placesList'), 
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(), 
    bounds = new kakao.maps.LatLngBounds(), 
    listStr = '';
    
    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();
    
    for ( var i=0; i<places.length; i++ ) {
		
        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i), 
            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다
		// console.log(places[i].id)
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function(marker, title) {
            kakao.maps.event.addListener(marker, 'mouseover', function() {
                displayInfowindow(marker, title);
            });

            kakao.maps.event.addListener(marker, 'mouseout', function() {
                infowindow.close();
            });

            itemEl.onmouseover =  function () {
                displayInfowindow(marker, title);
            };

            itemEl.onmouseout =  function () {
                infowindow.close();
            };
        })(marker, places[i].place_name);

        fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
   // map.setBounds(bounds);
}



// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {
	console.log(places.id);
    var el = document.createElement('li'),
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                '<div class="info">' +
                '   <h5 id="place">' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
    } else {
        itemStr += '    <span>' +  places.address_name  + '</span>'; 
    }
                 
	    itemStr += '  <span class="tel">' + places.phone  + '</span>'           
		itemStr += '  <span class="review_'+(index + 1) +'"><input type="button" id="reBtn" value="리뷰보기" onClick="reBtn(this)">' +
					 '</span>' + '<input type="hidden" id="kakaoid" value='+places.id+'>' + "</div>";         
	
    el.innerHTML = itemStr;
    el.className = 'item';
    ratingRoad(places.id, index);
    return el;
}





// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }   
    markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i; 

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    for (i=1; i<=pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {   
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
}
 


$(document).on("click", "#popupClose",function(){
	$(".popup-layer").fadeOut(function(){ $(this).remove(); });
	$(".popup-layer2").fadeOut(function(){ $(this).remove(); });
});

$(document).on("click", "#map", function(){
	$(".popup-layer").fadeOut(function(){ $(this).remove(); });
});

$(document).on("click", "#newgeo", function(){
	$(".popup-layer").fadeOut(function(){ $(this).remove(); });
	$(".popup-layer2").fadeOut(function(){ $(this).remove(); });
});

$(document).on("click", "#nowgeo", function(){
	$(".popup-layer").fadeOut(function(){ $(this).remove(); });
	$(".popup-layer2").fadeOut(function(){ $(this).remove(); });
});

$(document).on("click", "#defaultgeo", function(){
	$(".popup-layer").fadeOut(function(){ $(this).remove(); });
	$(".popup-layer2").fadeOut(function(){ $(this).remove(); });
});

$(document).on("click", ".popup", function(e){
	e.stopPropagation();
});




$(document).on("click","#insertForm",function(){
	$(".popup-layer2").remove();
	
	var popupClose = $("<input>");
	popupClose.attr({"type":"button","id":"popupClose2", "value":"닫기"});
	
	var alink = $("<input>");
	alink.attr({"type":"button","id":"reviewInsBtn","value":"리뷰 등록"});
	
	$("<div/>").addClass("popup-layer2").html(
			$("<div/>").addClass("popup").html(
				$(this).html()
			)
		).appendTo("body").fadeIn();
	$(".popup-layer2").append(popupClose).append("<br>");
	insertForm();
	$("#renickname").val("<%=mid%>");
	$("#renickname").prop("readonly",true);
	
	$('#reviewForm').append('<input type="button" id="insertBtn" value="등록하기">');

});

$(document).on("click", "#popupClose2",function(){
	$(".popup-layer2").fadeOut(function(){ $(this).remove(); });
});

$(document).on("click", "#map", function(){
	$(".popup-layer2").fadeOut(function(){ $(this).remove(); });
});

$(document).on("click", "#reBtn", function(){
	$(".popup-layer2").fadeOut(function(){ $(this).remove(); });
});


$(document).on("click", ".popup", function(e){
	e.stopPropagation();
});


// 리뷰 등록
$(document).on("click","#insertBtn",function(){
	var form = new FormData($("#reviewForm")[0]);
	form.append("kakaoid",placeId);
	let insertURL = "reviewInsert.ggd";
	let method = "POST"
	$.ajax({
		url:insertURL,
		type:method,
		enctype: 'multipart/form-data',
		data:form,
		cache:false,
		processData: false,
        contentType: false,	
		success:whenSuccess,
		error:whenError
	});
	
	function whenSuccess(resData){
		// 리스트 조회 후 태그 생성 
		//alert(resData);
		clearInsert();
		$(".popup-layer *").remove();
		reBtn(qwer);
		$(".popup-layer2").fadeOut(function(){ $(this).remove(); });
		//console.log($(this).parent());
	}
	
	function whenError(e){
		alert("에러발생 >>> : " + e);
	}
	
	function clearInsert(){
		$("#renickname").val("");
		$("#repass").val("");
		$("#recontent").val("");
	}
});

$(document).on("keyup","#recontent",function(){
	
	cut_200();
});



// 글자수 제한
function cut_200(){
//	console.log("콩");

	var str = $("#recontent").val();
	var strLeng = str.length;
	console.log(strLeng);
	while(getTextLength(str) > 200){
		strLeng--;
		str = str.substring(0,strLeng);
		$("#recontent").val(str);
	}
	$(".bytes").text(getTextLength(str));
}


function getTextLength(str){
	var len = 0;
	for (var i=0; i<str.length; i++){
		if(escape(str.charAt(i)).length == 6){
			len++;
		}
		len++;
	}
	return len;
}




// 리뷰 수정
$(document).on("click","#updateBtn",function(){
	updateBtn();
});



// 리뷰 삭제
$(document).on("click", "#deleteBtn", function(){	
	deleteBtn();
});

function logoutBtn(){
	$("#loginForm").attr("action","../emotion/logout.ggd");
	$("#loginForm").attr("method","POST");
	$("#loginForm").attr("enctype","application/x-www-form-urlencoded");
	$("#loginForm").submit();
}

</script>
</body>
</html>