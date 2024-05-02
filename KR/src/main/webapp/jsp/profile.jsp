<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    <%@include file="/WEB-INF/style.css"%>
</style>
<head>
    <%@include file="header.jspf"%>
    <title>Profile</title>
</head>
<body>
<form action="." method="post">
    <input type="submit" value="До каталогу">
</form>
    <br>
    <h2>
    Ім'я: ${sessionScope.get("user").getName()}
    <br>
        Ви
        <c:if test="${!sessionScope.get(\"user\").isAdmin()}">
            не
        </c:if>
        є адміністратором
    </h2>
<h2>
    Список збереженого:
</h2>
<h3>
    <span class="field">
    <c:forEach var="wish" items="${requestScope.get(\"list\")}">
        <p class="catalog">
            ${wish.getType()} <a href="${pageContext.request.contextPath}/do/equipment?${wish.getName()}">${wish.getName()}</a> ${wish.getPrice()}&nbsp&nbsp
            <form action="removeFromWishList" method="post">
        <input type="submit" value="Delete">
        <input type="hidden" value="${wish.getId()}" name="id">
    </form>
        </p>
    </c:forEach>
    </span>
</h3>
</body>
</html>
