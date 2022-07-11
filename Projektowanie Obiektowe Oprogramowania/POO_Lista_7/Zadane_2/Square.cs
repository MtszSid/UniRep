using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Zadane_2
{
    class Square : Shape
    {
        int _width;

        public Square(int x, int y, int width)
        {
            this.x = x;
            this.y = y;
            _width = width;
        }

        public override bool InTheShape(int x, int y)
        {
            return 
                ( x >= this.x && x <= this.x + _width ? 
                    ( y >= this.y && y <= this.y + _width ? 
                        true :
                        false ): 
                    false);
        }

        public override void Paint(PaintEventArgs e)
        {
            using (var pen = new Pen(Color.Pink, 3))
            {
                e.Graphics.DrawRectangle(pen, x, y, _width, _width);
            }
        }
    }
}
