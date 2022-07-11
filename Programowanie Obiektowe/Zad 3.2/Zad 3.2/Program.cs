using System;
using NTon;

namespace Zad_3._2
{
    class Program
    {
        static void Main(string[] args)
        {
            TimeNTon a = TimeNTon.Instance();
            TimeNTon b = TimeNTon.Instance();
            TimeNTon c = TimeNTon.Instance();
            TimeNTon d = TimeNTon.Instance();
            TimeNTon e = TimeNTon.Instance();
            TimeNTon f = TimeNTon.Instance();

            Console.WriteLine(a.Info());
            Console.WriteLine(b.Info());
            Console.WriteLine(c.Info());
            Console.WriteLine(d.Info());
            Console.WriteLine(e.Info());
            Console.WriteLine(f.Info());

        }
    }
}
