package basic;
import basic2.HttpStatusImplicit;
import basic2.HttpStatusInt;

/**
 * 列挙型の使い方を確かめるためのコード
 * @author Yuki
 * 課題
 * [1] パッケージ直下にenum型を配置せよ
 * [2] enum型の取り得る値を整数, 文字列それぞれに明示せよ
 * [3] enum型の変数と有効値の正しい比較方法を調べよ
 * [4] enum型の取り得る値を整数にした時、整数リテラルやint型の変数と比較した結果を確認せよ
 * [5] 上と同じことを文字列について行え
 * [6] enum型の値をswitchのラベルに使え
 * [7] enum型と組み込み型の違い、enum型とクラスの違いを説明せよ -> enum型は特殊なクラス。自クラスのインスタンスがクラス変数として保持されている。
 *                                                                 それぞれのインスタンスが一つであることが保証されているので==で比較可能
 *
 */

public class EnumTest {

  public static void main(String[] args) {
    // TODO 自動生成されたメソッド・スタブ
    HttpStatusImplicit status = HttpStatusImplicit.NOT_FOUND;
    HttpStatusImplicit status2 = HttpStatusImplicit.NOT_FOUND;
    System.out.println("comparison by equals: " + (status.equals(status2)));
    System.out.println("comparison by ==: " + (status == status2));
    
    // [4][5]
    HttpStatusInt status3 = HttpStatusInt.OK;
    int hoge = 200;
    // 以下はコンパイルエラーにはならない
    System.out.println("status3.equals(200): " + status3.equals(hoge));
    
    //[6]
    switch (status3) {
    case OK:
      System.out.println("switch case OK");
      break;
    case INTERNAL_SERVER_ERROR:
      System.out.println("switch case INTERNAL_SERVER_ERROR");
      break;
    case NOT_FOUND:
      System.out.println("switch case NOT_FOUND");
      break;
    }
  }

}
