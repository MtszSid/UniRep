using System;
using System.Reflection;

namespace Zad1_1_3
{
    class Person
    {
        private string Name;
        public string Surname;
        public Person(string name, string surname)
        {
            Name = name;
            Surname = surname;
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            int lin = 10000;
            Person P = new Person("Name0", "Surname0");
            FieldInfo f;
            
            for (int i =1; i<=100; i++)
            {
                P.Surname = "Surname" + i.ToString();
            }

            DateTime Start = DateTime.Now;
            for (int i = 1; i <= lin; i++)
            {
                P.Surname = "Surname" + i.ToString();
            }
            DateTime End = DateTime.Now;
            TimeSpan Czas = Start - End;
            Console.WriteLine(Czas);

            f = (typeof(Person)).GetField("Name", BindingFlags.NonPublic | BindingFlags.Instance);
            Start = DateTime.Now;

            for (int i = 1; i <= 100; i++)
            {
                f.SetValue(P, "Name" + i.ToString());
            }
            for (int i = 1; i <= lin; i++)
            {
                f.SetValue(P,"Name" + i.ToString());
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);

            Console.ReadLine();
        }
    }
}