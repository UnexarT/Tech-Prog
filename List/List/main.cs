using System.Collections;

internal class Program
{
    
    static void Main(string[] args)
    {
        {
            ArrayList array = new ArrayList();
            ChainList chain = new ChainList();

            Random rnd = new Random();
            for (int i = 0; i < 10; i++)
            {
                int operation = rnd.Next(4);
                int item = rnd.Next(10);
                int pos = rnd.Next(10);
                switch (operation)
                {
                    case 1:
                        array.Add(item);
                        chain.Add(item);
                        break;
                    case 2:
                        array.Delete(pos);
                        chain.Delete(pos);
                        break;
                    case 3:
                        array.Insert(pos, item);
                        chain.Insert(pos, item);
                        break;
                    case 4:
                        array.Clean();
                        chain.Clean();
                        break;
                }
            }

            array.Print();
            Console.WriteLine();
            chain.Print();

            int count = 0;
            for (int i = 0; i < array.Count; i++)
            {
                if (array[i] != chain[i]) { count++; }
            }
            Console.WriteLine($"\nКол-во несоответсвий: {count}");
            Console.WriteLine($"\nМода chain: {chain.Moda}");
            Console.WriteLine($"\nМода array: {array.Moda}");
        }
    }
}