<%--
  Created by IntelliJ IDEA.
  User: kkski
  Date: 19.04.22
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Delete scaffold</title>
</head>
<body>
<h2>Delete scaffold</h2>
<h3>Are you sure you want to delete following scaffold?</h3>
<table>
    <tr>
        <th>Site name</th>
        <th>ScaffId</th>
        <th>Name</th>
        <th>Area</th>
        <th>Builder name</th>
        <th>Foreman name</th>
        <th>Date of erection:</th>
        <th>Grade</th>
    </tr>
    <tr>
        <td>${site.name}</td>
        <td>${scaff.scaffoldId}</td>
        <td>${scaff.name}</td>
        <td>${scaff.area.name}</td>
        <td>${scaff.erectorName}</td>
        <td>${scaff.foremanName}</td>
        <td>${scaff.dateOfErection}</td>
        <td>${scaff.scaffoldGrade}</td>

    </tr>
</table>
<h3>By choosing yes you will delete all inspections assosciated with it.</h3>
<hr>
<p><a href="/app/site/${site.id}/scaffold/${scaff.id}/detailsscaffold/delete/confirm">Yes</a></p>
<p><a href="/app/site/${site.id}/scaffold/showscaffolds">No, back to list</a></p>
<hr>

</body>
</html>
