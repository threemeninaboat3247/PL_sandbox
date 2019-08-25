/**                                                                                                                                             *  逐一検索とマップを使った検索の実行速度の比較
 *  The comparison of one-by-one and map-based search
 *  結論:
 *      逐一検索だと指定された要素を見つける時間がベクタのサイズに比例
 *      マップを使うとベクタのサイズには関係しない
 *      マップを作る手間を考えても断然マップを使う方が早い（一回の検索しかしないならともかく）
 * Tag: map, vector, find, performance
 */

#include <random>
#include <iostream>
#include <map>
#include <algorithm>
#include <vector>
#include <chrono>

int make_map(const std::vector<int> &v, std::map<int,int> &map){
    map.clear();
    for(int i = 0; i < v.size(); i++){
        map.insert(std::make_pair(v.at(i),i));
    }
    return 0;
}

int main(){
    // make a randomly ordered vector
    std::vector<int> v(10000);
    std::cout << "vector size:" << v.size() << std::endl;
    for(int i = 0; i < v.size(); i++){
        v[i] = i;
    }
    std::random_shuffle(v.begin(), v.end());

    // make a map
    auto start = std::chrono::system_clock::now();
    std::map<int,int> v_map;
    make_map(v,v_map);
    auto end = std::chrono::system_clock::now();
    auto dur = end - start;
    auto msec = std::chrono::duration_cast<std::chrono::milliseconds>(dur).count();
    std::cout << "map creation:" << msec << "ms \n";

    // make a uniform random distribution
    std::random_device rnd;
    std::mt19937 mt(rnd());
    std::uniform_int_distribution<> rand(0,v.size() - 1);

    // record the time of map-basd search
    start = std::chrono::system_clock::now();
    for(int i = 0; i < v.size(); i++){
        int j = rand(mt);
        v_map.at(j);
    }
    end = std::chrono::system_clock::now();
    dur = end - start;
    msec = std::chrono::duration_cast<std::chrono::milliseconds>(dur).count();
    std::cout << "map-based:" << msec << "ms \n";

    // record the time of one-by-one search
    start = std::chrono::system_clock::now();
    for(int i = 0; i < v.size(); i++){
        int j = rand(mt);
        for(int k = 0; k < v.size(); k++){
            if(v.at(k) == j){
                break;
            }
        }
    }
    end = std::chrono::system_clock::now();
    dur = end - start;
    msec= std::chrono::duration_cast<std::chrono::milliseconds>(dur).count();
    std::cout << "one-by-one:" << msec << "ms \n";

    return 0;
}