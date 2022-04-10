<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Create an inspection</title>
</head>
<body>
<h2>Inspection for scaffold:</h2>
<table>
    <tr>
        <th>ScaffId</th>
        <th>Name</th>
        <th>Area</th>
        <th>Builder name</th>
        <th>Foreman name</th>
        <th>Date of erection:</th>
        <th>Grade</th>
    </tr>
    <tr>
        <td>${scaff.scaffoldId}</td>
        <td>${scaff.name}</td>
        <td>${scaff.area.name}</td>
        <td>${scaff.erectorName}</td>
        <td>${scaff.foremanName}</td>
        <td>${scaff.dateOfErection}</td>
        <td>${scaff.scaffoldGrade}</td>
    </tr>
</table>

<form:form method="POST" modelAttribute="inspectionForm" class="form-signin" action="">
    <h2 class="form-signin-heading">Provide inspection details</h2>
    <h4>Date of inspection:</h4>
    <spring:bind path="dateOfInspection">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="date" path="dateOfInspection" class="form-control" placeholder="Date of inspection:"
                        autofocus="true"></form:input>
            <form:errors path="dateOfInspection"></form:errors>
        </div>
    </spring:bind>
    <h4>Inspection remarks:</h4>
    <spring:bind path="inspectionMessage">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="inspectionMessage" class="form-control" placeholder="Inspection remarks:"
                        autofocus="true"></form:input>
            <form:errors path="inspectionMessage"></form:errors>
        </div>
    </spring:bind>
    <spring:bind path="approved">
        <h4>Approved?</h4>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:checkbox path="approved" class="form-control" placeholder="Approved?"
                        ></form:checkbox>
            <form:errors path="approved"></form:errors>
        </div>
    </spring:bind>


    <button type="submit">Submit</button>
</form:form>
</body>
</html>
