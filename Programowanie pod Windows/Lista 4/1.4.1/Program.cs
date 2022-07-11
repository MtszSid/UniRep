using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _1._4._1
{
    public static class Bar
    {
        public static int Foo(int x, int y)
        {
            return x + y;
        }
        public static dynamic Foo2(dynamic x, dynamic y)
        {
            return x + y;
        }

    }
    class Program
    {
        static void Main(string[] args)
        {
            int LiczbaProb = 1000;
            DateTime Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                Bar.Foo(proba, proba);
            }
            DateTime End = DateTime.Now;
            TimeSpan Czas = Start - End;
            Console.WriteLine(Czas);

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                Bar.Foo2(proba, proba);
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);

            Console.ReadLine();
        }
    }
}
