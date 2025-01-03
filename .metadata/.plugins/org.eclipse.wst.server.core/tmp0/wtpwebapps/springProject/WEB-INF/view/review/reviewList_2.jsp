<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% 
	Object obj = request.getAttribute("kakaoid");
	String kakaoid = "";
	if(obj != null){
		kakaoid = obj.toString();	
	}
	System.out.println("kakaoid >>> : " + kakaoid);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#rephoto{width:100px; height:100px;},
	.recontent{width:400px;}
</style>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		//selectAll();
		//var form = new FormData($("#reviewForm")[0]);
		//form.append("kakaoid",$("#kakaoid").val());
		//form.append("file", $("#file"));
		//form.append("renum")

		//console.log(form);
		$("#insertBtn").click(function(){ 
			$("#reviewForm").attr("method","POST");
			$("#reviewForm").attr("action","reviewInsert.psh");
			$("#reviewForm").submit();
			
		});
		
	});
	
	function selectAll(){
		let selectAllURL = "reviewListAll.psh";
		let method = "GET";
		let dataParam = {
			kakaoid : "0000"
		}
		$.ajax({
			url:selectAllURL,
			type:method,
			data:dataParam,
			success:whenSuccess,
			error:whenError
		});
		
		function whenSuccess(resData){
			alert(resData);
			listStr = resData.split("&");
			for(var i=0; i<listStr.length-1; i++){
				// alert(listStr[i]);
				listReview = listStr[i].split(",");
				var renum = listReview[0];
				var renickname = listReview[1];
				var recontent = listReview[2];
				var rephoto = listReview[3];
				var rerating = listReview[4];
				var reinsertdate = listReview[5];
				// alert(renum + reinsertdate);
				selectList(renum, renickname, recontent, rephoto, rerating, reinsertdate);
			}
		}
		
		function whenError(e){
			alert("오류 발생 : " + e);
		}
	}
	
	function selectList(renum, renickname, recontent, rephoto, rerating, reinsertdate){
		/*
<li>
	<p>
		<span>이름</span>
		<span>날짜</span>
		<input type="button" value="수정하기">
		<input type="button" value="삭제하기">
	</p>
	<p>내용</p>
</li>
		*/
		var liTag = $("<li>");
		var pTag1 = $("<p>");
		var spanName = $("<span>");
		spanName.html(renickname + "님");
		
		var spanDate = $("<span>");
		spanDate.html(" / " + reinsertdate);
		
		var delBtn = $("<input>");
		delBtn.attr({"type":"button", "id":"deleteBtn", "value":"삭제하기"});
		var updBtn = $("<input>");
		updBtn.attr({"type":"button", "id":"updateBtn", "value":"수정하기"});

		var pTag2 = $("<p>");
		
		var imgSpan = $("<span>");
		
		var imgTag = $("<img>");
		imgTag.attr({"src":"image/"+rephoto+".jpg", "id":"rephoto"});
		
		var spanRate = $("<span>")
		spanRate.html(rerating);
		
		var spanContent = $("<span>");
		spanContent.addClass("recontent");
		spanContent.html(recontent);
		
		pTag1.append(spanName).append(spanDate).append(updBtn).append(delBtn);
		imgSpan.append(imgTag);
		pTag2.append(imgSpan).append(spanRate).append(spanContent);
		liTag.append(pTag1).append(pTag2);
		$("#listAdd").append(liTag);
	}
	
	function clearInsert(){
		$("#renickname").val("");
		$("#repass").val("");
		$("#recontent").val("");
	}
</script>
</head>
<body>
<!-- 
<ul>

</ul>
 -->
<form id="reviewForm" name="reviewForm" enctype="multipart/form-data">
<table border="1">
<tr>
	<td><input type="hidden" id="kakaoid" name="kakaoid" value="<%=kakaoid%>"></td>
	<td>작성자</td>
	<td><input type="text" id="renickname" name="renickname"></td>
	<td>비밀번호</td>
	<td><input type="text" id="repass" name="repass"></td>
</tr>
<tr>
	<td>평점</td>
	<td colspan="3">
		<input type="radio" id="rerating" name="rerating" value="0.5">
		<input type="radio" id="rerating" name="rerating" value="1.0">
		<input type="radio" id="rerating" name="rerating" value="1.5">
		<input type="radio" id="rerating" name="rerating" value="2.0">
		<input type="radio" id="rerating" name="rerating" value="2.5">
		<input type="radio" id="rerating" name="rerating" value="3.0">
		<input type="radio" id="rerating" name="rerating" value="3.5">
		<input type="radio" id="rerating" name="rerating" value="4.0">
		<input type="radio" id="rerating" name="rerating" value="4.5">
		<input type="radio" id="rerating" name="rerating" value="5.0">
	</td>
</tr>
<tr>
	<td>내용</td>
	<td colspan="3"> <textarea id="recontent" name="recontent" rows="3" cols="50"></textarea></td>
</tr>
<tr>
	<td>사진</td>
	<td colspan="3"><input type="file" id="file" name="file"></td>
</tr>
</table>
	<input type="button" id="insertBtn" value="등록하기">
</form>
<hr>
<div id="line1">
<ul id="listAdd">

</ul>
</div>
</body>
</html>