<%@ page import="java.util.*" %>

<html>
    <body>
        <h1 align="center">
            Liquor recommendation JSP
        </h1><p>
            <% 
            String result = (String) request.getAttribute("types");
            out.println("Type: " + result);
            %>
    </body>
</html>