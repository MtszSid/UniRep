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
            this.Response.Write(this.Request.HttpMethod);
            this.Response.Write("<br>");
            this.Response.Write(this.Request.RawUrl);
            this.Response.Write("<br>");
            this.Response.Write(this.Request.Form);
        }
    }
}