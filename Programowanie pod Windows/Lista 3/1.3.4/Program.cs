using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace _1._3._4
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                DirectoryInfo di = new DirectoryInfo(@"C:\Program Files (x86)\Microsoft SDKs\Windows\v10.0A\bin\NETFX 4.8 Tools");
                List <FileInfo> dirs = new  List<FileInfo> (di.GetFiles());
                List<long> lengths = dirs.ConvertAll(x => x.Length);
                var T = lengths.Aggregate(((a, b) => a + b));
                Console.WriteLine(T);
            }
            catch (Exception e)
            {
                Console.WriteLine("The process failed: {0}", e.ToString());
            }
            Console.ReadKey();
        }
    }
}
