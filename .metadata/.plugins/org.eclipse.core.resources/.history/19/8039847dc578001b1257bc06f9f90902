<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="g.g.d.com.mem.vo.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 상세 페이지</title>
<%@ include file="header.jsp" %>

<%

String mnum=request.getAttribute("mnum").toString();
//System.out.println("====================================="+mnum);
%>
<script>
	$(document).ready(function(){
		$("#btnUpdate").click(function(){
			// 확인 대화상자	
			if(confirm("수정하시겠습니까?")){
				document.form1.action = "${path}/memberUpdate.do";
				document.form1.submit();
			}
		});
	});
	$(document).ready(function(){
		$("#btnDelete").click(function(){
			// 확인 대화상자 
			if(confirm("삭제하시겠습니까?")){
				document.form1.action = "${path}/memberDelete.do";
				document.form1.submit();
			}
		});
	});
</script>
</head>
<body>
<%@ include file="menu.jsp" %>
	<h2>회원정보 상세</h2>
	<form name="form1" method="post">
		<table border="1" width="400px">
			<tr>
				<td><input type="hidden" id="mnum" name="mnum" value='<%=mnum%>' readonly/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input name="mname" value="${detail.mname}" readonly/></td>
			</tr>
			<tr>
				<td>아이디</td>
				 <!-- id는 수정이 불가능하도록 readonly속성 추가 -->
				<td><input name="mid" value="${detail.mid}" readonly/></td>
			</tr>
			<!--  <tr>
				<td>비밀번호</td>
				<td><input type="password" name="mpw"></td>
			</tr>-->
			<tr>
				<td>생년월일</td>
				<td><input name="mbirth" value="${detail.mbirth}" readonly/></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><input name="mgender" value="${detail.mgender}" readonly/></td>
			</tr>
			<tr>
				<td>핸드폰</td>
				<td><input name="mhp" value="${detail.mhp}" ></td>
			</tr>
			<tr>
				<td>이메일주소</td>
				<td><input name="memail" value="${detail.memail}" ></td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td><input name="mzipcode" value="${detail.mzipcode}" readonly/></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input name="maddr" value="${detail.maddr}" readonly/></td>
			</tr>
			<tr>
				<td>상세주소</td>
				<td><input name="maddrdetail" value="${detail.maddrdetail}" ></td>
			</tr>
			<tr>
				<td>회원가입일자</td>
				<td>
					<input name="minsertdate" value="${detail.minsertdate}"  readonly/>
				</td>
			</tr>
			<tr>
				<td>회원정보 수정일자</td>
				<td>
					<input name="mupdatedate" value="${detail.mupdatedate}" readonly/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수정" id="btnUpdate">
					<input type="button" value="삭제" id="btnDelete">
					<div style="color: red;">${message}</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>