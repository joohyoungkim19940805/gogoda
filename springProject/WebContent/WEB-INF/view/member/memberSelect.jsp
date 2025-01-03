<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="g.g.d.com.mem.vo.MemberVO" %>
<%@ page import="g.g.d.com.mem.common.TempPassword" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 디바이스에 최적화된 크기로 출력됨 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0
      maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<!-- 추가 -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/main.css" />

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<title>회원 정보 상세 페이지</title>

<!-- daum 도로명주소 찾기 api -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<%
String mnum=request.getAttribute("mnum").toString();
//System.out.println("====================================="+mnum);
%>

<script>
$(document).ready(function(){
	
	// 비밀번호 확인 시작-----------------------------------------------------------
	$(document).on("click", "#mpwbtn", function(){
		console.log("midbtn >>> : ");
		//alert("mpwbtn >>> : ");

		let pwcheckURL = "pwCheck.ggd"
		let method = "POST";
		let dataParam = {
			mnum: $("#mnum").val(),
			mpw: $("#mpw").val(),
		};
		//dataParam = $("#boardUpdateForm").serialize();
		//alert("dataParam >>> : " + dataParam);
		
		$.ajax({
			url: pwcheckURL,
			type: method, // 요청 ContentType
			data: dataParam,
            // contentType: "application/json;",
            // dataType: "json", // 응답 ContentType
			success: whenSuccess,
			error: whenError
		});
		
		function whenSuccess(resData){
			//alert("resData >>> : " + resData);
			console.log(resData);
			var sVal = resData;
			if (resData){
				//$('#mpw').attr('disabled', true); // attr(), 1.6 이후 사용 prop()
				$('#mpwbtn').attr('style', 'visibility:hidden');
				$('#mpw').val();
				$('#mpw').focus();
				$('#pwC2').attr('style', 'display:show');
				$('#btnUpdate').attr('disabled', false);
				$('#btnDelete').attr('disabled', false);
				//$('#btnUpdate').css('background-color', 'red');
				//$('#btnDelete').css('color', 'red');
			}else{
				$('#mpw').val('');
				$('#mpw').focus();
				$('#btnUpdate').attr('disabled', true);
				$('#btnDelete').attr('disabled', true);
			}
		}
		function whenError(e){
			//alert("e >>> : " + e.responseText);	
		}
	});
	// 비밀번호 확인 끝-----------------------------------------------------------
	
	// 인증코드 발송  시작-----------------------------------------------------------
	var isCertification = false;
	var key = '<%=TempPassword.setTempPassWord(10)%>';
	$("#memailbtn").click(function() {// 메일 입력 유효성 검사
		var mail = $("#memail").val(); // 사용자의 이메일 입력값.
		if (mail == "") {
			alert("메일 주소가 입력되지 않았습니다.");
		} else {
			//alert(mail);
			//returnGetData(meail);
			//mail = mail + "@" + $(".domain").val(); // 셀렉트 박스에 @뒤 값들을 더함.
			$.ajax({
				type : 'get',
				url : 'mailCheck.ggd',
				dataType : 'json',
				async : "false",

				data : {
					"mail" : mail,
					"key" : key
				},
				success : function(data) {
					//alert(data);
					//console.log(data);					
				}
			});			
			alert("인증코드를 발송하였습니다.\n이메일을 확인하시기 바랍니다.");
			$("#emcode").css("display", "block");
			$("#code_check").css("display", "block");
		}
		//alert(key);
	});
	async function returnGetData(email){
		let data='';
		data=await emaulCon(email);
		//alert(data);
	}
	function emaulCon(email){
		var sendURL="";		
	}
	// -----인증 키 일치여부 확인 ---------------------------------------------------
	$("#emcode").on("propertychange change keyup paste input", function() {
		//alert("key>>>"+key);
		if ($("#emcode").val() == key) {
			$("#code_check").text("인증 완료!").css("color", "black");
  			isCertification = true;
  		} else {
  			$("#code_check").text("다시 확인하여 주시기 바랍니다.").css("color", "red");
  			isCertification = false;
  		}
  	});
	// 인증코드 발송  끝-----------------------------------------------------------
	
	//1~2 패스워드 일치 확인 
     $('#mpw2').blur(function() { 
        if ($('#mpw').val() != $(this).val()) { 
           $('#pw2_check').text('비밀번호가 일치하지 않습니다.'); 
           $('#pw2_check').css('color', 'red'); 
        } else { $('#pw2_check').text(''); 
       	} 
     }); 
     
	
	//  U
	$(document).on("click", "#btnUpdate", function(){
		//확인 대화상자
		if($('#mpw2').val().length>1){
			if($('#mpw').val()==$('#mpw2').val()){
			
				if(confirm("수정하시겠습니까?")){
					//document.form1.action = "gogodaProject/memberUpdate.ggd";
					//document.form1.submit();
					$.ajax({
						type : 'POST',
						url : 'memberUpdate.ggd',
						dataType : 'text',
						async : false,
						data : {
							"mnum":$('#mnum').val(),
							"mname":$('#mname').val(),
			    			"mid":$('#mid').val(),
			    			"mpw":$('#mpw').val(),
			    			"mbirth":$('#mbirth').val(),
			    			"mgender":$('#mgender').val(),
			    			"mhp":$('#mhp').val(),
			    			"memail":$('#memail').val(),
			    			"mzipcode":$('#mzipcode').val(),
			    			"maddr":$('#maddr').val(),
			    			"maddrdetail":$('#maddrdetail').val()
						},
						success : whenSuccess,
						error : whenError});
						
			    		function whenSuccess(data) {
							alert("다시 로그인해주세요");
							location.href="../../../../../emotion/logout.ggd";
			    		}
			    		function whenError(){
			    			
			    		}
				}
			}else{alert("비밀번호가 일치하지 않습니다.");}
		}else{alert("비밀번호가 일치하지 않습니다.");}
	});
	
	//  D
	$(document).on("click", "#btnDelete", function(){
		// 확인 대화상자	
		if(confirm("삭제하시겠습니까?")){
			//document.form1.action = "/gogodaProject/memberDelete.ggd";
			//document.form1.submit();
			$("#memberUpdateForm").attr({ "method":"POST","action":"memberDelete.ggd"}).submit();
		}
	});
});

