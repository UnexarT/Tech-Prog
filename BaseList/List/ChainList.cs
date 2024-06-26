using System.Xml;

class ChainList : BaseList 
{
    public class Node
    {
        public int Data { get; set; }

        public Node Next { get; set; }

        public Node(int data, Node next)
        {
            Data = data;
            Next = next;

        }
    }

    private Node head = null;
    //protected int count = 0;

    private Node Find(int position)
    {
        if (position >= count || position < 0) { return null; }
        int i = 0;
        Node p = head;
        while (p != null && i < position)
        {
            p = p.Next;
            i++;
        }

        if (i == position) { return p; }
        else { return null; }
        
    }

    public override void Add(int value)
    {
        if (head == null || count == 0) { head = new Node(value, null); }
        else
        {
            Find(count - 1).Next = new Node(value, null);
        }
        count++;
    }

    public override void Insert(int value, int position)
    {
        if (position < count && position > 0)
        {
            Node OldNode = Find(position - 1);
            Node NewNode = new Node(value, OldNode.Next);
            OldNode.Next = NewNode;
        }
        else 
        {
            if (position == 0) { head = new Node(value, head); }
            else { Add(value); count--; }
        }
        count++;
    }

    public override void Clean() 
    {

        head = null;
        count = 0;

    }

    public override void Delete(int position)
    {

        if (count == 1 && position == 0) { Clean(); }
        else
        {

            if (position < count && position >= 0)
            {
                if (position == 0) { head = head.Next; }
                else { Find(position - 1).Next = Find(position + 1); }
                count--;
            }
            
        }
    }

    /*public override int Count
    {
        get { return count; }
    }*/

    public override int this[int position]
    {
        get
        {
            if (position >= count || position < 0) { return 0; }
            else { return Find(position).Data; }
        }

        set
        {
            if (position < count && position >= 0) { Find(position).Data = value; }
        }
    }

    /*public void Print()
    {
        Console.Write("[");
        Node p = head;
        for (int i = 0; i < count; i++)
        {

            if (i == count - 1) { Console.Write(p.Data); }
            else { Console.Write($"{p.Data}, "); }
            p = p.Next;

        }
        Console.WriteLine("]");
    }*/

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
                int item = Find(i).Data;

                for (int j = 0; j < this.count; j++)
                    if (item == Find(j).Data) count++;


                if (count > maxCount)
                {
                    maxCount = count;
                    moda = item;
                }
            }
            return moda;
        }
    }

    protected override ChainList EmptyClone()
    {
        return new ChainList();
    }
    
    public override void Sort() // ���������� �������
    {
        Node p1 = head;
        Node p2 = head;
        for (int i = 0; p1 != null && i < count; i++)
        {
            for (int j = 0; p2 != null && j < count; j++)
            {
                if (p1.Data > p2.Data)
                {
                    int buffer = p2.Data;
                    p2.Data = p1.Data;
                    p1.Data = buffer;
                }
                p2 = p2.Next;
            }
            p1 = p1.Next;
        }
    }
}

