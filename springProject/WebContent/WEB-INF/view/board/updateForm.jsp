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
<title>글수정 화면</title>
<style>
	/*#updatetop{border-left:10px solid #ff0000; border-bottom: 1px solid #000066; padding: 10px; width:760px;}*/
</style>

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
					
		$(document).on("click", "#boardUpdate", function(){
			console.log("boardUpdate >>> : ");	
			oEditors.getById["bcontent"].exec("UPDATE_CONTENTS_FIELD", []);					
			
			$('#f_writeForm').attr({
				'action':'boardUpdate.ggd',
				'method':'POST',
				'enctype':'multipart/form-data'
			}).submit();
		});
	});
	
	$(function(){
		/*수정 버튼 클릭 시 처리 이벤트*/
		$("#boardUpdate").click(function(){
			// 입력값 체크
			if(!chkSubmit($('#bname'),"이름을")) return;
			else if(!chkSubmit($('#bsubject'),"제목을")) return;
			else if(!chkSubmit($('#bcontent'),"작성할 내용을")) return;
			else{
				if($('#file').val().indexOf(".")>-1){
					var ext = $('#file').val().split('.').pop().toLowerCase();
					if (jQuery.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
						alert('gif,png,jpg,jpeg 파일만 업로드 할 수 있습니다.');
						return;
					}
				}
				
				// console.log("기본 파일명 : "+$('#bfile').val());
				$("#f_writeForm").attr({
					"method":"POST",
					"action":"boardUpdate.ggd"
				});
				$("#f_writeForm").submit();
			}
		});
		/*목록 버튼 클릭 시 처리 이벤트*/
		$("#boardList").click(function(){
				location.href="boardList.ggd";
		});
	});
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
</form><br><br><br><br><br><br><br>
<header class="display-container" style="max-width:1500px;" id="home">
	<img class="applepie-image" src="/logo/applepie.png" alt="Applepie" width="1500" height="800">
	<div class="board-display">
	
<div id="boardContainer" align="left">

<!-- <div id="updatetop">
	<a href="../../emotion/mainpage.ggd">
		<img src="../images/common/gogoda.png" style="width:170px; height:170px" />
	</a>
	</div> -->	
	
	<div id="boardTit"><h2><b>글수정</b></h2></div><br><br><br>
	<form id="f_writeForm" name="f_writeForm" enctype="multipart/form-data">
		<input type="hidden" id="bnum" name="bnum" value="${updateData.bnum}" />
		<input type="hidden" id="bfile" name="bfile" value="${updateData.bfile}" />
	<table id="boardWrite">
		<tr>
			<td style="font-weight: bold; text-align:center; background-color: #f2f2f2;">작성자</td>
			<td><input type="text" name="bname" id="bname" value="${updateData.bname}" /></td>
		</tr>
		<tr>
			<td style="font-weight: bold; text-align:center; background-color: #f2f2f2;">글제목</td>
			<td><input type="text" name="bsubject" id="bsubject" value="${updateData.bsubject}" /></td>
		</tr>
		<tr>
			<td style="font-weight: bold; text-align:center; background-color: #f2f2f2;">내용</td>
			<td height="200"><textarea name="bcontent" id="bcontent"
			rows="10" cols="70">${updateData.bcontent}</textarea></td>
		</tr>
		<tr>
			<td style="font-weight: bold; text-align:center; background-color: #f2f2f2;">첨부파일</td>
			<td>
			<label for="file" id="fileSelect">파일 선택</label>
			<input type="file" name="file" id="file" style="display:none">
			</td>
		</tr>
		<tr>
			<td style="font-weight: bold; text-align:center; background-color: #f2f2f2;">비밀번호</td>
			<td><input type="password" name="bpw" id="bpw">
			<label>수정할 비밀번호를 입력해 주세요.</label></td>
		</tr>
	</table>
	</form>
	<div id="boardBut">
		<input type="button" value="수정" class="but" id="boardUpdate" />
		<input type="button" value="목록" class="but" id="boardList" />
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