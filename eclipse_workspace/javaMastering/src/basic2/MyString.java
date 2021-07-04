package basic2;

/** 
 * mutableなString型
 * @author Yuki
 */
public class MyString {
  private String _mystring;
  public MyString() {
    _mystring = "デフォルト文字列";
  }
  
  public int hashCode() {
    return _mystring.hashCode();
  }
  
  public boolean equals(Object obj1) {
    if (obj1 == this) return true;
    if (obj1 instanceof MyString) {
      MyString b = (MyString)obj1;
      if(this._mystring.equals(b.getString())) {
        return true;
      }
      return false;
      
    }
    return false;
  }
  
  public MyString(String str) {
    // 一見コピーしないとまずそうだが、Stringはimmutableなので_mystringの参照先が書き換えられる心配はない
    _mystring = str;
  }
  
  public void changeStr(String str) {
    _mystring = str;
    return;
  }
  
  public String getString() {
    return _mystring;
  }
}