using System;

namespace Zad_2
{
    class Grid
    {
        int[][] grid;
        public Grid(int x, int y)
        {
            grid = new int[x][];
            for(int i = 0; i < x; i++)
            {
                grid[i] = new int[y];
            }
        }
        
        public int[] this[int x]
        {
            get { return grid[x-1]; }
        }

        
        public int this[int x, int y]
        {
            get { return grid[x-1][y-1]; }
            set { grid[x-1][y-1] = value; }
        }
    }
    class Program
    {
        
        static void Main(string[] args)
        {
            Grid grid = new Grid(4, 4);
            for (int i = 1; i<=4; i++)
            {
                for(int j = 1; j <= 4; j++)
                {
                    grid[i, j] = i + j;
                }
            }
            grid[2, 2] = 5;     
            int elem = grid[1, 4]; 
            Console.WriteLine(grid[2,2]);
            Console.WriteLine(elem);
            Console.WriteLine(grid[4][3]);
        }
    }
}
