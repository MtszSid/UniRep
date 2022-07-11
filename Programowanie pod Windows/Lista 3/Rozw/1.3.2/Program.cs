using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;


namespace _3._2
{
    class Program
    {
        static void Main(string[] args)
        {
            System.IO.StreamReader file =
                new System.IO.StreamReader("file.txt");
            
            List<int> L = new List<int>();
            string line;
           
            
            while ((line = file.ReadLine()) != null)
            {
                try
                {
                    L.Add(Int32.Parse(line));
                }
                catch(Exception e)
                {
                    continue;
                }
            }
            file.Close();

            IEnumerable<int> Liczby1 =
                from liczba in L
                where liczba > 100
                orderby -liczba
                select liczba;


            foreach (var i in Liczby1) Console.WriteLine(i);


            IEnumerable<int> Liczby2 = L.Where(x => x > 100).OrderBy(x => -x);
            foreach (var i in Liczby2) Console.WriteLine(i);
            Console.ReadLine();
        }
    }
}
