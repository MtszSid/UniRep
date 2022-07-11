using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Zadanie_1.Models.Pasek;

namespace Zadanie_1.Controllers
{
    public class PasekController : Controller
    {
        // GET: Pasek
        [HttpGet]
        public ActionResult Index()
        {
            var model = new PasekModel();
            return View(model);
        }

        [HttpPost]
        public ActionResult Index(PasekModel model)
        {
            if (ModelState.IsValid)
            {
                return Redirect("/Pasek/Deklaracja?" + model.ToString());
            }
            else
                return View(model);
        }

        public ActionResult Deklaracja()
        {
            var qs = this.Request.QueryString;
            if( String.IsNullOrEmpty(qs["Imie"])        || 
                String.IsNullOrEmpty(qs["Nazwisko"])    ||
                String.IsNullOrEmpty(qs["Przedmiot"])   ||
                String.IsNullOrEmpty(qs["Prowadzacy"])   ||
                String.IsNullOrEmpty(qs["Data"]))
            {
                return Redirect("/");
            }
            return View();
        }
    }
}