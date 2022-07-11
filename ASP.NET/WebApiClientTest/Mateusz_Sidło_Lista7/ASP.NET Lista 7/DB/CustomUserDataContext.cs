using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ASP.NET_Lista_7.DB
{
    public class CustomUserDataContext
    {
        private const string str = "USER_DATA_CONTEXT";
        public static UserDataDataContext GetDataContext()
        {
            if (HttpContext.Current.Items[str] == null)
            {
                HttpContext.Current.Items[str] = new UserDataDataContext();
            }
            return (UserDataDataContext)HttpContext.Current.Items[str];
        }
    }
}