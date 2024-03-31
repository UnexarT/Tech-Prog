using System.Collections;

class Program
{
    static void Main(string[] args)
    {
        BaseList array = new ArrayList();
        BaseList chain = new ChainList();

        Random rnd = new Random();
        for (int i = 0; i < 10000; i++)
        {
            int operation = rnd.Next(3);
            int item1 = rnd.Next(50);
            int item2 = item1;
            int pos = rnd.Next(50);
            switch (operation)
            {
                case 1:
                    array.Add(item1);
                    chain.Add(item2);
                    break;
                case 2:
                    array.Delete(pos);
                    chain.Delete(pos);
                    break;
                case 3:
                    array.Insert(pos, item1);
                    chain.Insert(pos, item2);
                    break;
                    /*case 4:
                        array.Clean();
                        chain.Clean();
                        break;
                    case 5:
                        array.Sort();
                        chain.Sort();
                        break;*/
            }
        }

        Console.Write("Изначальный список: ");
        array.Print();

        // Клонирование
        BaseList arrayClone = array.Clone();
        Console.Write("Клонированный список: ");
        arrayClone.Print();
        if (arrayClone.IsEqual(array))
        {
            Console.WriteLine("Клонированние: идентичны");
        }
        else
        {
            Console.WriteLine("Клонированние: НЕидентичны");
        }

        // Сортировка
        arrayClone.Sort();

        // Упорядоченность
        bool flag = false;
        for (int i = 0; i < arrayClone.Count - 1; i++)
        {
            if (arrayClone[i] > arrayClone[i + 1])
            {
                flag = true;
                break;
            }
        }

        if (flag)
        {
            Console.WriteLine("Сорировка: НЕупорядоченна");
        }
        else
        {
            Console.WriteLine("Сорировка: упорядоченна");
        }


        // Проверка изменения количества символов после сортировки
        if (array.Count == arrayClone.Count)
        {
            Console.WriteLine("Count: НЕ изменилось");
        }
        else
        {
            Console.WriteLine("Count: изменилось");
        }

        // Целостность значений после сортировки
        int k = 0;
        int j = 0;
        while (k < array.Count)
        {
            while (j < arrayClone.Count)
            {
                if (array[k] == arrayClone[j])
                {
                    arrayClone.Delete(j);
                    j = 0;
                    break;
                }
                j++;
            }
            k++;
        }
        if (arrayClone.Count == 0)
        {
            Console.WriteLine("Целостность: НЕ изменилось");
        }
        else
        {
            Console.WriteLine("Целостность: изменилось");
        }
        Console.ReadLine();

       
    }
}