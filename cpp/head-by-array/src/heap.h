#include <iostream>
#include <functional>

using namespace std;

// MXA HEAP
class Heap
{
public:
    Heap();
    Heap(int size);
    virtual ~Heap();

    bool insert(int data);
    bool extract(int& data);
    bool is_full(void);
    bool is_empty(void);
    // traverse
    void traverse(void);

private:
    int* array;
    int size;
    int MAX_SIZE;

    void init(int size);
    int get_left(int);
    int get_right(int);
    int get_parent(int);
};

void Heap::traverse(void) {

    for(int i = 1; i < size + 1; i++) {
        std::cout << array[i] << std::endl;
    }
}

bool Heap::insert(int data) {

    if (is_full()) {
        return false;
    }

    int index = size + 1;
    array[index] = data;
    int temp;

    while(get_parent(index)) {
        if(array[index] < array[get_parent(index)]) {
            temp = array[index];
            array[index] = array[get_parent(index)];
            array[get_parent(index)] = temp;
        }

        index = get_parent(index);
    }
    
    size++;
    return true;
}

bool Heap::extract(int& data) {
    if(is_empty())
        return false;

    data = array[1];
    array[1] = array[size];
    size--;
    int index = 1;
    int min, left, right;

    while(get_left(index)) {
        // right 가 존재하면 left, right 중 큰 값을 찾음
        // left 만 존재

        // left or right 값과 현재값을 비교해서 작으면 break
        // 크면 값을 바꾸고 index 값도 변경하고,  
    }
    

    // todo
    
    return true;
}

int Heap::get_left(int current) {
    int index = (current * 2);

    if (index > size) {
        return 0;
    }
    
    return index;
}

int Heap::get_right(int current) {
    int index = (current * 2) + 1;

    if (index > size) {
        return 0;
    }
    
    return index;
}

int Heap::get_parent(int current) {
    return (current / 2);
}

bool Heap::is_full(void) {
    if (size == MAX_SIZE) {
        return true;
    }

    return false;
}

bool Heap::is_empty(void) {
    if (size == 0) {
        return true;
    }

    return false;
}


Heap::Heap() {
    init(100);
}

void Heap::init(int size) {
    this->size = 0;
    MAX_SIZE = size;
    // we don't use the array[0]
    array = new int[MAX_SIZE + 1];
}

Heap::Heap(int size) {
    if (size <= 0)
        init(100);
    else
        init(size);
}

Heap::~Heap() {
    delete array;
}




