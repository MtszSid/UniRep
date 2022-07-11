using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication1
{

    public class CustomHttpHandler : IHttpHandler
    {
        public bool IsReusable
        {
            get
            {
                return false;
            }
        }
        public void ProcessRequest(HttpContext context)
        {
            context.Response.AppendHeader("Content-type", "text/html");
            context.Response.Write("handler obsługuje " + context.Request.Url + "<br>");
            context.Response.Write(context.Request.HttpMethod + "<br>");
            //context.Response.Write(context.Request.Headers + "<br>");

            foreach (var i in context.Request.Headers.AllKeys)
            {
                context.Response.Write(i + ": " + context.Request.Headers[i] + "<br>");
            }

            context.Response.Write("<br>");
            //context.Response.Write(context.Request.Form + "<br>");

            foreach (var i in context.Request.Form.AllKeys)
            {
                context.Response.Write(i + ": " + context.Request.Form[i] + "<br>");
            }

            context.Response.Write("<br>");
           
            foreach (var i in context.Request.QueryString.AllKeys)
            {
                context.Response.Write(i + ": " + context.Request.QueryString[i] + "<br>");
            }

            context.Response.End();

        }
    }
}