//------ 우편번호 찾기  블럭  시작 -----------------------------------------------
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
// ------ 우편번호 찾기 발송  블럭 끝      -----------------------------------------------
</script>
</head>
<!-- ***************************************************** 프롬 위치변경, 내용 일부 삭제-->
<form class="form-horizontal" id="memberUpdateForm" name="memberUpdateForm">
<!-- ***************************************************** -->
<body>
<!--  -->

<!-- Navbar (sit on top) -->
<div class="w3-top">
	<div class="w3-bar w3-white w3-wide w3-padding w3-card">
		<a href="../../emotion/mainpage.ggd">
			<img src="/logo/GOGODA-logo.png" style="width:12%; height:12%">
		</a>
	</div>
</div><br><br><br><br><br><br><br>
<div class="container">
	<div class="row">
	<div class="col-md-8">
		<section>      
        <h1 class="entry-title"><span><b>${detail.mname}</b>님의 상세 정보</span> </h1>
        <hr>
			<input type="hidden" class="form-control" name="mnum" id="mnum" value='<%=mnum%>' readonly />
        
        <div class="form-group">
          	<label class="control-label col-sm-3">이름 <span class="text-danger">*</span></label>
          	<div class="col-md-8 col-sm-9">
            	<input type="text" class="form-control" name="mname" id="mname" value="${detail.mname}" readonly />        	
          	</div>
        </div>
        
		<div class="form-group">
          	<label class="control-label col-sm-3">아이디 <span class="text-danger">*</span></label>
          	<div class="col-md-8 col-sm-9">
            	<input type="text" class="form-control" name="mid" id="mid" value="${detail.mid}" readonly />
          	</div>
        </div>

        <div class="form-group">
          	<label class="control-label col-sm-3">비밀번호 <span class="text-danger">*</span></label>
          	
          	<div class="col-md-5 col-sm-5">
            	<div class="input-group">
              		<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
              		<input type="password" class="form-control" name="mpw" id="mpw" placeholder="비밀번호" />
          		</div>
          	</div>
            <div class="col-md-4 col-sm-4">	
	            <button type="button" id="mpwbtn" class="btn btn-info" >비밀번호 확인</button>
	        </div>
        </div>
        <div class="form-group" id="pwC2" style="display:none;">
			<label class="control-label col-sm-3">비밀번호 확인 <span class="text-danger">*</span></label>
			<div class="col-md-5 col-sm-5">
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
				<input type="text" class="form-control" name="mbirth" id="mbirth" value="${detail.mbirth}" readonly />
			</div>
        </div>
        
        <div class="form-group">
          	<label class="control-label col-sm-3">성별 <span class="text-danger">*</span></label>
          	<div class="col-md-8 col-sm-9">
            	<input type="text" class="form-control" name="mgender" id="mgender" value="${detail.mgender}" readonly>
          	</div>
        </div>
        
        <div class="form-group">
			<label class="control-label col-sm-3">핸드폰번호 <span class="text-danger">*</span><br>
			<small>('-' 없이 번호만 입력)</small></label>
 			<div class="col-md-8 col-sm-9">
            	<input type="text" class="form-control" name="mhp" id="mhp" value="${detail.mhp}" />
          	</div>
        </div>
        
		<div class="form-group">
			<label class="control-label col-sm-3">이메일 <span class="text-danger">*</span></label>
          	<div class="col-md-5 col-sm-5">
				<div class="input-group">
	              	<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
	              	<input type="email" class="form-control" name="memail" id="memail" value="${detail.memail}" /> 
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
				<input type="text" class="form-control" name="mzipcode" id="mzipcode" value="${detail.mzipcode}" readonly />
            </div>
            <div class="col-md-4 col-sm-4">	
	            <button type="button" id="memaddr" class="btn btn-default" onclick="execPostCode();">우편번호 찾기</button>
	        </div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-3"><span class="text-danger"></span></label>
          	<div class="col-md-8 col-sm-9">
				<input type="text" class="form-control" name="maddr" id="maddr" value="${detail.maddr}" readonly/>
            </div> 
        </div>
		<div class="form-group">
			<label class="control-label col-sm-3"><span class="text-danger"></span></label>
          	<div class="col-md-8 col-sm-9">
				<input type="text" class="form-control" name="maddrdetail" id="maddrdetail" value="${detail.maddrdetail}" />	
            </div> 
        </div>

		<div class="form-group">
          	<label class="control-label col-sm-3">회원 가입일</label>
          	<div class="col-md-8 col-sm-9">
            	<input type="text" class="form-control" name="minsertdate" id="minsertdate" value="${detail.minsertdate}"  readonly />
          	</div>
        </div>
		
		<div class="form-group">
          	<label class="control-label col-sm-3">회원정보 수정일</label> 	
          	<div class="col-md-8 col-sm-9">
            	<input type="text" class="form-control" name="mupdatedate" id="mupdatedate" value="${detail.mupdatedate}" readonly />
          	</div>
        </div>

        <div class="form-group">
          <div class="col-md-offset-5 col-xs-offset-5">
            <input type="button" name="btnUpdate" id="btnUpdate" value="수정" class="btn btn-primary btn-lg" disabled="disabled">        
            <input type="button" name="btnDelete" id="btnDelete" value="삭제" class="btn btn-danger btn-lg" disabled="disabled">
          </div>
        </div>
      </section>
     </div>
	</div>
</div>
<!-- Footer -->
<footer class="w3-center w3-black w3-padding-16">
  <p>Powered by&nbsp;<a href="../../emotion/mainpage.ggd" title="GOGODA" target="_blank" class="w3-hover-text-green"><b>GOGODA</b></a></p>
</footer>
</body>
</form>
</html>