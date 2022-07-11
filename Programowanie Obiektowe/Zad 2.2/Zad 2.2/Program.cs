//Mateusz Sidło Lista 2 Zadanie 4

using System;
using System.Collections.Generic;

namespace Zad_2._4
{
    class Program
    {
        static void Main(string[] args)
        {
            ListaLeniwa lista = new ListaLeniwa();
            Pierwsze lista1 = new Pierwsze();
            Console.WriteLine("size={0}", lista.Size());
            Console.WriteLine(lista.Element(5));
            Console.WriteLine("size={0}", lista.Size());
            Console.WriteLine(lista.Element(4));
            Console.WriteLine("size={0}", lista.Size());
            Console.WriteLine(lista.Element(10));
            Console.WriteLine(lista.Element(5));
            Console.WriteLine("size={0}", lista.Size());
            Console.WriteLine();
            Console.WriteLine(lista1.Element(10));
            for(int i=0; i <= 10; i++)
            {
                Console.WriteLine(lista1.Element(i));
            }
            Console.WriteLine("size={0}",lista.Size());
        }
    }

    public class ListaLeniwa
    {
        public List<int> elementy;
        public int liczba_elementow;
        private int elem;
        private readonly Random liczba_losowa;

        public ListaLeniwa()
        {
            elementy = new List<int>();
            liczba_elementow = 0;
            liczba_losowa = new Random();
        }

        public virtual int generator()
        {
            return liczba_losowa.Next();
        }

        public int Element(int pozycja)
        {
            while (liczba_elementow <= pozycja)
            {
                elem = generator();
                liczba_elementow++;
                elementy.Add(elem);
            }
            return elementy[pozycja];
        }

        public int Size()
        {
            return liczba_elementow;
        }

    }

    public class Pierwsze:ListaLeniwa
    {
        int el;
        const int a = 2;
        bool pierwsza;
        public Pierwsze()
        {
            elementy = new List<int>();
            liczba_elementow = 0;
        }

        public override int generator()
        {
            if (liczba_elementow == 0)
            {
                return a;
            }
            el = elementy[liczba_elementow - 1];
           
            while (true)
            {
                el++;
                pierwsza = true;
                foreach (int i in elementy)
                {
                    if (el % i == 0)
                    {
                        pierwsza = false;
                        break;
                    }
                }

                if (pierwsza)
                {
                    return el;
                }
            }
        }
    }

        
}
