using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ASP.NET_Lista_7.Controllers.API
{
    public class Zad3Controller : ApiController
    {
        [CustomAuthenticationFilter]
        public IHttpActionResult Get()
        {
            var list = new List<Person>()
            {
                new Person() { Name = "jan" },
                new Person() { Name = "tomasz" },
            };

            return this.Ok(list);
        }
        [CustomAuthenticationFilter]
        public IHttpActionResult Post(Person person)
        {
            return this.Ok(person);
        }
    }
}
