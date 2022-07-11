using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Zadanie_8
{
    public partial class Login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if(this.Session["UserName"] != null)
            {
                if (Session["PostAuthenticationRedirect"] == null)
                {
                    this.Response.Redirect("/LoggedIn.aspx");
                }
                else
                {
                    this.Response.Redirect(this.Session["PostAuthenticationRedirect"].ToString());
                    this.Session.Remove("PostAuthenticationRedirect");
                }
            }
        }

        protected void Submit_Click(object sender, EventArgs e)
        {
            if(Name.Text == Password.Text)
            {
                this.Session.Add("UserName", Name.Text);
                if(Session["PostAuthenticationRedirect"] == null)
                {
                    this.Response.Redirect("/LoggedIn.aspx");
                }
                else
                {
                    this.Response.Redirect(this.Session["PostAuthenticationRedirect"].ToString());
                    this.Session.Remove("PostAuthenticationRedirect");
                }
            }
        }
    }
}