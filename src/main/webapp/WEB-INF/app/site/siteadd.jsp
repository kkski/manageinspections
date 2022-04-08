<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Create a site</title>
</head>
<body>

<form:form method="POST" modelAttribute="siteForm" class="form-signin">
    <h2 class="form-signin-heading">Provide site data</h2>
    <spring:bind path="name">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="name" class="form-control" placeholder="Name of site:"
                        autofocus="true"></form:input>
            <form:errors path="name"></form:errors>
        </div>
    </spring:bind>


    <spring:bind path="address">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="address" class="form-control" placeholder="Address of site:"
                        autofocus="true"></form:input>
            <form:errors path="address"></form:errors>
        </div>
    </spring:bind>

    <button type="submit">Submit</button>
</form:form>
</body>
</html>
