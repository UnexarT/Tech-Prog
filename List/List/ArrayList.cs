class ArrayList
{

    int[] buffer = null;
    int count = 0;

    void Expand()
    {
        if (count == 0) { this.buffer = new int[1]; }
        else
        {
            if (count == this.buffer.Length)
            {
                int[] buffer = new int[this.buffer.Length * 2];

                for (int i = 0; i < count; i++) { buffer[i] = this.buffer[i]; }

                this.buffer = buffer;
            }
        }
    }

    void Compression()
    {
        if (count <= buffer.Length / 2)
        {
            int[] buffer = new int[this.buffer.Length / 2];

            for (int i = 0; i < count; i++) { buffer[i] = this.buffer[i]; }

            this.buffer = buffer;
        }
    }

    public void Add(int value)
    {
        Expand();
        buffer[count++] = value;
    }

    public void Insert(int value, int position)
    {
        if (position < count && position >= 0)
        {
            Expand();

            for (int i = count - 1; i >= position; i--)
            { buffer[i + 1] = buffer[i]; }

            buffer[position] = value;
            count++;

        }
        else
        {
            Add(value);
        }
    }

    public void Clean()
    {
        buffer = null;
        count = 0;
    }

    public void Delete(int position)
    {
        if (count == 1 && position == 0) { Clean(); }
        else
        {
            if (position < count && position >= 0)
            {
                for (int i = position; i < count - 1; i++) { buffer[i] = buffer[i + 1]; }

                buffer[count-- - 1] = 0;

                Compression();
            }
        }
    }

    public int Count
    {
        get { return count; }
    }

    public int this[int position]
    {
        get
        {
            if (position >= count || position < 0)
                return 0;
            else
                return buffer[position];
        }

        set
        {
            if (position < count && position >= 0) { buffer[position] = value; }
        }
    }

    public void Print()
    {
        Console.Write("[");
        for (int i = 0; i < count; i++)
        {

            if (i == count - 1) { Console.Write(buffer[i]); }
            else { Console.Write($"{buffer[i]}, "); }

        }
        Console.WriteLine("]");
    }

    public int Moda
    {
        get
        {
            if (count == 0) return -1;
            int maxCount = 0;
            int moda = 0;
            for (int i = 0; i < this.count; i++)
            {
                int count = 0;

                for (int j = 0; j < this.count; j++)
                    if (buffer[i] == buffer[j]) count++;


                if (count > maxCount)
                {
                    maxCount = count;
                    moda = buffer[i];
                }
            }
            return moda;
        }
    }
}
