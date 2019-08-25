/**
 * ストリームにフォーマット指定有りで書き出す方法を確かめるコード
 * How to output values to a stream with formatting
 * 結論：操作子を使う
 * Tag: stream, format, manipulator
 */
#include <iostream>
#include <iomanip>

int main() {
  // ストリームの状態を変える方法(<<一個分だけ有効)
  std::cout.width(10);
  std::cout << 12345678 << " " << 12345678 << " " << 12345678 << " " << std::endl;

  // 操作子を使う方法
  std::cout << std::setw(10) << 12345678 << " " << 12345678 << " " << 12345678 << " " << std::endl;
  return 0;

}