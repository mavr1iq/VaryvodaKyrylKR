<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    <%@include file="/WEB-INF/style.css"%>
</style>
<head>
    <%@include file="header.jspf"%>
    <title>${requestScope.get("equip").getName()}</title>
</head>
<body>
<form action="." method="post">
    <input type="submit" value="До каталогу">
</form>
<span class="nowrap">
<c:if test="${!empty sessionScope.get(\"user\")}">
    <c:if test="${!sessionScope.get(\"wish\").contains(requestScope.get(\"equip\").getId())}">
        <br>
        <form action="addToWishList" method="post">
            <input type="submit" value="*">
            <input type="hidden" value="${requestScope.get("equip").getId()}" name="id">
        </form>
    </c:if>
</c:if>
    </span>
<h1>Назва товару: ${requestScope.get("equip").getName()}</h1>
<h2>Ціна: ${requestScope.get("equip").getPrice()} грн</h2>
<h2>Категорія: ${requestScope.get("equip").getType()}</h2>
<div class="text-area">
<h3>Опис: ${requestScope.get("equip").getDescription()}</h3>
</div>
<c:if test="${requestScope.get(\"edit\")}">
<h2>Зміна товару</h2>
<br>
<form action="${pageContext.request.contextPath}/do/edit" method="post">
    <label>Назва: </label><input type="text" name="name"><br />
    <label>Опис: </label><input type="text" name="description"><br />
    <label>Ціна: </label><input type="text" name="price"><br />
    <label>Категорія: </label><input type="text" name="type"><br />
    <input type="submit" value="Змінити">
    <input type="hidden" name="hidden" value="${requestScope.get("equip").getName()}">
    </c:if>
</form>
</body>
</html>
