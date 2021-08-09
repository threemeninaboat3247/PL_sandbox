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
<script>
var fileHandle;
document.addEventListener("DOMContentLoaded", function() {
	const fileChooser = document.getElementById("fileChooser");
	fileChooser.addEventListener("click", setNewFileHandle); // file chooserを開くのはユーザの操作を起点とする必要がある
	const saveBtn = document.getElementById("saveBtn");
  saveBtn.addEventListener('click', async () => {
      try {
          const value = "test text";
          const writer = await fileHandle.createWritable();

          await writer.truncate(0);
          await writer.write(value);  // writeできるのはBlob, USVString, BufferSourceのどれか
          await writer.close();
      } catch (err) {
          console.error(err.message);
      }
  });
	
});


async function setNewFileHandle() {
	  const opts = {
	    types: [{
	      description: 'Text file',
	      accept: {'text/plain': ['.txt']},
	    }],
	  };
	  fileHandle = await window.showSaveFilePicker(opts);
}
</script>
</body>
</html>