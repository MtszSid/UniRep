<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm1.aspx.cs" Inherits="WebApplication1.WebForm1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <% 
                int i;
                for ( i=1; i<=5; i++ )
                {
                    Response.Write( string.Format( "<FONT size={0}>Witam w ASP.NET</FONT><br>", i ) );
                } 
            %>
        </div>
    </form>
</body>
</html>
