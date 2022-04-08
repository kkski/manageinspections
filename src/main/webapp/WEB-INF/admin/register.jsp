<%--
  Created by IntelliJ IDEA.
  User: kkski
  Date: 08.04.22
  Time: 00:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<form:form method="POST" modelAttribute="userForm" class="form-signin">
    <h2 class="form-signin-heading">Create your account</h2>
    <spring:bind path="username">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="username" class="form-control" placeholder="Username"
                        autofocus="true"></form:input>
            <form:errors path="username"></form:errors>
        </div>
    </spring:bind>

    <spring:bind path="password">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
            <form:errors path="password"></form:errors>
        </div>
    </spring:bind>

    <button type="submit">Submit</button>
</form:form>
</body>
</html>
