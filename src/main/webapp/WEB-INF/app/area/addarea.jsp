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
    <title>Create an area</title>
</head>
<body>

<div class="container-fluid">
    <div class="tm-site-header tm-mb-1">
        <div class="tm-em-box tm-bg-color-1">
            <h2>Add new area</h2>
        </div>
        <div class="tm-nav-container tm-bg-color-8">
            <div class="nav-item">
                <a href="/app/site/${siteId}/">
                    <span>Back to dashboard</span>
                </a>
            </div>
        </div>
        <div class="tm-em-box tm-bg-color-8">

            <form:form method="POST" modelAttribute="areaForm" class="form-signin">
                <h2 class="form-signin-heading">Provide area name</h2>
                <spring:bind path="name">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="name" class="form-control inputLogin" placeholder="Name of area:"
                                    autofocus="true"></form:input>
                        <form:errors path="name"></form:errors>
                    </div>
                </spring:bind>
                <button type="submit" class="inputLogin">Submit</button>
            </form:form>
        </div>
    </div>


    <footer class="text-center tm-mb-1">
        <p><a href="https://github.com/kkski">github.com/kkski</a></p>
    </footer>
</div>

</body>
</html>
