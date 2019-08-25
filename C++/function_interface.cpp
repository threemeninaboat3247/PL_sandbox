/**
 * 配列を受け取る関数のインタフェースが配列形式の時とポインタ形式の時の違いを確かめるためのコード
 * Check the difference between an array and pointer type argument of a function
 * 結論: 配列形式だと配列変数そのものの書き換えを禁止する方法はない。ポインタ形式だとポインタ変数そのものも書き換え禁止にできる
 * Tag: array, pointer, function, argument
 */
#include <stdio.h>


void func_array(const int array[], int size);
void func_pointer(const int* const array_head, int size);

int main(void) {
  int array[] = {1,2,3};
  int size    = (int)sizeof(array)/sizeof(array[0]);
  func_array(array, size);
  func_pointer(array, size);
  return 0;
}

void func_array(const int array[], int size) {
  printf("%s\n", __func__);
  int temp[] = {9, 9, 9};
  array = temp;  // 変更の制限の強いところにルーズなオブジェクトを渡すのはOK(コンパイラ警告なし) 逆はダメ これを禁止するconstの付け方は無い？
  for (int i = 0; i < size; ++i) {
    // array[i] = 0;
    printf("  %d\n", array[i]);
  }
}
void func_pointer(const int* const array_head, int size) {
  printf("%s\n", __func__);
  // int* anothor_pointer = (int*)array_head;    // castしてしまえば書き換え可能
  // int temp[] = {9, 9, 9};
  // array_head = temp;  // 変更の制限の強いところにルーズなオブジェクトを渡すのはOK(コンパイラ警告なし) 逆はダメ
  for (int i = 0; i < size; ++i) {
    // *(array_head + i) = 0;     // array_head + i はconst int*型なので、代入は無理
    // *(anothor_pointer + i) = 0;
    printf("  %d\n", *(array_head + i));
  }
}