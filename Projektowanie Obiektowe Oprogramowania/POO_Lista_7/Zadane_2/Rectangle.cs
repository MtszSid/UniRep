using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Zadane_2
{
    class Rectangle : Shape
    {
        int _width, _height;

        public Rectangle(int x, int y, int width, int height)
        {
            this.x = x;
            this.y = y;
            _width = width;
            _height = height;
        }

        public override bool InTheShape(int x, int y)
        {
            return
                (x >= this.x && x <= this.x + _width ?
                    (y >= this.y && y <= this.y + _height ?
                        true :
                        false) :
                    false);
        }

        public override void Paint(PaintEventArgs e)
        {
            using (var pen = new Pen(Color.DarkOrchid, 3))
            {
                e.Graphics.DrawRectangle(pen, x, y, _width, _height);
            }
        }
    }
}
