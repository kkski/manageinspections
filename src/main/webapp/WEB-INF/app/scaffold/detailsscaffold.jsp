<%--

  Created by IntelliJ IDEA.
  User: kkski
  Date: 09.04.22
  Time: 03:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Manage inspections of scaffold</title>
</head>
<body>

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
<hr>
<div>
    <a href="../../../app/inspection/add/${scaff.id}">
        <span>Add an inspection</span>
    </a>
</div>
<hr>
    <table>
        <tr>
            <th>Date:</th>
            <th>Message:</th>
            <th>Approval:</th>
        </tr>
        <c:forEach var="inspection" items="${inspectionList}">
            <tr>
                <td>${inspection.dateOfInspection}</td>
                <td>${inspection.inspectionMessage}</td>
                <td>${inspection.approved}</td>
            </tr>
        </c:forEach>



</table>
</body>
</html>