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
	
	onload=function(){	//window.onload – 페이지 완전히 로딩후 처리하기위한 스크립트 (윈도우 로딩후)
		var id = opener.document.getElementsByName("myid")[0].value;	//opener 객체는 자기 자신을 연 기존 창의 window객체를 참조
		document.getElementsByName("id")[0].value = id;
	}
	
	function confirm(bool){
		if(bool=="true"){
			opener.document.getElementsByName("mypw")[0].focus();
			opener.document.getElementsByName("myid")[0].title="y";
		} else{
			opener.document.getElementsByName("myid")[0].focus();
		}
		self.close();
	}

</script>


</head>
<%
	String idnotused = request.getParameter("idnotused");
%>

<body>

	<table>
		<tr>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td><%=idnotused.equals("true")?"아이디 생성 가능":"중복 아이디 존재" %></td>
			
		</tr>
		<tr>
			<td>
			<input type="button" value="확인" onclick="confirm('<%=idnotused %>');">
			</td>
		</tr>
	</table>

</body>
</html>