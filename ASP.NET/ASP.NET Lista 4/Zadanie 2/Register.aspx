<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Register.aspx.cs" Inherits="Zadanie_2.Register" %>

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
            <asp:Label runat="server" Text="Rep Password:" ID="PasswordRepLabel"></asp:Label>
        </div>
        <div>
            <asp:TextBox runat="server" ID="PasswordRepTextBox" TextMode="Password"></asp:TextBox>
        </div>
        <div>
            <asp:Label runat="server" Text="email:" ID="emailLabel"></asp:Label>
        </div>
        <div>
            <asp:TextBox runat="server" ID="EmailTextBox" TextMode="Email"></asp:TextBox>
        </div>
        <div>
            <asp:Label runat="server" ID="MessageLabel"></asp:Label>
        </div>
        <div>
            <asp:Button runat="server" ID="SendButton" Text="Register" OnClick="SendButton_Click" />
        </div>
        
    </form>
</body>
</html>
