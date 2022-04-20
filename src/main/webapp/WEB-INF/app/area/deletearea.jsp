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
    <title>Delete area</title>
</head>
<body>
<h2>Delete area</h2>
<h3>Are you sure you want to delete following area?</h3>
<table>
    <tr>
        <th>Site name</th>
        <th>Area name</th>
    </tr>
    <tr>
        <td>${site.name}</td>
        <td>${area.name}</td>

    </tr>
</table>
<h3>By choosing yes you will delete all scaffolds assosciated with it.</h3>
<hr>
<p><a href="/app/site/${site.id}/area/${area.id}/deletearea/confirm">Yes</a></p>
<p><a href="/app/site/${site.id}/area/showareas">No, back to list</a></p>
<hr>

</body>
</html>
