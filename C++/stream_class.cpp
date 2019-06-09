/**
 * 独自に定義したクラスを<<演算子でストリームに出力する方法を確かめるためのコード
 * How to output an instance of a user-defined class to a stream by using '<<' operator
 * 結論：クラス定義外のスコープで<<演算子をオーバーロードする。プライベートメンバに直接アクセスする場合は<<演算子をfriend登録しておく
 */
#include <iostream>
#include <sstream>
#include <string>

class MyClass
{
public:
  MyClass(int upper, int lower) {
    upper_ = upper;
    lower_ = lower;
  }

  std::string toString() const {
     std::stringstream ss;
     ss << upper_ << lower_;
     return ss.str();
  }

private:
  int upper_;
  int lower_;
};

std::ostream& operator<<(std::ostream& stream, const MyClass& value) {
  stream << value.toString();
  return stream;
}

int main() {
  MyClass m = MyClass(1,2);
  std::cout << m;
  return 0;
}