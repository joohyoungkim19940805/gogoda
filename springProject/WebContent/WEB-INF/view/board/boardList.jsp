<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom_tag.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,
	  maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta http-equiv="X-UA-Compaible" content="IE=edge,chrome=1" />
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/main.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/include/css/board.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/include/js/common.js"></script>
<script type="text/javascript">
	
$(window).load(function(){
	
	//var detailCount=$("#count").length;
	//$('#under_footer').css('margin-top',(detailCount*25)+'px');
	//$('.applepie-image').css('height',(800+(detailCount*305))+'px');

});
	
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
		/* 검색 후 검색 대상과 검색 단어 출력 */
		if ("<c:out value='${data.keyword}' />"!="") {
				$("#keyword").val("<c:out value='${data.keyword}' />");
				$("#search").val("<c:out value='${data.search}' />");
		}
		
		/* 한 페이지에 보여줄 레코드 수 조회 후 선택한 값 그대로 유지하기 위한 설정 */
		if ("<c:out value='${data.pageSize}' />"!="") {
			$("#pageSize").val("<c:out value='${data.pageSize}' />");
		}
		
		/* 검색 대상이 변경될 때마다 처리 이벤트 */
		$("#search").change(function(){
				if ($("#search").val()=="all") {
						$("#keyword").val("전체 데이터 조회합니다.");
				} else if($("#search").val()!="all"){
						$("#keyword").val("");
						$("#keyword").focus();
				}
		});
		
		/* 검색 버튼 클릭 시 처리 이벤트 */
		$("#searchData").click(function(){
			if ($("#search").val()!="all") {
					if(!chkSubmit($('#keyword'),"검색어를")) return;
			}
			goPage(1);
		});
		
		/* 한 페이지에 보여줄 레코드 수 변경될 때마다 처리 이벤트 */
		$("#pageSize").change(function(){
			goPage(1);
		});
		
		/* 글쓰기 버튼 클릭 시 처리 이벤트 */
		$("#writeForm").click(function(){
			if("${seName}"!=''){
				location.href="writeForm.ggd";
			}else{
				alert("로그인 후 이용해주세요.");
			}
		});
		
		/* 제목 클릭시 상세 페이지 이동을 위한 처리 이벤트 */
		$(".goDetail").click(function(){
			var bnum = $(this).parents("tr").attr("data-num");
			$("#bnum").val(bnum);
			// 상세 페이지로 이동하기 위해 form 추가 (id:detailForm)
			$("#detailForm").attr({
					"method":"get",
					"action":"boardDetail.ggd"
			});
			$("#detailForm").submit();
		});
	});
	
	/* 정렬 버튼 클릭 시 처리 함수 */
	function setOrder(order_by){
		$("#order_by").val(order_by);
		if ($("#order_sc").val()=='DESC') {
				$("#order_sc").val('ASC');
		} else {
				$("#order_sc").val('DESC');
		}
		goPage(1);
	}
	
	/* 검색과 한 페이지에 보여줄 레코드 수 처리 및 페이징을 위한 실질적인 처리 함수 */
	function goPage(page){
		if ($("#search").val()=="all") {
			 	$("#keyword").val("");
		}
		$("#page").val(page);
		$("#f_search").attr({
				"method":"get",
				"action":"boardList.ggd"
		});
		$("#f_search").submit();
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
</form><br><br><br><br><br>
<!-- Header -->
<header class="display-container" style="max-width:1500px; " id="home">
   <img class="applepie-image" src="/logo/applepie.png" alt="Applepie" width="1500">
   <img class="applepie-image" src="/logo/applepie.png" alt="Applepie" width="1500">
   <div class="board-display">
   
       <div id="boardContainer" align="left">
<!-- 
	<div id="boardtop">
	<a href="../../emotion/mainpage.ggd">
		<img src="../images/common/gogoda.png" style="width:170px; height:170px">
	</a>
	</div>
 -->	
	<div id="boardTit"><h2><b>GOGODA 게시판</b></h2></div>
	<!-- 상세 페이지 이동을 위한 form -->
	<form name="detailForm" id="detailForm">
		<input type="hidden" name="bnum" id="bnum">
		<input type="hidden" name="page" value="${data.page}">
		<input type="hidden" name="pageSize" value="${data.pageSize}">
	</form>
	
		<%--======================= 검색 기능 시작 ==============================--%>
	<div id="boardSearch">
		<form id="f_search" name="f_search">
			<input type="hidden" id="page" name="page" value="${data.page}" />
			<input type="hidden" id="order_by" name="order_by" value="${data.order_by}" />
			<input type="hidden" id="order_sc" name="order_sc" value="${data.order_sc}" />
			<table summary="검색">
				
				<colgroup>
					<col width="70%"></col>
					<col width="30%"></col>
				</colgroup>
				
				<tr>
					<td id="btd1" align="center">
						<!-- 
						<label>검색조건</label>
						 -->
						<select id="search" name="search">
							<option value="all">전체</option>
							<option value="bsubject">제목</option>
							<option value="bcontent">내용</option>
							<option value="bname">글쓴이</option>
						</select>
						<input type="text" name="keyword" id="keyword" placeholder="검색어를 입력하세요" />
						<input type="button" value="검색" id="searchData">
					</td>
					<td id="btd2" align="right">한 페이지에
						<select id="pageSize" name="pageSize">
							<option value="10">10줄</option>
							<option value="20">20줄</option>
							<option value="30">30줄</option>
							<option value="50">50줄</option>
							<option value="70">70줄</option>
							<option value="100">100줄</option>
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%--======================== 검색 기능 종료  ===============================--%>
	<%--======================== 리스트 시작  ===============================--%>
	<div id="boardList">
	<table summary="게시판 리스트" >
		<colgroup>
			<col width="12%" />
			<col width="10%" />
			<col width="39%" />
			<col width="15%" />
			<col width="10%" />
			<col width="15%" />
		</colgroup>
		<thead>
			<tr>
				<th><a href="javascript:setOrder('bnum');" style="font-size:12px; color:black; text-decoration:none;">글번호
				<c:choose>
					<c:when test="${data.order_by=='bnum' and data.order_sc=='ASC'}">▲</c:when>
					<c:when test="${data.order_by=='bnum' and data.order_sc=='DESC'}">▼</c:when>
					<c:otherwise>▲</c:otherwise>
				</c:choose></a></th>
				<th class="borcle">글쓴이</th>
				<th>제목</th>
				<th>사진</th>
				<th>조회수</th>
				<th><a href="javascript:setOrder('binsertdate');" style="font-size:12px; color:black; text-decoration:none;">작성일
				<c:choose>
					<c:when test="${data.order_by=='binsertdate' and data.order_sc=='ASC'}">▲</c:when>
					<c:when test="${data.order_by=='binsertdate' and data.order_sc=='DESC'}">▼</c:when>
					<c:otherwise>▲</c:otherwise>
				</c:choose></a></th>
				<!--  
				<th class="borcle">작성자</th>
				-->
			</tr>
		</thead>
		<tbody>
		<!-- 데이터 출력 -->
		<c:choose>
			<c:when test="${not empty boardList}">
				<c:forEach var="board" items="${boardList}" varStatus="status">
					<tr align="center" data-num="${board.bnum}">
						<td>${count - (status.count-1)}</td>
						<td id="count">${board.bname}</td>
						<td align="left">
						<span class="goDetail">${board.bsubject}</span>
						</td>
						<td><img src="${pageContext.request.contextPath}/uploadStorage/${board.bfile}"  width="50" height="50" ></td>
						<td>${board.bhit}</td>
						<td>${board.binsertdate.substring(0,11)}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6" align="center">등록된 게시물이 존재하지 않습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
	</div>
	<%--========================= 리스트 종료 ================================= --%>

	
	<%--======================== 글쓰기 버튼 출력 시작 =========================== --%>
	<div id="boardBut" >
		<input type="button" value="글쓰기" id="writeForm" >
	</div>
	<%--======================== 글쓰기 버튼 출력 종료 =========================== --%>
	<%--======================== 페이지 네비게이션 시작  =========================== --%>
	<div id="boardPage">
		<tag:paging page="${param.page}" total="${total}" list_size="${data.pageSize}" />
	</div>
	<%--======================== 페이지 네비게이션 종료  =========================== --%>
	
	
</div>
	</div>
		
</header>
<!-- Footer -->
<footer class="w3-center w3-black w3-padding-16" id="under_footer">
  <p>Powered by <a href="../../emotion/mainpage.ggd" title="GOGODA" target="_blank" class="w3-hover-text-green">GOGODA</a></p>
</footer>
</body>
</html>