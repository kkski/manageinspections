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
 <title>List of areas</title>
</head>
<body>

<a href="/app/site/${siteId}/area/add">
    <span>Add an area</span>
</a>

<h2>Areas list</h2>

<c:forEach var="area" items="${areaList}">
  ${area.name}
    <td><a href="/app/site/${siteId}/area/${area.id}/deletearea">Delete</a></td>
    <td><a href="/app/site/${siteId}/area/${area.id}/editarea">Edit</a></td>
    <br>
</c:forEach>
<a href="/app/site/${siteId}/">Back to dashboard</a>
</body>
</html>
