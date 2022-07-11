using Microsoft.AspNetCore.Cryptography.KeyDerivation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Zadanie_2
{
    public partial class WebForm1 : CustomPageClass
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void SendButton_Click(object sender, EventArgs e)
        {
            
            if (Membership.ValidateUser(this.UserNameTextBox.Text, this.PasswordTextBox.Text)){
                FormsAuthentication.RedirectFromLoginPage(this.UserNameTextBox.Text, false);
            }
            else
            {
                this.MessageLabel.Text = "Niewłaściwa nazwa użytkownika i/lub hasło";
            }
        }

        protected void RegButton_Click(object sender, EventArgs e)
        {
            this.Response.Redirect("Register.aspx");
        }
    }
}