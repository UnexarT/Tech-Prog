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

            for (int i = count++; i > position; i--) { buffer[i + 1] = buffer[i]; }

            buffer[position] = value;

        } else
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
        if (count == 1 && position == count - 1) { Clean(); }
        else
        {
            if (position < count - 1 && position >= 0) 
            {
                for (int i = position; i < count; i++) { buffer[i] = buffer[i + 1]; }

                buffer[count--] = 0;

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
            if (position >= count || position < 0) { return 0; }
            else { return buffer[position]; }
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
}
