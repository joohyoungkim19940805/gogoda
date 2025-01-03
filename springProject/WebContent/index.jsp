<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom_tag.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>

<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 디바이스에 최적화된 크기로 출력됨 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0
      maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>GOGODA HOMEPAGE</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/main.css" />
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var url=window.location.href.toString();
		//alert(url);
		//alert(url.indexOf());
		if(url.indexOf('.jsp')!=-1){
			$("#mainForm").attr("action","../../emotion/mainpage.ggd");
			$("#mainForm").attr("method","POST");
			$("#mainForm").attr("enctype","application/x-www-form-urlencoded");
			$("#mainForm").submit();
			
		}
		var boardData;
		//alert("ready");
		$(window).load(function(){
			if("${seName}"!=''){
				$("#loginbtn").remove();
				$("#meminsert").remove();
				$("#memlogin").append("<a href='../../mem/memberSelect.ggd' style='color:black' id='' class='comlogin'>${seName}</a>님 환영합니다. &nbsp;&nbsp;&nbsp;");
				$("#memlogin").append("<button type='button' class='comlogin' id='memlogout' onclick='logoutBtn()'>로그아웃</button>");
			}
			//게시판 최신순 조회
			 $.ajax({
	                url : '../../emotion/mainboard.ggd',
	                type : 'POST',
	                async:false,
	                datatype:'json',
	                success: function(data) {
	                	boardData=data;
	                	for(var i=0;i<boardData.length;i++){
	                		//
	                		if(i>10){break;}
	                		if(boardData[i]["bsubject"].length<35){
			                	var boardListData="<li>"+
								"<a href='../../board/boardDetail.ggd?bnum="+boardData[i]["bnum"]+"' style='color:black;' id='a-none'>"+
									"<td><b>"+boardData[i]["bname"]+"</td></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td>"+
								boardData[i]["bsubject"]+"</td><font color='#1551a4'<td>["+boardData[i]["cnt"]+"]</td></font></a>"+
								"</li><br>";
							}else{
								var boardListData="<li>"+
								"<a href='../../board/boardDetail.ggd?bnum="+boardData[i]["bnum"]+"' style='color:black;' id='a-none'>"+
									"<span><b>"+boardData[i]["bname"]+"</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
								boardData[i]["bsubject"].substring(0,35)+"<font color='#1551a4'>["+boardData[i]["cnt"]+"]</font></a>"+
								"</li><br>";
							}
		                	$("#boardlist-main").append(boardListData);
	                	}
	                },
	                error: function(){}			
			 });
			 //음식 랭킹 조회
			 $.ajax({
	                url : '../../emotion/foodrank.ggd',
	                type : 'POST',
	                async:false,
	                datatype:'json',
	                success: function(data) {
	                	foodData=data;
	                	console.log(data);
	                	console.log(foodData[0]["fname"]);
	                	for(var i=0;i<10;i++){
	                		
	                		if(foodData[i]["fname"].length<7){
	                		var foodListData="<li><a href='../review/map.ggd?food="+foodData[i]["fname"]+"'style='color:black;' id='a-none'><span><b>"+(i+1)+"위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+foodData[i]["fname"]+
								"</span>"+
								"<span id='ranking'>"+foodData[i]["foodcnt"]+"&nbsp;<b style='color:red;'>↑</b></span></a>"+
								"</li><br>"
	                		}else{
	                			var foodListData="<li><a href='../review/map.ggd?food="+foodData[i]["fname"]+"'style='color:black;' id='a-none'><span><b>"+(i+1)+"위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+foodData[i]["fname"].substring(0,6)+"..."+
								"</span>"+
								"<span id='ranking'>"+foodData[i]["foodcnt"]+"&nbsp;<b align='right' style='color:red;'>↑</b></span></a>"+
								"</li><br>"
	                		}
	                		$("#foodlist-rank").append(foodListData);
	                	}
	                },
	                error: function(){}			
			 });
			 //영화 랭킹 조회
			 $.ajax({
	                url : '../../emotion/movierank.ggd',
	                type : 'POST',
	                async:false,
	                datatype:'json',
	                success: function(data) {
	                	movieData=data;
	                	console.log(data);
	                	console.log(movieData[0]["mvname"]);
	                	for(var i=0;i<10;i++){
	                		//<a href='../emotion/moviecount.ggd?movieNum="+movieNum[i]+"&movieLink="+movieLink[i]+"' >"+
	                		if(movieData[i]["mvname"].length<7){
	                		var movieListData="<li><a href='../emotion/moviecount.ggd?movieNum="+movieData[i]["mvnum"]+"&movieLink="+movieData[i]["mvlink"]+"'style='color:black;' id='a-none'><span><b>"+(i+1)+"위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+movieData[i]["mvname"]+
								"</span>"+
								"<span id='ranking'>"+movieData[i]["moviecnt"]+"&nbsp;<b style='color:red;'>↑</b></span>"+
								"</li><br>"
	                		}else{
	                			var movieListData="<li><a href='../emotion/moviecount.ggd?movieNum="+movieData[i]["mvnum"]+"&movieLink="+movieData[i]["mvlink"]+"'style='color:black;' id='a-none'><span><b>"+(i+1)+"위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+movieData[i]["mvname"].substring(0,6)+"..."+
								"</span>"+
								"<span id='ranking'>"+movieData[i]["moviecnt"]+"&nbsp;<b align='right' style='color:red;'>↑</b></span>"+
								"</li><br>"
	                		}
	                		$("#movielist-rank").append(movieListData);
	                	}
	                },
	                error: function(){}			
			 });
			 
			 
		}); 

		
		$(document).on("click",".icon",function(){
			
			if("${seName}"!=''){
			
				$("#mainForm").attr("action","../../emotion/emotionSearch.ggd");
				$("#mainForm").attr("method","POST");
				$("#mainForm").attr("enctype","application/x-www-form-urlencoded");
				$("#mainForm").submit();
			}else{
				alert("로그인 후 이용해주십시오.");
			}
		});
		$(document).on("click","#loginbtn",function(){
			$("#mainForm").attr("action","../../logincontroller/login.ggd");
			$("#mainForm").attr("method","POST");
			$("#mainForm").attr("enctype","application/x-www-form-urlencoded");
			$("#mainForm").submit();
		});
		$(document).on("click","#meminsert",function(){
			$("#mainForm").attr("action","../../mem/registerForm.ggd");
			$("#mainForm").attr("method","POST");
			$("#mainForm").attr("enctype","application/x-www-form-urlencoded");
			$("#mainForm").submit();
		});
	});
	
	function logoutBtn(){
		$("#mainForm").attr("action","../../emotion/logout.ggd");
		$("#mainForm").attr("method","POST");
		$("#mainForm").attr("enctype","application/x-www-form-urlencoded");
		$("#mainForm").submit();
		
	}
	
