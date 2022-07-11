using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Zad_2._1._1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void buttonAkceptuj_Click(object sender, EventArgs e)
        {
            MessageBox.Show(this.richTextBoxNAzwa.Text + "\n" +
                this.richTextBoxAdres.Text + "\n" +
                this.comboBox1.Text + "\n" +
                (this.checkBoxDzienne.Checked ? "Dzienne\n" : "") +
                (this.checkBoxUzuelniajace.Checked ? "Uzupełniające\n" : ""));
        }

        private void buttonAnuluj_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
