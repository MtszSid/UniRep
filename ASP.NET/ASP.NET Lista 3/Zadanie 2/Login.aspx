﻿<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="Zadanie_1.WebForm1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:TextBox runat="server" ID="Name"></asp:TextBox>
        </div>
        <div>
            <asp:Button runat="server" ID="Submit" OnClick="Submit_Click" />
        </div>
    </form>
</body>
</html>
