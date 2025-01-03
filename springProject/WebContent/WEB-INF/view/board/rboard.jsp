<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
String str = (String)session.getAttribute("mid");
if(str==null){
	str="";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,
	  maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta http-equiv="X-UA-Compaible" content="IE=edge,chrome=1" />
<title>comment</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/board.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/rboard.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/common.js"></script>
<script type="text/javascript">
	
$(window).load(function(){
	
	var detailCount=$(".comment_item").length;
	$('#under_footer').css('margin-top',(detailCount*80)+'px');
	//$('.applepie-image').css('height',(800+(detailCount*280))+'px');
});
	$(function(){
		/* 기본 댓글 목록 불러오기 */
		var bnum = "<c:out value='${detail.bnum}' />";
		
		listAll(bnum)
		
		/* 댓글 내용 저장 이벤트 */
		$("#rboardInsert").click(function(){
			// 작성자 이름에 대한 입력여부 검사
			if(!chkSubmit($("#rbname"),"로그인 상태를")) return;
			else if(!chkSubmit($("#rbcontent"),"내용을")) return;
			else{
				var insertUrl = "../rboard/rboardInsert.ggd";
				/* 글 저장을 위한 Post 방식의 Ajax 연동 처리 */
				$.ajax({
					url:insertUrl, // 전송 url
					type: "post",  // 전송 시 method 방식
					headers:{
						"Content-Type":"application/json",
						"X-HTTP-Method-Override":"POST"
					},
					dataType:"text",
					data: JSON.stringify({
						bnum:bnum,
						rbname:$("#rbname").val(),
						rbpw:$("#rbpw").val(),
						rbcontent:$("#rbcontent").val()
					}),
					error:function(){ // 실행시 오류가 발생하였을 경우
						alert('시스템 오류 입니다. 관리자에게 문의 하세요.');
					},
					success:function(resultData){
						if (resultData=="SUCCESS") {
							alert("댓글 등록이 완료되었습니다.");
							dataReset();
							listAll(bnum);
						}
					}
				});
			}
		});
		
		/* 수정버튼 클릭시 수정폼 출력 */
		$(document).on("click",".update_form", function(){
			$(".reset_btn").click();
			var conText = $(this).parents("li").children().eq(1).html();
			console.log("conText:" + conText);
			$(this).parents("li").find("input[type='button']").hide();
			$(this).parents("li").children().eq(0).html();
			var conArea = $(this).parents("li").children().eq(1);
			
			conArea.html("");
			var data = "<textarea name='content' id='content'>" + conText + "</textarea>";
			data+="<input type='button' class='update_btn' value='수정완료'>";
			data+="<input type='button' class='reset_btn' value='수정취소'>";
			conArea.html(data);
		});
		
		/* 초기화 버튼 */
		$(document).on("click",".reset_btn", function(){
			var conText = $(this).parents("li").find("textarea").html();
			$(this).parents("li").find("input[type='button']").show();
			var conArea = $(this).parents("li").children().eq(1);
			conArea.html(conText);
		});
		
		/* 글 수정을 위한 Ajax 연동 처리*/
		$(document).on("click",".update_btn",function(){
			var rbnum = $(this).parents("li").attr("data-num");
			var rbcontent = $("#content").val();
			if(!chkSubmit($("#content"),"댓글 내용을"))return;
			else{
				$.ajax({
					url:'../rboard/'+rbnum+".ggd",
					type:'put',
					headers:{
						"Content-Type":"application/json",
						"X-HTTP-Method-Override":"PUT"},
					data:JSON.stringify({
						rbcontent:rbcontent}),
					dataType:'text',
					success:function(result){
						console.log("result: " + result);
						if (result == 'SUCCESS') {
							alert("수정 되었습니다.");
							listAll(bnum);
						}
					}
				});
			}
		});
		
		/* 글 삭제를 위한 Ajax 연동 처리*/
		$(document).on("click",".delete_btn", function(){
			var rbnum = $(this).parents("li").attr("data-num");
			console.log("rbnum: " + rbnum);
			
			if (confirm("선택하신 댓글을 삭제하시겠습니까?")) {
				$.ajax({
					type:'delete',
					url: '../rboard/' + rbnum + ".ggd",
					headers:{
						"Content-Type":"application/json",
						"X-HTTP-Method-Override":"DELETE"
					},
					dataType:'text',
					success:function(result){
						console.log("result:" + result);
						if (result == 'SUCCESS') {
							alert("삭제 되었습니다.");
							listAll(bnum);
						}
					}
					
				});
			}
		});
	});
		
	// 리스트 요청 함수
	function listAll(bnum){
		$("#comment_list").html("");
		var url = "../rboard/all/"+bnum+".ggd";
		$.getJSON(url, function(data){
			console.log(data.length);
			
			$(data).each(function(){
				var rbnum = this.rbnum;
				var rbname = this.rbname;
				var rbcontent = this.rbcontent;
				var rbdate = this.rbdate;
				addNewItem(rbnum,rbname,rbcontent,rbdate);
			});
		}).fail(function(){
			alert("댓글 목록을 불러오는데 실패하였습니다. 잠시후에 다시 시도해 주세요.");
		});
	}
	
	/* 새로운 글을 화면에 추가하기 위한 함수 */
	function addNewItem(rbnum,rbname,rbcontent,rbdate){
		
		// 새로운 글이 추가될 li 태그 객체
		var new_li = $("<li>");
		new_li.attr("data-num",rbnum);
		new_li.addClass("comment_item");
		
		// 작성자 정보가 지정될 <p>태그
		var writer_p = $("<p>");
		writer_p.css("margin-top","0px");
		writer_p.css("margin-bottom","0px");
		writer_p.css("margin-left","30px");
		writer_p.addClass("writer");
		
		// 작성자 정보의 이름
		var name_span = $("<span>");
		name_span.addClass("name");
		name_span.html(rbname + "님");
		
		// 작성일시
		var date_span = $("<span>");
		date_span.html("/" + rbdate + " ");
		
		// 수정하기 버튼
		var up_input = $("<input>");
		up_input.css("margin-left","220px");
		up_input.attr({"type":"button","value":"수정하기"});
		up_input.addClass("update_form");
		
		// 삭제하기 버튼
		var del_input = $("<input>");
		del_input.attr({"type":"button","value":"삭제하기"});
		del_input.addClass("delete_btn");
		
		// 내용
		var content_p = $("<p>");
		content_p.css("margin-top","0px");
		content_p.css("margin-bottom","0px");
		content_p.css("margin-left","30px");
		content_p.addClass("con");
		content_p.html(rbcontent);
		
		// 조립하기
		writer_p.append(name_span).append(date_span).append(up_input).append(del_input)
		new_li.append(writer_p).append(content_p);
		$("#comment_list").append(new_li);
	}
	
	/* INPUT 태그들에 대한 초기화 함수 */
	function dataReset(){
		$("#rbpw").val("");
		$("#rbcontent").val("");
	}
	</script>
	</head>
	<body>
		<div id="rboardContainer">
			<h1></h1>
			<div id="comment_write">
				<form id="comment_form">
					<div>
						<label for="rbname">글쓴이</label>
						<input type="text" name="rbname" id="rbname" value='<%=str %>'readonly/>
						<label for="rbname">비밀번호</label>
						<input type="password" name="rbpw" id="rbpw" />
						<input type="button" id="rboardInsert" value="저장">
					</div>
					<div>
						<label for="rbcontent">댓글 내용</label>
						<textarea name="rbcontent" id="rbcontent"></textarea>
					</div>
				</form>
			</div>
			<ul id="comment_list">
				<!-- 여기에 동적 생성 요소가 들어가게 됩니다. -->
			</ul>
		</div>
		<footer class="w3-center w3-black w3-padding-16" id="under_footer" >
			<p>Powered by <a href="../../emotion/mainpage.ggd" title="GOGODA" target="_blank" class="w3-hover-text-green" >GOGODA</a></p>
		</footer> 
	</body>
	</html>