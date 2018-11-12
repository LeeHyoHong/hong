<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% request.setCharacterEncoding("UTF-8"); %>
	<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>회원등급 변경 페이지</h1>
	
	<form action="answer.do" method="post">
		<input type="hidden" name="command" value="updaterole" />
		<input type="hidden" name="myno" value="${dto.myno }" />
		<table border="1">
			<col width="50"/>
			<col width="200"/>
			<tr>
				<th>번호</th>
				<td>${dto.myno }</td>
				<th>이름</th>
				<td>${dto.myname }</td>
				<th>등급</th>
				<td>
					<select name="myrole">
						<option value="USER" %{dto.myrole}.equals("USER")?"selected":"" %>일반회원</option>
							
					</select>
				</td>
				
			</tr>
		
		</table>
	</form>


</body>
</html>