using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Zadanie_4
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        /*
        Concurrent Requests and Session State
        Access to ASP.NET session state is exclusive per session, which means that if two different users make 
        concurrent requests, access to each separate session is granted concurrently. However, if two concurrent
        requests are made for the same session (by using the same SessionID value), the first request gets exclusive
        access to the session information. The second request executes only after the first request is finished.
        (The second session can also get access if the exclusive lock on the information is freed because the first 
        request exceeds the lock time-out.) If the EnableSessionState value in the @ Page directive is set to ReadOnly,
        a request for the read-only session information does not result in an exclusive lock on the session data. However, 
        read-only requests for session data might still have to wait for a lock set by a read-write request for session 
        data to clear.

        Concurrency Application
        Application state is free-threaded, which means that application state data can be accessed simultaneously by many 
        threads. Therefore, it is important to ensure that when you update application state data, you do so in a thread-safe 
        manner by including built-in synchronization support. You can use the Lock and UnLock methods to ensure data integrity
        by locking the data for writing by only one source at a time. You can also reduce the likelihood of concurrency 
        problems by initializing application state values in the Application_Start method in the Global.asax file. For more 
        information see ASP.NET Application Life Cycle Overview for IIS 5.0 and 6.0.
         */
        protected void Page_Load(object sender, EventArgs e)
        {
            for (int i = 0; i < 10; i++)
            {
                this.Response.Write(PseudoSingletonItem.Value + "<br>");
            }
            this.Response.Write(PseudoSingletonSession.Value + "<br>");
            this.Response.Write(PseudoSingletonApplication.Value + "<br>");
            
        }
    }
}