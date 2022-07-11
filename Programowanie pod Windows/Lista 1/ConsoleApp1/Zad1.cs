using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Zad1
    {
        static void Main(string[] args)
        {
            LinkedList<int> Rozw = new LinkedList<int>();
            int dziel, div, sum;
            for(int i = 1; i<=100000; i++)
            {
                dziel = i % 10;
                div = i;
                sum = 0;
                while(div > 0)
                {
                    div /= 10;
                    sum += dziel;
                    if(dziel == 0)
                    {
                        div = -1;
                        break;
                    }
                    else if(i % dziel != 0)
                    {
                        div = -1;
                        break;
                    }
                    dziel = div % 10;
                }

                if(div == 0 && i % sum == 0)
                {
                    Rozw.AddLast(i);
                }
            }
            foreach (int i in Rozw)
            {
                Console.Write("{0}, ", i);
            }
            
            Console.ReadLine();
        }
    }
}
