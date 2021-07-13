package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import basic2.MyString;
/**
 * ArrayListのコピー時の動作を確かめるためのコード
 * 
 * 課題
 * [1] Arrays.asListを使って生成したリストをArrayListのコンストラクタに渡してリストを生成し、元を変更した時に何がおこるか？
 * [2] ArrayListをcloneを作ってdeep copyすれば元を変更してもコピー先に影響が出ないことを確かめろ
 * @author Yuki
 *
 */
public class ArrayListTest {

  public static void main(String[] args) {
    // TODO 自動生成されたメソッド・スタブ
    int on = 3;
    switch (on) {
      case 0:
        // Stringはimmutableなのでsetでしか変えられないが
        List<String> original = Arrays.asList("hoge", "fuga");
        List<String> coppied = new ArrayList<>(original);
        Util.sysoList("original before", original);
        Util.sysoList("coppied before", coppied);
        original.set(0, "fuga");
        Util.sysoList("original after", original);
        Util.sysoList("coppied after", coppied);
        break;
    
      case 1:
        // mutableなMyStringだと
        List<MyString> original2 = Arrays.asList(new MyString("hoge"), new MyString("fuga"));
        List<MyString> coppied2 = new ArrayList<>(original2);
        Util.sysoMyStringList("original2 before", original2);
        Util.sysoMyStringList("coppied2 before", coppied2);
    //    original2.set(0, new MyString("fuga")); // setだと参照するオブジェクトの付け替えになるのでcoppiedに影響なし
        original2.get(0).changeStr("moge"); // getしたオブジェクトの改変だとcoppiedに影響あり
        Util.sysoMyStringList("original2 after", original2);
        Util.sysoMyStringList("coppied2 after", coppied2);
        break;
    
      case 2:
        // cloneもshallow copyなので上と同様な動作
        ArrayList<MyString> original3 = new ArrayList(Arrays.asList(new MyString("hoge"), new MyString("fuga")));
        ArrayList<MyString> coppied3 = (ArrayList<MyString>)original3.clone();
        Util.sysoMyStringList("original3 before", original3);
        Util.sysoMyStringList("coppied3 before", coppied3);
    //    original3.set(0, new MyString("fuga")); // setだと参照するオブジェクトの付け替えになるのでcoppiedに影響なし
        original3.get(0).changeStr("moge"); // getしたオブジェクトの改変だとcoppiedに影響あり
        Util.sysoMyStringList("original3 after", original3);
        Util.sysoMyStringList("coppied3 after", coppied3);
        break;
      
      case 3:
        // listのdeep copyを行う
        ArrayList<MyString> original4 = new ArrayList(Arrays.asList(new MyString("hoge"), new MyString("fuga")));
        // 下はむずいので後回し
//        ArrayList<MyString> coppied4 = new ArrayList<>(Arrays.asList(original4.stream()
//                                          .map(elm -> {return (MyString)elm.clone();}).toArray(MyString::new)));
        ArrayList<MyString> coppied4 = new ArrayList<>();
        for (MyString myString : original4) {
          coppied4.add(myString.clone());
        }
        Util.sysoMyStringList("original4 before", original4);
        Util.sysoMyStringList("coppied4 before", coppied4);
    //    original4.set(0, new MyString("fuga")); // setだと参照するオブジェクトの付け替えになるのでcoppiedに影響なし
        original4.get(0).changeStr("moge"); // getしたオブジェクトの改変だとcoppiedに影響あり
        Util.sysoMyStringList("original4 after", original4);
        Util.sysoMyStringList("coppied4 after", coppied4);
        break;
    }
  }

}
