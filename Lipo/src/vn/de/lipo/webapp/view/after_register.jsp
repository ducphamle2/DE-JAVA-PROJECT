<html>
<body>

<p><% if (request.getAttribute("error") != null) {%></p>
        <%= request.getAttribute("error")%>
    <% } else { %>
        ${email}<br> <%-- here we are using EL- expression language--%>
        <%= request.getAttribute("username")%><br> <%-- this is JSP expression - a shorter way to print without using println --%>
    <% } %>
</body>
</html>