<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<h2>Hello ${inspectorName}!</h2>
<br>
<h2>Site: ${chosenSite.name}!</h2>

<div>
    <a href="/app/scaffold/add">
        <span>Add a scaffold</span>
    </a>
</div>
<div>
    <a href="/app/scaffold/add">
        <span>Add an scaffold</span>
    </a>
</div>

<hr>

<div>



</div>


</body>
</html>
