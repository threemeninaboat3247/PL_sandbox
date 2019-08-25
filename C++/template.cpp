/*
 * コンテナをイテレーションする関数をテンプレートで
 * 書く際に注意するべきこと
 * Tag: template, iteration, container, vector
 */
#include <vector>
#include <iostream>
#include <string>

template<typename C, typename V>
std::vector<typename C::iterator> find_all(C &c, V v){
    std::vector<typename C::iterator> res;
    for(auto p = c.begin(); p != c.end(); ++p){
        if(*p == v){
            res.push_back(p);
        }
    }
    return res;
}

int main(){
    std::string mess {"Why is there something instead of nothing?"};
    std::vector<std::string::iterator> results = find_all(mess, 'e');
    std::cout << "results size:" << results.size();
    return 0;
}