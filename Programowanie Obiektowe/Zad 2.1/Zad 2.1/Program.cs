using System;
using System.Text;

namespace Zad_2._1
{
    class Program
    {
        static void Main()
        {
            RandomStream rand = new RandomStream();
            IntStream obj = new IntStream();
            PrimeStream prime = new PrimeStream();
            RandomWordStream slowo = new RandomWordStream();

            for (int i = 0; i < 10; i++)
            {
                Console.WriteLine(obj.Next());
            }

            for (int i = 0; i < 10; i++)
            {
                Console.WriteLine(prime.Next());
            }

            Console.WriteLine(prime.Eos());
            prime.Reset();

            for (int i = 0; i < 5; i++)
            {
                Console.WriteLine(rand.Next());
            }

            for(int i = 0; i < 10; i++)
            {
                Console.WriteLine(slowo.Next());
            }

        }
    }


    public class IntStream
    {
        public int aktualny;

        public IntStream()
        {
            aktualny = 0;
        }

        public int Next()
        {
            if (this.Eos())
            {
                Console.WriteLine("Przekroczono zakres");
                return 0;
            }
            else
            {
                return ++aktualny;
            }
        }

        public void Reset()
        {
            aktualny = 0;
        }

        public bool Eos()
        {
            if (aktualny >= Int32.MaxValue)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public class RandomStream : IntStream
    {
        new private readonly Random rand;
        public RandomStream()
        {
            rand = new Random();
            aktualny = rand.Next();
        }

        new public int Next()
        {
            aktualny = rand.Next();
            return aktualny;
        }

        new public bool Eos()
        {
            return false;
        }
        


    }

    public class PrimeStream : IntStream
    {
        int pierwsza,i;

        public PrimeStream()
        {
            aktualny = 2;
        }

        new public int Next()
        {
            if (this.Eos()) 
            {
                Console.WriteLine("Przekroczono zakres");
                return 0;
            }
            else
            {
                pierwsza = aktualny;
                aktualny++;
                i = 2;
                while (i * i <= aktualny)
                {
                    if (this.Eos())
                    {
                        Console.WriteLine("Przekroczono zakres");
                        return 0;
                    }
                    if (aktualny % i == 0)
                    {
                        aktualny++;
                        i = 2;
                        continue;
                    }
                    i++;
                }
            }
        
            return pierwsza;
        }

        new public void Reset()
        {
            aktualny = 2;
        }


    }

    public class RandomWordStream
    {
        PrimeStream prime = new PrimeStream();
        RandomStream rand = new RandomStream();
        StringBuilder str_builder = new StringBuilder();
        private int  pierwsza, losowa;
        char litera;
        


        public RandomWordStream()
        {
            pierwsza = prime.Next();
        }

        public string Next()
        {
            str_builder.Clear();
            for (int i=0; i<pierwsza; i++)
            {
                losowa = (rand.Next()) % 26 + 65;
                litera = Convert.ToChar(losowa);
                str_builder.Append(litera);
            }
            pierwsza = prime.Next();
            return str_builder.ToString();
        }

        
    }

}
