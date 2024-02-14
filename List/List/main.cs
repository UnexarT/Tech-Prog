internal class Program
{
    
    static void Main(string[] args)
    {
        ArrayList mas = new ArrayList();
        mas.Add(1);
        mas.Add(2);
        mas.Add(1);
        mas.Add(2);
        mas.Add(1);
        mas.Add(2);
        mas[3] = 555;
        mas.Insert(9, 7);
        mas.Delete(1);
        mas.Delete(1);
        mas.Print();
        Console.WriteLine(mas.Count);
        
    }
}