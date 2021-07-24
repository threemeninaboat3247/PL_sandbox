package basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

interface AsyncCallback {
  void notify(String message);
}

class AsyncProcess implements Runnable {
  private AsyncCallback callback;
  
  public AsyncProcess(AsyncCallback asyncCallback) {
    this.callback = asyncCallback;
  }
  
  public void run() {
    try {
      Thread.sleep(1000L);
      this.callback.notify("Finished.");
    } catch (InterruptedException ex){
      ex.printStackTrace();
    }
    System.out.println("AsyncProcess is finished.");
  }
}


public class ThreadTest {

  public static void main(String[] args) {
    // TODO 自動生成されたメソッド・スタブ
    ExecutorService executor = Executors.newSingleThreadExecutor();
    AsyncProcess proc = new AsyncProcess(new AsyncCallback() {
      public void notify(String message) {
        System.out.println("callback message:" + message);
        executor.shutdown();
      }
    });
    executor.execute(proc);
    System.out.println("AsyncProcess is started");
  }

}
