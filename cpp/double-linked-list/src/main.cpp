#include <iostream>
#include "list.h"

using namespace std;

int main(int argc, char *argv[])
{
    List<int> l;

    l.insert_last(2);
    l.insert_last(4);
    l.insert_last(6);
    l.insert_last(7);
    l.insert_last(8);
    l.traverse();

    std::cout << "erase" << std::endl;
    l.erase();
    l.traverse();

    std::cout << "insert" << std::endl;
    l.erase();
    l.insert_last(2);
    l.insert_last(4);
    l.insert_last(6);
    l.insert_last(7);
    l.insert_last(8);
    l.traverse();
        
    return 0;
}
