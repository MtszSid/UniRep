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
            
            List<string> L = new List<string>();
            string line;


            while ((line = file.ReadLine()) != null)
            {
                try
                {
                    if(string.IsNullOrEmpty(line) == false)
                    {
                        L.Add(line);
                    }
                }
                catch (Exception e)
                {
                    continue;
                }
            }
            file.Close();

            IEnumerable<char> Litery =
                from nazwisko in L
                group nazwisko by nazwisko[0] into g
                orderby g.Key
                select g.Key;


            foreach (var i in Litery) Console.WriteLine(i);

            Console.ReadLine();
        }
    }
}
