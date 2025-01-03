<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="g.g.d.com.mem.common.TempPassword" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 디바이스에 최적화된 크기로 출력됨 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0
      maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<!-- 추가 -->
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<title>GOGODA 회원 가입</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/main.css" />


<!-- daum 도로명주소 찾기 api -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">
//모든 공백 체크 정규식 
var empJ = /\s/g; 
//아이디 정규식 
var idJ = /^[a-z0-9][a-z0-9_\-]{4,19}$/; 
//비밀번호 정규식 
var pwJ = /^[A-Za-z0-9]{4,12}$/;
//이름 정규식 
var nameJ = /^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/; 
//이메일 검사 정규식 
var emailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; 
//휴대폰 번호 정규식 
var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/; 
/^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/ 

var birthJ = false; 

var address = $('#maddrdetail'); 

$(document).ready(function(){

	//*****************************************************
	var oldmid='';
	var key='';
	var mnum='';
	var oldmemail='';
	// mnum hidden
	$('#mnum').hide();
	
	var address = $('#maddrdetail'); 
	
	//아이디 중복확인 
	$("#mid").blur(function() { 
		if(oldmid!=''){
			if(oldmid!=$('#mid').val()){
				$('#id_check').text('다시 중복 확인을 해주십시오'); 
	            $('#id_check').css('color', 'red'); 
			}
			if(oldmid==$('#mid').val()){
				$('#id_check').text('확인 완료'); 
	            $('#id_check').css('color', 'green'); 
			}
		}
    });
	$("#memail").blur(function(){
		if(oldmemail!=''){
			if(oldmemail!=$('#memail').val()){
				$('#code_check').text('이메일 내용이 변동되었습니다. 다시 체크해주세요'); 
	            $('#code_check').css('color', 'red'); 
			}
			if(oldmemail==$('#memail').val()){
				$('#code_check').text('인증 완료!'); 
	            $('#code_check').css('color', 'green'); 
			}
		}
	});
	//*****************************************************
	
	//*****************************************************
	// id 중복확인  	
    $(document).on("click", "#midbtn", function(){
    	console.log("midbtn >>> : ");
    	//alert("midbtn >>> : "+$('#mid').val().length);
        if($('#mid').val().length>=5){ 
	    	let idcheckURL = "idCheck.ggd";
	    	let method = "POST";
	    	let midVal = $('#mid').val();
	         
	    	let dataParam = {"mid": midVal};
	         
	    	$.ajax({
	        	url: idcheckURL,
	            type: method, 
	            data: dataParam,
	            success: whenSuccess,
	            error: whenError
	        });
	         
	        function whenSuccess(resData){
	        	//alert(resData);
	            var sVal = resData;   
	            if ('ID_GOOD' == sVal){
	               alert("사용할 수 있는 아이디 입니다.");
	               $('#id_check').text('확인 완료'); 
	               $('#id_check').css('color', 'green'); 
	               oldmid=$('#mid').val()
	            }else{
	               alert("이미 사용 중인 아이디입니다.");
	               $('#id_check').text(''); 
	               $('#id_check').css('color', 'red'); 
	               $("#mid").val('');
	               $("#mid").focus();
	            }
	         }
			function whenError(){
				
	        }
        }else{
        	alert("아이디를 제대로 입력해주십시오.");
            $('#id_check').text('5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.'); 
            $('#id_check').css('color', 'red'); 
        }
    });
  //*****************************************************

/////////////////////////////////////////////////////////////////////////////////////
//$(function() {
	var isCertification = false;

	$("#memailbtn").click(function() {// 메일 입력 유효성 검사
		var mail = $("#memail").val(); // 사용자의 이메일 입력값.
		if (mail == "") {
			alert("메일 주소가 입력되지 않았습니다.");
		} else {
			alert("인증코드를 발송하였습니다.\n이메일을 확인하시기 바랍니다.");
			//returnGetData(meail);
			//mail = mail + "@" + $(".domain").val(); // 셀렉트 박스에 @뒤 값들을 더함.
			$.ajax({
				type : "POST",
				url : 'mailCheck.ggd',
				dataType : 'text',
				async : false,
				data : {
					"mail" : mail
				},
				success : whenSuccess,
				error: whenError
			});			
			
			$("#emcode").css("display", "block");
			$("#code_check").css("display", "block");
			function whenSuccess(keyData) {
				key=keyData;
				console.log(keyData);					
			}
			function whenError(){
				
			}
		}
	});
	
 //});   
      // 인증 키 일치여부 확인 
      $("#emcode").on("propertychange change keyup paste input", function() {
    	//alert("key>>>"+key);
  		if ($("#emcode").val() == key) {
  			$("#code_check").text("인증 완료!").css("color", "green");
  			oldmemail=$("#memail").val();
  			isCertification = true;
  		} else {
  			$("#code_check").text("다시 확인하여 주시기 바랍니다.").css("color", "red");
  			isCertification = false;
  		}
  	});
      
      // 회원 가입 전 메일 인증 여부 확인
      // 메일 인증이 되었다면 submit버튼을 클릭하면 서버측으로 사용자의 입력값이 전송됩니다.
      
  /////////////////////////////////////////////////////////////////////////////////////	
//*****************************************************
    $(document).on("click","#submitBtn",function(){ 
    	var checkMember=0;
    	if($('#id_check').text()!='확인 완료'){
    		alert('아이디를 확인하세요.');
    		return false;
    	}else{checkMember=checkMember+1;}//1
    	
    	if($('#zipcode').val()==''){
            alert('우편번호를 확인하세요.'); 
            return false; 
    	}else{checkMember=checkMember+1;}//2
    	if($('#maddrdetail').val()==''){
    		alert('상세 주소를 확인해주세요.');
    		return false;
    	}else{checkMember=checkMember+1;}//3
    	if($('#code_check').text()!='인증 완료!'){
    		alert('인증코드를 확인해주세요.');
    		return false;
    	}else{checkMember=checkMember+1;}//4
    	
    	// 아이디 정규식
		var inval_Arr = new Array(8).fill(false); 
        if (idJ.test($('#mid').val())) { 
        	inval_Arr[0] = true; 
        	checkMember=checkMember+1;//5
        } else { 
           inval_Arr[0] = false; 
           alert('아이디를 확인하세요.'); 
           return false; 
        } 
        
        // 비밀번호가 같은 경우 && 비밀번호 정규식 
        if (($('#mpw').val() == ($('#mpw2').val())) && pwJ.test($('#mpw').val())) { 
           inval_Arr[1] = true;
           checkMember=checkMember+1;//6
        } else { 
           inval_Arr[1] = false; 
           alert('비밀번호를 확인하세요.'); 
           return false; 
        } 
        
        // 이름 정규식 
        if (nameJ.test($('#mname').val())) { 
           inval_Arr[2] = true; 
           checkMember=checkMember+1;//7
        } else { inval_Arr[2] = false; 
           alert('이름을 확인하세요.'); 
           return false; 
        } 
        
        // 생년월일 정규식 
        if (birthJ) { 
           console.log(birthJ); 
           inval_Arr[3] = true; 
           checkMember=checkMember+1;//8
        } else { 
           inval_Arr[3] = false; 
           alert('생년월일을 확인하세요.'); 
           return false; 
        } 
        
        // 이메일 정규식 
        if (emailJ.test($('#memail').val())){ 
           console.log(phoneJ.test($('#memail').val())); 
           inval_Arr[4] = true;
           checkMember=checkMember+1;//9
        } else { 
           inval_Arr[4] = false; 
           alert('이메일을 확인하세요.'); 
           return false; 
        } 
        
        // 휴대폰번호 정규식
        if (phoneJ.test($('#mhp').val())) { 
           console.log(phoneJ.test($('#mhp').val())); 
           inval_Arr[5] = true;
           checkMember=checkMember+1;//10
        } else { 
           inval_Arr[5] = false; 
           alert('휴대폰 번호를 확인하세요.'); 
           return false; 
        } 
        
        //주소확인
        if(address.val() == ''){ 
           inval_Arr[7] = false; 
           alert('주소를 확인하세요.'); 
           return false; 
        }else {
           inval_Arr[7] = true;
           checkMember=checkMember+1;//11
        }

		if(checkMember<=11){ // 유효성 모두 통과 
           //alert('설문에 응답하면 회원가입이 완료됩니다.'); 
           	let idcheckURL = "memberInsert.ggd";
	    	let method = "POST";
	    	let mname=$('#mname').val();
	    	let mid=$('#mid').val();
	    	let mpw=$('#mpw').val();
	    	let mbirth=$('#mbirth').val();
	    	let mgender=$('#mgender').val();
	    	let mhp=$('#mhp').val();
	    	let memail=$('#memail').val();
	    	let mzipcode=$('#mzipcode').val();
	    	let maddr=$('#maddr').val();
	    	let maddrdetail=$('#maddrdetail').val();
	    	$.ajax({
				type : 'POST',
				url : 'memberInsert.ggd',
				dataType : 'text',
				async : false,
				data : {
					"mname":mname,
	    			"mid":mid,
	    			"mpw":mpw,
	    			"mbirth":mbirth,
	    			"mgender":mgender,
	    			"mhp":mhp,
	    			"memail":memail,
	    			"mzipcode":mzipcode,
	    			"maddr":maddr,
	    			"maddrdetail":maddrdetail
				},
				success : whenSuccess,
				error : whenError});
				
	    		function whenSuccess(data) {
	    			console.log(data);
	    			mnum=data;
	    			//alert(mnum);
					$.ajax({
						type : 'POST',
						url : 'moveSurvey.ggd',
						dataType : "html",
						data:mnum,
						cache : false
					})
					.done(function(dataHtml){
						//alert(dataHtml);
						dataHtml=dataHtml+"\n<input type='hidden' id='mnum' name='mnum' value='"+mnum+"'>";
						$('#memForm').children().remove();
						$('#memForm').html(dataHtml);
						
					});
										
				}
	    		function whenError(){
	    			
	    		}
        } else{ 
           	alert('정보를 다시 확인하세요.') 
        }
     });
  //*****************************************************
     $('#mid').blur(function() { 
        if (idJ.test($('#mid').val())) { 
           console.log('true'); 
        } else { 
           console.log('false'); 
           $('#id_check').text('5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.'); 
           $('#id_check').css('color', 'red'); 
        } 
     }); 
   //*****************************************************
     
     $('#mpw').blur(function() { 
        if (pwJ.test($('#mpw').val())) { 
           console.log('true'); 
           $('#pw_check').text(''); 
        } else { 
           console.log('false'); 
           $('#pw_check').text('4~12자의 숫자 , 문자로만 사용 가능합니다.'); 
           $('#pw_check').css('color', 'red');
        } 
     }); 
     
     //1~2 패스워드 일치 확인 
     $('#mpw2').blur(function() { 
        if ($('#mpw').val() != $(this).val()) { 
           $('#pw2_check').text('비밀번호가 일치하지 않습니다.'); 
           $('#pw2_check').css('color', 'red'); 
        } else { $('#pw2_check').text(''); 
       	} 
     }); 
     
     //이름에 특수문자 들어가지 않도록 설정 
     $("#mname").blur(function() { 
        if (nameJ.test($(this).val())) { 
           console.log(nameJ.test($(this).val())); 
           $("#name_check").text(''); 
        } else { 
           $('#name_check').text('한글 2~4자 이내로 입력하세요. (특수기호, 공백 사용 불가)');
           $('#name_check').css('color', 'red'); 
        } 
     }); 
          
     // 생일 유효성 검사 
     var birthJ = false; 
     
     // 생년월일 birthJ 유효성 검사 
     $('#mbirth').blur(function(){ 
        var dateStr = $(this).val(); 
        var year = Number(dateStr.substr(0,4)); // 입력한 값의 0~4자리까지 (연) 
        var month = Number(dateStr.substr(4,2)); // 입력한 값의 4번째 자리부터 2자리 숫자 (월) 
        var day = Number(dateStr.substr(6,2)); // 입력한 값 6번째 자리부터 2자리 숫자 (일) 
        var today = new Date(); // 날짜 변수 선언 
        var yearNow = today.getFullYear(); // 올해 연도 가져옴 
        
        if (dateStr.length <=8) { 
           // 연도의 경우 1900 보다 작거나 yearNow 보다 크다면 false를 반환합니다. 
           if (year > yearNow || year < 1900 ){ 
              $('#birth_check').text('생년월일을 확인해주세요'); 
              $('#birth_check').css('color', 'red'); 
           } else if (month < 1 || month > 12) { 
              $('#birth_check').text('생년월일을 확인해주세요 '); 
              $('#birth_check').css('color', 'red'); 
           } else if (day < 1 || day > 31) { 
              $('#birth_check').text('생년월일을 확인해주세요 '); 
              $('#birth_check').css('color', 'red'); 
           } else if ((month==4 || month==6 || month==9 || month==11) && day==31) { 
              $('#birth_check').text('생년월일을 확인해주세요 '); 
              $('#birth_check').css('color', 'red'); 
           } else if (month == 2) { 
              var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)); 
              if (day>29 || (day==29 && !isleap)) { 
                 $('#birth_check').text('생년월일을 확인해주세요 '); 
                 $('#birth_check').css('color', 'red'); 
              } else{ $('#birth_check').text(''); birthJ = true; 
              } 
           }else{ 
              $('#birth_check').text(''); birthJ = true; 
           }//end of if 
        }else{ 
           //1.입력된 생년월일이 8자 초과할때 : auth:false 
           $('#birth_check').text('생년월일을 확인해주세요 '); 
           $('#birth_check').css('color', 'red'); 
        } 
     }); //End of method /* 
     
     // email check
     $("#memail").blur(function() { 
        if (emailJ.test($(this).val())) { 
          console.log(nameJ.test($(this).val()));
           $("#email_check").text(''); 
        } else { 
           $('#email_check').text('이메일 양식을 확인해주세요.'); 
           $('#email_check').css('color', 'red'); 
        } 
     });    
     
     // 휴대전화 
     $('#mhp').blur(function(){ 
        if(phoneJ.test($(this).val())){ 
           console.log(nameJ.test($(this).val())); 
           $("#phone_check").text(''); 
        } else { 
           $('#phone_check').text('핸드폰번호를 확인해주세요 '); 
           $('#phone_check').css('color', 'red'); 
        } 
     });  
     
});


