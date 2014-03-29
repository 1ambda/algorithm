#include "list.h"
#include <iostream>
using namespace std;

List::List()
{
    head = nullptr;
    size = 0;
}

List::~List()
{
    erase();
}

void List::erase(void) {

    if (size == 0) {
        return;
    }

    Node* current = head;

    while(current != nullptr) {
        Node* temp = current;
        current = current->next;
        delete temp;
    }

    head = nullptr;
    size = 0;
}

void List::traverse(void)
{
    for (Node* temp = head; temp != nullptr; temp = temp->next) {
        std::cout << "Data : " << temp->data << ", Address : " << temp << std::endl;
    }
}

void List::insert(int data)
{
    // if list is emtpy
    if (isEmpty()) {
        head = new Node(data);
    } else {
        Node* temp = head;

        while (temp->next != nullptr) {
            temp = temp->next;
        }

        temp->next = new Node(data);
    }
    
    size++;
}

bool List::remove(int index)
{
    if (isEmpty()) {
        return false;
    }

    if (index < 0 || index >= size) {
        return false;
    }

    // remove head
    if (index == 0) {
        Node* temp = head;
        head = (head->next != nullptr) ? head->next : nullptr;
        delete temp;
    } else {
        Node* before = head;
        for(int i = 0; i < index - 1; i++) {
            before = before->next;
        }

        Node* after = before->next->next;
        Node* removed = before->next;
        before->next = after;
        delete removed;
    }

    return true;
}

bool List::get(int index, int * result)
{
    if (isEmpty()) {
        return false;
    }

    if (index < 0 || index >= size) {
        return false;
    }

    Node* temp = head;

    for (int i = 0; i < index; i++) {
        temp = temp->next;
    }

    *result = temp->data;
    
    return true;
}

bool List::isEmpty(void)
{
    if (size == 0) {
        return true;
    }

    return false;
}

