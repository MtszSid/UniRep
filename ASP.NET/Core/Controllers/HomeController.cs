using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using Core.Models;
using Microsoft.AspNetCore.Authorization;
using Core.DB;

namespace Core.Controllers;

[Authorize]
public class HomeController : Controller
{
    DaneStudentowDataContext _dbContext;
    public HomeController(DaneStudentowDataContext dbContext)
    {
        _dbContext = dbContext;
    }
    public IActionResult Index(int id = 2)
    {
        var cookie = this.Request.Cookies["cookie"];
        if (string.IsNullOrEmpty(cookie))
        {
            this.Response.Cookies.Append("cookie", "foo");
        }
        
        var session = this.HttpContext.Session.Get<SessionData>("session");
        if (session == null)
        {
            this.HttpContext.Session.Set<SessionData>("session", new SessionData() { Data = "bar" });
            session = this.HttpContext.Session.Get<SessionData>("session");
        }

        var querry = from st in _dbContext.Students
                  where st.ID == id
                  select st;
        var odp = querry.FirstOrDefault();

        if (odp is null)
        {
            var Student = new Student();
            Student.ID = -1;
            Student.Imie = "Example";
            Student.Nazwisko = "Example";
            Student.DataUrodzenia = DateTime.Now;
            odp =  Student;
        }

        return View(odp);
    }

    public IActionResult Privacy()
    {
        return View();
    }

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }
}
