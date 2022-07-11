using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Zadanie_8
{
    public partial class LoggedIn : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (this.Session["UserName"] == null)
            {
                this.Session.Add("PostAuthenticationRedirect", "/LoggedIn.aspx");

                this.Response.Redirect("/Login.aspx");
            }
            
        }

        protected void Red1_Click(object sender, EventArgs e)
        {
            this.Response.Redirect("/Response1.aspx");
        }

        protected void Red2_Click(object sender, EventArgs e)
        {
            this.Response.Redirect("/Response2.aspx");
        }

        protected void Red3_Click(object sender, EventArgs e)
        {
            this.Response.Redirect("/Response3.aspx");
        }

        protected void LogOut_Click(object sender, EventArgs e)
        {
            if (this.Session["UserName"] != null)
            {
                this.Session.Remove("UserName");
            }
            this.Response.Redirect("/Login.aspx");
        }
    }
}