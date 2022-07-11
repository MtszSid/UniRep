<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Deklaracja.aspx.cs" Inherits="ASP.NET_Lista_2.Deklaracja" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server" method="post" >
        <div>
            Imię: <asp:TextBox ID="Imie" runat="server"></asp:TextBox>
        </div>
        <div>
            Nazwisko: <asp:TextBox ID="Nazwisko" runat="server"></asp:TextBox>
        </div>
        <div>
            Przedmiot: <asp:TextBox ID="Przedmiot" runat="server"></asp:TextBox>
        </div>
        <div>
            Zadanie 1: <asp:TextBox ID="Z1" runat="server" TextMode="Number"></asp:TextBox>
        </div>
        <div>
            Zadanie 2: <asp:TextBox ID="Z2" runat="server" TextMode="Number"></asp:TextBox>
        </div>
        <div>
            Zadanie 3: <asp:TextBox ID="Z3" runat="server" TextMode="Number"></asp:TextBox>
        </div>
        <div>
            Zadanie 4: <asp:TextBox ID="Z4" runat="server" TextMode="Number"></asp:TextBox>
        </div>
        <div>
            Zadanie 5: <asp:TextBox ID="Z5" runat="server" TextMode="Number"></asp:TextBox>
        </div>
        <div>
            Zadanie 6: <asp:TextBox ID="Z6" runat="server" TextMode="Number"></asp:TextBox>
        </div>
        <div>
            Zadanie 7: <asp:TextBox ID="Z7" runat="server" TextMode="Number"></asp:TextBox>
        </div>
        <div>
            Zadanie 8: <asp:TextBox ID="Z8" runat="server" TextMode="Number"></asp:TextBox>
        </div>
        <div>
            Zadanie 9: <asp:TextBox ID="Z9" runat="server" TextMode="Number"></asp:TextBox>
        </div>
        <div>
            Zadanie 10: <asp:TextBox ID="Z10" runat="server" TextMode="Number"></asp:TextBox>
        </div>
        <div>
            <asp:Button runat="server" ID ="Send" Text="Stwórz Deklarację"/>
        </div>
    </form>
</body>
</html>
