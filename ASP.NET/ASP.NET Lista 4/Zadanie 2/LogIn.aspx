<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="LogIn.aspx.cs" Inherits="Zadanie_2.WebForm1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Label runat="server" Text="Username:" ID ="UsernameLabel"></asp:Label>
        </div>
        <div>
            <asp:TextBox runat="server" ID="UserNameTextBox"></asp:TextBox>
        </div>
        <div>
            <asp:Label runat="server" Text="Password:" ID="PasswordLabel"></asp:Label>
        </div>
        <div>
            <asp:TextBox runat="server" ID="PasswordTextBox" TextMode="Password"></asp:TextBox>
        </div>
        <div>
            <asp:Label runat="server" ID="MessageLabel"></asp:Label>
        </div>
        <div>
            <asp:Button runat="server" ID="SendButton" Text="LogIn" OnClick="SendButton_Click" />
        </div>
        <div>
            <asp:Button runat="server" ID="RegButton" Text="Register" OnClick="RegButton_Click" />
        </div>
    </form>
</body>
</html>
