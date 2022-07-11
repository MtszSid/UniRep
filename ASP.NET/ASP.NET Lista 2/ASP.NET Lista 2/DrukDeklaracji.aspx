<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="DrukDeklaracji.aspx.cs" Inherits="ASP.NET_Lista_2.WebForm1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Table runat="server" ID="table1">
                <asp:TableHeaderRow runat="server" Width ="600" >
                    <asp:TableCell runat="server"></asp:TableCell>
                    <asp:TableCell runat="server" ID="ImieCell" ColumnSpan ="4"></asp:TableCell>
                    <asp:TableCell runat="server" ColumnSpan="2">Przedmiot:</asp:TableCell>
                    <asp:TableCell runat="server" ColumnSpan="3" ID="PrzedmiotCell"></asp:TableCell>
                </asp:TableHeaderRow>
                <asp:TableRow runat="server">
                    <asp:TableCell runat="server" Width="80">Zadanie</asp:TableCell>
                    <asp:TableCell runat="server" Width="50">1</asp:TableCell>
                    <asp:TableCell runat="server" Width="50">2</asp:TableCell>
                    <asp:TableCell runat="server" Width="50">3</asp:TableCell>
                    <asp:TableCell runat="server" Width="50">4</asp:TableCell>
                    <asp:TableCell runat="server" Width="50">5</asp:TableCell>
                    <asp:TableCell runat="server" Width="50">6</asp:TableCell>
                    <asp:TableCell runat="server" Width="50">7</asp:TableCell>
                    <asp:TableCell runat="server" Width="50">8</asp:TableCell>
                    <asp:TableCell runat="server" Width="50">9</asp:TableCell>
                    <asp:TableCell runat="server" Width="50">10</asp:TableCell>
                </asp:TableRow>
                <asp:TableRow runat="server">
                    <asp:TableCell runat="server"></asp:TableCell>
                    <asp:TableCell runat="server" ID="Zad1"></asp:TableCell>
                    <asp:TableCell runat="server" ID="Zad2"></asp:TableCell>
                    <asp:TableCell runat="server" ID="Zad3"></asp:TableCell>
                    <asp:TableCell runat="server" ID="Zad4"></asp:TableCell>
                    <asp:TableCell runat="server" ID="Zad5"></asp:TableCell>
                    <asp:TableCell runat="server" ID="Zad6"></asp:TableCell>
                    <asp:TableCell runat="server" ID="Zad7"></asp:TableCell>
                    <asp:TableCell runat="server" ID="Zad8"></asp:TableCell>
                    <asp:TableCell runat="server" ID="Zad9"></asp:TableCell>
                    <asp:TableCell runat="server" ID="Zad10"></asp:TableCell>
                </asp:TableRow>
            </asp:Table>
        </div>
    </form>
</body>
</html>
