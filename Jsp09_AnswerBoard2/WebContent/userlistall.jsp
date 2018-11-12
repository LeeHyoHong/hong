<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% request.setCharacterEncoding("UTF-8"); %>
	<% response.setContentType("text/html; charset=UTF-8"); %>
    

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>회원 정보 조회(all)</h1>
	
	<table border="1">
		<col width="50">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="500">
		<col width="150">
		<col width="300">
		<col width="100">
		<col width="50">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입여부</th>
			<th>등급</th>
		</tr>	
		<c:choose>
			<c:when test="${empty list}">
				<tr>
					<td colspan="8">=====회원이 없습니다=====</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.myno }</td>
						<td>${dto.myid }</td>
						<td>${dto.mypw }</td>
						<td>${dto.myname }</td>
						<td>${dto.myaddr }</td>
						<td>${dto.myphone }</td>
						<td>${dto.myemail }</td>
						<td>${dto.myenabled }</td>
						<td>${dto.myrole }</td>
					</tr>
				</c:forEach>
			
			</c:otherwise>
	
		</c:choose>
		
	</table>
		


</body>
</html>