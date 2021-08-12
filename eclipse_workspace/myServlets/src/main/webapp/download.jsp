<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ボタンクリックでファイルチューザーを出すサンプルコード</title>
</head>
<body>
<a><button id="fileChooser">file chooser</button></a>
<a ><button id="saveBtn">save</button></a>
<button id="button">save at local</button>
<script>

document.addEventListener("DOMContentLoaded", function() {
  const button = document.getElementById("button");
  button.addEventListener("click", async function(e) {
    const opts = {
      suggestedName: 'output.csv',
      types: [{
        description: 'csv file',
        accept: {'text/csv': ['.csv']},
      }],
    };
    let fileHandle = await showSaveFilePicker(opts);
    let xhr = new XMLHttpRequest();
    xhr.open("get", "/myServlets/Book1.csv", true);
    xhr.responseType = "blob";
    xhr.onload = async function() {
      let blob = xhr.response;
      const writer = await fileHandle.createWritable();
      await writer.truncate(0);
      await writer.write(blob);
      await writer.close();
    };
    xhr.send();
  }, false);
});

</script>
</body>
</html>