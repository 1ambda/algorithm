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

    int result = 0;

    if (l.get(3, &result)) {
        std::cout << result << std::endl;
    }
    
    return 0;
}
