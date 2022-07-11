using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using CustomInterface;

namespace FormsApp
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var deploymentUri = System.Deployment.Application.ApplicationDeployment.CurrentDeployment.ActivationUri;
            
            var serviceUri = new Uri(deploymentUri, "./Service1.svc");

            var address = new EndpointAddress(serviceUri);
            var binding = new BasicHttpBinding();
            var factory = new ChannelFactory<MyInterface>(binding);
            var proxy = factory.CreateChannel(address);
            string result = proxy.DoSomething("test");

            MessageBox.Show(result);
        }
    }
}
