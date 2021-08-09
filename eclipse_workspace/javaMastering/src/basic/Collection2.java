package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import basic2.MyString;

/**
 * ArrayListの実用的な使い方を練習するためのテストコード
 * 
 * 課題
 * [1] サイズ10のArrayListのindex: 3 - 5のビューを取得せよ
 * [2] [1]のArrayListの先頭やindex:3の要素の後に新規の要素を追加した時に何が起こるか確認せよ
 *     --> originalを編集した後にviewを操作しようとするとConcurrentModificationExceptionが発生する。originalをいじる時はviewは使わない
 * [3] ビュー経由でオリジナルを編集せよ
 * [4] ビュー経由でオリジナルを編集できなくする方法があるか確認せよ -> なさげ。JavaにはJavaScriptのfreezeみたいなものはない
 * [5] ArrayListを途中から凍結してreadonlyにする方法があるか確認せよ -> なさげ
 * 
 * @author Yuki
 *
 */

public class Collection2 {

  public static void main(String[] args) {
    // TODO 自動生成されたメソッド・スタブ
    // [1]
    ArrayList<MyString> original = new ArrayList<>(Arrays.asList(new MyString("1"),
                                                               new MyString("2"),
                                                               new MyString("3"),
                                                               new MyString("4"),
                                                               new MyString("5"),
                                                               new MyString("6"),
                                                               new MyString("7"),
                                                               new MyString("8"),
                                                               new MyString("9"),
                                                               new MyString("10")));
    Util.sysoMyStringList("original:", original);
    List<MyString> view = original.subList(3, 6);
    Util.sysoMyStringList("view:", view);
    // [2] 
    System.out.println("after removing");
//     original.remove(0);
    view.remove(0);
    Util.sysoMyStringList("original:", original);
    Util.sysoMyStringList("view:", view);
    
    // [3]
    view.get(1).changeStr("hoge");
    Util.sysoMyStringList("original:", original);
    Util.sysoMyStringList("view:", view);
    view.add(new MyString("fuga"));
    Util.sysoMyStringList("original:", original);
    Util.sysoMyStringList("view:", view);

  }

}
