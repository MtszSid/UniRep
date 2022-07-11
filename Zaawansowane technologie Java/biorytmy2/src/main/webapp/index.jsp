<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.math.RoundingMode" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%!
    BigDecimal getPhysicalRhythm(int daysSinceBirth){
        return BigDecimal.valueOf(Math.sin(2 * Math.PI * daysSinceBirth / 23)).setScale(2, RoundingMode.HALF_EVEN); // 23
    }

    BigDecimal getEmotionalRhythm(int daysSinceBirth){
        return BigDecimal.valueOf(Math.sin(2 * Math.PI * daysSinceBirth / 28)).setScale(2, RoundingMode.HALF_EVEN); // 28
    }

    BigDecimal getIntellectualRhythm(int daysSinceBirth){
        return BigDecimal.valueOf(Math.sin(2 * Math.PI * daysSinceBirth / 33)).setScale(2, RoundingMode.HALF_EVEN); // 33
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Biorytmy!</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <div id = "top">
        <h1>
            <%= "Biorytmy!" %>
        </h1>
    </div>
    <div id="content">

        <img id="calendar" src="res/Calendar_18.png"  alt="calendar"/>
        <br/>

        <form action="index.jsp" id="form" method="post">
        <input type="date" id="birthday" name="birthday"
               value=<%=getServletConfig().getInitParameter("DefaultDate")%>
               max= <% Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    out.print(formatter.format(calendar.getTime()));%> >
        <button>Send</button>
        </form>

        <%String date =  request.getParameter("birthday");

            if(date != null){
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                    GregorianCalendar grCalendar = new GregorianCalendar();
                    grCalendar.setTime(format.parse(date));

                    GregorianCalendar nowCalendar = (GregorianCalendar) calendar;

                    int days = (int) ChronoUnit.DAYS.between(grCalendar.toZonedDateTime(), nowCalendar.toZonedDateTime());

                    int months = (int) ChronoUnit.MONTHS.between(grCalendar.toZonedDateTime(), nowCalendar.toZonedDateTime());

                    int years = (int) ChronoUnit.YEARS.between(grCalendar.toZonedDateTime(), nowCalendar.toZonedDateTime());

                    nowCalendar.add(Calendar.DAY_OF_YEAR, -5);

                    days -= 5;

                    out.println("Od narodzin minęło:  <br \\>");
                    out.println(years + " lat <br \\>");
                    out.println(months + " miesięcy <br \\>");
                    out.println(days + " dni <br \\>");
        %>


                <table class="biorhythms">
                    <tr class="tableHeader">
                        <td class="tableCell"> Data </td>
                        <td class="tableCell"> Rytm Fizyczny</td>
                        <td class="tableCell"> Rytm Emocjonalny</td>
                        <td class="tableCell"> Rytm Intelektualny</td>
                    </tr>
                    <%

                    for(int i = 0; i < 5; i++){ %>
                    <tr class="tableRow">
                        <td class="tableCell"> <%=format.format(nowCalendar.getTime()) %> </td>
                        <td class="tableCell"> <%=getPhysicalRhythm(days)     %></td>
                        <td class="tableCell"> <%=getEmotionalRhythm(days)    %></td>
                        <td class="tableCell"> <%=getIntellectualRhythm(days) %></td>
                    </tr>
                    <%
                        nowCalendar.add(Calendar.DAY_OF_YEAR, 1);
                        days++;
                    }

                    %>
                    <tr class="tableRowCurrentDate">
                        <td class="tableCell"> <%=format.format(nowCalendar.getTime()) %> </td>
                        <td class="tableCell"> <%=getPhysicalRhythm(days)     %></td>
                        <td class="tableCell"> <%=getEmotionalRhythm(days)    %></td>
                        <td class="tableCell"> <%=getIntellectualRhythm(days) %></td>
                    </tr>
                        <%
                        nowCalendar.add(Calendar.DAY_OF_YEAR, 1);
                        days++;

                    for(int i = 0; i < 28; i++){ %>
                    <tr class="tableRow">
                        <td class="tableCell"> <%=format.format(nowCalendar.getTime()) %> </td>
                        <td class="tableCell"> <%=getPhysicalRhythm(days)     %></td>
                        <td class="tableCell"> <%=getEmotionalRhythm(days)    %></td>
                        <td class="tableCell"> <%=getIntellectualRhythm(days) %></td>
                    </tr>
                        <%
                        nowCalendar.add(Calendar.DAY_OF_YEAR, 1);
                        days++;
                    }

                    out.println("</table>");
                } catch (ParseException e) {
                    e.printStackTrace();
                    out.println("Good try, but that's not a date.");
                }

            }

        %>
    </div>
    <div id="bottom">
        <%=pageContext.getServletContext().getInitParameter("Name")%>,
        <%=pageContext.getServletContext().getInitParameter("Email")%>
        <br />
    </div>
</body>
</html>