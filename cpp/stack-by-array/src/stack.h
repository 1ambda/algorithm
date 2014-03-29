// stack.h
// 2014.03.29 by Anster
#include <iostream>
#include <functional>

using namespace std;

template <typename T>
class Stack {
public:
    Stack<T>();
    Stack<T>(T t);
    ~Stack<T>();
    bool pop(void);
    bool top(T* t);
    bool push(T t);
    size_t get_size(void);
    size_t get_max_size(void);
    bool is_empty(void);
    bool is_full(void);
    void traverse(void);
    // traverse using lambda
    void traverse(std::function< void(T t)>& f);

private:
    T* container;
    size_t MAX_SIZE;
    size_t size;
    static const size_t DEFAULT_SIZE = 100;
};

// custom print method using lambda
template <typename T>
void Stack<T>::traverse(std::function< void(T t) >& f) {
    for (int i = 0; i < size; i++) {
        f(container[i]);
    }
}

template <typename T>
Stack<T>::Stack() {
    MAX_SIZE = Stack<T>::DEFAULT_SIZE;
    size = 0;
    container = new T[MAX_SIZE];
}

template <typename T>
Stack<T>::Stack(T t) {
    MAX_SIZE = t;
    size = 0;
    container = new T[MAX_SIZE];
}

template <typename T>
Stack<T>::~Stack() {
    delete []container;
}

template <typename T>
bool Stack<T>::pop(void) {
    if(is_empty()) {
        return false;
    }

    size--;
    return true;
}

template <typename T>
bool Stack<T>::top(T* t) {
    if (is_empty()) {
        return false;
    }

    *t = container[size-1];
    return true;
}

template <typename T>
bool Stack<T>::push(T t) {
    if (is_full()) {
        return false;
    }

    container[size] = t;
    size++;

    return true;
}

template <typename T>
size_t Stack<T>::get_size(void) {
    return size;
}
template <typename T>
size_t Stack<T>::get_max_size(void) {
    return MAX_SIZE;
}

template <typename T>
bool Stack<T>::is_empty(void) {
    if (size == 0) {
        return true;
    }

    return false;
}

template <typename T>
bool Stack<T>::is_full(void) {
    if (size == MAX_SIZE) {
        return true;
    }

    return false;
}

template <typename T>
void Stack<T>::traverse(void) {

    for(int i = 0;  i < size; i++) {
        std::cout << "[" << i << "] : " << container[i] << std::endl;
    }
}
