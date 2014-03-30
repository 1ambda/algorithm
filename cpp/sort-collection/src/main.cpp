#include <iostream>
#include <functional>
#include "sort.h"

using namespace std;

int main(int argc, char *argv[])
{
    int arr[10] = { 1, 1, 3, 8, 1, 4, 2, 7, 10, 15 };
    // int arr[5] = { 7, 3, 7, 6, 7 };

    function<bool(int&, int&)> f = [](int& l, int&r)->bool {
        if (l > r) {
            return true;
        }

        return false;
    };

    // Sort<int>::bubble(arr, 10, f);
    // Sort<int>::selection(arr, 10, f);
    // Sort<int>::insertion(arr, 10, f);
    // Sort<int>::merge(arr, 10, f);
    // Sort<int>::quick(arr, 10, f);
    Sort<int>::quick(arr, 10, f);
    

    for(int i = 0; i < 10; i++) {
        std::cout << arr[i] << " " << std::endl;
    }
    
    return 0;
}
