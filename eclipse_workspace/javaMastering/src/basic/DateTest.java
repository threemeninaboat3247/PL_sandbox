package basic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * 日時、時間、タイマーの使い方を確かめるためのテストコード
 * @author Yuki
 * 
 *
 */

public class DateTest {

  public static void main(String[] args) {
    // TODO 自動生成されたメソッド・スタブ
    int on = 1;
    switch (on) {
    // 古い方法 DateとCalendarを使う
    case 0:
      {
        // Dateは主に時間の計測に用いる 日時単位の計算はできないが、文字列との相互変換はできる
        Date date = new Date();
        System.out.println(date);
        Date date_specified = new Date(5000L);
        System.out.println(date_specified);
        
        // 日時はCalenderで扱う 日時単位の計算はできるが、文字列との相互変換ができない
        Calendar cal = Calendar.getInstance();
        System.out.println("Calenderの値");
        System.out.println(cal);
        System.out.println("getTimeの値");
        System.out.println(cal.getTime());
        cal.set(Calendar.MINUTE, 44);
        cal.add(Calendar.MONTH, 4);
        System.out.println(cal.getTime());
        cal.set(2022, 0, 3, 7, 7, 7);
        
        // format
        DateFormat format = new SimpleDateFormat("yy 年 MM 月 dd 日 HH 時 mm 分 ss 秒");
        System.out.println(format.format(cal.getTime()));

        // 時間の計測
        Date start = new Date();
        try {
          Thread.sleep(3456L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        Date end = new Date();
        System.out.printf("経過時間: %d millis sec", end.getTime() - start.getTime());
        break;
      }
      
    // 新しい方法 
    case 1: 
      {
        // 日時の取得
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
        // 日時の可算
        System.out.println(dateTime.plusDays(100));
        // フォーマット
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy/MM/dd   HH:mm:ss.SSS");
        System.out.println(dtf.format(dateTime));
        
        // 時間の計測
        LocalTime start = LocalTime.now();
        try {
          Thread.sleep(3456L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        LocalTime end = LocalTime.now();
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("ss.SSS 秒");
        System.out.println("経過時間: " + ChronoUnit.SECONDS.between(end, start));
        break;
      }
    }
  }

}
