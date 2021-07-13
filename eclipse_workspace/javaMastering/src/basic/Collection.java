package basic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * コレクション型や配列の使い方を確認するためのコード
 * 
 * 課題
 *  [1] 配列の宣言、領域の確保、初期化が行われるタイミングを確認せよ
 *  [2] 関数に配列を渡す時、関数が配列を返す時の配列サイズや配列がどこに確保されるかを確認せよ
 *  [3] 配列とリストの違いを理解して使いどころを考えよ 
 *        -> javaにおいては配列は使わない方がいい
 *           配列はサイズ固定、リストは可変。要素の挿入、削除のコストが低い
 *  [4] 配列をリストに変換する方法、リストを配列に変換する方法を調べよ
 *  [5] リスト内の指定した値が入っている場所のインデックスを全て返す関数を書け
 *  [6] リストに要素を追加、削除せよ
 *  [7] 二つのリストそれぞれの一部をdeepcopyして結合し、作ったリストに対する操作が与える影響をチェックせよ
 *  [8] リストの要素が基本型、参照型の時それぞれについてリストの同値性を適切に判断せよ(特にリストが入れ子構造になっている場合も)
 *  [9] イテレータを使ってリストを初期化せよ -> =による初期化は無理
 *  [10] 拡張for文でリストを書き換える方法はあるか？ -> 基本型は無理。参照型はループ変数への再代入ではなくメソッドを使うことで変更は可能
 *  [11] genericsを使って任意のデータ型リストを標準出力せよ
 *  [12] lambda式を理解せよ
 *
 * @author Yuki
 *
 */

public class Collection {

  /**
   * @param args
   */
  // 任意の初期値で初期化された配列を返す関数
  private static double[] retArray(int length, double init) {
    double[] array = new double[length];
    for (int i = 0; i < array.length; i++) {
      array[i] = init;
    }
    return array;
  }
  
  private static double[] retAscendingArray(int length, double start, double step) {
    double[] array = new double[length];
    for (int i = 0; i < length; i++) {
      array[i] = start + step * (double)i;
             
    }
    return array;
  }
  
  
  private static void rewriteArray(double[] array, double new_value) {
    for (int i = 0; i < array.length; i++) {
      array[i] = new_value;
    }
    return;
  }
  
  private static void sysoutArray(double[] array) {
    for (double d : array) {
      System.out.println(d);
    }
  }
  
  private static ArrayList<Double> convertToArrayList(double[] array) {
    ArrayList<Double> list = new ArrayList<>();
    for (int i = 0; i < array.length; i++) {
      list.add(Double.valueOf(array[i]));
    }
    return list;
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO 自動生成されたメソッド・スタブ
    // [1],[2]
    //宣言のみ
    double[] array1;
    //サイズを指定して定義1(デフォルト値で初期化)
    double[] array2 = new double[10];
    //サイズを指定して定義2(デフォルト値で初期化)
    int size = 20;
    double[] array3 = new double[size];
    //宣言+{}による初期化
    double[] array4 = {1, 2, 3, 4};
    //生成されてない配列を使おうとするとコンパイルエラー
    //System.out.println("array1.length: " + array1.length);
    array1 = retArray(17, Math.PI);
//    List list1 = Arrays.asList(array1);
    System.out.println("array1 is as follows");
    sysoutArray(array1);
    rewriteArray(array1, Math.E);
    System.out.println("array1 is as follows");
    sysoutArray(array1);
    
    //[4]
    // double配列からリストへの変換
    ArrayList<Double> list1 = new ArrayList<>();
    for (double d : array1) {
      list1.add(Double.valueOf(d));
    }
    // リストからdouble配列への変換
    double[] array5 = new double[list1.size()];
    Iterator<Double> itr = list1.iterator();
    int i = 0;
    while(itr.hasNext()) {
      array5[i++] = itr.next().doubleValue();
    }
    System.out.println("array5 is as follows");
    sysoutArray(array5);
    
    // 二つのリストの一部を結合して新しいリストをつくる
    double[] array6 = retAscendingArray(10, 0.0, 2.0);
    double[] array7 = retAscendingArray(10, 100.0, 3.0);
    ArrayList<Double> list6 = convertToArrayList(array6);
    ArrayList<Double> list7 = convertToArrayList(array7);
//    ArrayList<Double> list8 = Stream.concat(list6.steam()., null)
  }

}
