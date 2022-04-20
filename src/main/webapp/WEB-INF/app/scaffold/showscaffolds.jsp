<%--
  Created by IntelliJ IDEA.
  User: kkski
  Date: 08.04.22
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>List of scaffolds</title>
</head>
<body>
<h2>Scaffolds list</h2>
<table>
    <tr>
        <th>ScaffId</th>
        <th>Name</th>
        <th>Area</th>
        <th>Builder name</th>
        <th>Foreman name</th>
        <th>Date of erection:</th>
        <th>Grade</th>
        <th>Approval</th>
    </tr>

<%--<form:form method="POST" modelAttribute="employee">--%>
    <c:forEach var="scaff" items="${scaffoldList}">
        <tr>
            <td>${scaff.scaffoldId}</td>
            <td>${scaff.name}</td>
            <td>${scaff.area.name}</td>
            <td>${scaff.erectorName}</td>
            <td>${scaff.foremanName}</td>
            <td>${scaff.dateOfErection}</td>
            <td>${scaff.scaffoldGrade}</td>
            <td>${scaff.approval}</td>
            <td><a href="/app/site/${siteId}/scaffold/${scaff.id}/detailsscaffold">Details</a></td>
        </tr>
<%--</form>--%>
<%--        <form:form method="POST" modelAttribute="site" class="form-signin">--%>
<%--            <spring:bind path="scaffoldId">--%>
<%--    <div class="form-group ${status.error ? 'has-error' : ''}">--%>
<%--                    <form:hidden path="scaffold" value = "${scaff.id}"/>--%>
<%--    </div>--%>

<%--            </spring:bind>--%>
<%--            <button type="submit">Choose</button>--%>
<%--        </form:form>--%>


    </c:forEach>
</form>

</table>
<div>
    <a href="/app/site/${siteId}">
        <span>Return to dashboard</span>
    </a>
</div>
</body>
</html>
