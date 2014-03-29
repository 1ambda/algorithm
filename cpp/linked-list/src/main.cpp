#include <iostream>
#include "list.h"

using namespace std;

int main(int argc, char *argv[])
{

    List l;

    l.insert(3);
    l.insert(4);
    l.insert(5);
    l.insert(6);
    l.traverse();

    l.erase();
    l.traverse();

    l.insert(3);
    l.traverse();

    
    return 0;
}
