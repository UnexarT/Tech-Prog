#include "BaseList.cpp"

class ArrayList : public BaseList
{
protected:
    ArrayList* EmptyClone() override { return new ArrayList(); }

private:
    
	int* buffer = nullptr;
    int bufferSize = 1;

    void Expand()
    {
        if (count == 0) this->buffer = new int[bufferSize];
        else
        {
            if (count == bufferSize)
            {
                bufferSize *= 2;
                int* buffer = new int[bufferSize];
                for (int i = 0; i < count; i++) buffer[i] = this->buffer[i]; 
                delete[] this->buffer;
                this->buffer = buffer;
            }
        }
    }

    void Compression()
    {
        if (count <= bufferSize / 2)
        {
            bufferSize /= 2;
            int* buffer = new int[bufferSize];
            for (int i = 0; i < count; i++) buffer[i] = this->buffer[i]; 
            delete[] this->buffer;
            this->buffer = buffer;
        }
    }

public:

    void Add(int value) override
    {
        Expand();
        buffer[count++] = value;
    }

    void Insert(int value, int position) override
    {
        if (position < count && position >= 0)
        {
            Expand();
            for (int i = count - 1; i >= position; i--) buffer[i + 1] = buffer[i];
            buffer[position] = value;
            count++;
        }
        else Add(value);
    }

    void Clean() override
    {
        buffer = nullptr;
        count = 0;
        bufferSize = 1;
    }

    void Delete(int position) override
    {
        if (count == 1 && position == 0) Clean();
        else
        {
            if (position < count && position >= 0)
            {
                for (int i = position; i < count - 1; i++) buffer[i] = buffer[i + 1]; 
                buffer[count - 1] = 0;
                count--;
                Compression();
            }
        }
    }

    int& operator[](int position) override
    {
        int null = 0;
        if (position >= count || position < 0) return null; 
        else return buffer[position]; 
    }

    ~ArrayList() override
    { 
        delete[] buffer;
        cout << "Array помер\n"; 
    }
};


