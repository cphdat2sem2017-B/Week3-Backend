<%-- 
    Document   : view
    Created on : Feb 23, 2017, 9:37:13 AM
    Author     : Thomas Hartmann - tha@cphbusiness.dk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Data from the form:</h1>
        Username <% out.print(session.getAttribute("username")); %><br>
        Password <% out.print(session.getAttribute("password")); %><br>
        Today is <%= (new java.util.Date())%> 
        
    </body>
</html>
