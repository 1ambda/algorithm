#include "node.h"

Node::Node()
{
    data = 0;
    initialize();
}

Node::Node(int data)
{
    this->data = data;
    initialize();
}

Node::~Node()
{
}

void Node::initialize(void)
{
    prev = nullptr;
    next = nullptr;
}
