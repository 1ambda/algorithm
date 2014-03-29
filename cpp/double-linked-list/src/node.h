template <typename T>
class Node
{
public:
    Node();
    Node(T t);
    ~Node();

    template <typename TYPE> friend class List;

private:
    T data;
    Node* next;
    Node* prev;
};

template <typename T>
Node<T>::Node()
{
    data = nullptr;
    prev = nullptr;
    next = nullptr;
}

template <typename T>
Node<T>::Node(T t)
{
    data = t;
    prev = nullptr;
    next = nullptr;
}

template <typename T>
Node<T>::~Node()
{
}
