#pragma once
#include <iostream>
using namespace std;


class BaseList
{
protected:
    int count;
    virtual BaseList* EmptyClone() = 0;

public:
    int GetCount() { return count; }
    virtual void Add(int value) = 0;
    virtual void Insert(int value, int position) = 0;
    virtual void Delete(int position) = 0;
    virtual void Clean() = 0;
    virtual int& operator[](int position) = 0;

    void Print()
    {
        cout << "[";
        for (int i = 0; i < count; i++)
        {
            if (i == count - 1) cout << (*this)[i];
            else cout << (*this)[i] << ", ";
        }
        cout << "]\n";
    }

    void Assign(BaseList* source)
    {
        Clean();
        for (int i = 0; i < source->GetCount(); i++)
        {
            Add((*source)[i]);
        }
    }

    void AssignTo(BaseList* dest) { dest->Assign(this); }

    BaseList* Clone()
    {
        BaseList* cloneList = EmptyClone();
        cloneList->Assign(this);
        return cloneList;
    }

    virtual void Sort() // Сортировка вставками для ArrayList
    {
        for (int i = 1; i < count; i++)
        {
            int cur = (*this)[i];
            int j = i;
            while (j > 0 && cur < (*this)[j - 1])
            {
                (*this)[j] = (*this)[j - 1];
                j--;
            }
            (*this)[j] = cur;
        }
    }

    bool IsEqual(BaseList* list)
    {
        if (count != (*list).GetCount()) return false; 
        else
        {
            for (int i = 0; i < count; i++)
            {
                if ((*this)[i] != (*list)[i]) return false; 
            }
            return true;
        }
    }

    virtual ~BaseList() {};
};