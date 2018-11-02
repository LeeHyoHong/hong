<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

	function registForm(){
		location.href = "answer.do?command=registform";
	}
	
</script>

</head>
<body>

	<%-- <jsp:forward page="answer.do?command=list"></jsp:forward> --%>
	<form action="answer.do" method="post">
	<input type="hidden" name="command" value="login">
	<table border="1">
		<col width="100"/>
		<col width="100"/>
		<tr>
			<th>I D : </th>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<th>P W : </th>
			<td><input type="text" name="pw"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="login">
				<input type="button" value="regist" onclick="registForm();">
			</td>
		</tr>	
	</table>
	
	</form>

</body>
</html>