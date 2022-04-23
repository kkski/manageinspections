<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Start page</title>
</head>
<body>

<div class="container-fluid">
    <div class="tm-site-header tm-mb-1">
        <div class="tm-site-name-container tm-bg-color-1">
            <h1 class="tm-text-white">Manage Inspections v0.0.1</h1>
        </div>
        <div class="tm-em-box tm-bg-color-8">
            <h2>Hello ${inspectorName}!</h2>
            <h2 class="tm-mb-2 tm-title-color">Choose or add a site:</h2>

            <div>
                <select class="inputLogin" onchange="location = this.value;">
                    <option value="../app/site">Choose a site:</option>
                    <c:forEach var="site" items="${sitesList}">
                        <option value="../app/site/${site.id}">${site.name}</option>

                    </c:forEach>
                </select>
            </div>

            <div style="padding-top:10px">
                <a href="../app/site/add">
                    <span><h2>Add a site</h2></span>
                </a>
            </div>

            <div style="padding-top:10px">
                <a href="../logout">
                    <span><h2>Logout</h2></span>
                </a>
            </div>
        </div>


        <footer class="text-center tm-mb-1">
            <p><a href="https://github.com/kkski">github.com/kkski</a></p>
        </footer>
    </div>



</div>


</body>
</html>
