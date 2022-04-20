<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<h2>Hello ${inspectorName}!</h2>
<h1>Site: ${chosenSite.name}</h1>
<hr>
<div>
    <a href="/app/site">
        <span>Choose another site</span>
    </a>
</div>
<div class="mom">
<div class="child">
    <a href="/app/site/${chosenSite.id}/scaffold/add">
        <span>Add a scaffold</span>
    </a>
</div>
<div class="child">
    <a href="/app/site/${chosenSite.id}/scaffold/showscaffolds">
        <span>Show scaffolds on site</span>
    </a>
</div>
<div class="child">
    <a href="/app/site/${chosenSite.id}/area/add">
        <span>Add an area</span>
    </a>
</div>
</div>

<hr>

<div>
<p>There are ${scaffoldListCount} scaffolds on this site.</p>


</div>


</body>
</html>
