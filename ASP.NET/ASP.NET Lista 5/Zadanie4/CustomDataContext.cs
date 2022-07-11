using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Zadanie4
{
    public class CustomDataContext
    {
        private const string str = "DATA_CONTEXT";
        public static UserDataClassesDataContext GetDataContext()
        {
            if(HttpContext.Current.Items[str] == null)
            {
                HttpContext.Current.Items[str] = new UserDataClassesDataContext();
            }
            return (UserDataClassesDataContext)HttpContext.Current.Items[str];
        }
    }
}