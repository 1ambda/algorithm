#include <iostream>
#include "tree.h"

using namespace std;

int main(int argc, char *argv[])
{

  BinarySearchTree<int> bst;

  function<int(int&, int&)> comparator = [](int& lhs, int& rhs)->int{
    if(lhs > rhs) {
      return 1;
    } else if (lhs < rhs) {
      return -1;
    } else {
      return 0;
    }
  };

  function<void(int& value)> printer = [](int& value)->void{
    std::cout << value << std::endl;
  };

  bst.insert(3, comparator);
  bst.insert(2, comparator);
  bst.insert(5, comparator);
  bst.insert(4, comparator);
  bst.insert(6, comparator);

  // std::cout << bst.search(1, comparator) << std::endl;
  // bst.insert(1, comparator);
  // std::cout << bst.search(1, comparator) << std::endl;

  bst.remove(5, comparator);
  bst.traverse_pre_order(printer);
  
  return 0;
}

