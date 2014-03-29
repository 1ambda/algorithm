#include "node.h"

class List
{
public:
    List();
    ~List();

    Node* head;
    int size;

    void traverse(void);
    void insert(int data);
    bool remove(int index);
    bool get(int index, int* result);
    bool isEmpty(void);
    void erase(void);
};

