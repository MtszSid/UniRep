using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Zadanie_1
{
    public partial class WebForm2 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (this.Request.Cookies["User"] == null)
            {
                this.Response.Redirect("/Login.aspx");
            }
            else if (this.Request.Cookies["User"] != null && DateTime.Now.Ticks - long.Parse(Request.Cookies["User"]["Timestamp"]) >= 10000000)
            {
                this.Request.Cookies["User"].Expires = DateTime.Now.AddHours(-1);
                this.Response.Cookies.Add(this.Request.Cookies["User"]);
                this.Response.Redirect("/Login.aspx");
            }
            else
            {
                this.Response.Write("Witaj " + Request.Cookies["User"]["Username"]);
            }
        }
    }
}