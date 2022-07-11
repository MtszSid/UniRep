using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zadanie_4
{
    internal class Program
    {
        static void Main(string[] args)
        {   
            Shape s = new Square() { Width = 10};
            Shape s2 = new Rectangle() { Width = 10, Height =5};

            AreaCalculator areaCalculator = new AreaCalculator();

            Console.WriteLine(areaCalculator.CalculateArea(s));
            Console.WriteLine(areaCalculator.CalculateArea(s2));

            Console.ReadLine();
        }
    }

    public abstract class Shape { }


    public class Rectangle : Shape
    {
        public virtual int Width { get; set; }
        public virtual int Height { get; set; }
    }

    public class Square : Shape
    {
        public virtual int Width
        { get; set; }

    }
    
    public class AreaCalculator
    {
        public int CalculateArea(Rectangle s)
        {
            return s.Width * s.Height;
        }

        public int CalculateArea(Square s)
        {
            return s.Width * s.Width;
        }

        public int CalculateArea(Shape s)
        {
            if(typeof(Square) == s.GetType())
            {
                return CalculateArea((Square)s);
            }
            else if(typeof(Rectangle) == s.GetType())
            {
                return CalculateArea((Rectangle)s);
            }
            throw new NotImplementedException();
        }
    }
    

}
