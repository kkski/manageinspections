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

<c:forEach var="scaff" items="${scaffoldList}">
    ${scaff.name}
    <br>
</c:forEach>
</body>
</html>