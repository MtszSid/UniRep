using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Zadanie_1
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            
        }

        protected void Submit_Click(object sender, EventArgs e)
        {
            var cookie = new HttpCookie("User");
            cookie.Values.Add("Username", Name.Text);
            cookie.Values.Add("Timestamp", DateTime.Now.Ticks.ToString());
            this.Response.Cookies.Add(cookie);
            this.Response.Redirect("/Response.aspx");
        
        }

    }
}