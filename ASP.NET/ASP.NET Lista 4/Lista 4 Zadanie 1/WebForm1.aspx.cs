using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Lista_4_Zadanie_1
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            label1.Text = System.Security.Principal.WindowsIdentity.GetCurrent().Name;
            label2.Text = this.User.Identity.IsAuthenticated ? this.User.Identity.Name : "anon";
        }
    }
}