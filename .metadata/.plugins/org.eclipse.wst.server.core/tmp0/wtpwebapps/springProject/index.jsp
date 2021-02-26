<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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
left: 200px;
}

.rank-table-food{
position:relative;
left:700px;
}
.rank-table-movie{
position:relative;
left:800px;
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
		//alert("ready");
		$(document).on("click",".icon",function(){
			
			$("#mainForm").attr("action","emotion/emotionSearch.ggd");
			$("#mainForm").attr("method","GET");
			$("#mainForm").attr("enctype","application/x-www-form-urlencoded");
			$("#mainForm").submit();
		});
		$(document).on("click","#loginbtn",function(){
			$("#mainForm").attr("action","logincontroller/loginpage.ggd");
			$("#mainForm").attr("method","POST");
			$("#mainForm").attr("enctype","application/x-www-form-urlencoded");
			$("#mainForm").submit();
		});
		$(document).on("click","#meminsert",function(){
			$("#mainForm").attr("action","mem/registerForm.ggd");
			$("#mainForm").attr("method","GET");
			$("#mainForm").attr("enctype","application/x-www-form-urlencoded");
			$("#mainForm").submit();
		});
	});
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
			
			<div align="center">
				<th>
					<td>
						<img src="/springProject/logo/pjimg.png" id="mainimg">
					</td>	
					<td>
						<img src="/springProject/logo/logo.png" id="mainimg2">
					</td>

				</th>
			</div>
			<div align="center">
				<div class="box">
					<div class="container-1">
						<span class="icon"><i class="fa fa-search"></i></span>
						<input type="text" id="text" name="text" style="width: 1450px; height:70px;'" placeholder="오늘 어떤 일이 있으셨나요?">
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
									<a href="board/boardList.ggd" style="color:black" id="a-none"><span style="width: 400px; display: inline-block; float: right; font-size:12px;" align="right">더보기</span></a>
								</td>

								<td id="line-title" class="rank-table-food" style="font-size:14px;font-weight:bold;" width="100%">
									<span style="width: 180px; display: inline-block; float: right;" align="right">음식 랭킹</span>
								</td>
								<td id="line-title" class="rank-table-movie" style="font-size:14px;font-weight:bold;" width="100%">
									<span style="width: 180px; display: inline-block; float: right;" align="right">영화 랭킹</span>
								</td>
							</tr>
							<tr>
								<td style="width:50%;">
									<li>
										<a href="" style="color:black;" id="a-none">
											<span><b>작성자1</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										난... ㄱㅏ끔...<font color="#1551a4">[5]</font></a>
									</li><br>
									<li>
										<a href="" style="color:black;" id="a-none">
											<span><b>작성자2</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										눈물을 흘린ㄷㅏ....<font color="#1551a4">[999]</font></a>
									</li><br>
									<li>
										<a href="" style="color:black;" id="a-none">
											<span><b>작성자3</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										ㄱㅏ끔은 눈물을 참을 수 없는 ㄴㅐ가 별루ㄷㅏ...<font color="#1551a4">[7]</font></a>
									</li><br>
									<li>
										<a href="" style="color:black;" id="a-none">
											<span><b>작성자4</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										맘이 ㅇㅏㅍㅏ서.... <font color="#1551a4">[1]</font></a>
									</li><br>
									<li>
										<a href="" style="color:black;" id="a-none">
											<span><b>작성자5</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										소ㄹㅣ치며... 울 수 있ㄷㅏ는건....<font color="#1551a4"></font></a>
									</li><br>
									<li>
										<a href="" style="color:black;" id="a-none">
											<span><b>작성자6</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										좋은ㄱㅓㅇㅑ.....<font color="#1551a4">[2]</font></a>
									</li><br>
									<li>
										<a href="" style="color:black;" id="a-none">
											<span><b>작성자7</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										ㅁㅓ... 꼭 슬ㅍㅓㅇㅑ만 우는 건 ㅇㅏ니잖ㅇㅏ...^^<font color="#1551a4">[6]</font></a>
									</li><br>
									<li>
										<a href="" style="color:black;" id="a-none">
											<span><b>작성자8</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										난...눈물ㅇㅣ.... 좋 다....<font color="#1551a4">[4]</font></a>
									</li><br>
									<li>
										<a href="" style="color:black;" id="a-none">
											<span><b>작성자9</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										ㅇㅏ니...<font color="#1551a4">[7]</font></a>
									</li><br>
									<li>
										<a href="" style="color:black;" id="a-none">
											<span><b>작성자10</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										ㅁㅓ리가ㅇㅏ닌....<font color="#1551a4">[100]</font></a>
									</li><br>
									<li>
										<a href="" style="color:black;" id="a-none">
											<span><b>작성자11</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										맘으로.....우는 ㄴㅐㄱㅏ좋ㄷㅏ.....<font color="#1551a4">[1000]</font></a>
									</li><br>
								</td>
								<td class="rank-table-food" style="font-size:13px;" align="center">
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>100&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>2위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>12&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>1&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>1&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>1&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>1&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>1&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>1&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>1&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>1&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
								</td>
								<td class="rank-table-movie" style="font-size:13px;" align="center">
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>100&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>100&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>100&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>100&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>100&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>100&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>100&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>100&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>100&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
									<li>
										<span><b>1위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가자미
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>100&nbsp;<b style="color:red;">↑</b></span>
									</li><br>
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