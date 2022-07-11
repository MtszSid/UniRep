using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;


namespace _2._1._3
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            //this.SetStyle(ControlStyles.AllPaintingInWmPaint | ControlStyles.UserPaint | ControlStyles.OptimizedDoubleBuffer, true);
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            this.Invalidate();
            
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            e.Graphics.Clear(SystemColors.Control);

            e.Graphics.DrawEllipse(new Pen(Color.DimGray, 5), 50, 50, 200, 200);
            DateTime now = DateTime.Now;
            int sec = now.Second;
            int minutes = now.Minute;
            int hours = now.Hour % 12;
            float hx = (float)Math.Cos((hours * 30 - 90 + (6 * minutes) / 360) * Math.PI / 180) * 50 + 150;
            float hy = (float)Math.Sin((hours * 30 - 90 + (6 * minutes) / 360) * Math.PI / 180) * 50 + 150;
            e.Graphics.DrawLine(new Pen(Color.Black, 5), 150, 150, hx, hy);
            float minx = (float)Math.Cos((minutes * 6 - 90) * Math.PI / 180) * 70 + 150;
            float miny = (float)Math.Sin((minutes * 6 - 90) * Math.PI / 180) * 70 + 150;
            e.Graphics.DrawLine(new Pen(Color.Black, 5), 150, 150, minx, miny);
            float secx = (float)Math.Cos((sec * 6 - 90) * Math.PI / 180) * 80 + 150;
            float secy = (float)Math.Sin((sec * 6 - 90) *Math.PI / 180) * 80 + 150;
            e.Graphics.DrawLine(Pens.Red, 150, 150, secx, secy);
           
        }
    }
}
