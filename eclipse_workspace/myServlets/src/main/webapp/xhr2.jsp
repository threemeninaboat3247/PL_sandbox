<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- XMLHttpRequestを使ってformデータを送信するサンプルコードj -->
<html>
<head>
<meta charset="UTF-8">
<title>XML Http Request Demo</title>
</head>
<body>
<label>方法１ 自分でフォームデータ形式(URLEncodeされたキーと値のペアを&で連結したもの)を送る方法</label>
<br>
<button id="button1">Click Me!</button>
<br>
<br>
<label>方法2 自分でFormDataオブジェクトを生成して送る方法</label>
<br>
<button id="button2" type="button" onclick="sendData2({test:'ok'})">Click Me!</button>
<br>
<br>
<label>方法3 htmlのformタグを使って送る方法</label>
<br>
<form id="myForm" enctype="application/x-www-form-urlencoded">
  <label for="myName">Send me your name:</label>
  <input id="myName" name="name" value="John">
  <input name="hoge" value="moge">
  <input type="submit" value="Send Me!">
  <input type="file" name="file">
</form>

<script>
// 方法1
const btn = document.querySelector('button');

function sendData( data ) {
  console.log( 'Sending data' );

  const XHR = new XMLHttpRequest();

  let urlEncodedData = "",
      urlEncodedDataPairs = [],
      name;

  // data オブジェクトを、URL エンコードしたキーと値のペアの配列に変換します
  for( name in data ) {
    urlEncodedDataPairs.push( encodeURIComponent( name ) + '=' + encodeURIComponent( data[name] ) );
  }

 // キーと値のペアをひとつの文字列に連結して、Web ブラウザーのフォーム送信方式に
 // 合うよう、エンコードされた空白をプラス記号に置き換えます。
  urlEncodedData = urlEncodedDataPairs.join( '&' ).replace( /%20/g, '+' );

  // データが正常に送信された場合に行うことを定義します
  XHR.addEventListener( 'load', function(event) {
    alert( 'Yeah! Data sent and response loaded. response: ' + XHR.responseText );
  } );

  // エラーが発生した場合に行うことを定義します
  XHR.addEventListener( 'error', function(event) {
    alert( 'Oops! Something went wrong.' );
  } );

  // リクエストをセットアップします
  XHR.open( 'POST', '/myServlets/FormReceiver', true);

  // フォームデータの POST リクエストを扱うために必要な HTTP ヘッダを追加します
  XHR.setRequestHeader( 'Content-Type', 'application/x-www-form-urlencoded' );

  // 最後に、データを送信します
  XHR.send( urlEncodedData );
}

btn.addEventListener( 'click', function() {
  sendData( {test:'ok'} );
} )

// 方法2
const btn2 = document.getElementById('button2');

function sendData2(data) {
  const XHR = new XMLHttpRequest(),
        FD  = new FormData();

  // データを FormData オブジェクトに投入します
  for(name in data) {
    FD.append(name, data[name]);
  }

  // データが正常に送信された場合に行うことを定義します
  XHR.addEventListener('load', function(event) {
    alert('Yeah! Data sent and response loaded. response: ' + XHR.responseText);
  });

  // エラーが発生した場合に行うことを定義します
  XHR.addEventListener('error', function(event) {
    alert('Oups! Something goes wrong.');
  });

  // リクエストをセットアップします
  XHR.open('POST', '/myServlets/FormReceiver');

  // FormData オブジェクトを送信するだけです。HTTP ヘッダは自動的に設定されます
  XHR.send(FD);
}

// 方法3
window.addEventListener("load", function () {
  function sendData() {
    const XHR = new XMLHttpRequest();

    // FormData オブジェクトと form 要素を紐づけます
    const FD  = new FormData(form);

    // データが正常に送信された場合に行うことを定義します
    XHR.addEventListener("load", function(event) {
      alert(event.target.responseText);
    });

    // エラーが発生した場合に行うことを定義します
    XHR.addEventListener("error", function(event) {
      alert('Oups! Something goes wrong.');
    });

    // リクエストをセットアップします
    XHR.open("POST", "/myServlets/FormReceiver");

    // 送信したデータは、ユーザーがフォームで提供したものです
    XHR.send(FD);
  }

  // form 要素にアクセスしなければなりません
  const form = document.getElementById("myForm");

  // フォームの submit イベントを乗っ取ります
  form.addEventListener("submit", function (event) {
    event.preventDefault();

    sendData();
  });
});
</script>
</body>
</html>