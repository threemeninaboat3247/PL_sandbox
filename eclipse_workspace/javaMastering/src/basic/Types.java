package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import basic2.MyString;

/**
 * 課題:
 *  ・[1] Javaの全てのプリミティブ型を宣言、初期化してコンソール出力せよ
 *  ・[2] String型について理解せよ
 *  ・[3] System.out.printlnのフォーマット出力を確認せよ(桁数指定、パディング、指数表記)
 *  ・[4] int-double, double-文字列間の変換を行いデータの丸めについて確認せよ
 *  ・[5] 関数に変数を渡して関数内で変数を変更した時の動作について理解せよ
 *  ・[6] コレクション型(配列、辞書型、リスト、集合)を宣言して要素を追加せよ
 *  ・[7] 別パッケージにある自作クラスをこのファイル内でしようせよ
 * 
 * @author Yuki
 *
 */


public class Types {
  
  public static void tryToChangeStr(String str) {
    str = "値は書き換えさせてもらった";
    return;
  }
  
  public static void tryToChangeStr(MyString str) {
    str.changeStr("値は書き換えさせてもらった");
    return;
  }
//  public static void tryToChangeMutableObj()
  
  public static void main(String[] args) {
    // [1][3]
    // 整数
    byte b_hoge = 127;
    short s_hoge = 32767;
    int i_hoge = Integer.MAX_VALUE;
    long l_hoge = Long.MIN_VALUE;
    
    // 実数
    float f_hoge = 123.45678F; // 小数リテラルをfloat型にいれるには末尾にFが必要(扱える範囲であっても)
    double d_hoge = Double.MAX_VALUE;
    
    // 文字
    char c_hoge = 'b';
    
    // 真偽値
    boolean bool_hoge = true;
    
    // いろいろ書式を付けて出力
    System.out.println("b_hoge: " + b_hoge);
    System.out.printf("s_hoge: %06d, i_hoge: %020d, l_hoge: %d\n", 
                      s_hoge, i_hoge, l_hoge);
    System.out.printf("f_hoge: %.7e, d_hoge: %e\n", f_hoge, d_hoge);
    System.out.println(String.format("c_hoge: %5c", c_hoge));
    System.out.println(String.format("bool_hoge: %-10b", bool_hoge));
    
    // [2][5]
    // String型は参照型。(大文字始まりは全て参照型。)
    // 内部変数に文字配列を持つがそれがprivate finalなのでimmutable
    // 等価かどうかの判定にはequalsメソッドかjava.util.Objects.equalsを使う
    String mystring = "誰にも私は変えられない";
    System.out.println("tryToChangeStr 呼び出し前のmystring: " + mystring);
    tryToChangeStr(mystring);
    System.out.println("tryToChangeStr 呼び出し後のmystring: " + mystring);
    mystring = "私はかつての自分ではない";
    System.out.println("再代入後のmystring: " + mystring);
    
    // String型がmutableだとどうなるかというお話
    MyString mystring2 = new MyString("誰にも私は変えられない");
    System.out.println("tryToChangeStr 呼び出し前のmystring2: " + mystring2.getString());
    tryToChangeStr(mystring2);
    System.out.println("tryToChangeStr 呼び出し後のmystring2: " + mystring2.getString());
    
    // String型の比較(String Constant Poolがあるので""で初期化した場合に限っては==で比較ができてしまう
    // Stringがnew String("hoge")で初期化されることもあるのでequalsを使うのが正しい
    String dquo1 = "Pool内の文字列";
    String dquo2 = "Pool内の文字列";
    String newString = new String("Pool内の文字列");
    System.out.println("dquo1==dquo2:" + String.valueOf(dquo1 == dquo2));    
    System.out.println("dquo1==newString:" + String.valueOf(dquo1 == newString));    
    System.out.println("dquo1.equals(newString):" + dquo1.equals(newString));    
    
    // switch文のラベルとの比較はequalsを使って行われるので同値比較になるので安心
    String input = new String("値の比較");
    switch (input) {
    case "値の比較":
      System.out.println("文字列でswitchしても値の比較が行われる");
      break;
    default:
      System.out.println("default句");
      break;
    }
    
    //[4]
    // 整数がた同士
    long l_i_max_plus_1 = Integer.MAX_VALUE + 1;
    int i_max_plus_1 = (int)l_i_max_plus_1; // 大を小に移すときは明示的なcastが必要
    l_i_max_plus_1 = i_max_plus_1; // 小を大に移す時には明示的なcastは必要ない
    
    // 整数->文字列
    System.out.printf("%-10s: %s\n", "mannual", String.valueOf(l_i_max_plus_1));
    System.out.printf("%-10s: %d\n", "auto", l_i_max_plus_1);
    
    // 文字列->整数
    i_max_plus_1 = Integer.parseInt("-2147483648"); // intに収まらない値だと例外発生
    
    // [6]
    // 配列
    int[] aint1; // 変数宣言
    aint1 = new int[5]; // 領域確保(この段階で規定値で初期化される)
    for(int i = 0; i < aint1.length; i++) {
      System.out.printf("index: %d, value: %d\n", i, aint1[i]);
    }
    
    int[] aint2 = {1,2,3}; // 宣言と初期化を同時に行う
    for(int i = 0; i < aint2.length; i++) {
      System.out.printf("index: %d, value: %d\n", i, aint2[i]);
    }
    
    // 配列の比較
    MyString[] mystrings = {new MyString("hoge"), new MyString("moge")};
    MyString[] mystrings2 = {new MyString("hoge"), new MyString("moge")};
    // 以下では配列の参照値だけが比較されてfalse
    System.out.println("==による比較: " + (mystrings == mystrings2));
    // Arrays.equalsを使うと配列の各要素同士が比較される
    // MyStringでequalsをオーバライドしないとObjectのequalsが使われる
    // それは==でアドレスの比較だけをしているのでfalse
    System.out.println("equlasで非同一オブジェクトの同値判定を行った結果: " + java.util.Objects.equals(new MyString("hoge"), new MyString("hoge")));
    System.out.println("Arrays.equalsによる比較(1と2): " + Arrays.equals(mystrings, mystrings2));     
    MyString[][] mystrings3 = {{new MyString("hoge")}, {new MyString("moge")}};
    MyString[][] mystrings4 = {{new MyString("hoge")}, {new MyString("moge")}};
    // 多次元配列の場合はdeepEqualsを使わないと配列の参照値比較が行われる
    System.out.println("Arrays.equalsによる比較(3と4): " + Arrays.equals(mystrings3, mystrings4));     
    System.out.println("Arrays.deepEqualsによる比較(3と4): " + Arrays.deepEquals(mystrings3, mystrings4));     

    // リスト Listインタフェースを実装しているのがArrayList, LinkedList
    // ArrayList Listインタフェースを実装している。
    ArrayList<MyString> arrayList = new ArrayList<>();
    MyString hoge = new MyString("hoge");
    arrayList.add(hoge);
    arrayList.add(new MyString("moge"));
    arrayList.add(0, new MyString("buzz"));
    hoge.changeStr("fuga");

    for(int i = 0; i < arrayList.size(); i++) {
      System.out.printf("index: %d, value: %s\n", i, arrayList.get(i).getString());
    }
    
    // 集合 Setインタフェースを実装しているのがHashSet, TreeSet
    // 自作クラスでは同値なオブジェクトのhashCodeの返り値が同じであるように実装しておく必要がある
    // javaはhashの比較->equalsの比較を行って同値性の判定を行う
    HashSet<MyString> hashSet = new HashSet<MyString>();
    hashSet.add(new MyString("hoge"));
    hashSet.add(new MyString("hoge"));
    hashSet.add(new MyString("moge"));
    System.out.printf("hashSetの要素数: %d\n", hashSet.size());
    
    // 辞書型 Mapインタフェースを実装しているのがHashMap, TreeMap。ハッシュ表のサイズが分かっている場合は指定した方が良い
    HashMap<Integer, String> hashMap = new HashMap<>(20);
    hashMap.put(0, "hoge");
    hashMap.put(1, "moge");
    hashMap.put(2, "fuga");
    // key, valueをイテレーション
    for (Integer i : hashMap.keySet()) {
      System.out.println("key: " + i);
      System.out.println("value: " + hashMap.get(i));
    }
    // value全てをイテレーション 
    for (String str : hashMap.values()) {
      System.out.println("value: " + str);
    }
    

  }
  
  
}