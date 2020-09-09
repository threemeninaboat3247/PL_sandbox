var p = new RegExp("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", "gi");

var p2 = new RegExp("\(", "gi");

var str = "サポートサイトはhttp://www.wings.msn.to/です。";

var str2 = "()";

var result = str2.match(p2);

for (var i = 0, len = result.length; i < len; i++) {
  console.log(result[i]);
}