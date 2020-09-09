# JavaScriptに関するメモ書き

## 参考文献

- JavaScript本格入門　山田祥寛

## 用語整理

### 言語

| term(日本語)                               | 説明                                                         | cf.       |
| ------------------------------------------ | ------------------------------------------------------------ | --------- |
| object(オブジェクト)                       | 連想配列の事。                                               | 山田2.3.2 |
| property(プロパティ)                       | オブジェクトからキーでアクセスできる値のこと                 |           |
| method(メソッド)                           | プロパティの中で関数であるもの                               |           |
| prototype property(プロトタイププロパティ) | オブジェクトの持つ特別なプロパティ。複製されたオブジェクトのプロトタイププロパティは全て同一の実体を参照している。 | 山田5.2.1 |
|                                            |                                                              |           |
|                                            |                                                              |           |

### ツール・ライブラリ

| term(日本語) | 説明                          | cf.  |
| ------------ | ----------------------------- | ---- |
| NPM          | Node.jsのパッケージ管理ツール |      |
| Node.js      | JavaScriptのプラットフォーム  |      |
|              |                               |      |



## 開発環境

エディタはvscode、動確・デバッグはchromeの開発者ツールを使って行う。

### NPMパッケージ

| package name     | version | explanation                                                  |
| ---------------- | ------- | ------------------------------------------------------------ |
| babel-cli        | 6.26.0  | ES6のJavaScriptをNode.jsのJavaScriptに変換する。             |
| browserify       | 16.5.0  | Node.jsのJavaScriptをブラウザ環境のJavaScriptに変換する。    |
| babelify         | 8.0.0   | babelをbrowserifyで使うためのラッパー。                      |
| babel-preset-env | 1.7.0   | JavaScriptの変換方法を指定するためのパッケージ。babelifyの-tオプションに指定する。 |
| exorcist         | 1.0.1   | JavaScriptを変換した際の元ソースと変換結果の対応関係(source map)を出力する。 |
| watchify         | 3.11.1  | browserifyを変更監視モードで実行するためのツール             |
|                  |         |                                                              |
|                  |         |                                                              |
|                  |         |                                                              |



## コーディング規約

### 形式的

| 種類                          | 規則                                     | 例                       |
| ----------------------------- | ---------------------------------------- | ------------------------ |
| 関数                          | lowerCamelCase                           | changeState, initDom     |
| クラス、コンストラクタ        | UpperCamelCase                           | StateEdit, StatePlay     |
| 内部変数                      | lowerCamel Case                          | balanceTotal, arrayIndex |
| publicなメソッド、プロパティ  | lowerCamelCase                           | -                        |
| privateなメソッド、プロパティ | lowerCamelCase + _(アンダースコアー)     | changeState_, data\_     |
| 定数                          | 全部大文字、_区切り                      | CONST_VALUE              |
| ファイル名                    | 全部小文字、-(ハイフン)区切り、.js拡張子 | math-util.js             |
|                               |                                          |                          |
|                               |                                          |                          |



## イベント関連

### 自作イベントを作って情報を詰めて、イベントリスナーで情報を取り出す方法

```javascript
e = new CustomEvent("event_name", {key: 1});	/* 作成 */
sender.dispatchEvent(e);						/* 発火 */
receiver.addEventListener("event_name", function(e) {... = e.key});	/* 情報へのアクセス */
```

### onとaddEventListenerの違い
onはjQueryのイベントリスナ登録のためのメソッド。addEventListenerはJavaScriptの機能。

## クラス関連
### クラスの拡張
JavaScriptにおいては「クラス = コンストラクタ」なので、Parentクラスを継承したChildクラスを定義するには以下のようにする。
- Childの定義の中でParent(this)のようにコールする
- ChildのprototypeにParentのインスタンスをセットする
- ChildのprototypeにChild独自のプロパティを定義する

```java
/* 親クラス */
var Parent = function(name) {
    this.name = name;
};
Parent.prototype.getName = function() {
    return this.name;
} 

/* 子クラス */
var Child = function(name) {
    Parent(this);
}
Child.prototype = new Parent("hoge");
Child.prototype.only_child = "fuga";
```

