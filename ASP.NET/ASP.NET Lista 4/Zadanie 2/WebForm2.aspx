<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm2.aspx.cs" Inherits="Zadanie_2.WebForm2" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Label runat="server" ID="lab1" Text="WebForm2"></asp:Label>
        </div>
        <div>
            <asp:Button runat="server" ID="LogOut" Text="LogOut" OnClick="LogOut_Click" />
        </div>
    </form>
</body>
</html>
