<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Zakupy</title>
    <link rel="stylesheet" href="style.css">
    <meta http-equiv="refresh" content="0; url=/Zakupy/zakupy" />
</head>
<body>
<div id = "top">
    <h1>
        Redirecting
    </h1>
</div>
<div id="content">

</div>
<div id="bottom">
    <%=pageContext.getServletContext().getInitParameter("Name")%>,
    <%=pageContext.getServletContext().getInitParameter("Email")%>
    <br />
</div>
</body>
</html>