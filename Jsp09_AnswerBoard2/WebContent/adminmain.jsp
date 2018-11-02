<%
	response.setHeader("Cache-control", "no-store"); //데이터가 변경되었을 때 이전 내용을 화면에 보여주지 않도록 캐시에 응답결과를 저장하지 않음
%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% request.setCharacterEncoding("UTF-8"); %>
	<% response.setContentType("text/html; charset=UTF-8"); %>
    
<%@ page import="com.answer.dto.AnswerDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- <% 
	AnswerDto dto = (AnswerDto)session.getAttribute("dto");
%> --%>

	
	
	<div>
		<span> ${dto.myid }님 환영합니다.
		(등급 : ${dto.myrole } </span>
	</div>
	
	<div>
		<div>
			<a href="answer.do?command=userlistall">회원정보 조회(ALL)</a>
		</div>
		<div>
			<a href="answer.do?command=userlistenabled">회원정보 조회(enabled='Y')</a>
		</div>
		<div>
			<a href="answer.do?command=list">글 목록</a>
		</div>
	</div>

</body>
</html>