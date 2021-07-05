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
<h1>状態: init</h1>
<form method="post" enctype="multipart/form-data" action="/myServlets/FileReceiver">
<input type="file">
<input type="submit" value="upload" name="btn_type">
</form>
<form method="get" action="/myServlets/Controller">
<input type="submit" value="run" name="btn_type">
</form>
<input disabled type="submit" value="save" name="btn_type">
<input disabled type="submit" value="resume" name="btn_type">
<%
}
%>

<%
if (state.getState() == "running"){
%>
<h1>状態: running</h1>
<form method="post" enctype="multipart/form-data" action="/myServlets/FileReceiver">
<input disabled type="file">
<input disabled type="submit" value="upload" name="btn_type">
</form>
<input disabled type="submit" value="run" name="btn_type">
<form method="get" action="/myServlets/Controller">
<input type="submit" value="save" name="btn_type">
</form>
<input disabled type="submit" value="resume" name="btn_type">
<%
}
%>

<%
if (state.getState() == "stopped"){
%>
<h1>状態: stopped</h1>
<form method="post" enctype="multipart/form-data" action="/myServlets/FileReceiver">
<input disabled type="file">
<input disabled type="submit" value="upload" name="btn_type">
</form>
<input disabled type="submit" value="run" name="btn_type">
<input disabled type="submit" value="save" name="btn_type">
<input disabled type="submit" value="resume" name="btn_type">
<%
}
%>

<%
if (state.getState() == "loaded"){
%>
<h1>状態: loaded</h1>
<form method="post" enctype="multipart/form-data" action="/myServlets/FileReceiver">
<input type="file">
<input type="submit" value="upload" name="btn_type">
</form>
<form method="get" action="/myServlets/Controller">
<input type="submit" value="run" name="btn_type">
</form>
<input disabled type="submit" value="save" name="btn_type">
<form method="get" action="/myServlets/Controller">
<input type="submit" value="resume" name="btn_type">
</form>
<%
}
%>

</body>
</html>