//우편번호 찾기 버튼 클릭시 발생 이벤트 
  function execPostCode() { 
     new daum.Postcode({ 
        oncomplete: function(data) { 
           // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분. 
           // 도로명 주소의 노출 규칙에 따라 주소를 조합한다. 
           // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다. 
           var fullRoadAddr = data.roadAddress; // 도로명 주소 변수 
           var extraRoadAddr = ''; // 도로명 조합형 주소 변수 
           
           // 법정동명이 있을 경우 추가한다. (법정리는 제외) 
           // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다. 
           if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){ 
              extraRoadAddr += data.bname; 
           } 
           
           // 건물명이 있고, 공동주택일 경우 추가한다. 
           if(data.buildingName !== '' && data.apartment === 'Y'){ 
              extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
           } 
           
           // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다. 
           if(extraRoadAddr !== ''){ 
              extraRoadAddr = ' (' + extraRoadAddr + ')'; 
           } 
           
           // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다. 
           if(fullRoadAddr !== ''){ 
              fullRoadAddr += extraRoadAddr; 
           } 
           
           // 우편번호와 주소 정보를 해당 필드에 넣는다. 
           console.log(data.zonecode); 
           console.log(fullRoadAddr); 
           /* 
           var a = console.log(data.zonecode); 
           var b = console.log(fullRoadAddr); 
           if(a == null || b = null){
              alert("주소를 확인하세요."); return false; } 
           */
           
           $("[name=mzipcode]").val(data.zonecode); 
           $("[name=maddr]").val(fullRoadAddr); 
           
           document.getElementById('mzipcode').value = data.zonecode; //5자리 새우편번호 사용 
           document.getElementById('maddr').value = fullRoadAddr; 
           //document.getElementById('mem_detailaddress').value = data.jibunAddress; 
        } 
     }).open(); 
  } 
