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
            this.Response.Redirect("/Response.aspx?Name=" + this.Name.Text + "&Surname=" + this.Surname.Text);
        }

        protected void Link_Click(object sender, EventArgs e)
        {

        }
    }
}