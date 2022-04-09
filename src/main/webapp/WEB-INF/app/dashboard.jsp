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
<h1>Site: ${chosenSite.name}</h1>
<div>
    <a href="/start/2">
        <span>Choose another site</span>
    </a>
</div>
<div>
    <a href="/app/scaffold/add">
        <span>Add a scaffold</span>
    </a>
</div>
<div>
    <a href="/app/scaffold/showscaffolds">
        <span>Show scaffolds on site</span>
    </a>
</div>
<div>
    <a href="/app/area/add">
        <span>Add an area</span>
    </a>
</div>

<hr>

<div>



</div>


</body>
</html>
