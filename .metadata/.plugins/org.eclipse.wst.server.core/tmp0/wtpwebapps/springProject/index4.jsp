<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//alert("ready");
		$(document).on("click","#shbtn",function(){
			
			$("#methodForm").attr("action","testEmotion/search.ggd");
			$("#methodForm").attr("method","GET");
			$("#methodForm").attr("enctype","application/x-www-form-urlencoded");
			$("#methodForm").submit();
			
		});
	});
</script>
</head>
<form name="methodForm" id="methodForm">
<body>
	<a href="testEmotion.kjh">테스트</a>

	<input type="text" id="text" name="text">
	<button type="button" id="shbtn">검색</button>
	<%-- <% response.sendRedirect("login.do"); --%>
	<%-- <% response.sendRedirect("department_insertForm.do"); --%>
</form>
</body>
</html>