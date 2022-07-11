using ASP.NET_Lista_7.Models.MVC;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;

namespace ASP.NET_Lista_7.Controllers.MVC
{
    public class AccountController : Controller
    {
        [HttpGet]
        public ActionResult LogIn()
        {
            var model = new AccountLogInModel();
            return View(model);
        }

        [HttpPost]
        public ActionResult LogIn(AccountLogInModel model)
        {
            if (Membership.ValidateUser(model.UserName, model.Password))
            {
                FormsAuthentication.RedirectFromLoginPage(model.UserName, false);
                return new EmptyResult();
            }
            model.Message = "Nieprawidłowe dane logowania";
            return View(model);
        }

        public ActionResult LogOut()
        {
            FormsAuthentication.SignOut();
            return Redirect("/");
        }
        
    }
}