using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ASP.NET_Lista_7.DB
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