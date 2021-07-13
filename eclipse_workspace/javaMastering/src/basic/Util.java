package basic;

import java.util.Iterator;
import java.util.List;

import basic2.MyString;

public class Util {
  public static <t> void sysoList(String message, List<t> c) {
    int i = 0;
    System.out.println(message);
    for (Iterator iterator = c.iterator(); iterator.hasNext();) {
      t ele = (t) iterator.next();
      System.out.printf("index: %d, value: %s\n", i++, String.valueOf(ele));
    }
  }
  
  public static void sysoMyStringList(String message, List<MyString> c) {
    int i = 0;
    System.out.println(message);
    for (Iterator iterator = c.iterator(); iterator.hasNext();) {
      MyString ele = (MyString)iterator.next();
      System.out.printf("index: %d, value: %s\n", i++, ele.getString());
    }
  }
}
