using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2._1._2
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            CopyWithProgress();
        }

        private void CopyWithProgress()
        {
            pBar1.Visible = true;
            pBar1.Minimum = 1;
            pBar1.Maximum = 10;
            pBar1.Value = 1;
            pBar1.Step = 1;

            
            for (int x = 1; x <= 10; x++)
            {
                
                pBar1.PerformStep();
                
            }
        }
    }
}
