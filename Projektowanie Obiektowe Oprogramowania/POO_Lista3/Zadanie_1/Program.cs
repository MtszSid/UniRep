using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zadanie_1
{
    internal class Program
    {
        
        static void Main(string[] args)
        {
            MeanCalculatorCreator creator = new MeanCalculatorCreator();
            IMeanCalculator  meanCalculator = creator.createMeanCalculator();

            Console.WriteLine(meanCalculator.GeometricMean(10.5, 15));
            Console.WriteLine(meanCalculator.ArithmeticMean(1.5, 15));
            Console.ReadLine();
        }
    }

    internal class MeanCalculatorCreator
    {
        /*
         Creator
         */
        public IMeanCalculator createMeanCalculator()
        {
            return new Calculator();
        }
    }

    internal interface IMeanCalculator
    {
        double GeometricMean(double x, double y);
        double ArithmeticMean(double x, double y);
    }

    internal class Calculator: IMeanCalculator
    {
        /*
         High Cohesion      -- wysokie sprzężenie metod
         Low Coupling       -- mała zależność do innych klas
         Information Expert -- ta klasa ma informacje koneczne do obliczenia pola kołą
         */

        public const double PI = 3.141;

        public double sqrt(double number)
        {
            return Math.Sqrt(number); //Powiedzmy, że tu jest jakaś implementacja algorytmu.
        }

        public double square(double number)
        {
            return number * number;
        }
        public double sum (double x, double y)
        {
            return x + y;
        }
        public double GeometricMean(double x, double y)
        {
            return sqrt(sum(square(x),  square(y)));
        }
        public double ArithmeticMean(double x, double y)
        {
            return sum(x, y) / 2;
        }
        public double AreaOfACircle(double radius)
        {
            return PI * square(radius);
        }
    }
}
