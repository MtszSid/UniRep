using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zad1_2_3
{
    class Program
    {
        static void Main(string[] args)
        {
            List<int> L = new List<int>() { 1, 2, 3, 6, 7, 9, 2, 15, 19, 16, 17, 41 };
            List<string>L1 = L.ConvertAll<string>(x => "n" + (x + 2).ToString());
            L1.ForEach(x => Console.WriteLine(x));
            List<int> L2 = L.FindAll(x => x % 2 == 0);
            L2.ForEach(x => Console.WriteLine(x));
            Console.WriteLine();
            L.ForEach(x => Console.WriteLine(x));
            L.RemoveAll(x => x % 3 == 0);
            L.ForEach(x => Console.WriteLine(x));

            L.Sort(delegate (int x, int y) {
                if (x % 7 == y % 7)
                    return 0;
                if (x % 7 > y % 7)
                    return 1;
                else
                    return -1;
            });
            L.ForEach(x => Console.WriteLine(x));

            Console.ReadLine();

        }
    }
}
