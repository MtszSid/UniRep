using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Zadane_2
{
    abstract class Shape
    {
        public int x { get; set; }
        public int y { get; set; }

        public abstract void Paint(PaintEventArgs e);
        public abstract bool InTheShape(int x, int y);
    }
}
