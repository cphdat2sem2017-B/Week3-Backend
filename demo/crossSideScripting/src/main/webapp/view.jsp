<%-- 
    Document   : nonescaped
    Created on : Feb 23, 2017, 5:28:45 PM
    Author     : Thomas Hartmann - tha@cphbusiness.dk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = " java.util.* " %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show result</title>
    </head>
    <body>
<!--    These measures below against XXS attacks are only partial attempts to prevent Cross site scripting.
        in addition use a http filter on the servlet container so that outgoing responses get filtered.
        See this ressource for extended list of preventions-->
        <h1>See result!</h1>
        <!--First use of the user inputted data runs the containing script-->
        <% out.print(session.getAttribute("txt")); %>
        <!--Second use just print the script to the output.-->
        <c:out value="${txt}"></c:out><br>
        <!--Third use prints the script to a input types value attribute:-->
        Input text here: <input type="text" name="username" value="${fn:escapeXml(txt)}"/>
    </body>
</html>
