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

	<jsp:useBean id="dto" class="com.answer.dto.AnswerDto" scope="request"></jsp:useBean>
	
	<h1>상세 글 보기</h1>
	
	<table border="1">
		<tr>
			<th>번호</th>
			<td><jsp:getProperty property="boardno" name="dto"/></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><jsp:getProperty property="writer" name="dto"/></td>
		</tr>
		<tr>
			<th>제   목</th>
			<td><jsp:getProperty property="title" name="dto"/></td>
		</tr>
		<tr>
			<th>내   용</th>
			<td><textarea rows="10" cols="60" readonly="readonly"><jsp:getProperty property="content" name="dto"/></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='answer.do?command=updateform&boardno=${dto.boardno}'"/>
				<input type="button" value="삭제" onclick="location.href='answer.do?command=delete&boardno=<jsp:getProperty property="boardno" name="dto"/>'"/>
				<input type="button" value="목록" onclick="location.href='answer.do?command=list'"/>
				<input type="button" value="답글" onclick="location.href='answer.do?command=answerform&parentboardno=${dto.boardno}'">				
			</td>
		</tr>
		
	</table>

</body>
</html>