</script>
</head>
<!-- ***************************************************** 프롬 위치변경, 내용 일부 삭제-->
<form class="form-horizontal" id="memForm" name="memForm">
<!-- ***************************************************** -->
<body>
<!-- Navbar (sit on top) -->
<div class="w3-top">
	<div class="w3-bar w3-white w3-wide w3-padding w3-card">
		<a href="../../emotion/mainpage.ggd">
			<img src="/logo/GOGODA-logo.png" style="width:12%; height:12%">
		</a>
	</div>
</div><br><br><br><br><br>
<!--  -->
<div class="container" style="width:70%;">
	<div class="row">
	<div class="col-md-8">
		<section>      
        <h1 class="entry-title"><span><b>Sign Up</b></span> </h1>
        <hr><!-- onsubmit="return submitCheck() -->
        
		
		<div class="form-group" style="display:none">
          	<label class="control-label col-sm-3">회원번호 <span class="text-danger">*</span></label>
       		<div class="col-md-8 col-sm-9">
        		<input type="text" class="form-control" name="mnum" id="mnum" placeholder="회원 번호" readonly />
       		</div>
        </div>
        
        <div class="form-group">
          	<label class="control-label col-sm-3">이름 <span class="text-danger">*</span></label>
          	<div class="col-md-8 col-sm-9">
            	<input type="text" class="form-control" name="mname" id="mname" placeholder="이름" />
            	<div class="font" id="name_check"></div>            	
          	</div>
        </div>
        
        <div class="form-group">
          	<label class="control-label col-sm-3">아이디 <span class="text-danger">*</span></label>
          	
          	<div class="col-md-5 col-sm-5">
            	<input type="text" class="form-control" name="mid" id="mid" placeholder="아이디" />
            	<div class="font" id="id_check"></div>
          	</div>
            <div class="col-md-4 col-sm-4">	
	            <button type="button" id="midbtn" class="btn btn-info" >중복 확인</button>
	            <!-- <button type="button" id="midbtnjson">아이디체크(JSON)</button> -->
	        </div>
        </div>
        
		<div class="form-group">
			<label class="control-label col-sm-3">비밀번호 <span class="text-danger">*</span></label>
			<div class="col-md-8 col-sm-9">
				<div class="input-group">
              		<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
              		<input type="password" class="form-control" name="mpw" id="mpw" placeholder="비밀번호" />
           	  		<div class="font" id="pw_check"></div>
           		</div>
          	</div>
        </div>
        
		<div class="form-group">
			<label class="control-label col-sm-3">비밀번호 확인 <span class="text-danger">*</span></label>
			<div class="col-md-8 col-sm-9">
            	<div class="input-group">
              		<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
              		<input type="password" class="form-control" name="mpw2" id="mpw2" placeholder="비밀번호 확인" />
            		<div class="font" id="pw2_check"></div>
            	</div>
          	</div>
        </div>
        
		<div class="form-group">
			<label class="control-label col-sm-3">생년월일 <span class="text-danger">*</span></label>
			<div class="col-md-8 col-sm-9">
				<input type="text" class="form-control" name="mbirth" id="mbirth" placeholder="예:19940121" />
				<div class="font" id="birth_check"></div>
			</div>
        </div>
        
        <div class="form-group">
          	<label class="control-label col-sm-3">성별 <span class="text-danger">*</span></label>
          	<div class="col-md-8 col-sm-9">
            	<label><input name="mgender" id="mgender" type="radio" value="M" checked>남 </label>
            	<label><input name="mgender" id="mgender" type="radio" value="F" >여 </label>
          	</div>
        </div>
        
        <div class="form-group">
			<label class="control-label col-sm-3">핸드폰번호 <span class="text-danger">*</span><br>
			<small>('-' 없이 번호만 입력)</small></label>
 			<div class="col-md-8 col-sm-9">
            	<input type="text" class="form-control" name="mhp" id="mhp" placeholder="예:01055557777" />
            	<div class="font" id="phone_check"></div>
          	</div>
        </div>
        
		<div class="form-group">
			<label class="control-label col-sm-3">이메일 <span class="text-danger">*</span></label>
          	<div class="col-md-5 col-sm-5">
				<div class="input-group">
	              	<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
	              	<input type="email" class="form-control" name="memail" id="memail" placeholder="sunny@example.com" /> 
              	</div>
          	</div>
          	
    <!--       	<input type="text" placeholder="E-Mail" name="memail" id="memail">@ 
    			<select name="domain" id="domain">
					<option value="gmail.com">gmail.com</option>
					<option value="naver.com">naver.com</option>
					<option value="daum.net">daum.net</option>
					<option value="yahoo.co.kr">yahoo.co.kr</option>
				</select>
	  -->
          	
            <div class="col-md-4 col-sm-4">	
	            <button type="button" id="memailbtn" class="btn btn-default" >인증 코드 발송</button>
	        </div>          	
        </div>
        <div class="form-group">
			<label class="control-label col-sm-3"></label>
          	<div class="col-md-5 col-sm-5">
	              	<input type="text" class="form-control" name="emcode" id="emcode" placeholder="인증코드" /> 
	              	<div class="font" id="code_check"></div>
          	</div>          	
        </div>
        
        <div class="form-group">
			<label class="control-label col-sm-3">주소 <span class="text-danger">*</span></label>        
			<div class="col-md-5 col-sm-5">
				<input type="text" class="form-control" name="mzipcode" id="mzipcode" placeholder="우편번호" readonly />
            </div>
            <div class="col-md-4 col-sm-4">	
	            <button type="button" id="memaddr" class="btn btn-default" onclick="execPostCode();">우편번호 찾기</button>
	        </div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-3"><span class="text-danger"></span></label>
          	<div class="col-md-8 col-sm-9">
	            	<input type="text" class="form-control" name="maddr" id="maddr" placeholder="도로명 주소" readonly/>
            </div> 
        </div>

		<div class="form-group">
			<label class="control-label col-sm-3"><span class="text-danger"></span></label>
          	<div class="col-md-8 col-sm-9">
	            	<input type="text" class="form-control" name="maddrdetail" id="maddrdetail" placeholder="상세주소" />	
            </div> 
        </div>

        <div class="form-group">
          <div class="col-xs-offset-5 col-xs-10">
          <!-- *************************************** 회가입 id=추가, type=변경 --> 
            <input name="Submit" type="button" id="submitBtn" value="다음 페이지" class="btn btn-primary btn-lg">
          <!--****************************************-->
          </div>
        </div>
      
      </section>
    </div>
	</div>
</div>
<!-- Footer -->
<footer class="w3-center w3-black w3-padding-16">
  <p>Powered by <a href="../../emotion/mainpage.ggd" title="GOGODA" target="_blank" class="w3-hover-text-green">GOGODA</a></p>
</footer>
</body>
</form>
</html>