using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Zadanie_1.Models;
using Zadanie_1.Models.Students;

namespace Zadanie_1.Controllers
{
    public class StudentsController : Controller
    {
        // GET: Students
        public ActionResult Index(
            int page = 1,
            string sort = "GivenName", string sortdir = "ASC")
        {
                var model = new StudentsIndexModel();
                var dataLayer = new DataLayer();

                model.Students = new PagedEnumerable<Student>()
                {
                    Items = dataLayer.GetUsers(string.Format("{0} {1}", sort, sortdir), (page - 1) * 10, 10),
                    TotalCount = dataLayer.TotalUsers()
                };

                return View(model);
        }

        [HttpGet]
        public ActionResult Edit()
        {
            if(RouteData.Values["id"] == null)
            {
                Redirect("/Students");
            }
            var id = int.Parse(RouteData.Values["id"].ToString());

            var dataContext = CustomDataContext.GetDataContext();

            var ans = from st in dataContext.Students
                      where st.ID == id
                      select st;
            if(ans.Count() == 0)
            {
                return Redirect("/Students");
            }

            var stud = ans.First();
            var model = new StudentsEditModel();

            model.ID = stud.ID;
            model.Name = stud.Imie;
            model.Surname = stud.Nazwisko;
            model.BirthDate = stud.DataUrodzenia;
            model.CityName = (stud.Miejscowosc != null ? stud.Miejscowosc.Nazwa : "");

            return View(model);

        }

        [HttpPost]
        public ActionResult Edit(StudentsEditModel model, string btn = "submit")
        {
            var id = model.ID ;

            if (btn == "cancel")
            {
                return Redirect("/Students");
            }

            if (ModelState.IsValid) {
                var dataContext = CustomDataContext.GetDataContext();

                Miejscowosc msc = null;

                var ans = from st in dataContext.Students
                          where st.ID == id
                          select st;

                if (ans.Count() == 0)
                {
                    return Redirect("/Students");
                }

                if (!String.IsNullOrEmpty(model.CityName))
                {
                    var ans2 = from ct in dataContext.Miejscowoscs
                               where ct.Nazwa == model.CityName
                               select ct;

                    if (ans2.Count() > 0)
                    {
                        msc = ans2.First();
                    }
                    else
                    {
                        msc = new Miejscowosc();
                        msc.Nazwa = model.CityName;
                    }
                }

                var stud = ans.First();

                stud.Imie = model.Name;
                stud.Nazwisko = model.Surname;
                stud.DataUrodzenia = model.BirthDate;
                stud.Miejscowosc = msc;

                dataContext.SubmitChanges();

                return Redirect("/Students");
            }
            return View(model);

        }

        [HttpGet]
        public ActionResult New()
        {
            var model = new StudentsNewModel();
            return View(model);
        }

        [HttpPost]
        public ActionResult New(StudentsNewModel model, string btn="submit")
        {
            if(btn == "cancel")
            {
                return Redirect("/Students");
            }
            if (ModelState.IsValid)
            {
                var dataContext = CustomDataContext.GetDataContext();

                Student s = new Student();
                s.Imie          = model.Name;
                s.Nazwisko      = model.Surname;
                s.DataUrodzenia = model.BirthDate;

                if (!String.IsNullOrEmpty(model.CityName))
                {
                    var ans = from ct in dataContext.Miejscowoscs 
                              where ct.Nazwa == model.CityName 
                              select ct;
                    Miejscowosc m;
                    if (ans.Count() == 0)
                    {
                        m = new Miejscowosc();
                        m.Nazwa = model.CityName;
                    }
                    else
                    {
                        m = ans.First();
                    }

                    s.Miejscowosc = m;
                }

                dataContext.Students.InsertOnSubmit(s);
                dataContext.SubmitChanges();

                return Redirect("/Students");

            }
            return View(model);
        }

        [HttpGet]
        public ActionResult Delete()
        {
            if (RouteData.Values["id"] == null)
            {
                Redirect("/Students");
            }
            return View();
        }

        [HttpPost]
        public ActionResult Delete(string btn = "cancel")
        {

            if (btn == "cancel")
            {
                return Redirect("/Students");
            }
            else
            {
                if (RouteData.Values["id"] == null)
                {
                    Redirect("/Students");
                }

                var id = int.Parse(RouteData.Values["id"].ToString());

                var dataContext = CustomDataContext.GetDataContext();

                var ans = from st in dataContext.Students where st.ID == id select st;

                dataContext.Students.DeleteAllOnSubmit(ans);

                dataContext.SubmitChanges();

                return Redirect("/Students");
            }
        }

    }
}

