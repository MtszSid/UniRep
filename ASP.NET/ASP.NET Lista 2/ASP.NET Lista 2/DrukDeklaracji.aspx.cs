using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ASP.NET_Lista_2
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (this.Session["Imie"] == null
                || this.Session["Nazwisko"] == null
                || this.Session["Przedmiot"] == null)
            {
                this.Response.Redirect("/Deklaracja.aspx");
            }
            else
            {
                ImieCell.Text = $"{Session["Imie"]} {Session["Nazwisko"]}";
                PrzedmiotCell.Text = Session["Przedmiot"].ToString();

                for (var i = 1; i <= 10; i++)
                {
                    (this.GetType().GetField
                        ("Zad" + i,
                        System.Reflection.BindingFlags.Instance |
                        System.Reflection.BindingFlags.NonPublic)
                        .GetValue(this) as TableCell).Text = (Session["Z" + i] != null &&
                                                              !string.IsNullOrEmpty(Session["Z" + i].ToString())
                                                                ? Session["Z" + i].ToString() : "0");

                }
            }
            //if (this.Request.QueryString["Imie"] == null
            //    || this.Request.QueryString["Nazwisko"] == null
            //    || this.Request.QueryString["Przedmiot"] == null)
            //{
            //    this.Response.Redirect("/Deklaracja.aspx");
            //}
            //else
            //{
            //    ImieCell.Text = $"{this.Request.QueryString["Imie"]} {this.Request.QueryString["Nazwisko"]}";
            //    PrzedmiotCell.Text = this.Request.QueryString["Przedmiot"].ToString();

            //    for (var i = 1; i <= 10; i++)
            //    {
            //        (this.GetType().GetField
            //            ("Zad" + i,
            //            System.Reflection.BindingFlags.Instance |
            //            System.Reflection.BindingFlags.NonPublic)
            //            .GetValue(this) as TableCell).Text = (this.Request.QueryString["Z" + i] != null &&
            //                                                  !string.IsNullOrEmpty(this.Request.QueryString["Z" + i].ToString())
            //                                                    ? this.Request.QueryString["Z" + i].ToString() : "0");

            //    }
            //}
        }
    }
}