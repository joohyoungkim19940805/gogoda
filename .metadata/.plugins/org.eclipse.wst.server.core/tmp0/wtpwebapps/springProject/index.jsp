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
<title>Insert title here</title>
<style>
#ranking{

float:right;
text-align: right;
vertical-align: middle;
}

li{
	list-style:none;
}

#mainimg{
width:150px; 
height:200px;
}
#line-title{
border-bottom: 1px solid Gainsboro ;
padding: 0px;
}
#a-none { text-decoration:none } 

.board-wi-he{
width:10px;
position: relative;
left: 430px;
}

.rank-table-food{
position:relative;
left:100px;
}
.rank-table-movie{
position:relative;
left:150px;
}


.container-1 input#text{

  font-size: 10pt;
  color: #63717f;
  padding-left: 45px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  border-radius: 5px;
  -webkit-transition: background .55s ease;
-moz-transition: background .55s ease;
-ms-transition: background .55s ease;
-o-transition: background .55s ease;
transition: background .55s ease;
}
.container-1 input#text::-webkit-input-placeholder {
   color: #65737e;
}
 
.container-1 input#text:-moz-placeholder { /* Firefox 18- */
   color: #65737e;  
}
 
.container-1 input#text::-moz-placeholder {  /* Firefox 19+ */
   color: #65737e;  
}
 
.container-1 input#text:-ms-input-placeholder {  
   color: #65737e;  
}
.container-1 .icon{
  position: absolute;

  margin-left: 17px;
  margin-top:17px;
  z-index: 1;
  color: #4f5b66;
}
.container-1 input#text:hover, .container-1 input#text:focus, .container-1 input#text:active{
    outline:none;
    background: #E8F5FF;
  }
