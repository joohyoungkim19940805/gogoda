<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//alert("ready");
		$(document).on("click","#loginbtn",function(){
			$("#mainForm").attr("action","logincontroller/loginpage.ggd");
			$("#mainForm").attr("method","POST");
			$("#mainForm").attr("enctype","application/x-www-form-urlencoded");
			$("#mainForm").submit();
		});
	});
</script>
</head>
<body>
<form name="mainForm" id="mainForm">
<button type="button" id="loginbtn">로그인</button>
</form>
</body>
</html>