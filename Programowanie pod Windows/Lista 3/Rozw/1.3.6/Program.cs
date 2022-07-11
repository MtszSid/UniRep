using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _1._3._6
{
    class Program
    {
        class IPLog
        {
            public string Time { get; }
            public string IP { get; }
            public string Type { get; }
            public string Addr { get; }
            public string RetVal { get; }
            
            public IPLog(string t, string ip, string type, string a, string ret)
            {
                Time = t;
                IP = ip;
                Type = type;
                Addr = a;
                RetVal = ret;
            }
        }
        static void Main(string[] args)
        {
            System.IO.StreamReader file =
                new System.IO.StreamReader("file.txt");

            List<IPLog> L = new List<IPLog>();
            string line;


            while ((line = file.ReadLine()) != null)
            {
                try
                {
                    string[] S = line.Split(' ');
                    L.Add(new IPLog(S[0], S[1], S[2], S[3], S[4]));
                }
                catch (Exception e)
                {
                    continue;
                }
            }
            file.Close();

            var Enum =
                from
                l1 in L
                group l1 by l1.IP into g
                orderby -g.Count() 
                select new { g.Key, Count = g.Count() };

            Enum = Enum.Take(3);

            foreach (var i in Enum)
            {
                Console.WriteLine("{0} {1}", i.Key, i.Count);
            }
            Console.ReadLine();
        }
    }
}
