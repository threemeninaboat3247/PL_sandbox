/*
 * コピーコンストラクタの定義
 * Tag: constructor, copy
 */
#include <iostream>

class NoCopyConstructor{
public:
    int public1;
    NoCopyConstructor(int public1):public1{public1}{}
};

class CopyConstructor{
public:
    int public1;
    CopyConstructor(int public1):public1{public1}{}
    CopyConstructor(const CopyConstructor& a){
        std::cout << "copy constructor was called!" << std::endl;
        public1 = a.public1+1;
    }
    CopyConstructor& operator=(const CopyConstructor& a){
        std::cout << "copy substitution operator was called!" << std::endl;
        public1 = a.public1-1;
        return *this;
    }
};

int main(){
    CopyConstructor c{1};
    CopyConstructor c2 = c;
    std::cout << "c2.public1:" << c2.public1 << std::endl;
    c2 = c;
    std::cout << "c2.public1:" << c2.public1 << std::endl;
    return 0;
}