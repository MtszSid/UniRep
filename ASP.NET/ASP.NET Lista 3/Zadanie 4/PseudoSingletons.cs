using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Zadanie_4
{
    public class PseudoSingletonItem
    {
        const string Key = "ITEM";
        
        public static string Value { 
            get
            {
                if(HttpContext.Current.Items[Key] == null)
                {
                    HttpContext.Current.Items[Key] = "I'm PseudoSingletonItem " + DateTime.Now.Ticks;
                }
                return HttpContext.Current.Items[Key].ToString();
            }
        }
    }

    public class PseudoSingletonSession
    {
        const string Key = "ITEM";

        public static string Value
        {
            get
            {
                if (HttpContext.Current.Session[Key] == null)
                {
                    HttpContext.Current.Session[Key] = "I'm PseudoSingletonSession " + DateTime.Now.ToString();
                }
                return HttpContext.Current.Session[Key].ToString();
            }
        }
    }

    public class PseudoSingletonApplication
    {
        const string Key = "ITEM";
        static object _lock = new object();
        public static string Value
        {
            get
            {
                if (HttpContext.Current.Application[Key] == null)
                {
                    //HttpContext.Current.Application.Lock();
                    //HttpContext.Current.Application[Key] = "I'm value" + "Application";
                    //HttpContext.Current.Application.UnLock();
                    
                    lock (_lock)
                    {
                        HttpContext.Current.Application[Key] = "I'm PseudoSingletonApplication " + DateTime.Now.ToString();
                    }
                }
                return HttpContext.Current.Application[Key].ToString();
            }
        }
    }
}