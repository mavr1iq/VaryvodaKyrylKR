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
<h1>Catalog</h1>

<c:forEach var="equip" items="${requestScope.get(\"list\")}">
    <div class = field>
        <c:if test="${!empty sessionScope.get(\"user\")}">
            <c:if test="${!sessionScope.get(\"wish\").contains(equip.getId())}">
                <form action="addToWishList" method="post">
                    <input type="submit" value="*">
                    <input type="hidden" value="${equip.getId()}" name="id">
                </form>&nbsp&nbsp
            </c:if>
        </c:if>
        <p class="catalog">
            ${equip.getType()} <a href="${pageContext.request.contextPath}/do/equipment?${equip.getName()}">${equip.getName()}</a> ${equip.getPrice()}&nbsp грн &nbsp&nbsp
                <c:if test="${sessionScope.get(\"user\").isAdmin()}">
                    <form action="${pageContext.request.contextPath}/do/action" method="post" >
                        <input type="submit" value="Edit" name="action">
                        <input type="submit" value="Delete" name="action">
                        <input type="hidden" value="${equip.getName()}" name="hidden">
                    </form>
                </c:if>

        </p>
    </div>
    <br>
</c:forEach>
</body>
<footer>
    <%@include file="footer.jspf"%>
</footer>
</html>
