using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ASP.NET_Lista_3
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            this.Response.AddHeader("Content-Language", "pl-PL");

            foreach(var i in this.Request.Headers.AllKeys){
                this.Response.Write(i + ": " + this.Request.Headers[i] + "<br>");
            }

            this.Response.Write(this.Server.MapPath("WebForm1.aspx") + "<br>");


            Page P = HttpContext.Current.Handler as Page;
            
            this.Response.Write(P.Request.Path + "<br>");
        }
    }
}