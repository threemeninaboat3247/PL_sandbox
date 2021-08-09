<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- XMLHttpRequestを使って複数のサーブレットからの応答をページのリロード無しで順次表示するサンプルコードj -->
<!-- 
課題
1. xhrを使ってサーブレットからのresponseを受け取る OK
2. xhrを使って複数のサーブレットからのresponseを一つのhtml内にリロード無しで表示する OK
3. サーブレットからのresponseが返ってくるまで待機中であることをユーザに告知する OK
 -->
<html>
<head>
<meta charset="UTF-8">
<title>XML Http Request Demo</title>
</head>
<body>
<div id="result"></div>
<div id="result2"></div>
<script>
document.addEventListener("DOMContentLoaded", function() {
	  let xhr = new XMLHttpRequest();
	  let xhr2 = new XMLHttpRequest();
	  xhr.onreadystatechange = function() {
		  if (xhr.readyState === 4) { //通信完了
			  if (xhr.status === 200) { //通信成功
				  result.textContent = xhr.responseText;
          xhr2.open("GET", "/myServlets/StateSender", true);
          xhr2.send(null);
			  } else { //通信失敗
				  result.textContent = "サーバエラーが発生しました。";
			  }
		  } else { //通信未完
			  result.textContent = "通信中...";
		  }
	  };
	  
	  xhr.open("GET", "/myServlets/Calculator", true);
	  xhr.send(null);

	  xhr2.onreadystatechange = function() {
		  if (xhr2.readyState === 4) { //通信完了
			  if (xhr2.status === 200) { //通信成功
				  result2.textContent = xhr2.responseText;
			  } else { //通信失敗
				  result2.textContent = "サーバエラーが発生しました。";
			  }
		  } else { //通信未完
			  result2.textContent = "通信中...";
		  }
	  };
	  
}, false);


</script>
</body>
</html>