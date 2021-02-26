<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>메인페이지</title>
<style type="text/css">
	.title{
		text-align: center;
		font-size: 130px;
		font-weight: bold;
	}
	.searchbtn{
		text-align: center;
	}
</style>
<script type="text/javascript">
	function loginbtn(){
		document.loginmem.action = "main/login.ggd";
		document.loginmem.submit();
	}
	
	function memberbtn(){
		document.loginmem.action = "main/member.ggd"
		document.loginmem.submit();
	}
	
	function boardbtn(){
		document.board.action = "main/board.ggd";
		document.board.submit();
	}
</script>


</head>
<body>
	<div class="loginbtn" align="right">
		<form name="loginmem" method="get">
			<input type="button" id="login" value="로그인" onClick="javascript:loginbtn();">
			<input type="button" id="member" value="회원가입" onClick="javacript:memberbtn();">
		</form>
	</div>
	<div class="main">
		<div class="title">
			꼬꼬다
		</div>
		<div class="searchbtn">
			<Form name="search" method="get">
				<input type="text" id="Gobtn" placeholder="사용자 감정 입력">
				<input type="button" value="전송" onClick="javascript:searchbtn();">
			</form>
		</div>
	</div>
	<div class="boardbtn">
		<Form name="board" method="get">
			<input type="button" value="더보기" onClick="javascript:boardbtn();">
		</Form>
	</div>
</body>
</html>