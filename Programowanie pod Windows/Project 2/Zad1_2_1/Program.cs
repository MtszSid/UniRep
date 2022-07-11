using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zad1_2_1
{
    class Program
    {
        static void Main(string[] args)
        {
            object s;
            ArrayList T = new ArrayList();
            List<string> L = new List<string>();
            Hashtable HT = new Hashtable();
            Dictionary<int, string> DC = new Dictionary<int, string>();


            int LiczbaProb = 10000;
            DateTime Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                T.Add("P" + proba.ToString());
            }
            DateTime End = DateTime.Now;
            TimeSpan Czas = Start - End;
            Console.WriteLine(Czas);

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                L.Add("P" + proba.ToString());
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);
            Console.WriteLine();

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                s = T[proba];
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                s = L[proba];
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);
            Console.WriteLine();

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                T.RemoveAt(0);
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                L.RemoveAt(0);
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);
            Console.WriteLine();

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                HT.Add(proba, "P" + proba.ToString());
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                DC.Add(proba, "P" + proba.ToString());
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);
            Console.WriteLine();

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                s = HT[proba];
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                s = DC[proba];
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);
            Console.WriteLine();

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                HT.Remove(proba);
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                DC.Remove(proba);
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);
            Console.WriteLine();


            Console.ReadLine();
        }
    }
}
