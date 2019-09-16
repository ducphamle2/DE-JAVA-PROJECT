<!DOCTYPE html>
<html>
<body>

<p>An absolute URL: <a href="http://localhost:8080/Liquor-v1/cookie.html">click here</a></p>
<p><%= request.getAttribute("email")%></p> <%-- this is JSP expression - a shorter way to print without using println --%>
<p><%= request.getAttribute("password")%></p>
</body>
</html>