package basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * ファイルの読み書きの方法を確認するためのコード
 * java.ioのクラスの名前について
 * 文字の入力ストリーム       : ***Reader (例) ファイル入力文字ストリーム FileReader
 * rawバイトの入力ストリーム  : ***InputStream (例) ファイル入力バイトストリーム FileInputStream
 * 文字の出力ストリーム       : ***Writer (例) ファイル出力文字ストリーム FileWriter
 * rawバイトの出力ストリーム  : ***OutputStream (例) ファイル出力バイトストリーム FileOutputStream
 * 
 * 課題
 * [1] バイトストリームを開いてファイルの読み書きを行え
 * [2] ファイルストリームを開いて読み書きを行え
 * [3] バッファリングを使って[1,2]を行え
 * [4] Cのstaticなローカル変数のように一度しか実行されない宣言と初期化処理を記述せよ
 * [5] ファイルの読み書きで起こりえる例外を考慮して適切なtry catchでリソースを解放せよ
 * [6] ファイルとディレクトリのコピー、削除、作成を行え
 *  
 * @author Yuki
 *
 */

public class File {

  public static void main(String[] args) {
    // TODO 自動生成されたメソッド・スタブ
    int on = 4;
    Path path = Paths.get("./input_test.txt");
    Path path_o = Paths.get("./output_test.txt");
    Path dir_path = Paths.get("./layer1/layer2/layer3");
    Path current = Paths.get(".");
    Path source = Paths.get("./layer1");
    Path target = Paths.get("./destination");
    switch (on) {
      // [1],[5]
      case 0:
        // 読み込み
        try (InputStream is = Files.newInputStream(path)) {
          for (int ch; (ch = is.read()) != -1;) {
            System.out.print((char)ch);
          }
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        // 書き込み
        byte[] data = new byte[] {0x41, 0x42, 0x43};
        try (OutputStream os = Files.newOutputStream(path_o, StandardOpenOption.APPEND,
                                                     StandardOpenOption.CREATE,
                                                     StandardOpenOption.WRITE);) {
          os.write(data);
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;

      // [2],[3],[5]
      case 1:
        // 文字コード指定で読み込み、書き込み
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
             BufferedWriter bw = Files.newBufferedWriter(path_o, StandardCharsets.UTF_8,
                                                         StandardOpenOption.APPEND,
                                                         StandardOpenOption.WRITE);) {
          ArrayList<String> lines = new ArrayList<>(); 
          for(String line; (line = br.readLine()) != null;) {
            System.out.println(line);
            lines.add(line);
          }
          for (String line : lines) {
            bw.append(line);
            bw.newLine();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      // [6]
      case 2:
        // 上の階層ディレクトリから順に作っていく 既にある場合はエラーにならない
        try {
          Files.createDirectories(dir_path);
        } catch (IOException e) {
          e.printStackTrace();
        }
        // 最下層ディレクトリにファイルを作る
        Path file_path = dir_path.resolve("myfile");
        try {
          Files.createFile(file_path);
        } catch (FileAlreadyExistsException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
        
        // ファイル(ディレクトリならば自分自身のみ)をコピーする
        Path coppied = file_path.resolveSibling("coppied");
        Path to_be_deleted = file_path.resolveSibling("to_be_deleted");
        try {
          Files.copy(file_path, coppied);
          Files.copy(file_path, to_be_deleted);
        } catch (IOException e) {
          e.printStackTrace();
        }
         
        // ファイル(ディレクトリならば空の時のみ例外が発生しない)を削除する
        try {
          Files.delete(to_be_deleted);
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
        
      case 3:
        // ディレクトリの再帰的なコピー
        // layer1をまるごとdestination下にコピーする
        try {
          Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
          @Override
          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Path targetFile = target.resolve(current.relativize(file));
            Path parentDir = targetFile.getParent();
            Files.createDirectories(parentDir);
            Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
            return FileVisitResult.CONTINUE;
          }
        });
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case 4:
        // ディレクトリの再帰的な削除
        // destinationをまるごと削除する
        try {
          Files.walkFileTree(target, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException{
              Files.delete(dir);
              return FileVisitResult.CONTINUE;
            }
            
            @Override 
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
              Files.delete(file);
              return FileVisitResult.CONTINUE;
            }
          });
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
    }
  }
}
