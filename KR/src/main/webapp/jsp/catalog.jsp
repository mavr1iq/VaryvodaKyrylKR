<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    <%@include file="/WEB-INF/style.css"%>
</style>
<head>
    <title>Catalog</title>
    <%@include file="header.jspf"%>
</head>
<body>
<h1><a href="${pageContext.request.contextPath}/do">Catalog</a></h1>
<span class="types">
    <c:forEach var="type" items="${requestScope.get(\"types\")}">
        <div>
        <a href="${pageContext.request.contextPath}/do/sort?${type.getId()}">${type.getName()}</a>
        <c:if test="${sessionScope.get(\"user\").isAdmin()}">
            <form action="${pageContext.request.contextPath}/do/action" method="post" >
                <input type="submit" value="Edit" name="action">
                <input type="submit" value="Delete" name="action">
                <input type="hidden" value="${type.getName()}" name="hidden">
                <input type="hidden" value="category" name="type">
            </form>
        </c:if>
        </div>
    </c:forEach>
</span>
<br>
<c:forEach var="equip" items="${requestScope.get(\"list\")}">
    <div class = field>
        <c:if test="${!empty sessionScope.get(\"user\")}">
            <c:if test="${!sessionScope.get(\"wish\").contains(equip.getId())}">
                <form action="${pageContext.request.contextPath}/do/addToWishList" method="post">
                    <input type="submit" value="*">
                    <input type="hidden" value="${equip.getId()}" name="id">
                </form>&nbsp&nbsp
            </c:if>
        </c:if>
        <p class="catalog">
            ${equip.getType()} <a href="${pageContext.request.contextPath}/do/equipment?${equip.getId()}">${equip.getName()}</a> ${equip.getPrice()}&nbsp грн &nbsp&nbsp
                <c:if test="${sessionScope.get(\"user\").isAdmin()}">
                    <form action="${pageContext.request.contextPath}/do/action" method="post" >
                        <input type="submit" value="Edit" name="action">
                        <input type="submit" value="Delete" name="action">
                        <input type="hidden" value="${equip.getId()}" name="hidden">
                        <input type="hidden" value="equip" name="type">
                    </form>
                </c:if>

        </p>
    </div>
    <br>
</c:forEach>
<c:if test="${sessionScope.get(\"user\").isAdmin()}">
    <form action="${pageContext.request.contextPath}/do/proceedAdd" method="post">
        <input type="submit" value="Add Equipment" name="add">
        <input type="submit" value="Add Category" name="add">
    </form>
</c:if>
</body>
<div class="footer">
    <%@include file="footer.jspf"%>
</div>
</html>
