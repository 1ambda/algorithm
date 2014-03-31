#include <iostream>

using namespace std;

int binary_search(int* arr, int length, int value) {

    if (arr == nullptr || length < 0)
        return false;

    int left = 0;
    int right = length - 1;
    int mid;

    while(left <= right) {
        mid = left + (right - left) / 2;

        if(arr[mid] == value) {
            return mid;
        } else if (arr[mid < value]) {
            left = mid + 1;
        } else if (arr[mid] > value) {
            right = mid - 1;
        }
    }

    return -1;
}

int main(int argc, char *argv[])
{
    int arr[10] = { 1, 2, 4, 5, 7, 10, 11, 17, 21, 25 };

    // ensure that you save a sorted array
    std::cout << "index : " << binary_search(arr, 10, 21) << std::endl;
    
    return 0;
}

