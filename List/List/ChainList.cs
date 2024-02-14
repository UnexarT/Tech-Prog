class ChainList
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

    Node head = null;
    int count = 0;

    public Node Find(int position)
    {
        if (position >= count || position <= 0) { return null; }
        int i = 0;
        Node p = head;
        while (p != null && i++ < position)
        {
            p = p.Next;
        }

        if (i == position) { return p; }
        else { return null; }
        
    }

    public void Add(int value)
    {
        if (head == null) { head = new Node(value, null); }
        else
        {

            Node lastNode = Find(count - 1);
            lastNode.Next = new Node(value,null);
            count++;

        }
    }

    public void Insert(int value, int position)
    {
        if (position < count && position > 0)
        {

            Node OldNode = Find(position);
            Node NewNode = new Node(value, OldNode);
            OldNode.Next = NewNode;

        }
        else 
        {

            if (position == 0) { head = new Node(value, head); }
            else { Add(value); }

        }
        count++;
    }

    public void Clean(int position) 
    {

        head = null;
        count = 0;

    }


}
    
