using System;
using Lista;

namespace Zad_3._1
{
    class Program
    {
        static void Main(string[] args)
        {
            Lista<int> L = new Lista<int> ();
            Console.WriteLine(L.Pusta());
            L.Add_begining(3);
            Console.WriteLine(L.Pusta());
            Console.WriteLine(L.Pop_end());
            Console.WriteLine(L.Pusta());
            L.Add_begining(1);
            L.Add_end(2);
            L.Add_begining(0);
            Console.WriteLine(L.Pop_end());
            Console.WriteLine(L.Pop_begining());
            Console.WriteLine(L.Pop_end());
            Console.WriteLine(L.Pusta());
        }
    }
}
