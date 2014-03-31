#include "heap.h"

int main(int argc, char *argv[])
{
    Heap a(20);

    a.insert(0);
    a.insert(3);
    a.insert(5);
    a.insert(2);
    a.insert(1);

    a.traverse();
    
    return 0;
}
