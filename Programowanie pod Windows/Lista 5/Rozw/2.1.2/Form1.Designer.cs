namespace _2._1._2
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
            System.Windows.Forms.TreeNode treeNode1 = new System.Windows.Forms.TreeNode("bar1");
            System.Windows.Forms.TreeNode treeNode2 = new System.Windows.Forms.TreeNode("bar2");
            System.Windows.Forms.TreeNode treeNode3 = new System.Windows.Forms.TreeNode("bar3");
            System.Windows.Forms.TreeNode treeNode4 = new System.Windows.Forms.TreeNode("bar4");
            System.Windows.Forms.TreeNode treeNode5 = new System.Windows.Forms.TreeNode("foo1", new System.Windows.Forms.TreeNode[] {
            treeNode1,
            treeNode2,
            treeNode3,
            treeNode4});
            System.Windows.Forms.TreeNode treeNode6 = new System.Windows.Forms.TreeNode("Root", new System.Windows.Forms.TreeNode[] {
            treeNode5});
            System.Windows.Forms.TreeNode treeNode7 = new System.Windows.Forms.TreeNode("bar5");
            System.Windows.Forms.TreeNode treeNode8 = new System.Windows.Forms.TreeNode("bar6");
            System.Windows.Forms.TreeNode treeNode9 = new System.Windows.Forms.TreeNode("foo2", new System.Windows.Forms.TreeNode[] {
            treeNode7,
            treeNode8});
            System.Windows.Forms.TreeNode treeNode10 = new System.Windows.Forms.TreeNode("bar7");
            System.Windows.Forms.TreeNode treeNode11 = new System.Windows.Forms.TreeNode("foo3", new System.Windows.Forms.TreeNode[] {
            treeNode10});
            System.Windows.Forms.TreeNode treeNode12 = new System.Windows.Forms.TreeNode("Root2", new System.Windows.Forms.TreeNode[] {
            treeNode9,
            treeNode11});
            System.Windows.Forms.ListViewItem listViewItem1 = new System.Windows.Forms.ListViewItem(new string[] {
            "A",
            "A",
            "B"}, -1);
            System.Windows.Forms.ListViewItem listViewItem2 = new System.Windows.Forms.ListViewItem(new string[] {
            "B",
            "B",
            "C"}, -1);
            System.Windows.Forms.ListViewItem listViewItem3 = new System.Windows.Forms.ListViewItem(new string[] {
            "C",
            "C",
            "D"}, -1);
            System.Windows.Forms.ListViewItem listViewItem4 = new System.Windows.Forms.ListViewItem(new string[] {
            "D",
            "E",
            ""}, -1);
            this.treeView1 = new System.Windows.Forms.TreeView();
            this.pBar1 = new System.Windows.Forms.ProgressBar();
            this.listView1 = new System.Windows.Forms.ListView();
            this.columnHeader1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader3 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.SuspendLayout();
            // 
            // treeView1
            // 
            this.treeView1.Location = new System.Drawing.Point(91, 12);
            this.treeView1.Name = "treeView1";
            treeNode1.Name = "Node3";
            treeNode1.Text = "bar1";
            treeNode2.Name = "Node4";
            treeNode2.Text = "bar2";
            treeNode3.Name = "Node5";
            treeNode3.Text = "bar3";
            treeNode4.Name = "Node6";
            treeNode4.Text = "bar4";
            treeNode5.Name = "Node1";
            treeNode5.Text = "foo1";
            treeNode6.Name = "Node0";
            treeNode6.Text = "Root";
            treeNode7.Name = "Node9";
            treeNode7.Text = "bar5";
            treeNode8.Name = "Node10";
            treeNode8.Text = "bar6";
            treeNode9.Name = "Node7";
            treeNode9.Text = "foo2";
            treeNode10.Name = "Node11";
            treeNode10.Text = "bar7";
            treeNode11.Name = "Node8";
            treeNode11.Text = "foo3";
            treeNode12.Name = "Node2";
            treeNode12.Text = "Root2";
            this.treeView1.Nodes.AddRange(new System.Windows.Forms.TreeNode[] {
            treeNode6,
            treeNode12});
            this.treeView1.Size = new System.Drawing.Size(306, 384);
            this.treeView1.TabIndex = 0;
            // 
            // pBar1
            // 
            this.pBar1.Location = new System.Drawing.Point(91, 402);
            this.pBar1.Name = "pBar1";
            this.pBar1.Size = new System.Drawing.Size(618, 23);
            this.pBar1.Style = System.Windows.Forms.ProgressBarStyle.Continuous;
            this.pBar1.TabIndex = 1;
            // 
            // listView1
            // 
            this.listView1.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader1,
            this.columnHeader2,
            this.columnHeader3});
            this.listView1.FullRowSelect = true;
            this.listView1.GridLines = true;
            this.listView1.HideSelection = false;
            listViewItem1.Tag = "";
            this.listView1.Items.AddRange(new System.Windows.Forms.ListViewItem[] {
            listViewItem1,
            listViewItem2,
            listViewItem3,
            listViewItem4});
            this.listView1.Location = new System.Drawing.Point(404, 13);
            this.listView1.Name = "listView1";
            this.listView1.Size = new System.Drawing.Size(305, 383);
            this.listView1.TabIndex = 2;
            this.listView1.UseCompatibleStateImageBehavior = false;
            this.listView1.View = System.Windows.Forms.View.Details;
            // 
            // columnHeader1
            // 
            this.columnHeader1.Text = "Value 1";
            this.columnHeader1.Width = 100;
            // 
            // columnHeader2
            // 
            this.columnHeader2.Text = "Value 2";
            this.columnHeader2.Width = 100;
            // 
            // columnHeader3
            // 
            this.columnHeader3.Text = "Value 3";
            this.columnHeader3.Width = 100;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.listView1);
            this.Controls.Add(this.pBar1);
            this.Controls.Add(this.treeView1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TreeView treeView1;
        private System.Windows.Forms.ProgressBar pBar1;
        private System.Windows.Forms.ListView listView1;
        private System.Windows.Forms.ColumnHeader columnHeader1;
        private System.Windows.Forms.ColumnHeader columnHeader2;
        private System.Windows.Forms.ColumnHeader columnHeader3;
    }
}

