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
    <title>Register inspector data</title>
</head>
<body>

<div class="container-fluid">
    <div class="tm-site-header tm-mb-1">
        <div class="tm-site-name-container tm-bg-color-1">
            <h1 class="tm-text-white">Manage Inspections v0.0.1</h1>
        </div>

        <div class="tm-em-box tm-bg-color-8">
            <h2 class="tm-mb-2 tm-title-color">Registration</h2>

            <form:form method="POST" modelAttribute="inspectorForm" class="form-signin">
                <h2 class="form-signin-heading">Provide inspector data</h2>
                <spring:bind path="firstName">
                    First name:
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="firstName" class="form-control, inputLogin" placeholder="First name"
                                    autofocus="true"></form:input>
                        <form:errors path="firstName"></form:errors>
                    </div>
                </spring:bind>
                Last name:
                <spring:bind path="lastName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="lastName" class="form-control, inputLogin" placeholder="Last name"
                                    autofocus="true"></form:input>
                        <form:errors path="lastName"></form:errors>
                    </div>
                </spring:bind>
                Choose inspector grade (1-4):
                <spring:bind path="inspectorGrade">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="inspectorGrade" class="form-control, inputLogin" placeholder="Inspector grade (1-4)"
                                    autofocus="true"></form:input>
                        <form:errors path="inspectorGrade"></form:errors>
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
