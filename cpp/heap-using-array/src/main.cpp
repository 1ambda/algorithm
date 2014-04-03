#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

void swap(int *lhs, int* rhs) {
    int temp = *lhs;
    *lhs = *rhs;
    *rhs = temp;
}

int get_parent(int child) {
    return ((child % 2) == 0) ? (child - 1) / 2 : (child / 2);
}

// Ref : http://en.wikipedia.org/wiki/Heapsort#Pseudocode
void sift_down(int* arr, int current, int last) {

    int left;
    int right;
    int max;

    while((current * 2) + 1 <= last) {
        left = (current * 2) + 1;
        right = (current * 2) + 2;
        max = current;

        if (arr[left] > arr[max]) {
            max = left;
        }

        if (right <= last && arr[right] > arr[max]) {
            max = right;
        }

        if (max != current) {
            swap(&arr[current], &arr[max]);
            current = max;
        } else {
            return;
        }
    }
}

void sift_up(int* arr, int root, int current) {
    
    int parent;
    
    while (current > root) {
        parent = get_parent(current);
        if (arr[parent] >= arr[current])
            return;

        swap(&arr[parent], &arr[current]);
        current = parent;
    }
}

void heapify_top_down(int* arr, int length) {

    int end = 1;

    while(end < length) {
        sift_up(arr, 0, end++);
    }
}

void heapify_buttom_up(int* arr, int length) {
    
    int end = length - 1;
    int current = get_parent(end);

    while(current >= 0) {
        sift_down(arr, current--, end);
    }
}

void heap_sort(int* arr, int length) {
    if (arr == nullptr || length <= 1) {
        return;
    }

    // heapify
    // heapify_top_down(arr, length);
    heapify_buttom_up(arr, length);

    // sort
    int end = length - 1;
    while(end > 0) {
        swap(&arr[0], &arr[end--]);
        sift_down(arr, 0, end);
    }
}


void make_test_array(int* arr, int length, int max) {
    if (arr == nullptr || length <= 1) {
        return;
    }

    srand((unsigned int)time(NULL));
    for(int i = 0; i < length; i++) {
        arr[i] = rand() % max;
    }
}

bool is_sorted_array(int* arr, int length) {
    if (arr == nullptr || length < 1) {
        return false;
    }

    for(int i = 0; i < length - 1; i++) {
        if (arr[i] > arr[i+1])
            return false;
    }

    return true;
}

int main(int argc, char *argv[])
{
    int size = 1000;
    int* arr = new int[size];

    make_test_array(arr, size, size * 2);
    std::cout << is_sorted_array(arr, size) << std::endl;
    heap_sort(arr, size);
    std::cout << is_sorted_array(arr, size) << std::endl;

    delete []arr;
    return 0;
}

