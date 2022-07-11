using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _1._3._7
{
    class Program
    {
        static void Main(string[] args)
        {
            var item = new { Field1 = "The value", Field2 = 5 };

            var T = new[] { item }.ToList();
            T.Add(new { Field1 = "The", Field2 = 10 });
            T.Add(new { Field1 = "This", Field2 = 20 });
            T.Add(new { Field1 = "That", Field2 = 30 });

            T.ForEach(i => Console.WriteLine("{0}, {1}", i.Field1, i.Field2));
            
            Console.ReadLine();
        }
    }
}
