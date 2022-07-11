using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Zadanie_8
{
    public partial class Response1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (this.Session["UserName"] == null)
            {
                this.Session.Add("PostAuthenticationRedirect", "/Response1.aspx");

                this.Response.Redirect("/Login.aspx");
            }
            else
            {
                this.Response.Write("Hi " + this.Session["UserName"] + " this is Response1.aspx");
            }

        }
    }
}