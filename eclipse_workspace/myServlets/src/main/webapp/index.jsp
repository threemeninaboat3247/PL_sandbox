<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="hoge.State"%>
<jsp:useBean id="state" class="hoge.State" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Control Panel</title>
</head>
<body>
<h1>Control Panel</h1>

<script>
function test1(){
	console.log("onclick event"); //test1と表示される
}
</script>

<%
if (state.getState() == "init"){
%>
<form method="get" action="/myServlets/Controller">
<input type="submit" onclick=test()  value="run" name="btn_type">
</form>
<%
}
%>

<%
if (state.getState() == "running"){
%>
<form method="get" action="/myServlets/Controller">
<input type="submit" value="pause" name="btn_type">
</form>
<%
}
%>

<%
if (state.getState() == "halt"){
%>
<form method="get" action="/myServlets/Controller">
<input type="submit" value="resume" name="btn_type">
</form>
<%
}
%>

<%
if (state.getState() == "finish"){
%>
<form method="get" action="/myServlets/Controller">
<input type="submit" value="end" name="btn_type">
</form>
<%
}
%>

</body>
</html>