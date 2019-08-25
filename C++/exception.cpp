/**
 * 前段の処理の成功を前提にした処理が連続して並んでいる場合の例外の利用の有効性を確かめるためのコード
 * How to use exception in a sequential processes
 * Tag: exception
 */
#include <vector>
#include <iostream>
#include <stdexcept>

typedef std::vector<int> Ints;
typedef std::vector<Ints> IntsList;
typedef std::vector<IntsList> IntsListList;

class MyException : public std::runtime_error {
public:
  MyException(const char *_Message, int res):runtime_error(_Message),_Errinfo(res) {
  }

  int returncode() {
    return _Errinfo;
  }

private:
  int _Errinfo;
};

// 整数配列を指定された個数でグルーピングする関数
void groupInts(const Ints &in, const int &num, IntsList &out);

// 整数グループ配列を指定された個数でグルーピングする関数
void groupIntsList(const IntsList &in, const int &num, IntsListList &out);

void groupInts(const Ints &in, const int &num, IntsList &out) {
  if (in.size() % num == 0) {
    for(int i = 0; i < in.size(); i += num) {
      Ints group;
      for(int j = 0; j < num; ++j) {
        group.push_back(in.at(i + j));
      }
      out.push_back(group);
    }
  } else {
    throw MyException("divide error", -1);
  }
}

void groupIntsList(const IntsList &in, const int &num, IntsListList &out) {
  if (in.size() % num == 0) {
    for(int i = 0; i < in.size(); i += num) {
      IntsList group;
      for(int j = 0; j < num; ++j) {
        group.push_back(in.at(i + j));
      }
      out.push_back(group);
    }
  } else {
    throw MyException("divide error", -1);
  }
}

int main() {
  const Ints in = {1,2,3,4,5,6,7,8,9,10};
  IntsList ints_list;
  IntsListList ints_list_list;
  try {
    groupInts(in, 3, ints_list);
    groupIntsList(ints_list, 1, ints_list_list);
  } catch (MyException &e) {
    std::cout << e.what() << std::endl;
  }
  return 0;
}