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

    std::cout << "remove" << std::endl;
    l.insert(3, 1);
    l.traverse();
    std::cout << "remove" << std::endl;
    l.insert(0, 0);
    l.traverse();
    std::cout << "remove" << std::endl;
    l.insert(9, 6);
    l.traverse();

    std::cout << "reverse" << std::endl;
    l.reverse();
    l.traverse();
        
    return 0;
}
