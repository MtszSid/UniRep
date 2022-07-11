// See https://aka.ms/new-console-template for more information

using System.Collections;

namespace Exercise_4;


class Program
{
      /* this is the Comparison<int> to be converted */
      static int IntComparer( int x, int y )
      {
          return x.CompareTo( y );
      }
  
      static void Main( string[] args )
      {
          ArrayList a = new ArrayList() { 1, 5, 3, 3, 2, 4, 3 };
          
          a.Sort( new ComparerAdapter<int>(IntComparer));

          foreach (var i in a)
          {
              Console.WriteLine(i);
          }
          
          Console.ReadLine();
      }
}
  