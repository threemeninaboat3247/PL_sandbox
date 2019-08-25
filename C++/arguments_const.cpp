/**
 *  ポインタ型参照の関数引数への適切なconst修飾子の付け方
 *  How to attach 'const' modifiers to a function argument which is a pointer type reference
 *  結論：find系のポインタ出力引数はconst int* &ptr_outのように定義する
 *  Tag: pointer, reference, const, argument, function
 */
#include <stdio.h>
#include <vector>
typedef std::vector<int> MyVector;

// 一見入力引数のように見える出力引数 第二引数のconstはptr_outが指す変数が書き換えられないことを意味する
bool change_pointer(const int* ptr_in, const int* &ptr_out);
// ベクタを入力引数で受け取り、指定された値があればそこへポインタをセットして返す関数
bool find_value(const int &target, const MyVector &vec, const int* &ptr_out);
//bool find_value(const int &target, const MyVector &vec, int* &ptr_out);    // constなベクタのある要素にconst *でないポインタをセットすることはできない
int main(){
  const int  i  = 0;
  const int  j  = 1;
  const int* pi = &i; // int* pi = &i はアウト
  pi = &j;            // const * の順なので、ポインタの値自体は変えられる
  //*pi = 1;            // ポインタの指す実体はいじれない
  pi = &i;
  printf("*pi (before calling change_pointer): %d\n", *pi);
  change_pointer(&j, pi);  //
  printf("*pi (after calling change_pointer): %d\n", *pi);

  const int* answer;
  MyVector vec{1,2,3,4};
  answer = &vec[0];
  printf("*answer (before calling find_value): %d\n", *answer);
  find_value(2, vec, answer);
  printf("*answer (after calling find_value): %d\n", *answer);
  return 0;
}

bool change_pointer(const int* ptr_in, const int* &ptr_out){
  ptr_out = ptr_in;
  return true;
}

bool find_value(const int &target, const MyVector &vec, const int* &ptr_out){
//bool find_value(const int &target, const MyVector &vec, int* &ptr_out){
  bool is_found = false;
  for (const auto &element : vec) {
    if (element == target) {
      ptr_out = &element;
      is_found = true;
      break;
    }
  }
  return is_found;
}