using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _2._3._2
{
    class Set<T>
    {
        Dictionary<T, short> _set;

        public Set()
        {
            _set = new Dictionary<T, short>();
        }
        public void Add(T elem)
        {
            if (_set.ContainsKey(elem) == false)
                _set.Add(elem, 0);
        }

        public void Delete(T elem)
        {
            _set.Remove(elem);
        }

        public bool Contains(T elem)
        {
            return _set.ContainsKey(elem);
        }

        public override string ToString()
        {
            string S = "";
            S += "{";
            foreach(var t in _set){
                S += t.Key;
                S += ", ";
            }
            S += "}";
            return S;
        }
    }
    class Program
    {
        static void Main(string[] args)
        {
            Set<int> T = new Set<int>();
            for(int i = 0; i < 50; i++)
            {
                T.Add(i);
            }
            for (int i = 0; i < 10; i++)
            {
                T.Add(i);
            }
            for (int i = 10; i < 20; i++)
            {
                T.Delete(i);
            }
            Console.WriteLine(T);
            Console.ReadLine();
        }
    }
}
