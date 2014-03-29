#include "node.h"
#include <iostream>
using namespace std;

template <typename T>
class List
{
public:
    List();
    ~List();
    bool is_empty(void);
    void erase(void);
    size_t get_size(void);
    bool insert(T t, int index);
    void insert_first(T t);
    void insert_last(T t);
    bool remove(int index);
    bool remove_first(void);
    bool remove_last(void);
    void traverse(void);
    void reverse(void);
    // sort? using functor? or lambda?

private:
    Node<T>* head;
    Node<T>* tail;
    size_t size;
};

template <typename T>
List<T>::List()
{
    size = 0;
    head = nullptr;
    tail = nullptr;
}

template <typename T>
List<T>::~List()
{
    erase();
}

template <typename T>
void List<T>::erase(void) {
    
}

template <typename T>
bool List<T>::is_empty(void)
{
    if (size == 0) {
        return true;
    }

    return false;
}

template <typename T>
size_t List<T>::get_size(void)
{
    return size;
}

template <typename T>
bool List<T>::insert(T t, int index)
{
    if (index < 0 || index >= size) {
        return false;
    }

    if (is_empty()) {
        return false;
    }

    Node<T>* current = head;

    for(int i = 0; i < index; i++) {
        current = current->next;
    }

    if(index == 0) {
        insert_first(t);
        return true;
    } else if (index == (size-1)) {
        insert_last(t);
        return true;
    } else {
        // middle
        Node<T>* temp = new Node<T>(t);
        current->prev->next = temp;
        temp->prev = current->prev;

        temp->next = current;
        current->prev = temp;
    }

    size++;
    return true;
}

template <typename T>
void List<T>::insert_first(T t)
{
    if (head == nullptr) {
        head = new Node<T>(t);
        tail = head;
    } else {
        Node<T>* before = new Node<T>(t);
        before->next = head;
        head->prev = before;
        head = before;
    }

    size++;
}

template <typename T>
void List<T>::insert_last(T t)
{
    if (head == nullptr) {
        head = new Node<T>(t);
        tail = head;
    } else {
        Node<T>* after = new Node<T>(t);
        after->prev = tail;
        tail->next = after;
        tail = after;
    }

    size++;
}

template <typename T>
bool List<T>::remove(int index)
{
    if (index < 0 || index >= size ) {
        return false;
    }

    if (is_empty()) {
        return false;
    }

    Node<T>* current = head;

    for (int i = 0; i < index; i++) {
        current = current->next;
    }

    if (index == 0) {
        return remove_first();
    } else if (index == (size-1)) {
        return remove_last();
    } else {
        // middle
        current->prev->next = current->next;
        current->next->prev = current->prev;
        delete current;
    }
    
    size--;
    return true;
}

template <typename T>
bool List<T>::remove_first(void)
{
    if (is_empty()) {
        return false;
    }

    Node<T>* temp = head;
    if (size == 1) {
        tail = nullptr;
        head = nullptr;
    }else {
        head->next->prev = nullptr;
        head = head->next;
    }

    delete temp;
    size--;
    return true;
}

template <typename T>
bool List<T>::remove_last(void)
{
    if (is_empty()) {
        return false;
    }

    Node<T>* temp = tail;
    if (size == 1) {
        head = nullptr;
        tail = nullptr;
    } else {
        tail->prev->next = nullptr;
        tail = tail->prev;
    }

    delete temp;
    size --;
    return true;
}

template <typename T>
void List<T>::traverse(void)
{
    if (is_empty()) {
        return;
    }

    for(Node<T>* temp = head; temp != nullptr; temp = temp->next) {
        cout << "Data : " << temp->data << ", Address : " << temp << endl;
    }
}

template <typename T>
void List<T>::reverse(void)
{
    if (size < 2) {
        return;
    }

    Node<T>* current = head;

    for(int i = 0; i < size; i++) {
        Node<T>* temp = current->prev;
        current->prev = current->next;
        current->next = temp;
        current = current->prev;
    }

    Node<T>* temp = tail;
    tail = head;
    head = temp;
}
