#include "BaseList.cpp"

class ChainList : public BaseList
{
protected:
    ChainList* EmptyClone() override
    {
        return new ChainList();
    }

public:
    class Node
    {
    /*private:
        int Data;
        Node* Next;*/

    public:
        int Data; // private делает невозможным нормальную перегрузку []
        Node* Next;

        void SetData(int Data) { this->Data = Data; }
        int GetData() { return Data; }

        void SetNext(Node* Next){ this->Next = Next; }
        Node* GetNext() { return Next; }

        Node(int Data, Node* Next)
        {
            SetData(Data);
            SetNext(Next);
        }
    };

    void Add(int value) override
    {
        if (head == nullptr || count == 0) head = new Node(value, nullptr); 
        else Find(count - 1)->SetNext(new Node(value, nullptr));
        count++;
    }

    void Insert(int value, int position) override
    {
        if (position < count && position > 0)
        {
            Node* OldNode = Find(position - 1);
            Node* NewNode = new Node(value, OldNode->GetNext());
            OldNode->SetNext(NewNode);
        }
        else
        {
            if (position == 0) head = new Node(value, head); 
            else 
            {
                Add(value); 
                count--; 
            }
        }
        count++;
    }

    void Clean() override
    {
        head = nullptr;
        count = 0;
    }

    void Delete(int position) override
    {

        if (count == 1 && position == 0) Clean(); 
        else
        {
            if (position < count && position >= 0)
            {
                if (position == 0) head = head->GetNext(); 
                else Find(position - 1)->SetNext(Find(position + 1)); 
                count--;
            }
        }
    }

    int& operator[](int position) override
    {
        int null = 0;
        if (position >= count || position < 0)  return null;
        else return Find(position)->Data;
    }

    void Sort() override // Сортировка пузырьком
    {
        Node* p1 = head;
        Node* p2 = p1;
        for (int i = 0; p1 != nullptr && i < count; i++)
        {
            for (int j = 0; p2 != nullptr && j < count; j++)
            {
                if (p1->GetData() > p2->GetData())
                {
                    int buffer = p2->GetData();
                    p2->SetData(p1->GetData());
                    p1->SetData(buffer);
                }
                p2 = p2->GetNext();
            }
            p1 = p1->GetNext();
            p2 = p1;
        }
    }

    ~ChainList() override
    {
        Node* p = head;
        while (p != nullptr)
        {
            head = head->GetNext();
            delete[] p;
            p = head;
        }
        cout << "Chain помер\n";
    }

private:
	Node* head = nullptr;
    Node* Find(int position)
    {
        if (position >= count || position < 0)  return nullptr;
        int i = 0;
        Node* p = head;
        while (p != nullptr && i < position)
        {
            p = p->GetNext();
            i++;
        }
        if (i == position) return p; 
        else return nullptr; 
    }
};