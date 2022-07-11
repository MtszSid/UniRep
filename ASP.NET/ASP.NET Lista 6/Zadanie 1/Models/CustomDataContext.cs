using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;
using System.Web;

namespace Zadanie_1.Models
{
    public class CustomDataContext
    {
        private const string name = "DATA_CONTEXT";

        public static StudentsDataContext GetDataContext()
        {
            if (HttpContext.Current.Items[name] == null)
            {
                HttpContext.Current.Items[name] = new StudentsDataContext();
            }
            return (StudentsDataContext)HttpContext.Current.Items[name];
        }
    }
}