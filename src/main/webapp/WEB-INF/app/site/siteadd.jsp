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
    <title>Create a site</title>
</head>
<body>

<div class="container-fluid">
    <div class="tm-site-header tm-mb-1">
        <div class="tm-site-name-container tm-bg-color-1">
            <h1 class="tm-text-white">Manage Inspections v0.0.1</h1>
        </div>
        <div class="tm-em-box tm-bg-color-8">
            <h2 class="tm-mb-2 tm-title-color">Create a new site.</h2>

            <div>
                <form:form method="POST" modelAttribute="siteForm" class="form-signin">
                    <h2 class="form-signin-heading">Provide site data</h2>
                    <spring:bind path="name">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="name" class="form-control inputLogin" placeholder="Name of site:"
                                        autofocus="true"></form:input>
                            <form:errors path="name"></form:errors>
                        </div>
                    </spring:bind>


                    <spring:bind path="address">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="address" class="form-control inputLogin" placeholder="Address of site:"
                                        autofocus="true"></form:input>
                            <form:errors path="address"></form:errors>
                        </div>
                    </spring:bind>

                    <button type="submit" class="inputLogin">Submit</button>
                </form:form>
            </div>

            <div style="padding-top:10px">
                <a href="../app/site/add">
                    <span><h2>Add a site</h2></span>
                </a>
            </div>

        </div>
    </div>


    <footer class="text-center tm-mb-1">
        <p><a href="github.com/kkski">github.com/kkski</a></p>
    </footer>
</div>

</body>
</html>
