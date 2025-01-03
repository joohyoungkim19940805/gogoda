<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,
	  maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta http-equiv="X-UA-Compaible" content="IE=edge,chrome=1" />
<title>글상세 보기</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/main.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/board.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/common.js"></script>
<script type="text/javascript">
	
	var butChk = 0; // 수정버튼과 삭제버튼을 구별하기 위한 변수
	
	$(function(){
		
		if("${seName}"!=''){
			$("#loginbtn").remove();
			$("#meminsert").remove();
			$("#memlogin").append("<a href='../../mem/memberSelect.ggd' style='color:black' id='' class='comlogin'>${seName}</a>님 환영합니다. &nbsp;&nbsp;&nbsp;");
			$("#memlogin").append("<button type='button' class='comlogin' id='memlogout' onclick='logoutBtn()'>로그아웃</button>");
		}
		
		$(document).on("click","#loginbtn",function(){
			$("#loginForm").attr("action","../../logincontroller/login.ggd");
			$("#loginForm").attr("method","POST");
			$("#loginForm").attr("enctype","application/x-www-form-urlencoded");
			$("#loginForm").submit();
		});
		
		$("#pwdChk").hide();
		
		/* 첨부파일 이미지 보여주기 위한 속성 추가*/
		var file = "<c:out value='${detail.bfile}' />";
		if (file!="") {
			 	$("#fileImage").attr({
			 			src:"${pageContext.request.contextPath}/uploadStorage/${detail.bfile}",
			 			width:"250px",
			 			height:"200px"
			 	});
		}
		
		/*수정 버튼 클릭 시 처리 이벤트*/
		$("#updateForm").click(function(){
			if("${seName}"!=''){
				$("#pwdChk").show();
				$("#msg").text("비밀번호를 입력해 주세요.").css("color","#000099");
				butChk = 1;
			}else{
				alert("로그인 상태를 확인해주세요");
			}
			
		});
		

		/*삭제 버튼 클릭 시 처리 이벤트*/
		$("#boardDelete").click(function(){
			if("${seName}"!=''){
				$("#pwdChk").show();
				$("#msg").text("비밀번호를 입력해 주세요.").css("color","#000099");
				butChk = 2;
			}else{
				alert("로그인 상태를 확인해주세요.");
			}
			
		});
		
		/*비밀번호 확인 버튼 클릭 시 처리 이벤트*/
		$("#pwdBut").click(function(){
				pwdConfirm();
		});
		
		/*목록 버튼 클릭 시 처리 이벤트*/
		$("#boardList").click(function(){
				location.href="boardList.ggd";
		});
	});
	
	/*비밀번호 확인 버튼 클릭시 실질적인 처리 함수*/
	function pwdConfirm(){
		if (!chkSubmit($("#bpw"),"비밀번호를")) return; 
		else{
			$.ajax({
				url:"pwdConfirm.ggd", 	// 전송 url
				type:"POST",				 	// 전송 시 method 방식
				data:$("#f_pwd").serialize(),	// 폼전체 데이터 전송
				error:function(){				// 실행시 오류가 발생하였을 경우
					alert('시스템 오류입니다. 관리자에게 문의 하세요');
				},								// 정상적으로 실행이 되었을 경우
				success:function(resultData){
					var goUrl="";				// 이동할 경로를 저장할 변수
					if (resultData == 0) {		// 일치하지 않는 경우
						$("#msg").text("비밀번호가 일치하지 않습니다.").css("color","red");
						$("#bpw").select();
					}else if (resultData == 1) { // 일치할 경우
						$("#msg").text("");
						if (butChk == 1) {
							goUrl = "updateForm.ggd";
						} else if(butChk == 2){
							goUrl = "boardDelete.ggd";
						}
						$("#f_data").attr("action",goUrl);
						$("#f_data").submit();
					}
				}
			});	
		}
	}
	function logoutBtn(){
		$("#loginForm").attr("action","../emotion/logout.ggd");
		$("#loginForm").attr("method","POST");
		$("#loginForm").attr("enctype","application/x-www-form-urlencoded");
		$("#loginForm").submit();
		
	}
</script>
</head>
<body>

<!-- Navbar (sit on top) -->
<form name="loginForm" id="loginForm">
<div class="w3-top">
	<div class="w3-bar w3-white w3-wide w3-padding w3-card">
		<a href="../../emotion/mainpage.ggd">
			<img src="/logo/GOGODA-logo.png" style="width:12%; height:12%">
		</a>
	<div class="w3-right w3-hide-small" id="memlogin">
		<a href="#로그인" class="w3-bar-item w3-button" id="loginbtn">로그인</a>
		<a href="#회원 가입" class="w3-bar-item w3-button" id="meminsert">회원가입</a>
    </div>
	</div>
</div>
</form>
<!-- Header -->
<header class="display-container" style="max-width:1500px; " id="home">
   <img class="applepie-image" src="/logo/applepie.png" alt="Applepie" width="1500">
   <img class="applepie-image" src="/logo/applepie.png" alt="Applepie" width="1500">
   <div class="board-display">
   
       <div id="boardContainer" align="left">
<!-- 
	<div id="Detailtop">
	<a href="../../emotion/mainpage.ggd">
		<img src="../images/common/gogoda.png" style="width:170px; height:170px" />
	</a>
	</div>  -->
	
	<div id="boardTit"><h2><b>글 상세 조회</b></h2></div>
	<form name="f_data" id="f_data" method="POST">
		<input type="hidden" name="bnum" value="${detail.bnum}" />
		<input type="hidden" name="bfile" id="bfile" value="${detail.bfile}" />
	</form>
	<%--======================비밀번호 확인 버튼 및 버튼 추가 시작 =====================--%>
	<table id="boardPwdBut">
		<tr>
			<td id="btd1"> 
				<div id="pwdChk">
					<form name="f_pwd" id="f_pwd">
						<input type="hidden" name="bnum" id="bnum" value="${detail.bnum}" />
						<label for="bpw" id="l_pwd"></label>
						<input type="password" name="bpw" id="bpw" />
						<input type="button" id="pwdBut" value="확인" />
						<span id="msg"></span>
					</form>
				</div>
			</td>
			<td id="btd2">
				<input type="button" value="수정" id="updateForm">
				<input type="button" value="삭제" id="boardDelete">
				<input type="button" value="목록" id="boardList">
			</td>
		</tr>
	</table>
	<%--======================비밀번호 확인 버튼 및 버튼 추가 종료 =====================--%>
	
	<%--========================== 상세 정보 보여주기 시작 =========================--%>
	<div id="boardDetail">
		<table>
			<colgroup>
				<col width="20%" />
				<col width="30%" />
				<col width="20%" />
				<col width="30%" />
			</colgroup>
			<tbody>
				<tr>
					<td class="ac">글쓴이</td>
					<td>${detail.bname}</td>
					<td class="ac">작성일</td>
					<td>${detail.binsertdate}</td>
				</tr>
				<tr>
					<td class="ac">제목</td>
					<td colspan="3">${detail.bsubject}</td>
				</tr>
				<tr class="ctr">
					<td class="ac">내용</td>
					<td colspan="3">${detail.bcontent}</td>
				</tr>
				<tr class="ctr">
					<td class="ac">사진</td>
					<td colspan="3"><img id="fileImage" width="150px" height="100px"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<%--========================== 상세 정보 보여주기 종료 =========================--%>
	
</div>

	</div>
		
</header>
<!-- Footer-->
 <jsp:include page="rboard.jsp"></jsp:include>

</body>
</html>