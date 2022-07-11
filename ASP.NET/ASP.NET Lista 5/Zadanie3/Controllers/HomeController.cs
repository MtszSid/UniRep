using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Zadanie3.Models.Home;

namespace Zadanie3.Controllers
{
    public class HomeController : Controller
    {
        // GET: Home
        [HttpGet]
        public ActionResult Index()
        {
            var model = new HomeIndexModel();
            return View(model);
        }

        [HttpPost]
        public ActionResult Index(HomeIndexModel model)
        {
            return View(model);
        }

        public ActionResult Secret()
        {
            return View();
        }
    }
}