# jQueryに関するメモ書き

## 参考文献

- JavaScript本格入門　山田祥寛

## 用語整理

| term(日本語)         | 説明 | cf.  |
| -------------------- | ---- | ---- |
| property(プロパティ) |      |      |
| attribute(属性値)    |      |      |
|                      |      |      |
|                      |      |      |
|                      |      |      |
|                      |      |      |



## セレクタ関連
### 記号'$'の意味 [cf.](https://qiita.com/yassh/items/056162f3a2ffb32eca59)

JavaScriptにおいては'$'は'a'といった記号と変わらない。普通の文字として扱われる。
jQueryをライブラリとして読み込んだ場合にはjQueryの機能にアクセスするために使用される。

```javascript
/* jQueryオブジェクトを生成 */
$(".class");				/* セレクタ */
$("<html></html>");			/* HTML文字列 */
$(this);					/* javascriptオブジェクト */
/* DOM構築後に関数を実行 即時関数による変数名の競合を避ける目的もある？*/
$(function(){});
```

### $(".hoge")とした時にhogeクラスが複数あると何が起こる？ [cf.](https://jquery-master.net/basic/jquery_object.php)

hogeクラスである全ての要素が取得され、操作を実行すると全ての要素に対して実行される。値の取得だけは例外的に最初の要素の値が取得される。

### \$(セレクタ文字列)と$(HTML文字列)の違いは?

\$(セレクタ文字列)はDOMから取得されたjQueryオブジェクト。$(HTML文字列)は新規に作られたjQueryオブジェクト。

### jQueryオブジェクトがネストしている時にセレクタで子要素にアクセスする方法

childrenメソッドを使う

## オブジェクトの拡張関連(非推奨)

### オブジェクトに動的にメソッドやプロパティを追加してからcloneしたら、追加分はどうなる？

cloneはDOM構造のコピーなので、オブジェクトに動的に追加したメソッドはコピーされない。

## イベント関連
### 自作イベントを作って情報を詰めて、イベントリスナーで情報を取り出す方法

```javascript
e = $.Event("event_name", {key_name: 1});					/* 作成 */
sender.trigger(e);											/* 発火 */
receiver.on("event_name", function(e) {... = e.key_name});	/* 情報へのアクセス */
```

### onとaddEventListerの違い

onはJQueryのイベントリスナ登録のためのメソッド。addEventListenerはJavaScriptの機能。

## メソッド関連
### $(".class").methodで処理を行う際に特定の要素だけ例外的な処理をしたい




