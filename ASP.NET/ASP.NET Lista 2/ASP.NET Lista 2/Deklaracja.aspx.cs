using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ASP.NET_Lista_2
{
    public partial class Deklaracja : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

            if (!(string.IsNullOrEmpty(Imie.Text)
                  || string.IsNullOrEmpty(Nazwisko.Text)
                  || string.IsNullOrEmpty(Przedmiot.Text)))
            {
                this.Session.Add("Imie", Imie.Text);
                this.Session.Add("Nazwisko", Nazwisko.Text);
                this.Session.Add("Przedmiot", Przedmiot.Text);

                for (var i = 1; i <= 10; i++)
                {
                    this.Session.Add("Z" + i,
                        (this.GetType().GetField("Z" + i, System.Reflection.BindingFlags.Instance | System.Reflection.BindingFlags.NonPublic)
                        .GetValue(this) as TextBox).Text);
                }

                this.Response.Redirect("/DrukDeklaracji.aspx");
            }

            //if (!(string.IsNullOrEmpty(Imie.Text)
            //      || string.IsNullOrEmpty(Nazwisko.Text)
            //      || string.IsNullOrEmpty(Przedmiot.Text)))
            //{
            //    this.Response.Redirect("/DrukDeklaracji.aspx?" + this.Request.Form);
            //}
        }
    }
}