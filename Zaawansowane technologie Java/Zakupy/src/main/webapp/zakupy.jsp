<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="com.example.zakupy.ShoppingModel" %>
<%@ page import="com.example.zakupy.HelloServlet" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %><%--
  Created by IntelliJ IDEA.
  User: mati3
  Date: 22.05.2022
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Zakupy</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div id = "top">
    <h1>
        <%= "Zakupy !!!" %>
    </h1>
    <img src="Shopping_cart_icon.png" id="shoppingBags">
</div>
<div id="content">
<form action="zakupy" method="post">
    <input type="text" name="ProductName" id="textInput">
    <select name="Type" id="selectType">
        <%
            List<String> conf = Collections.list(config.getInitParameterNames());
            Pattern pattern = Pattern.compile("TYP-.*");

            for (String i: conf) {
                Matcher matcher = pattern.matcher(i);
                if(matcher.find()){
        %>
                <option value="<%= config.getInitParameter(i) %>"><%= config.getInitParameter(i)%></option>
        <%
                }
            }
        %>
    </select>
    <button type="submit" name="SubmitButton" id="submitButton" value="submit">Zapisz</button>
    <button type="submit" name="SubmitButton" id="deleteButton" value="delete">Usuń</button>
</form>

    <table class="shoppingListTable">
        <tr class="tableHeader">
            <td class="tableCell"> Nazwa </td>
            <td class="tableCell"> Typ</td>
        </tr>
    <%
    HashSet<ShoppingModel> collection =  (HashSet<ShoppingModel>)session.getAttribute(HelloServlet.SessionID);

    if(collection != null)
    for (ShoppingModel  m: collection) {
     %>
        <tr class="tableRow">
            <td class="tableCell"> <%= m.getName() %> </td>
            <td class="tableCell"> <%= m.getType()%></td>
        </tr>
    <% }%>
    </table>
</div>

<div id="bottom">
    <%=pageContext.getServletContext().getInitParameter("Name")%>,
    <%=pageContext.getServletContext().getInitParameter("Email")%>
    <br />
</div>
</body>
</html>
