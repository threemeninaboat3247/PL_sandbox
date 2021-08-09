package basic;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 一方のスレッドでデータを生成し、他方のスレッドで生成したデータをグラフにプロットするサンプルコード
 * @author Yuki
 *
 */
class Data {
  double x;
  double y;
  
  public Data(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public double getX() {
    return this.x;
  }
  
  public double getY() {
    return this.y;
  }
}

class SharedArrayList {
  private ArrayList<Data> dataSeries = new ArrayList<>();
  private boolean dataGenerationFinished = false;
  private int graphPlottedDataNum = 0;
  
  public synchronized void addData(Data newData) {
    dataSeries.add(newData);
  }
  
  public synchronized void plotDatas(XYSeries xySeries) {
    for (int index = graphPlottedDataNum; index < dataSeries.size(); index++) {
      Data data = dataSeries.get(index);
      xySeries.add(data.getX(), data.getY());
      graphPlottedDataNum++;
    }
  }
  
  public synchronized void  setDataGenerationFinished(boolean finished) {
    this.dataGenerationFinished = finished;
  }
  
  public synchronized boolean isDataGenerationFinished() {
    return dataGenerationFinished;
  }
  
  public synchronized int getGraphPlottedDataNum() {
    return graphPlottedDataNum;
  }
}

class DataGenerator implements Callable<String> {
  public static final Logger logger = LoggerFactory.getLogger(DataGenerator.class);
  // スレッド間共有オブジェクト
  private SharedArrayList sharedArrayList;
  private int dataNum = 1000;
  
  public DataGenerator(SharedArrayList sharedArrayList) {
    this.sharedArrayList = sharedArrayList;
  }
  
  public String call() {
    logger.info("Started data generation. Data number projection: {}", dataNum);
    int i = 0;
    try {
      for (; i < dataNum; i++) {
        Thread.sleep(10);
        sharedArrayList.addData(new Data(Math.random(), Math.random()));
      }
      sharedArrayList.setDataGenerationFinished(true);
      logger.info("Successfully Finished. Generated data number: {}", i);
    } catch (InterruptedException e) {
      logger.debug("Interrupted. Generated data number: {}", i);
      sharedArrayList.setDataGenerationFinished(true);
    }
    return "DataGenerator finished";
  }
}

class GraphDrawer implements Callable<String> {
  private static final Logger logger = LoggerFactory.getLogger(GraphDrawer.class);
  private static final long updateIntervalMillis = 500;
  // スレッド間共有オブジェクト
  SharedArrayList sharedArrayList;
  
  public GraphDrawer(SharedArrayList sharedArrayList) {
    this.sharedArrayList = sharedArrayList;
  }
  
  public String call() {
    XYSeries datas = new XYSeries("datas");
    XYSeriesCollection datasCollection = new XYSeriesCollection();
    datasCollection.addSeries(datas);
    Test1_1 frame = new Test1_1(datasCollection);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(10, 10, 500, 500);
    frame.setTitle("グラフサンプル");
    frame.setVisible(true);
    
    try {
      while (!sharedArrayList.isDataGenerationFinished()) {
        Thread.sleep(updateIntervalMillis);
        sharedArrayList.plotDatas(datas);
      }
      logger.info("Successfully finished. Plotted data number: {}", sharedArrayList.getGraphPlottedDataNum());
    } catch (InterruptedException e) {
      logger.debug("Interrupted. Plotted data number: {}", sharedArrayList.getGraphPlottedDataNum());
    }
    return "GraphDrawer finished";
  }
}

class Test1_1 extends JFrame{
  Test1_1(XYSeriesCollection data){
    JFreeChart chart = 
      ChartFactory.createScatterPlot("来店者傾向",
                                     "気温",
                                     "人数",
                                     data,
                                     PlotOrientation.VERTICAL, 
                                     true, 
                                     false, 
                                     false);

    ChartPanel cpanel = new ChartPanel(chart);
    getContentPane().add(cpanel, BorderLayout.CENTER);
  }
}

public class GraphDraw {
  public static final Logger logger = LoggerFactory.getLogger(GraphDraw.class);
  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(2);
    SharedArrayList al = new SharedArrayList();
    DataGenerator dg = new DataGenerator(al);
    GraphDrawer gd = new GraphDrawer(al);
    Future<String> dgResult = executor.submit(dg);
    Future<String> gdResult= executor.submit(gd);
    
    try {
//      Thread.sleep(3000);
//      dgResult.cancel(true);
//      String message = dgResult.get();
//      logger.info("Interruped DataGenerator. DataGenerator's message is {}");
      Thread.sleep(3000);
      gdResult.cancel(true);
//      message = gdResult.get();
      logger.info("Interrupted GraphDrawer. GraphDrawer's message is {}");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      executor.shutdown();
    }

  }
}
