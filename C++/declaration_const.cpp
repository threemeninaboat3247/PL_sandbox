/**
 * 変数宣言時の*とデータ型の位置の任意性を確かめるためのコード
 * How '*' and data type can be arranged in an idetifier declaration
 * 結論：単語間の順序を変えない限りは*を左右のどちらに寄せるかは任意なので、自分の流儀で統一して大丈夫
 */
#include <stdio.h>
int static_int = 5;

// 空白は無視され、データ型、*、識別子に切り分けられる
int* ptr_1 = NULL;
int *ptr_2 = NULL;
int * ptr_3 = NULL;
int*ptr_4 = NULL;

// constが付くと任意性がさらに増える
//const *int const_ptr_1;  // *の左にデータ型が無い事は許されない
const int* const_ptr_1;     // 実体の書き換え禁止 const修飾: int*
int const *const_ptr_2;     // 実体の書き換え禁止 const修飾: int*
int const* const_ptr_3;     // *の寄せ方の任意性は常にある
int* const const_ptr_4 = &static_int;     // ポインタ自身の書き換え禁止 const修飾：*
const int* const const_ptr_5 = &static_int; // ポインタ自身と実体の書き換え禁止 const修飾: *, int*

// *と参照が絡むと・・・
const int* &const_ref_1 = const_ptr_1;    // const_ptr_1の別名と解釈するのが最良, const修飾: int*

int main(){
  int hoge = 0;
  int moge = 1;
  const_ptr_1 = &hoge;  // ポインタを介した実体の書き換えが禁止されるだけで、constでないintのアドレスを入れることはできる
  printf("before address change, *const_ptr_1: %d\n", *const_ptr_1);
  const_ptr_1 = &moge;
  printf("after address change, *const_ptr_1: %d\n", *const_ptr_1);

  //const_ptr_4 = &hoge;  // ポインタ自身の書き換え禁止

  // *const_ref_1 = 99;    // 値の書き換え禁止
  printf("*const_ref_1: %d\n", *const_ref_1);
  return 0;
}