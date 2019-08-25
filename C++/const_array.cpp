/**
 * 定数配列の意味を確かめるためのコード
 * The meaning of "constant array"
 * 結論: 配列変数自体は元から書き換え不可能なので、consで守るべきは要素だけ
 * Tag: array, const, declaration
 */
#include <stdio.h>

int main(void) {
  const int array[] = {1,2,3,4};  // 要素型const int の配列という意味
  int loose_array[] = {5,6,7,8};
  // array = loose_array;      // 配列は左オペランドではint[]型扱い、右オペランドではint*型扱いになるらしい よって置き換え不可
  for (int i = 0; i < (int)(sizeof(array)/sizeof(array[0])); ++i) {
    // array[i] = 0;
    printf("%d\n", array[i]);
  }
}