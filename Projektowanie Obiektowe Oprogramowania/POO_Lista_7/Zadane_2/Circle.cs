using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Zadane_2
{
    class Circle: Shape
    {
        int  _diameter;

        public Circle(int x, int y, int radius)
        {
            this.x = x;
            this.y = y;
            _diameter = 2 * radius;
        }

        public override bool InTheShape(int x, int y)
        {
            return
                Math.Sqrt(Math.Pow(this.x + _diameter / 2 - x, 2) + Math.Pow(this.y + _diameter / 2 - y, 2)) <= _diameter / 2;
        }

        public override void Paint(PaintEventArgs e)
        {
            using (var pen = new Pen(Color.DarkOrange, 3))
            {
                e.Graphics.DrawEllipse(pen, x, y, _diameter, _diameter);
            }
        }
    }
}
