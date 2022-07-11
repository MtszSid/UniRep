using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;
using ASP.NET_Lista_7.Models.MVC;

namespace ASP.NET_Lista_7.Controllers.MVC
{
    [Authorize]
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }

    }
}