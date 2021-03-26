<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>취향 설문 조사</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/main.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/survey.css" />

<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">

<script type="text/javascript">
//체크박스 체크 확인하기 

$(document).ready(function(){
	//alert(">>>"+mnum);
	$("#I").click(function(){
		
		var positivefood=$("input[name='positivefood']:checked").val();
		console.log("gSize >>> : "+ $("input[name='positivefood']:checked").val());

		var negativefood=$("input[name='negativefood']:checked").val();
		console.log("gSize >>> : "+ $("input[name='negativefood']:checked").val());

		var obj = $("[name='movietaste']");
		console.log("obj>>"+obj);
		var chkArray = new Array(); // 배열 선언
		$('input:checkbox[name=movietaste]:checked').each(function () { // 체크된 체크박스의 value 값을 가지고 온다.
	 		 chkArray.push(this.value);
		});
		
		var flag = "";
		flag = aaa();
		console.log(flag);

		if(flag.length>0){
			$('#memForm').attr('action', 'surveyInsert.ggd?positivefood='+positivefood+'&negativefood='+negativefood+'&movietaste='+flag);
			$('#memForm').attr('method', 'POST');
			$('#memForm').attr('enctype', 'multipart/form-data');
			$('#memForm').submit();
			
		}
	});
	
	function aaa(){
		var flag = false;
		var values = document.getElementsByName("movietaste");
		//alert(values.length);
		var count = 0;
		var movietaste="";
		for(var i=0; i<values.length; i++){
			if(values[i].checked){
				movietaste+=values[i].value+",";
				count++;
			}	
			//return flag;
		}
		if(count < 2){
			alert("2개 이상 선택해야 합니다.");
		}else{
			alert("GOGODA의 회원이 되신 것을 진심으로 환영합니다.!");
			flag = true;
		}
		return movietaste;
	}
});

</script>
</head>

<body>
<!-- Navbar (sit on top) -->
<div class="w3-top">
	<div class="w3-bar w3-white w3-wide w3-padding w3-card">
		<a href="../../emotion/mainpage.ggd">
			<img src="/logo/GOGODA-logo.png" style="width:12%; height:12%">
		</a>
	</div>
</div><br><br><br><br><br>

	<div class="suveycontainer">
		<section>
			<h1 class="entry-title">
				<span><b>설문 조사</b></span>
			</h1>
			<hr>
			<div class="survey">
			<form name="memForm" id="memForm" enctype="multipart/form-data">

				<h3>1. 아침부터 기분이 좋은 날! 이럴 때 어떤 음식을 먹고 싶나요?</h3>
				<label class="container">1. 든든하게 배를 채울 음식 
					<input name="positivefood" id="positivefood" type="radio" value="1">
					<span class="checkmark"></span>
				</label> 
				<label class="container">2. 맵거나 짠 자극적인 음식 
					<input name="positivefood" id="positivefood" type="radio" value="2">
					<span class="checkmark"></span>
				</label> 
				<label class="container">3. 단짠이 잘 어우러진 음식 
					<input name="positivefood" id="positivefood" type="radio" value="3">
					<span class="checkmark"></span>
				</label> 
				<label class="container">4. 술 안주로 적절한 음식
					<input name="positivefood" id="positivefood" type="radio" value="4">
					<span class="checkmark"></span>
				</label><br>

				<h3>2. 속상한 일들로 기분이 울쩍한 날... 어떤 음식을 먹고 싶나요?</h3>
				<label class="container">1. 든든하게 배를 채울 음식 
					<input name="negativefood" id="negativefood" type="radio" value="1">
					<span class="checkmark"></span>
				</label> <label class="container">2. 맵거나 짠 자극적인 음식 
					<input name="negativefood" id="negativefood" type="radio" value="2">
					<span class="checkmark"></span>
				</label> <label class="container">3. 단짠이 잘 어우러진 음식 
					<input name="negativefood" id="negativefood" type="radio" value="3">
					<span class="checkmark"></span>
				</label> <label class="container">4. 술 안주로 적절한 음식
					<input name="negativefood" id="negativefood" type="radio" value="4">
					<span class="checkmark"></span>
				</label><br>

				<h3>3. 어떤 장르의 영화를 좋아하나요?</h3>
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="drama" />
					1: 드라마 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="war" />
					2: 전쟁 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="action" />
					3: 액션 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="family" />
					4: 가족 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="adult" />
					5: 성인물(에로) <span class="checkmark2"></span>
				</label><br>
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="crime" />
					6: 범죄 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="mystery" />
					7: 미스터리 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="animation" />
					8: 애니메이션 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="comedy" />
					9: 코미디 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="horror" />
					10: 공포(호러) <span class="checkmark2"></span>
				</label><br>
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="romance" />
					11: 멜로/로맨스 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="SF" />
					12: SF <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="documentary" />
					13: 다큐멘터리 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="thriller" />
					14: 스릴러 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="musical" />
					15: 뮤지컬 <span class="checkmark2"></span>
				</label><br>
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="opera" />
					16: 공연 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="fantasy" />
					17: 판타지 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="adventure" />
					18: 어드벤처 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="historical" />
					19: 사극 <span class="checkmark2"></span>
				</label> 
				<label class="container2"> 
					<input type="checkbox" id="movietaste" name="movietaste" value="western" />
					20: 서부극(웨스턴) <span class="checkmark2"></span>
				</label>
				<br><br>
				<div class="button">
					<input type="button" id="I" value="제출">
				</div>
			</form>
			</div>
		</section>
	</div>
<!-- Footer -->
<footer class="w3-center w3-black w3-padding-16">
  <p>Powered by <a href="../../emotion/mainpage.ggd" title="GOGODA" target="_blank" class="w3-hover-text-green">GOGODA</a></p>
</footer>
</body>
</html>