package basic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * synchronizedの動作を確認するためのテストコード
 * 
 * 目的
 * スレッドセーフに作られていないクラスのインスタンスをスレッドセーフに扱うためにsynchronizedを使う方法を理解する
 * 
 * 課題
 * [0] スレッドを立てて処理を行う方法を確認せよ(Threadの継承、Runnableのimplement、それぞれの特徴)
 * [1] 複数のスレッドで同一オブジェクトを共用する方法を調べよ
 * [2] スレッドセーフでない操作を複数のスレッドで実行して問題が起こることを確認せよ
 * [3] 複数のsynchronizedメソッドを定義して一つがロックされている時に残りもロックされていることを確認せよ
 * [4] [1]の状況で一方のスレッドで該当オブジェクトのロックを取得した時にもう一方のスレッドで該当オブジェクトの
 *      メソッド呼び出し等はできるか確認せよ
 * [5] Concurrent Utilitiesを使った現代的なマルチスレッドプログラムを書いて何が改善されているかを理解せよ
 * @author Yuki
 *
 */

class MyThread extends Thread {
  private static final Logger logger = LoggerFactory.getLogger(MyThread.class);
  @Override
  public void run() {
    logger.info("run start");
    try {
      Thread.sleep(10000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    logger.info("run end");
  }
}

class MyRunnable implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(MyRunnable.class);
  public void run() {
    Thread t = Thread.currentThread();
    logger.info(String.format("run start in Thread (%s)", t.getName()));
    try {
      Thread.sleep(10000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    logger.info(String.format("run end in Thread (%s)", t.getName()));
  }
}

class IncrementSample implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(MyRunnable.class);
  private int count = 0;
  
  public void run() {
    int remaining;
    remaining = count % 3;
    count++;
    switch (remaining) {
    case 0:
      methodA();
      break;
    case 1:
      methodB();
      break;
    case 2:
      methodC();
    }
  }
  
  public synchronized void methodA() {
    logger.info("methodA was called. sleep 20 sec.");
    try {
      Thread.sleep(20000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    logger.info("methodA was finished");
  }
  public synchronized void methodB() {
    logger.info("synchronized methodB was called and finished");
  }
  public void methodC() {
    logger.info("asynchronized methodC was called and finished");
  }
  
  public int getCount() {
    return this.count;
  }
}

public class ThreadTest2 {
  private static final Logger logger = LoggerFactory.getLogger(ThreadTest2.class);

  public static void main(String[] args) {
    // TODO 自動生成されたメソッド・スタブ
    int on = 3;
    switch (on) {
    // [1] Threadを継承する方法
    case 0:
      MyThread myThread = new MyThread();
      myThread.start();
      logger.info("started MyThread");
      try {
        myThread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      logger.info("main end");
      break;
      
    // [2] Runnableをimplementsする方法 Runnable実装クラスの同一インスタンスを複数のThreadのコンストラクタに渡せる
    // Runnableインスタンスを介して並列処理を記述しやすい反面、同期非同期に注意する必要がある
    case 1:
      MyRunnable r = new MyRunnable();
      Thread t = new Thread(r);
      Thread t2 = new Thread(r);
      t.start();
      logger.info(String.format("started Thread (id: %d, name: %s).", t.getId(), t.getName()));
      t2.start();
      logger.info(String.format("started Thread (id: %d, name: %s).", t2.getId(), t2.getName()));
      try{
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      t.interrupt(); // interruptで割り込めるのは割り込み先がsleep, join, waitで待ちの状態にある時だけ
      try {
        t2.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      logger.info("main end");
      break;
    // [2] 
    case 2:
      for(int i = 0; i < 10; i++) {
        IncrementSample is = new IncrementSample();
        Thread[] threads = new Thread[10];
        
        for(int j = 0; j < threads.length; j++) {
          threads[j] = new Thread(is);
        }
        
        for(int j = 0; j < threads.length; j++) {
          threads[j].start();
        }
        
        for(int j = 0; j < threads.length; j++) {
          try{
            threads[j].join();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        logger.info(String.format("%d time result: %d", i, is.getCount()));
      }
      // [3]{4]
    case 3:
      IncrementSample is = new IncrementSample();
      Thread threadA = new Thread(is);
      Thread threadB = new Thread(is);
      Thread threadC = new Thread(is);
      threadA.start();
      try{
        Thread.sleep(5000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      threadB.start();
      threadC.start();
      try {
        threadA.join();
        threadB.join();
        threadC.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      break;
    }
  }

}
