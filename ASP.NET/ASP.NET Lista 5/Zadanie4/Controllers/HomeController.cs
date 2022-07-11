using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;
using Zadanie4.Models;

namespace Zadanie4.Controllers
{

    public class HomeController : Controller
    {
        // GET: Home
        [Authorize]
        public ActionResult Index()
        {
            var model = new HomeLogInModel();
            return View();
        }

        [HttpGet]
        public ActionResult LogIn()
        {
            var model = new HomeLogInModel();
            return View(model);
        }

        [HttpPost]
        public ActionResult LogIn(HomeLogInModel model)
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

        [Authorize(Roles = "ADMIN")]
        public ActionResult Admin()
        {
            return View();
        }
    }
}