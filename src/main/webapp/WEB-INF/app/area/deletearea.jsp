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
<style><%@include file="/WEB-INF/css/style.css"%></style>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Delete area</title>
</head>
<body>

<div class="container-fluid">
    <div class="tm-site-header tm-mb-1">
        <div class="tm-em-box tm-bg-color-1">
            <h2>Delete area</h2>
        </div>
        <div class="tm-em-box tm-bg-color-8">
            <h3>Are you sure you want to delete following area?</h3>
            <table>
                <tr>
                    <th>Site name</th>
                    <th>Area name</th>
                </tr>
                <tr>
                    <td><h2>${site.name}</h2></td>
                    <td><h2>${area.name}</h2></td>

                </tr>
            </table>
            <h3>By choosing yes you will delete all scaffolds assosciated with it.</h3>
            <hr>
            <p class="inputLogin nav-item"><a href="/app/site/${site.id}/area/${area.id}/deletearea/confirm">Yes</a></p>
            <p class="inputLogin nav-item"><a href="/app/site/${site.id}/area/showareas">No, back to list</a></p>
        </div>
    </div>


    <footer class="text-center tm-mb-1">
        <p><a href="github.com/kkski">github.com/kkski</a></p>
    </footer>
</div>


<hr>

</body>
</html>
