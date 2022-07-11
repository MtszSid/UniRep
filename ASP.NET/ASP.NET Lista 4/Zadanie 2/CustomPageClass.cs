using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Web;

namespace Zadanie_2
{
    public class CustomPageClass : System.Web.UI.Page
    {
        private DataClasses1DataContext _dataContext;
        public DataClasses1DataContext DataContext { 
            get
            {
                if(_dataContext == null)
                {
                    var cs = ConfigurationManager.ConnectionStrings["UserDataConnectionString"].ConnectionString;
                    _dataContext = new DataClasses1DataContext(cs);
                }

                return _dataContext;
            } 
        }

        public override void Dispose()
        {
            if(_dataContext != null)
            {
                _dataContext.Dispose();
            }

            base.Dispose();
        }
    }
}