<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Register inspector data</title>
</head>
<body>

<form:form method="POST" modelAttribute="inspectorForm" class="form-signin">
    <h2 class="form-signin-heading">Provide inspector data</h2>
    <spring:bind path="firstName">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="firstName" class="form-control" placeholder="First name"
                        autofocus="true"></form:input>
            <form:errors path="firstName"></form:errors>
        </div>
    </spring:bind>
    <spring:bind path="lastName">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="lastName" class="form-control" placeholder="Last name"
                        autofocus="true"></form:input>
            <form:errors path="lastName"></form:errors>
        </div>
    </spring:bind>
    <spring:bind path="inspectorGrade">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="inspectorGrade" class="form-control" placeholder="Inspector grade (1-4)"
                        autofocus="true"></form:input>
            <form:errors path="inspectorGrade"></form:errors>
        </div>
    </spring:bind>

    <button type="submit">Submit</button>
</form:form>
</body>
</html>
