<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="header.jspf"%>
    <title>Add Element</title>
</head>
<body>
<form action="." method="post">
    <input type="submit" value="До каталогу">
</form>
    <c:choose>
        <c:when test="${requestScope.get(\"equip\")}">
            <form action="${pageContext.request.contextPath}/do/add" method="post">
                <input type="text" name="name" placeholder="Назва"><br />
                <input type="text" name="description" placeholder="Опис"><br />
                <input type="text" name="price" placeholder="Ціна"><br />
                <input type="text" name="type" placeholder="Категорія"><br />
                <input type="submit" value="Додати">
                <input type="hidden" value="equip" name="hidden">
            </form>
        </c:when>
        <c:when test="${!requestScope.get(\"equip\")}">
            <c:if test="${!requestScope.get(\"edit\")}">
                <form action="${pageContext.request.contextPath}/do/add" method="post">
                    <input type="text" name="name" placeholder="Назва"><br />
                    <input type="text" name="category" placeholder="В якій категорії"><br />
                    <input type="submit" value="Додати">
                    <input type="hidden" value="category" name="hidden">
                </form>
            </c:if>
            <c:if test="${requestScope.get(\"edit\")}">
                <form action="${pageContext.request.contextPath}/do/editCategory" method="post">
                    <input type="text" name="name" placeholder="Назва"><br />
                    <input type="text" name="category" placeholder="В якій категорії"><br />
                    <input type="submit" value="Змінити">
                    <input type="hidden" value="${requestScope.get("query")}" name="hidden">
                </form>
            </c:if>
        </c:when>
    </c:choose>
</body>
<div class="footer">
    <%@include file="footer.jspf"%>
</div>
</html>
