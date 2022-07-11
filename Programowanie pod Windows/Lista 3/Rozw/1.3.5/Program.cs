using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _1._3._5
{
    class Person
    {
        public string Name { get; }
        public string Surname { get; }
        public string PESEL { get; }

        public Person (string n, string s, string p)
        {
            Name = n;
            Surname = s;
            PESEL = p;
        }
    }

    class Account
    {
        public string PESEL { get; }
        public string Nr { get; }

        public Account(string p, string nr)
        {
            PESEL = p;
            Nr = nr;
        }
    }
    class Program
    {
        static void Main(string[] args)
        {
            System.IO.StreamReader people =
               new System.IO.StreamReader("People.txt");

            List<Person> L1 = new List<Person>();
            List<Account> L2 = new List<Account>();
            string line;


            while ((line = people.ReadLine()) != null)
            {
                try
                {
                    if (string.IsNullOrEmpty(line) == false)
                    {
                        string[] S = line.Split(' ');
                        L1.Add(new Person(S[0], S[1], S[2]));

                    }
                }
                catch (Exception e)
                {
                    continue;
                }
            }
            people.Close();

            System.IO.StreamReader accounts =
               new System.IO.StreamReader("Accounts.txt");


            while ((line = accounts.ReadLine()) != null)
            {
                try
                {
                    if (string.IsNullOrEmpty(line) == false)
                    {
                        string[] S = line.Split(' ');
                        L2.Add(new Account(S[0], S[1]));

                    }
                }
                catch (Exception e)
                {
                    continue;
                }
            }
            accounts.Close();

            var Dane =
                from
                l1 in L1
                join l2 in L2 on l1.PESEL equals l2.PESEL
                select new { l1.Name, l1.Surname, l1.PESEL, l2.Nr };

            foreach(var i in Dane)
            {
                Console.WriteLine(i);
                
            }
            Console.ReadLine();
        }
    }
}
