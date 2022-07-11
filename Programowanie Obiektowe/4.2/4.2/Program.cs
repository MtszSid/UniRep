
using System;
using System.Collections;
using System.Collections.Generic;

namespace _4._2
{
    class Program
    {
        static void Main(string[] args)
        {
            PrimeCollection pc = new PrimeCollection();
            foreach (int p in pc)
            {
                Console.WriteLine(p);
            }
        }
    }

    class PrimeCollection : IEnumerable<int>, IEnumerator<int>
    {
        int liczba_elementow;
        int obecny;
        List<int> pierwsze;
        int position = 0;

        public PrimeCollection()
        {
            liczba_elementow = 0;
            pierwsze = new List<int>();
            obecny = 2;

        }


        bool Wzakresie(int I)
        {
            return (I > 0);
        }

        int elem(int p)
        {

            if (p >= liczba_elementow)
            {
                uzupelnij_liste(p - liczba_elementow );
            }
            
            return pierwsze[p-1];
        }

        void uzupelnij_liste(int n)
        {
            bool pierwsza;
            while(n > 0) {
                if (obecny == 2)
                {
                    n--;
                    pierwsze.Add(obecny);
                    obecny++;
                    liczba_elementow++;
                    continue;
                }
                
                if (Wzakresie(obecny))
                {
                    pierwsza = true;
                    foreach (int I in pierwsze)
                    {
                        if (obecny % I == 0)
                        {
                            pierwsza = false;
                            break;
                        }
                    }
                    if (pierwsza)
                    {
                        n--;
                        pierwsze.Add(obecny);
                        liczba_elementow++;
                    }
                    obecny += 2;
                }
                else
                {
                    return;
                }
            }
        }


        public int Size()
        {
            return liczba_elementow;
        } 

        public IEnumerator<int> GetEnumerator()
        {
            return this;
        }

       

        IEnumerator IEnumerable.GetEnumerator()
        {
            return this;
        }

        

        public int Current
        {
            get
            {
                return elem(position);
            }
        }

        public bool MoveNext()
        {
            position++;
            return Wzakresie(position);
        }

        public void Reset()
        {
            position = 0;
        }

        public void Dispose()
        {

        }

        object IEnumerator.Current
        {
            get
            {
                return Current;
            }
        }

    }
}

