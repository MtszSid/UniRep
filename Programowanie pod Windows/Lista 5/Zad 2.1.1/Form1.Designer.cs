namespace Zad_2._1._1
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.groupBoxUczelnia = new System.Windows.Forms.GroupBox();
            this.groupBoxRodzSt = new System.Windows.Forms.GroupBox();
            this.buttonAnuluj = new System.Windows.Forms.Button();
            this.buttonAkceptuj = new System.Windows.Forms.Button();
            this.richTextBoxNAzwa = new System.Windows.Forms.RichTextBox();
            this.labelNazwa = new System.Windows.Forms.Label();
            this.labelAdres = new System.Windows.Forms.Label();
            this.richTextBoxAdres = new System.Windows.Forms.RichTextBox();
            this.comboBox1 = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.checkBoxDzienne = new System.Windows.Forms.CheckBox();
            this.checkBoxUzuelniajace = new System.Windows.Forms.CheckBox();
            this.groupBoxUczelnia.SuspendLayout();
            this.groupBoxRodzSt.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBoxUczelnia
            // 
            this.groupBoxUczelnia.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.groupBoxUczelnia.Controls.Add(this.richTextBoxAdres);
            this.groupBoxUczelnia.Controls.Add(this.labelAdres);
            this.groupBoxUczelnia.Controls.Add(this.labelNazwa);
            this.groupBoxUczelnia.Controls.Add(this.richTextBoxNAzwa);
            this.groupBoxUczelnia.ForeColor = System.Drawing.SystemColors.ControlText;
            this.groupBoxUczelnia.Location = new System.Drawing.Point(12, 12);
            this.groupBoxUczelnia.Name = "groupBoxUczelnia";
            this.groupBoxUczelnia.Size = new System.Drawing.Size(412, 105);
            this.groupBoxUczelnia.TabIndex = 0;
            this.groupBoxUczelnia.TabStop = false;
            this.groupBoxUczelnia.Text = "Uczelnia";
            // 
            // groupBoxRodzSt
            // 
            this.groupBoxRodzSt.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.groupBoxRodzSt.Controls.Add(this.checkBoxUzuelniajace);
            this.groupBoxRodzSt.Controls.Add(this.checkBoxDzienne);
            this.groupBoxRodzSt.Controls.Add(this.label1);
            this.groupBoxRodzSt.Controls.Add(this.comboBox1);
            this.groupBoxRodzSt.ForeColor = System.Drawing.SystemColors.ControlText;
            this.groupBoxRodzSt.Location = new System.Drawing.Point(12, 132);
            this.groupBoxRodzSt.Name = "groupBoxRodzSt";
            this.groupBoxRodzSt.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.groupBoxRodzSt.Size = new System.Drawing.Size(412, 91);
            this.groupBoxRodzSt.TabIndex = 1;
            this.groupBoxRodzSt.TabStop = false;
            this.groupBoxRodzSt.Text = "Rodzaj studiow";
            // 
            // buttonAnuluj
            // 
            this.buttonAnuluj.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.buttonAnuluj.Location = new System.Drawing.Point(343, 229);
            this.buttonAnuluj.Name = "buttonAnuluj";
            this.buttonAnuluj.Size = new System.Drawing.Size(75, 23);
            this.buttonAnuluj.TabIndex = 2;
            this.buttonAnuluj.Text = "Anuluj";
            this.buttonAnuluj.UseVisualStyleBackColor = true;
            this.buttonAnuluj.Click += new System.EventHandler(this.buttonAnuluj_Click);
            // 
            // buttonAkceptuj
            // 
            this.buttonAkceptuj.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.buttonAkceptuj.Location = new System.Drawing.Point(18, 229);
            this.buttonAkceptuj.Name = "buttonAkceptuj";
            this.buttonAkceptuj.Size = new System.Drawing.Size(75, 23);
            this.buttonAkceptuj.TabIndex = 3;
            this.buttonAkceptuj.Text = "Akceptuj";
            this.buttonAkceptuj.UseVisualStyleBackColor = true;
            this.buttonAkceptuj.Click += new System.EventHandler(this.buttonAkceptuj_Click);
            // 
            // richTextBoxNAzwa
            // 
            this.richTextBoxNAzwa.Location = new System.Drawing.Point(84, 22);
            this.richTextBoxNAzwa.Multiline = false;
            this.richTextBoxNAzwa.Name = "richTextBoxNAzwa";
            this.richTextBoxNAzwa.Size = new System.Drawing.Size(322, 20);
            this.richTextBoxNAzwa.TabIndex = 0;
            this.richTextBoxNAzwa.Text = "";
            // 
            // labelNazwa
            // 
            this.labelNazwa.AutoSize = true;
            this.labelNazwa.Location = new System.Drawing.Point(7, 25);
            this.labelNazwa.Name = "labelNazwa";
            this.labelNazwa.Size = new System.Drawing.Size(43, 13);
            this.labelNazwa.TabIndex = 1;
            this.labelNazwa.Text = "Nazwa:";
            // 
            // labelAdres
            // 
            this.labelAdres.AutoSize = true;
            this.labelAdres.Location = new System.Drawing.Point(10, 53);
            this.labelAdres.Name = "labelAdres";
            this.labelAdres.Size = new System.Drawing.Size(37, 13);
            this.labelAdres.TabIndex = 2;
            this.labelAdres.Text = "Adres:";
            // 
            // richTextBoxAdres
            // 
            this.richTextBoxAdres.ForeColor = System.Drawing.SystemColors.WindowText;
            this.richTextBoxAdres.Location = new System.Drawing.Point(84, 53);
            this.richTextBoxAdres.Multiline = false;
            this.richTextBoxAdres.Name = "richTextBoxAdres";
            this.richTextBoxAdres.Size = new System.Drawing.Size(322, 20);
            this.richTextBoxAdres.TabIndex = 3;
            this.richTextBoxAdres.Text = "";
            // 
            // comboBox1
            // 
            this.comboBox1.FormattingEnabled = true;
            this.comboBox1.Items.AddRange(new object[] {
            "1,5-roczne",
            "2-letnie",
            "3-letnie",
            "3,5-letnie",
            "4-letnie",
            "4,5-letnie",
            "5-letnie"});
            this.comboBox1.Location = new System.Drawing.Point(84, 20);
            this.comboBox1.Name = "comboBox1";
            this.comboBox1.Size = new System.Drawing.Size(322, 21);
            this.comboBox1.TabIndex = 0;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(11, 24);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(59, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "Cykl nauki:";
            // 
            // checkBoxDzienne
            // 
            this.checkBoxDzienne.AutoSize = true;
            this.checkBoxDzienne.Location = new System.Drawing.Point(84, 60);
            this.checkBoxDzienne.Name = "checkBoxDzienne";
            this.checkBoxDzienne.Size = new System.Drawing.Size(65, 17);
            this.checkBoxDzienne.TabIndex = 2;
            this.checkBoxDzienne.Text = "Dzienne";
            this.checkBoxDzienne.UseVisualStyleBackColor = true;
            // 
            // checkBoxUzuelniajace
            // 
            this.checkBoxUzuelniajace.AutoSize = true;
            this.checkBoxUzuelniajace.Location = new System.Drawing.Point(220, 60);
            this.checkBoxUzuelniajace.Name = "checkBoxUzuelniajace";
            this.checkBoxUzuelniajace.Size = new System.Drawing.Size(95, 17);
            this.checkBoxUzuelniajace.TabIndex = 3;
            this.checkBoxUzuelniajace.Text = "Uzupełniające";
            this.checkBoxUzuelniajace.UseVisualStyleBackColor = true;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(436, 270);
            this.Controls.Add(this.buttonAkceptuj);
            this.Controls.Add(this.buttonAnuluj);
            this.Controls.Add(this.groupBoxRodzSt);
            this.Controls.Add(this.groupBoxUczelnia);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.MaximizeBox = false;
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Wybór uczelni";
            this.groupBoxUczelnia.ResumeLayout(false);
            this.groupBoxUczelnia.PerformLayout();
            this.groupBoxRodzSt.ResumeLayout(false);
            this.groupBoxRodzSt.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBoxUczelnia;
        private System.Windows.Forms.GroupBox groupBoxRodzSt;
        private System.Windows.Forms.Button buttonAnuluj;
        private System.Windows.Forms.Button buttonAkceptuj;
        private System.Windows.Forms.RichTextBox richTextBoxAdres;
        private System.Windows.Forms.Label labelAdres;
        private System.Windows.Forms.Label labelNazwa;
        private System.Windows.Forms.RichTextBox richTextBoxNAzwa;
        private System.Windows.Forms.CheckBox checkBoxUzuelniajace;
        private System.Windows.Forms.CheckBox checkBoxDzienne;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ComboBox comboBox1;
    }
}

