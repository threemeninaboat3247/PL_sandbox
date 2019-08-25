/**
 * 構造体のコンストラクタがどのタイミングで呼ばれるかを確かめるためのコード
 * When the default constuctor of a structure is called
 * 結論：宣言のみでOK. デフォルトコンストラクタを持つユーザ定義型は必ず初期化される。(cf. STROUSTRUP p.171)
 * Tag: constructor, initialization
 */
#include <string>
#include <iostream>

struct MyStructure{
public:
  std::string name;
  int         age;

  // default constructor
  MyStructure():name{"nobody"},age{99} {
    std::cout << name << std::endl;
  }
};

int main() {
  std::cout << "before declaration" << std::endl;
  MyStructure ms; // 宣言のみ
  std::cout << "before access" << std::endl;
  std::cout << ms.age;  // いきなり参照
  std::cout << "after access" << std::endl;
  return 0;
}