<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%! String mid = ""; %>
<%! String mname = ""; %>
<%! String mpw = ""; %>
<%! String memail = ""; %>
<%!	HttpSession hs = null; %>	    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Login_Form</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/main.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/login.css" />

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$(document).on("click","#login_btn",function(){
		
		var id = $("#mid").val();
		var pw = $("#mpw").val();
		//alert("로그인 버튼 누름~" + id + "==" + pw);
	
		$.ajax({
			url : "loginresult.ggd",
			type : "POST",
			async : false,
			data : {
				"mid" : id,
				"mpw" : pw
			},
			dataType : 'text',
			success : function(data) {
				//alert(data);
				console.log("data : " + data);
				if (data == "login_ok") {
					alert("로그인 성공. 메인페이지로 이동");
					$("#loginForm").attr("action","../emotion/login.ggd");
					$("#loginForm").attr("method","POST");
					$("#loginForm").attr("enctype","application/x-www-form-urlencoded");
					$("#loginForm").submit();
				} else if (data == "not_find_id") {
					alert("일치하는 아이디가 없습니다. 다시 입력해주세요.");
	
				} else{
					//alert(data);
				}
			},
			error : function(data) {
				console.log("에러발생");
			}
		});
	});
	
	
});

</script>
</head>
<body>
<!-- Navbar (sit on top) -->
<div class="w3-top">
	<div class="w3-bar w3-white w3-wide w3-padding w3-card">
		<a href="../../emotion/mainpage.ggd">
			<img src="/logo/GOGODA-logo.png" style="width:15%; height:15%">
		</a>
	</div>
</div>
	<form name="loginForm" id="loginForm">
		<div class="box">
			<h1>Log in</h1><br>
				<div class="inputBox">
					<input type="text" name="mid" id="mid" required onkeyup="this.setAttribute('value', this.value);"  value="">
					<label>ID</label>
				</div>
				<div class="inputBox">
					<input type="password" name="mpw" id="mpw" required onkeyup="this.setAttribute('value', this.value);" value="">
					<label>Password</label>
				</div>
			<div>
			</div>	
			<br>
			<!-- 네이버 로그인 창으로 이동 -->
			<div id="naver_id_login" style="text-align:left"><a href="${url}">
			<img width="190" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/></a>
			<input type="submit" name="login_btn" id="login_btn" value="Log In" style="text-align:right; margin-right:17px"><br><br><br></div>
			<div class="link_look">
				<a href="idFind.ggd">아이디 찾기</a>&nbsp;
				<a href="pwFind.ggd">비밀번호  찾기</a>&nbsp;
				<a href="../../mem/registerForm.ggd" id="memberhover">회원가입</a>
			</div>
		
		</div>
	</form>
	
	

			

	
</body>
</html>