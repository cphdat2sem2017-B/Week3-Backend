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
        <title>reflected XSS</title>
        <style>body{font-family: sans-serif;}</style>
    </head>
    <body>
        <h1>Reflected Cross Site Scripting</h1>
        2 kinds of XSS: reflected and hosted. This is an example of reflected cross site scripting where user input is reflected back to the user without being filtered.
        XSS attacks are prevented in many browsers so check the console to see if the attack was registered.
        IE let it through in my test, Chrome didn´t.
        <form name="form1" action="Control" method="POST">
            <textarea name="txt" rows="5" cols="50" placeholder="Write some malicious javascript here"><script>alert("Hello from the secret script");</script>
            </textarea><br>
            <input type="submit" value="Enter data" name="submit" />
        </form>
    </body>
</html>
