#include <iostream>
#include <functional>
#include "heap.h"

using namespace std;

int get_parent(int index) {
    return (index % 2) ? (index - 1) / 2 : (index / 2);
}

void swap(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

// MIN Heap
void sift_up(int* a, int root, int current) {

    while(current > root) {
        if (a[get_parent(current)] <= a[current]) {
            break;
        }

        swap(&a[get_parent(current)], &a[current]);
        current = get_parent(current);
    }
}

void heapify(int* a, int length) {
    if (length < 2) {
        return;
    }

    for(int i = 1; i < length; i++) {
        sift_up(a, 0, i);
    }
};



int main(int argc, char *argv[])
{
    Heap a(20);

    // a.insert(0);
    // a.insert(3);
    // a.insert(5);
    // a.insert(2);
    // a.insert(1);

    // a.traverse();

    int arr[5] = { 7, 6, 5, 4, 8};
    heapify(arr, 5);

    for(int i = 0; i < 5; i++) {
        std::cout << arr[i] << std::endl;
    }
    
    return 0;
}

