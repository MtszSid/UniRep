using Microsoft.AspNetCore.Cryptography.KeyDerivation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Zadanie_2
{
    public partial class Register : CustomPageClass
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void SendButton_Click(object sender, EventArgs e)
        {
            if(String.IsNullOrEmpty(this.UserNameTextBox.Text)      ||
                String.IsNullOrEmpty(this.PasswordTextBox.Text)     ||
                String.IsNullOrEmpty(this.PasswordRepTextBox.Text)  ||
                String.IsNullOrEmpty(this.EmailTextBox.Text))
            {
                this.MessageLabel.Text = "Fill in all fields!";
                return;
            }

            if(this.PasswordTextBox.Text != this.PasswordRepTextBox.Text)
            {
                this.MessageLabel.Text = "Passwords are not the same!";
                return;
            }

            var dataContext = this.DataContext;

            var usr_name = from user in dataContext.USERs
                      where user.UserName == this.UserNameTextBox.Text
                      select user;

            if(usr_name.FirstOrDefault() != null)
            {
                this.MessageLabel.Text = "Username already taken!";
                return;
            }

            var usr_email = from user in dataContext.USERs
                            where user.Email == this.EmailTextBox.Text
                            select user;
            
            if (usr_email.FirstOrDefault() != null)
            {
                this.MessageLabel.Text = "Email already taken!";
                return;
            }

            var usr = new USER();
            usr.UserName = this.UserNameTextBox.Text;
            usr.Email = this.EmailTextBox.Text;
            usr.PASSWORD = createPassword(this.PasswordTextBox.Text);

            dataContext.USERs.InsertOnSubmit(usr);

            dataContext.SubmitChanges();

            this.Response.Redirect("LogIn.aspx");
        }

        protected PASSWORD createPassword(String password)
        {
            // generate a 128-bit salt using a cryptographically strong random sequence of nonzero values
            byte[] salt = new byte[128 / 8];
            using (var rngCsp = new RNGCryptoServiceProvider())
            {
                rngCsp.GetNonZeroBytes(salt);
            }
            

            // derive a 256-bit subkey (use HMACSHA256 with 100,000 iterations)
            string hashed = Convert.ToBase64String(KeyDerivation.Pbkdf2(
                password: password,
                salt: salt,
                prf: KeyDerivationPrf.HMACSHA256,
                iterationCount: 100000,
                numBytesRequested: 256 / 8));

            var pass = new PASSWORD();
            pass.Hash = hashed;
            pass.Salt = Convert.ToBase64String(salt);
            pass.NumberOfHashes = 100000;
            return pass;
        }
    }
}