abstract class BaseList
{
    protected int count;

    public int Count { get { return count; } }

    public abstract void Add(int value);

    public abstract void Insert(int position, int value);

    public abstract void Delete(int position);

    public abstract void Clean();

    public abstract int this[int position] { get; set; }

    public void Print()
    {
        Console.Write("[");
        for (int i = 0; i < count; i++)
        {
            if (i == Count - 1) 
            {
                Console.Write(this[i]); 
            }
            else 
            { 
                Console.Write($"{this[i]}, "); 
            }
        }
        Console.WriteLine("]");
    }

    public void Assign(BaseList source)
    {
        Clean();
        for (int i = 0; i < source.Count; i++)
        {
            Add(source[i]);
        }
    }

    public void AssignTo(BaseList dest)
    {
        dest.Assign(this);
    }

    public BaseList Clone()
    {
        BaseList cloneList = EmptyClone(); // Зачем?
        cloneList.Assign(this);
        return cloneList;
    }

    public virtual void Sort() // Сортировка вставками для ArrayList
    {
        for (int i = 1; i < count; i++)
        {
            int cur = this[i];
            int j = i;
            while (j > 0 && cur < this[j - 1])
            {
                this[j] = this[j - 1];
                j--;
            }
            this[j] = cur;
        }
    }

    public bool IsEqual(BaseList list)
    {
        if (count != list.Count) { return false; }
        else  
        {
            for (int i = 0; i < count; i++)
            {
                if (this[i] != list[i]) { return false; }
            }
            return true;
        }
    }

    protected abstract BaseList EmptyClone();
}
