<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%! String mid = ""; %>
<%! String mname = ""; %>
<%! String mpw = ""; %>
<%! String memail = ""; %>
<%!	HttpSession hs = null; %>	    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginForm</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$(document).on("click","#login_btn",function(){
		
		var id = $("#mid").val();
		var pw = $("#mpw").val();
		alert("로그인 버튼 누름~" + id + "==" + pw);
	
		$.ajax({
			url : "../logincontroller/loginresult.ggd",
			type : "GET",
			data : {
				"mid" : id,
				"mpw" : pw
			},
			dataType : "text",
			success : function(data) {
				alert(data);
				console.log("data : " + data);
				if (data == "true") {
					alert("로그인 성공. 메인페이지로 이동");
					location.href="../index.jsp"
				} else if (data == "false") {
					alert("일치하는 회원 정보가 없습니다. 다시 입력해주세요.");
	
				} else {
					console.log("컨트롤러 에러");
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
<form name="loginForm" id="LoginForm">
		<div align="center" style="position:relative; left:0px; top:300px; width:100%; height:100%;">		
			<div class="login">
			<h1 class="logo"><img src=""  width="500px" alt="로고" /></h1>				
			
			<div class="entry_ipt">	
				<div class="user_id">
					<label for="mid"><img src=""  width="300" height="200" alt=" 아이디  " />&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<input type="text" id="mid" placeholder="아이디를 입력하세요">
				</div> 
				<p></p>	
				<div class="user_pwd">
					<label for="mpw"><img src=""  width="300" height="200" alt="비밀번호" />&nbsp;</label>
					<input type="password" id="mpw" placeholder="비밀번호를 입력하세요">
				</div>
				<p></p>	
				<div class="login_btn" align="center">
					<input type="submit" style="width:200pt; height:20pt;"
					   id="login_btn" alt="로그인" value="로그인"><!-- css에서 모양 수정 -->
				</div>
				<p></p>		
				<div class="link_look">
					<a href="idFind.ggd">아이디 찾기</a>&nbsp;
					<a href="pwFind.ggd">비밀번호  찾기</a>&nbsp;
					<a href="ss" class="link_look">회원가입</a>
				</div>
				
			</div> 
			</div> <!-- //login -->
		</div> <!-- //end of style -->
</form>
</body>

</html>