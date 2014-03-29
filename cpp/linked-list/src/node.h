class Node
{
public:
    Node();
    Node(int data);
    ~Node();
    void initialize(void);

    int data;
    Node* prev;
    Node* next;
};
