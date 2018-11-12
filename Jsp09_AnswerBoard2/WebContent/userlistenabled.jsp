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

<script type="text/javascript">
	
	function updateRole(myno){
		location.href = "AnswerController.jsp?command=updateroleform&myno="+myno;
	}

</script>

</head>
<body>

	<h1>회원정보조회(enabled='Y')</h1>
	
	<table border=1>
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>등급</th>
			<th>등급변경</th>
		</tr>
		<c:choose>
			<c:when test="${empty list }">
				<tr>
					<td colspan="8">=====등록된 회원이 없습니다.=====</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.myno }</td>
						<td>${dto.myid }</td>
						<td>${dto.myname }</td>
						<td>${dto.myemail }</td>
						<td>${dto.myrole }</td>
						<td><button onclick="updateRole(${dto.myno})">변경</button></td>
					</tr>
				</c:forEach>
				<tr>
					<td	colspan="6">
						<button onclick="location.href='AnswerController.java?command=login&id=${dto.myid}&pw=${dto.mypw }'">메인</button>
					</td>
				</tr>
				
			</c:otherwise>
		</c:choose>
		
	
	</table>

</body>
</html>