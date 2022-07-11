using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ASP.NET_Lista_7.DB;
using ASP.NET_Lista_7.Models.API;

namespace ASP.NET_Lista_7.Controllers.API
{
    public class Zad1Controller : ApiController
    {
        // GET: api/Zad1
        public IHttpActionResult Get()
        {
            var dataContext = CustomDataContext.GetDataContext();
            var q = from st in dataContext.Students
                    select new
                    {
                        ID = st.ID,
                        Name = st.Imie,
                        Surname = st.Nazwisko,
                        BirthDate = st.DataUrodzenia
                    };
            return this.Ok(q);
        }

        // GET: api/Zad1/5
        public IHttpActionResult Get(int id)
        {
            var dataContext = CustomDataContext.GetDataContext();
            var q = from st in dataContext.Students
                    where st.ID == id
                    select new
                    {
                        ID = st.ID,
                        Name = st.Imie,
                        Surname = st.Nazwisko,
                        BirthDate = st.DataUrodzenia
                    };
            return this.Ok(q);
        }

        // POST: api/Zad1
        public IHttpActionResult Post(Zad1Person person)
        {
            if (person == null) return this.BadRequest("no data");
            var dataContext = CustomDataContext.GetDataContext();
            Student s = new Student();
            s.Imie = person.Name;
            s.Nazwisko = person.Surname;
            s.DataUrodzenia = person.BirthDate;
            dataContext.Students.InsertOnSubmit(s);

            dataContext.SubmitChanges();

            return this.Ok(person);
        }

        // PUT: api/Zad1/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/Zad1/5
        public void Delete(int id)
        {
        }
    }
}
