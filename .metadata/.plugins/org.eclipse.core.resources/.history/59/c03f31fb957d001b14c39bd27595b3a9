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
	#updatetop{border-left:10px solid #ff0000; border-bottom: 1px solid #000066; padding: 10px; width:760px;}
</style>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/board.css" />
<!-- 웹에디터 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/common.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){	
		
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
	<div id="updatetop">
	<a href="../../springProject/emotion/mainpage.ggd">
		<img src="../images/common/gogoda.png" style="width:150px; height:100px" />
	</a>
	</div>
	<div id="boardTit"><h3>글수정</h3></div>
	<form id="f_writeForm" name="f_writeForm" enctype="multipart/form-data">
		<input type="hidden" id="bnum" name="bnum" value="${updateData.bnum}" />
		<input type="hidden" id="bfile" name="bfile" value="${updateData.bfile}" />
	<table id="boardWrite">
		<tr>
			<td>작성자</td>
			<td><input type="text" name="bname" id="bname" value="${updateData.bname}" /></td>
		</tr>
		<tr>
			<td>글제목</td>
			<td><input type="text" name="bsubject" id="bsubject" value="${updateData.bsubject}" /></td>
		</tr>
		<tr>
			<td>내용</td>
			<td height="200"><textarea name="bcontent" id="bcontent"
			rows="10" cols="70">${updateData.bcontent}</textarea></td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td><input type="file" name="file" id="file"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="bpw" id="bpw">
			<label>수정할 비밀번호를 입력해 주세요.</label></td>
		</tr>
	</table>
	</form>
	<div id="boardBut">
		<input type="button" value="수정" class="but" id="boardUpdate" />
		<input type="button" value="목록" class="but" id="boardList" />
	</div>
</body>
</html>