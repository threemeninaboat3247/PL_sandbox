package basic;

import java.util.Arrays;
import java.util.List;

class MyState {
  private static final List<String> states = Arrays.asList("init", "runnning", "loaded", "stopped");
  private String state;
  
  public MyState(String state) {
    if (states.contains(state)) {
      this.state = state;
    } else {
      throw new IllegalArgumentException("未定義の状態をセットしようとした。セットしようとした状態: " + state);
    }
    return;
  }
  
  public void setState(String state) {
    if (states.contains(state)) {
      this.state = state;
    } else {
      throw new IllegalArgumentException("未定義の状態をセットしようとした。セットしようとした状態: " + state);
    }
    return;
  }
  
  public String getState() {
    return this.state;
  }
  
}

public class DesignPattern {

  public static void main(String[] args) {
    // TODO 自動生成されたメソッド・スタブ
//    MyState myState = new MyState("hoge");
    MyState myState = new MyState("Init");
    myState.setState("loaded");
    System.out.println(myState.getState());
    myState.setState("hoge");
    System.out.println(myState.getState());
  }

}
