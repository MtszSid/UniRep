using System;
using System.Reflection;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zad1_1_4
{
    public class Oznakowane : Attribute
    {

    }

    public class Foo
    {
        [Oznakowane]
        public int Bar()
        {
            return 1;
        }
        public int Qux()
        {
            return 2;
        }
    }

    class BadanieMetod
    {
        public static void Methody(object o)
        {
            MethodInfo[] Methods = o.GetType().GetMethods(BindingFlags.Public | BindingFlags.Instance);
            foreach (MethodInfo i in Methods){
                if(i.ReturnType == typeof(int) && i.GetParameters().Length == 0)
                {
                    if(i.GetCustomAttribute(typeof(Oznakowane)) as Oznakowane != null)
                    {
                        Console.WriteLine(i.Invoke(o, null));
                    }
                }
            }
        }
    }
    class Program
    {
        

        static void Main(string[] args)
        {
            Foo f = new Foo();
            BadanieMetod.Methody(f);

            Console.ReadLine();
        }

        
    }
}
