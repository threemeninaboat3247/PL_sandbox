package basic;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 画像ファイルの読み書きを理解するためのテストコード
 * @author Yuki
 * 
 * 課題
 * [1] 画像ファイルを読み込んでサイズ、ファイル形式を表示せよ
 * [2] 読み込んだ画像を表示せよ
 * [3] 特定のピクセルのRGBAの値をそれぞれ取得する関数をかけ
 * [4] 読み込んだ画像を編集し、結果を2*3で並べて出力せよ
 *
 */

class MyPanel extends JPanel{
  Image img;
  public MyPanel(Image img) {
    this.img = img;
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 0, 0, this);
  }
}

public class MyImage {
  
  // [3]
  public static void getRGBA(BufferedImage bi, int x, int y) {
    if ( x < 0 || x >= bi.getWidth() || y < 0 || y >= bi.getHeight()) {
      throw new IllegalArgumentException();
    } else {
      System.out.printf("(x, y): %x\n", bi.getRGB(x, y));
    }
  }

  public static void main(String[] args) {
    // TODO 自動生成されたメソッド・スタブ
    // [1]
    Path imgPath = Paths.get("C:/Users/Yuki/Desktop/tmp/sample.png");
    Path outPath = Paths.get("C:/Users/Yuki/Desktop/tmp/out.png");
    try {
      final BufferedImage bi = ImageIO.read(imgPath.toFile());
      final int width = bi.getWidth();
      final int height = bi.getHeight();
      final int exRateW = 3;
      final int exRateH = 2;
      final BufferedImage out = new BufferedImage(width * exRateW, height * exRateH, BufferedImage.TYPE_INT_RGB);
      System.out.println("image size(width, height): " + width + ", " + height);
      // [2] swingを使う
      EventQueue.invokeLater(() -> {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(bi);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mp);
        frame.setVisible(true);
      });
      getRGBA(bi, 10, 10);
      getRGBA(bi, 50, 50);
      
      // [4]
      int tmp;
      for (int x = 0; x < width * exRateW; x++) {
        for (int y = 0; y < height * exRateH; y++) {
          tmp = bi.getRGB(x % width, y % height);
          tmp &= 0xff0000ff;
          out.setRGB(x, y, tmp);
        }
      }
      ImageIO.write(out, "png", outPath.toFile());
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
}
