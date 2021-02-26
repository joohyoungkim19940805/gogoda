<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
	<h2>회원 목록</h2>
	<input type="button" value="회원등록" onclick="location.href='${path}/registerForm.do'">
	<table border="1" width="2000px">
		<tr>
			<th>회원 번호</th>
			<th>이름</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>생년월일</th>
			<th>성별</th>
			<th>핸드폰</th>
			<th>이메일</th>
			<th>우편번호</th>
			<th>주소</th>
			<th>상세주소</th>
			<th>삭제여부</th>
			<th>회원가입일자</th>
			<th>회원수정일자</th>
		</tr>
		<c:forEach var="list" items="${listAll }">
		<tr>
			<td>${list.mnum}</td>
			<!-- 회원정보 상세조회를 위해 a태그 추가 -->
			<td><a href="${path}/memberSelect.do?mnum=${list.mnum}">${list.mname}</a></td>
			<td>${list.mid}</td>
			<td>${list.mpw}</td>
			<td>${list.mbirth}</td>
			<td>${list.mgender}</td>
			<td>${list.mhp}</td>
			<td>${list.memail}</td>
			<td>${list.mzipcode}</td>
			<td>${list.maddr}</td>
			<td>${list.maddrdetail}</td>
			<td>${list.mdeleteyn}</td>
			<td>${list.minsertdate}</td>
			<td>${list.mupdatedate}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>