﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Zadanie_2
{
    public partial class WebForm3 : CustomPageClass
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void LogOut_Click(object sender, EventArgs e)
        {
            FormsAuthentication.SignOut();
            
        }
    }
}