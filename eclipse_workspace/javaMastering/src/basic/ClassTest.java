package basic;

/**
 * クラスのメンバ変数に参照型変数があるときのsetter, getterの書き方をチェックするコード
 * 
 * 課題
 * [1] 配列メンバをsetする前にgetするとどうなるかをチェックせよ -> NullPointerException発生
 * [2] メンバ変数を宣言時に初期化する方法とコンストラクタで初期化する方法を比較せよ
 *     -> 変わらない。インスタンス生成時に値が決まって値が変わらないものはコンストラクタ、値が変わるものはメンバ変数の宣言時という使い分けをしたい
 * [3] privateなメンバ変数をgetter経由で取得できる設計の是非を検討せよ
 * [4] getする側ではgetした後にオブジェクトをいじらない事を明示する変数宣言の仕方、あるいはメンバ変数への修飾子を確認せよ -> 不可能
 * @author Yuki
 *
 */

class MyClass {
  private double[] dArray;
  
  public double[] getDArray() {
    return this.dArray;
  }
  
  public void setDArray(double[] dArray) {
    this.dArray = dArray;
  }
}

public class ClassTest {

  public static void main(String[] args) {
    // TODO 自動生成されたメソッド・スタブ
    int on = 2;
    MyClass myClass = new MyClass();
    switch (on) {
    // [1]
    case 0:
      {
        myClass.setDArray(new double[5]);
        double[] dArray = myClass.getDArray();
        System.out.printf("dArray.length = %d\n", dArray.length);
        myClass.setDArray(new double[10]);
        dArray = myClass.getDArray();
        System.out.printf("dArray.length = %d\n", dArray.length);
        break;
      }
    // [3]
    case 1:
      {
        myClass.setDArray(new double[] {0,0,0});
        double[] dArray = myClass.getDArray();
        System.out.printf("myClass.getDArray()[0] = %e\n", myClass.getDArray()[0]);
        dArray[0] = 3.14;
        System.out.printf("myClass.getDArray()[0] = %e\n", myClass.getDArray()[0]); // setterで変えた訳でもないのにgetterの結果が変わる
        // plan 1: setする時にコピーしてセットし、getする時もコピーを返す -> 処理は重い
        break;
      }
    case 2:
      {
        final int[] iHoge = {1,2,3};
        iHoge[0] = 100; // finalは再代入が禁止されるだけ
//        iHoge = new int[] {3,2,1};
      }
    }

  }

}
