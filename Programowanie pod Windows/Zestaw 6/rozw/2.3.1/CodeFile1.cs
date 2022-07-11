using System;
using System.Globalization;

namespace ConsoleApp1
{
    class Complex : IFormattable
    {
        int real;
        int imaginary;

        public Complex(int r)
        {
            real = r;
            imaginary = 0;
        }
        public Complex(int r, int i)
        {
            real = r;
            imaginary = i;
        }
        public static Complex operator +(Complex a) => a;
        public static Complex operator -(Complex a) => new Complex(-a.real, -a.imaginary);

        public static Complex operator +(Complex a, Complex b)
            => new Complex(a.real + b.real, a.imaginary + b.imaginary);

        public static Complex operator -(Complex a, Complex b)
            => a + (-b);

        public static Complex operator *(Complex a, Complex b)
            => new Complex(a.real * b.real - a.imaginary * b.imaginary, a.real * b.imaginary + a.imaginary * b.real);

        public static Complex operator /(Complex a, Complex b)
        {
            int z = b.real * b.real + b.imaginary * b.imaginary;
            if (z == 0)
            {
                throw new DivideByZeroException();
            }
            return new Complex((a.real * b.real + a.imaginary * b.imaginary) / z, (a.imaginary * b.real + a.real * b.imaginary) / z);
        }

        override public string ToString()
        {
            return ToString("", CultureInfo.CurrentCulture);
        }
        public string ToString(string format)
        {
            return ToString(format, CultureInfo.CurrentCulture);
        }
        public string ToString(string format, IFormatProvider formatProvider)
        {
            if (String.IsNullOrEmpty(format)) format = "d";
            if (formatProvider == null) formatProvider = CultureInfo.CurrentCulture;
            switch (format.ToLowerInvariant())
            {
                case "d":
                    if (real == 0 && imaginary == 0)
                        return "0";
                    else if (real == 0)
                        return imaginary.ToString() + "i";
                    if (imaginary == 0)
                        return real.ToString();
                    else
                        return real.ToString() + " + " + imaginary.ToString() + "i";
                case "w":
                    return "[" + real.ToString() + ", " + imaginary.ToString() + "]";
                default:
                    throw new FormatException(String.Format("The {0} format string is not supported.", format));
            }

        }
    }
    class Program
    {
        static void Main(string[] args)
        {
            Complex z = new Complex(4, 3);
            Complex x = new Complex(4, -6);
            Complex y = new Complex(0, 3);
            Complex b = new Complex(2, 0);

            Console.WriteLine(String.Format("{0}", z));
            Console.WriteLine(String.Format("{0:d}", z));
            Console.WriteLine(String.Format("{0:w}", z));
            Console.WriteLine(String.Format("{0}", z - z));
            Console.WriteLine(String.Format("{0}", -x));
            Console.WriteLine(String.Format("{0}", z - x));
            Console.WriteLine(String.Format("{0}", x + b * y));
            Console.WriteLine(String.Format("{0}", x / b));
            
            Console.ReadLine();
        }
    }
}
