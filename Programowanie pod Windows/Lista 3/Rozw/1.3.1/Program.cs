using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


static class StringMethodsExtensions
{
    
    public static bool IsPalindrome(this string S)
    {
        int i = 0;
        int j = S.Length - 1;
        while(i < j)
        {
            while (Char.IsWhiteSpace(S[i]) || Char.IsPunctuation(S[i]))
            {
                i++;
            }
            while (Char.IsWhiteSpace(S[j]) || Char.IsPunctuation(S[j]))
            {
                j--;
            }
            if(i <= j)
            {
                if(Char.ToLower(S[i]) != Char.ToLower(S[j]))
                    return false;
                else
                {
                    i++;
                    j--;
                }
            }
        }
        return true;
    }
}

namespace Lista_3
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("kajak".IsPalindrome());
            Console.WriteLine("Kobyła ma mały bok.".IsPalindrome());
            Console.ReadLine();
        }
    }
}
