/**
 * Tag: reference, move
 */
#include <iostream>

class NoMoveClass{
public:
    int value;
    NoMoveClass(int v):value {v}{}
    ~NoMoveClass(){}

    NoMoveClass(const NoMoveClass &n){
        std::cout << "copy constructor" << std::endl;
        value = n.value + 1;
    }
    NoMoveClass& operator=(const NoMoveClass& n){
        std::cout << "copy substitution" << std::endl;
        value = n.value - 1;
    }
    NoMoveClass(NoMoveClass &&n){
        std::cout << "move constructor" << std::endl;
        value = n.value;
        n.value = 0;
    }
};

NoMoveClass& handle(NoMoveClass& n){
    return n;
}

int main(){
    NoMoveClass n {9};
    NoMoveClass &n2 = n;
    std::cout << n2.value << std::endl;
    return 0;
}