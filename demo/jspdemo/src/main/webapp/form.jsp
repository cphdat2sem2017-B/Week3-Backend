<%-- 
    Document   : form
    Created on : Feb 23, 2017, 9:36:50 AM
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
        <h1>Enter data</h1>
        <form name="form1" action="Control" method="POST">
            <input type="text" name="user" value="" />
            <input type="text" name="password" value="" />
            <input type="submit" value="Enter data" name="submit" />
        </form>
    </body>
</html>
