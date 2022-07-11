<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="LoggedIn.aspx.cs" Inherits="Zadanie_8.LoggedIn" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Button runat="server" ID="Red1" Text="Go to Response1.aspx" OnClick="Red1_Click" />
        </div>
        <div>
            <asp:Button runat="server" ID="Red2" Text="Go to Response2.aspx" OnClick="Red2_Click" />
        </div>
        <div>
            <asp:Button runat="server" ID="Red3" Text="Go to Response3.aspx" OnClick="Red3_Click" />
        </div>
        <div>
            <asp:Button runat="server" ID="LogOut" Text="LogOut" OnClick="LogOut_Click" />
        </div>
    </form>
</body>
</html>
