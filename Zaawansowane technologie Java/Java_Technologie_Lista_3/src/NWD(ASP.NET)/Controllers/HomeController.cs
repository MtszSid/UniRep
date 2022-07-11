using NWD.Models.Home;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace NWD.Controllers
{
    public class HomeController : Controller
    {
        
        [HttpPost]
        public ActionResult GCD(int a, int b)
        {
            var model = new HomeGCDModel();

            model.a = a;
            model.b = b;
            model.nwd = Euclid.getGCD(a, b);

            return View(model); 
        }
    }

    abstract class Euclid
    {
        public static int getGCD(int a, int b)
        {
            if (a < b)
            {
                var c = a;
                a = b;
                b = c;
            }
            while (b > 0)
            {
                var c = b;
                b = a % b;
                a = c;
            }
            return a;
        }
    }
}