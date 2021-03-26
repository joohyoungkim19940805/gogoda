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
<title>글쓰기 화면</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/main.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/board.css" />
<!-- 웹에디터 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/common.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){	
		
		if("${seName}"!=''){
			$("#loginbtn").remove();
			$("#meminsert").remove();
			$("#memlogin").append("<a href='../../mem/memberSelect.ggd' style='color:black' id='' class='comlogin'>${seName}</a>님 환영합니다. &nbsp;&nbsp;&nbsp;");
			$("#memlogin").append("<button type='button' class='comlogin' id='memlogout' onclick='logoutBtn()'>로그아웃</button>");
		}
	
		var oEditors = [];
		$(function(){
			nhn.husky.EZCreator.createInIFrame({
				oAppRef : oEditors,
				elPlaceHolder : "bcontent", // textarea id 				
				sSkinURI : "${pageContext.request.contextPath}/se2/SmartEditor2Skin.html", // SmartEditor2Skin.html 에디터 스킨 				
				fCreator : "createSEditor2"
			});
		});
					
		$(document).on("click", "#boardInsert", function(){
			console.log("boardInsert >>> : ");	
			oEditors.getById["bcontent"].exec("UPDATE_CONTENTS_FIELD", []);					
			
			$('#f_writeForm').attr({
				'action':'boardInsert.ggd',
				'method':'POST',
				'enctype':'multipart/form-data'
			}).submit();
		});
	});
	
	
	$(function(){
		/*저장 버튼 클릭 시 처리 이벤트*/
		$("#boardInsert").click(function(){
			//alert(('#bcontent').val().replace('?',''));
			// 입력값 체크
			if (!chkSubmit($('#bname'),"이름을")) return;
			else if (!chkSubmit($('#bsubject'),"제목을")) return;
			// else if (!chkSubmit($('#bcontent'),"작성할 내용을")) return;
			else if (!chkSubmit($('#file'),"첨부파일을")) return;
			else if (!chkSubmit($('#bpw'),"비밀번호를")) return;
			else{
				
				/*배열내의 값을 찾아서 인덱스를 반환(요소가 없을 경우 -1 반환 )
				jQuery.inArray(찾을 값,검색 대상의 배열)*/
					var ext = $('#file').val().split('.').pop().toLowerCase();
					if (jQuery.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
						 	alert('gif,png,jpg,jpeg 파일만 업로드 할 수 있습니다.');
						 	return;
					}
					$("#f_writeForm").attr({
							"method":"POST",
							"action":"/boardInsert.ggd"
					});
					$("#f_writeForm").submit();
						}
			        });				
					
					/*목록 버튼 클릭 시 처리 이벤트*/
					$("#boardList").click(function(){
						location.href="boardList.ggd";
					});
			
		});
	
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
<header class="display-container" style="max-width:1500px;" id="home">
	<img class="applepie-image" src="/logo/applepie.png" alt="Applepie" width="1500" height="800">
	<div class="board-display">
<div id="boardContainer" align="left">
<!-- 	
	<div id="writetop">
	<a href="../../emotion/mainpage.ggd">
		<img src="../images/common/gogoda.png" style="width:170px; height:170px" />
	</a>
	</div>
 -->
	<div id="boardTit"><h2><b>글쓰기</b></h2></div><br><br><br>
	<form id="f_writeForm" name="f_writeForm" enctype="multipart/form-data">
		<table id="boardWrite">
			<tr>
				<td style="font-weight: bold; text-align:center; background-color: #f2f2f2;">글쓴이</td>
				<td><input type="text" name="bname" id="bname" value="${seName}" readonly></td>
			</tr>
			<tr>
				<td style="font-weight: bold; text-align:center; background-color: #f2f2f2;">글제목</td>
				<td><input type="text" name="bsubject" id="bsubject"></td>
			</tr>
			<tr>
				<td style="font-weight: bold; text-align:center; background-color: #f2f2f2;">내용</td>
				<td height="200"><textarea name="bcontent" id="bcontent"
								rows="10" cols="70"></textarea></td>
			</tr>
			<tr>
				<td style="font-weight: bold; text-align:center; background-color: #f2f2f2;">첨부파일</td>
				<td>
				<label for="file" id="fileSelect">파일선택</label>
				<input type="file" name="file" id="file" style="display:none"></td>
			</tr>
			<tr>
				<td style="font-weight: bold; text-align:center; background-color: #f2f2f2;">비밀번호</td>
				<td><input type="password" name="bpw" id="bpw"></td>
			</tr>
		</table>
	</form>
	<div id="boardBut">
		<input type="button" value="저장" class="but" id="boardInsert">
		<input type="button" value="목록" class="but" id="boardList">
	</div>
</div>

	</div>
		
</header>
<!-- Footer-->
<footer class="w3-center w3-black w3-padding-16">
  <p>Powered by <a href="../../emotion/mainpage.ggd" title="GOGODA" target="_blank" class="w3-hover-text-green">GOGODA</a></p>
</footer> 
</body>
</html>