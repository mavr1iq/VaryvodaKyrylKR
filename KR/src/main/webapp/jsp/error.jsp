<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error!</title>
</head>
<body>
    <%@include file="header.jspf"%>
    <form action="." method="post">
        <input type="submit" value="До каталогу">
    </form>
    <br>
    Помилка '<c:out value="${requestScope.get(\"error\")}"/>'
</body>
</html>
