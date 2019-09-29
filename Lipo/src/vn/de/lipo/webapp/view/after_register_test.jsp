<html>
<body>

<p>An absolute URL: <a href="http://localhost:8080/Liquor-v1/cookie.html">click here</a></p>
<p><% if (request.getAttribute("error") != null) {%></p>
        <%= request.getAttribute("error")%>
    <% } else { %>
        <%= request.getAttribute("email")%><br><%-- this is JSP expression - a shorter way to print without using println --%>
        <%= request.getAttribute("username")%><br>
    <% } %>
</body>
</html>