</script>
</head>
<form name="mainForm" id="mainForm">
<body>
<!-- Navbar (sit on top) -->
<div class="w3-top">
	<div class="w3-bar w3-white w3-wide w3-padding w3-card">
		<a href="../../emotion/mainpage.ggd">
			<img src="/logo/GOGODA-logo.png" style="width:12%; height:12%">
		</a>
		<!-- Float links to the right. Hide them on small screens -->
<!--  	<div align="right" id="memlogin">
			<button type="button" id="loginbtn">로그인</button>
			<button type="button" id="meminsert">회원 가입</button>
		</div>
-->	
		
    <div class="w3-right w3-hide-small" id="memlogin">
		<a href="#로그인" class="w3-bar-item w3-button" id="loginbtn">로그인</a>
		<a href="#회원 가입" class="w3-bar-item w3-button" id="meminsert">회원가입</a>
    </div>
	</div>
</div>
<br>
<!-- Header -->
<header class="w3-display-container w3-content w3-wide" style="max-width:1500px;" id="home">
	<img class="w3-image" src="/logo/applepie.png" alt="Applepie" width="1500" height="800">
	<div class="w3-display-middle w3-margin-top w3-center">
	<!-- 
		<h1 class="w3-xxlarge w3-text-white"> <span class="w3-hide-small w3-text-light-grey">맛있</span><span class="w3-padding w3-black w3-opacity-min"><b>GO</b></span></h1>
		<h1 class="w3-xxlarge w3-text-white"> <span class="w3-hide-small w3-text-light-grey">재밌</span><span class="w3-padding w3-black w3-opacity-min"><b>GO</b></span></h1>
		<h1 class="w3-xxlarge w3-text-white"> <span class="w3-hide-small w3-text-light-grey">신난</span><span class="w3-padding w3-black w3-opacity-min"><b>DA</b></span></h1>
	 -->
	 	<div class="box" align="center">
			<div class="container-1">
				<span class="icon"><i class="fa fa-search"></i></span>
				<input type="text" id="text" name="text" style="width: 700px; height:55px;'" placeholder="오늘 어떤 일이 있으셨나요?">
			</div>
		</div>
	</div>
		
</header>

<!-- Page content -->
<div class="w3-content w3-padding" style="max-width:1564px">
	<div id="topboard" align="center" style="position: relative; z-index: 0; overflow: hidden; min-width: 100%; height: 100%; touch-action: pan-x; user-select: none; -webkit-user-drag: none; width: 210px;">
			<br>
  <div id="under">
			<tr>
				<td id="under-table"><!-- white -->
					<table class="board-wi-he" bgcolor="white" border='0'
							style="border-style:solid;border-width:0px;border-color:#c2c1c6; margin-bottom: 5px; " >
					
							<tr>
								<th colspan="" id="line-title"style="font-size:14px;font-weight:bold;" width="100%">
								<span>게시글</span>
									<a href='../../board/boardList.ggd' style='color:black' id='a-none'><span style="width: 500px; display: inline-block; float: right; font-size:12px;" align="right">더보기</span></a>
								</th>
									
								<th>
								</th>

								<th id="line-title" class="rank-table-food" style="font-size:14px;font-weight:bold;" width="100%">
									<span style="width: 180px; display: inline-block; float: left; margin-bottom:12px;" align="left" >음식 랭킹</span>
								</th>
								<th>
								</th>
								
								<th id="line-title" class="rank-table-movie" style="font-size:14px;font-weight:bold;" width="100%">
									<span style="width: 180px; display: inline-block; float: left; margin-bottom:12px;" align="left">영화 랭킹</span>
								</th>
							</tr>
							<tbody>
								<td style="width:50%;" id="boardlist-main" name="boardlist-main">
						
								</td>
								
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								
								<td class="rank-table-food" id="foodlist-rank" name="foodlist-rank" style="font-size:13px; vertical-align:top;">
									
								</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								
								<td class="rank-table-movie" id="movielist-rank"style="font-size:13px; vertical-align:top;">
									
								</td>
							</tbody>
					</table>
				</td>
			</tr>
		</div>
	</div>
</div>
	<!-- Footer -->
<footer class="w3-center w3-black w3-padding-16">
  <p>Powered by <a href="../../emotion/mainpage.ggd" title="GOGODA" target="_blank" class="w3-hover-text-green">GOGODA</a></p>
</footer>
</body>
</form>
</html>