<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Start page</title>
</head>
<body>
<h2>Hello ${inspectorName}!</h2>

<div>
    <a href="../app/site/add">
        <span>Add a site</span>
    </a>
</div>
<hr>
<div>
    Choose site you work on now:
</div>
<div>

            <select onclick="location = this.value;">
                <option value="disabled" disabled>Choose a site:</option>
            <c:forEach var="site" items="${sitesList}">
                <option value="../app/site/${site.id}">${site.name}</option>

            </c:forEach>
            </select>






<%--    <c:forEach var="site" items="${sitesList}">--%>
<%--        <form:form method="POST" modelAttribute="siteChosen">--%>


<%--            <form:option value="${site.name}" label="#{site.name}"/>--%>


<%--            <button type="submit">Choose</button>--%>
<%--        </form:form>--%>

<%--    </c:forEach>--%>
</div>


</body>
</html>
