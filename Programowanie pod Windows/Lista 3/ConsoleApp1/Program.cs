using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Program
    {
        static int Fib(int i)
        {
            Func<int, int> fib = null;
            fib = x => x <= 2 ? 1 : fib(x - 1) + fib(x - 2);
            return fib(i);
        }

        static void Main(string[] args)
        {
            List<int> list = new List<int>() { 1, 2, 3, 4, 5 };
            foreach (var item in
            list.Select(i => )
                Console.WriteLine(item);
        }
    }
    }
}
