<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Edit a scaffold</title>
</head>
<body>

<form:form method="POST" modelAttribute="scaffoldForm" class="form-signin">
    <h2 class="form-signin-heading">Provide scaffold details</h2>
    Scaffold identification:
    <spring:bind path="scaffoldId">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="scaffoldId" class="form-control" placeholder="${scaff.scaffoldId}"
                        autofocus="true"></form:input>
            <form:errors path="scaffoldId"></form:errors>
        </div>
    </spring:bind>
    Name of scaffold:
    <spring:bind path="name">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="name" class="form-control" placeholder="${scaff.name}"
                        autofocus="true"></form:input>
            <form:errors path="name"></form:errors>
        </div>
    </spring:bind>
    Name of builder:
    <spring:bind path="erectorName">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="erectorName" class="form-control" placeholder="${scaff.erectorName}"
                        autofocus="true"></form:input>
            <form:errors path="erectorName"></form:errors>
        </div>
    </spring:bind>
    Name of foreman:
    <spring:bind path="foremanName">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="foremanName" class="form-control" placeholder="${scaff.foremanName}"
                        autofocus="true"></form:input>
            <form:errors path="foremanName"></form:errors>
        </div>
    </spring:bind>
    Inspector grade (1-4):
    <spring:bind path="scaffoldGrade">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="scaffoldGrade" class="form-control" placeholder="${scaff.scaffoldGrade}"
                        autofocus="true"></form:input>
            <form:errors path="scaffoldGrade"></form:errors>
        </div>
    </spring:bind>

    Choose zone:
    <spring:bind path="area">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:select path="area">
                <form:option value="NONE">--SELECT--</form:option>
                <c:forEach var="area" items="${areaList}">
                    <form:option value="${area.name}">${area.name}</form:option>
                </c:forEach>
            </form:select>
        </div>
    </spring:bind>
    Choose date of erection:
    <spring:bind path="dateOfErection">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="date" path="dateOfErection" class="form-control" placeholder="Date of erection (DD/MMYYYY)"
                        autofocus="true"></form:input>
            <form:errors path="dateOfErection"></form:errors>
        </div>
    </spring:bind>



    <button type="submit">Change</button>
</form:form>
</body>
</html>
