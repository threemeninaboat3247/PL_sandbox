<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="hoge.State"%>
<jsp:useBean id="state" class="hoge.State" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script>
<title>Control Panel</title>
</head>
<body>
<h1>Control Panel</h1>
<%
if (state.getState() == "init"){
%>
<h1>状態: init</h1>
<form method="post" enctype="multipart/form-data" action="/myServlets/FileReceiver">
<input type="file">
<input type="submit" value="upload" name="btn_type">
</form>
<form method="get" action="/myServlets/Controller" name="btn_form">
<input type="submit" value="run" id="run">
<input disabled type="submit" value="save" id="save">
<input disabled type="submit" value="resume" id="resume">
</form>
<%
}
%>

<%
if (state.getState() == "running"){
%>
<h1>状態: running</h1>
<form method="post" enctype="multipart/form-data" action="/myServlets/FileReceiver">
<input type="file">
<input type="submit" value="upload" name="btn_type">
</form>
<form method="get" action="/myServlets/Controller" name="btn_form">
<input disabled type="submit" value="run" id="run">
<input type="submit" value="save" id="save">
<input disabled type="submit" value="resume" id="resume">
</form>
<%
}
%>

<%
if (state.getState() == "stopped"){
%>
<h1>状態: stopped</h1>
<form method="post" enctype="multipart/form-data" action="/myServlets/FileReceiver">
<input type="file">
<input type="submit" value="upload" name="btn_type">
</form>
<form method="get" action="/myServlets/Controller" name="btn_form">
<input disabled type="submit" value="run" id="run">
<input disabled type="submit" value="save" id="save">
<input disabled type="submit" value="resume" id="resume">
</form>
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
<form method="get" action="/myServlets/Controller" name="btn_form">
<input type="submit" value="run" id="run">
<input disabled type="submit" value="save" id="save">
<input type="submit" value="resume" id="resume">
</form>
<%
}
%>

<script>
//1秒毎にサーバに最新の状態を問い合わせ、それが自分の表示している状態と違えば再度index.jspへリクエストを出して
// そのレスポンスでもって表示を更新する
const timer = 1000;
document.addEventListener("DOMContentLoaded", function() {
	setInterval(function(){
		console.log("sent request to StateSender");
		$.get("StateSender", function(data) {
		if(data != "<%=state.getState()%>") {
				location.href = location.protocol + "//" + location.host + "/myServlets/index.jsp";
			}
		})
	}, timer);
});

document.addEventListener("DOMContentLoaded", function() {
	document.getElementById("run").addEventListener("click", function(){
		window.open("/myServlets/Calculator", "tab2", "width=400, height=400");
		let ele = document.createElement("input");
		ele.display = "none";
		ele.value = "run";
		ele.name = "btn_type";
		document.btn_form.appendChild(ele);
	});
});

document.addEventListener("DOMContentLoaded", function() {
	document.getElementById("save").addEventListener("click", function(){
		let ele = document.createElement("input");
		ele.display = "none";
		ele.value = "save";
		ele.name = "btn_type";
		document.btn_form.appendChild(ele);
	});
});

document.addEventListener("DOMContentLoaded", function() {
	document.getElementById("resume").addEventListener("click", function(){
		window.open("/myServlets/Calculator", "tab2", "width=400, height=400");
		let ele = document.createElement("input");
		ele.display = "none";
		ele.value = "resume";
		ele.name = "btn_type";
		document.btn_form.appendChild(ele);
	});
});
</script>
</body>
</html>