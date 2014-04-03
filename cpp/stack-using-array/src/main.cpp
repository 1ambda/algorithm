#include "stack.h"
#include <iostream>
#include <functional>

using namespace std;

int main(int argc, char *argv[])
{

    Stack<int> s(100);
    s.traverse();

    s.push(3);
    s.push(4);
    s.traverse();

    int result = -1;
    if (s.top(&result)) {
        std::cout << "top : " << result << std::endl;
    }

    std::cout << "pop" << std::endl;
    s.pop();
    s.traverse();

    std::cout << "push" << std::endl;
    s.push(4);
    s.push(5);

    std::function<void (int)> func = [](int x)->void {
        std::cout << "Data " << x << std::endl;
    };

    s.traverse(func);
    
    return 0;
}
