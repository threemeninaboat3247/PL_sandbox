package basic;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 乱数の扱いをチェックするためのサンプルコード
 * @author Yuki
 * 課題
 * 1. 0-99の整数の一様分布乱数を生成せよ
 * 2. 0-piのdouble値の一様分布乱数を生成せよ
 * 3. seedを指定すると同じ結果が得られる事、seedを指定しないと別の結果が得られる事を確かめよ
 */
public class MathTest {

  public static void main(String[] args) {
    // TODO 自動生成されたメソッド・スタブ
    // 1.
    Random rand = new Random();
    System.out.println("uniform distribution from 0 to 99");
    final int TRYNUM = 10000;
    ArrayList<Integer> nums = new ArrayList<>(TRYNUM);
    for(int i = 0; i < TRYNUM; i++) {
      int num = rand.nextInt(100);
      nums.add(Integer.valueOf(num));
    }
    Map<Integer, Integer> map = nums.stream().collect(
      Collectors.groupingBy(
        Function.identity(),
        Collectors.summingInt( (s) -> 1)
      )
    );
    System.out.println(map);
    
    // 2.
    double d_random = Math.PI * rand.nextDouble();
    System.out.println(d_random);
    
    // 3.
    Random rand1 = new Random();
    Random rand2 = new Random();
    Random rand3 = new Random(1);
    Random rand4 = new Random(1);
    System.out.printf("rand1: %f, rand2: %f, rand3: %f, rand4: %f\n", rand1.nextDouble(), rand2.nextDouble(), rand3.nextDouble(), rand4.nextDouble());
  }

}
