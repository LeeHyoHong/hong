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

	<h1>게시글 보기</h1>
	
	<form action="answer.do" method="post">
		<input type="hidden" name="command" value="update">
		<input type="hidden" name="boardno" value="${dto.boardno }"> 
		
		<table border="1">
			<tr>
				<th>번호</th>
				<td>${dto.boardno }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.writer }</td>
			</tr>
			<tr>
				<th>제	목</th>
				<td><input type="text" name="title" value="${dto.title }"></td>
			</tr>
			<tr>
				<th>내	용</th>
				<td><textarea rows="10" cols="60" name="content">${dto.content }</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="수정">
				<input type="button" value="삭제" onclick="location.href='answer.do?command=delete&boardno=${dto.boardno}'">
				<input type="button" value="목록" onclick="location.href='answer.do?command=list'">
				</td>
			</tr>	
		</table>
	</form>


</body>
</html>