</style>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var boardData;
		//alert("ready");
		$(window).load(function(){
			if("${seName}"!=''){
				$("#loginbtn").remove();
				$("#meminsert").remove();
				$("#memlogin").append("<a href='../../springProject/mem/memberSelect.ggd' style='color:black' id='' class='comlogin'>${seName}</a>님 환영합니다. &nbsp;&nbsp;&nbsp;");
				$("#memlogin").append("<button type='button' class='comlogin' id='memlogout' onclick='logoutBtn()'>로그아웃</button>");
			}
			//게시판 최신순 조회
			 $.ajax({
	                url : '../../springProject/emotion/mainboard.ggd',
	                type : 'POST',
	                async:false,
	                datatype:'json',
	                success: function(data) {
	                	boardData=data;
	                	for(var i=0;i<boardData.length;i++){
	                		if(boardData[i]["bsubject"].length<35){
			                	var boardListData="<li>"+
								"<a href='../../springProject/board/boardDetail.ggd?bnum="+boardData[i]["bnum"]+"' style='color:black;' id='a-none'>"+
									"<span><b>"+boardData[i]["bname"]+"</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
								boardData[i]["bsubject"]+"<font color='#1551a4'>["+boardData[i]["cnt"]+"]</font></a>"+
								"</li><br>";
							}else{
								var boardListData="<li>"+
								"<a href='../../springProject/board/boardDetail.ggd?bnum="+boardData[i]["bnum"]+"' style='color:black;' id='a-none'>"+
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
	                url : '../../springProject/emotion/foodrank.ggd',
	                type : 'POST',
	                async:false,
	                datatype:'json',
	                success: function(data) {
	                	foodData=data;
	                	console.log(data);
	                	console.log(foodData[0]["fname"]);
	                	for(var i=0;i<10;i++){
	                		
	                		if(foodData[i]["fname"].length<7){
	                		var foodListData="<li><a href='../review/test1.ggd?food="+foodData[i]["fname"]+"'style='color:black;' id='a-none'><span><b>"+(i+1)+"위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+foodData[i]["fname"]+
								"</span>"+
								"<span id='ranking'>"+foodData[i]["foodcnt"]+"&nbsp;<b style='color:red;'>↑</b></span></a>"+
								"</li><br>"
	                		}else{
	                			var foodListData="<li><a href='../review/test1.ggd?food="+foodData[i]["fname"]+"'style='color:black;' id='a-none'><span><b>"+(i+1)+"위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+foodData[i]["fname"].substring(0,7)+"..."+
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
	                url : '../../springProject/emotion/movierank.ggd',
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
	                			var movieListData="<li><a href='../emotion/moviecount.ggd?movieNum="+movieData[i]["mvnum"]+"&movieLink="+movieData[i]["mvlink"]+"'style='color:black;' id='a-none'><span><b>"+(i+1)+"위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+movieData[i]["mvname"].substring(0,7)+"..."+
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
			
				$("#mainForm").attr("action","../../springProject/emotion/emotionSearch.ggd");
				$("#mainForm").attr("method","GET");
				$("#mainForm").attr("enctype","application/x-www-form-urlencoded");
				$("#mainForm").submit();
			}else{
				alert("로그인 후 이용해주십시오.");
			}
		});
		$(document).on("click","#loginbtn",function(){
			$("#mainForm").attr("action","../../springProject/logincontroller/loginpage.ggd");
			$("#mainForm").attr("method","POST");
			$("#mainForm").attr("enctype","application/x-www-form-urlencoded");
			$("#mainForm").submit();
		});
		$(document).on("click","#meminsert",function(){
			$("#mainForm").attr("action","../../springProject/mem/registerForm.ggd");
			$("#mainForm").attr("method","POST");
			$("#mainForm").attr("enctype","application/x-www-form-urlencoded");
			$("#mainForm").submit();
		});
	});
	
	function logoutBtn(){
		$("#mainForm").attr("action","../emotion/logout.ggd");
		$("#mainForm").attr("method","POST");
		$("#mainForm").attr("enctype","application/x-www-form-urlencoded");
		$("#mainForm").submit();
		
	}
	
</script>
</head>
<form name="mainForm" id="mainForm">
<body>
	<div>
		<div id="topele">
			<div align="right" id="memlogin">
				<button type="button" id="loginbtn">로그인</button>
				<button type="button" id="meminsert">회원가입</button>
			</div>
			<br><br>
			<div align="center">
				<th>
					<td>
						<img src="/springProject/logo/pjimg.png" id="mainimg">
					</td>	
					&nbsp;&nbsp;&nbsp;
					<td>
						<img src="/springProject/logo/logo.png" id="mainimg2">
					</td>

				</th>
			</div>
			<br>
			<div align="center">
				<div class="box">
					<div class="container-1">
						<span class="icon"><i class="fa fa-search"></i></span>
						<input type="text" id="text" name="text" style="width: 810px; height:55px;'" placeholder="오늘 어떤 일이 있으셨나요?">
					</div>
				</div>
			</div>
		</div>
		<div id="topboard" align="center" style="position: relative; z-index: 0; overflow: hidden; min-width: 100%; height: 100%; touch-action: pan-x; user-select: none; -webkit-user-drag: none; width: 210px;">
			<br>
			<br>
			<br>
		</div>
		<br>
		<br>
		<div id="under">
			<tr>
				<td id="under-table"><!-- white -->
					<table class="board-wi-he" bgcolor="white" border='0'
							style="border-style:solid;border-width:0px;border-color:#c2c1c6; margin-bottom: 5px; " >
						<tbody>
							<tr>
								<td id="line-title"style="font-size:14px;font-weight:bold;" width="100%">
									<span>게시글</span>
									<a href='../../springProject/board/boardList.ggd' style='color:black' id='a-none'><span style="width: 500px; display: inline-block; float: right; font-size:12px;" align="right">더보기</span></a>
								</td>

								<td id="line-title" class="rank-table-food" style="font-size:14px;font-weight:bold;" width="100%">
									<span style="width: 180px; display: inline-block; float: left;" align="left">음식 랭킹</span>
								</td>
								<td id="line-title" class="rank-table-movie" style="font-size:14px;font-weight:bold;" width="100%">
									<span style="width: 180px; display: inline-block; float: left;" align="left">영화 랭킹</span>
								</td>
							</tr>
							<tr>
								<td style="width:50%;" id="boardlist-main" name="boardlist-main">
									
								</td>
								<td class="rank-table-food" id="foodlist-rank" name="foodlist-rank" style="font-size:13px;" align="top">
									
								</td>
								<td class="rank-table-movie" id="movielist-rank"style="font-size:13px;" align="top">
									
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</div>
	</div>
</body>
</form>
</html>