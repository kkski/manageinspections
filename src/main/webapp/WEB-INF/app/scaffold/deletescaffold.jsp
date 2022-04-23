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
    <title>Delete scaffold</title>
</head>
<body>


<div class="container-fluid">
    <div class="tm-site-header tm-mb-1">
        <div class="tm-em-box tm-bg-color-1">
            <h2>Delete scaffold</h2>
        </div>
        <div class="tm-em-box tm-bg-color-8">
            <h3>Are you sure you want to delete following scaffold?</h3>
            <table>
                <tr>
                <tr><td><b>ScaffId:</b> ${scaff.scaffoldId}</td></tr>
                <tr><td><b>Name:</b> ${scaff.name}</td></tr>
                <tr><td><b>Area:</b> ${scaff.area.name}</td></tr>
                <tr><td><b>Builder name:</b> ${scaff.erectorName}</td></tr>
                <tr><td><b>Foreman name:</b> ${scaff.foremanName}</td></tr>
                <tr><td><b>Date of erection:</b> ${scaff.dateOfErection}</td></tr>
                <tr><td><b>Grade:</b> ${scaff.scaffoldGrade}</td></tr>
                <tr><td><b>Approval:</b> ${scaff.approval}</td></tr>
                </tr>
            </table>
            <hr>
            <h3>By choosing yes you will delete all inspections associated with it.</h3>
            <hr>
            <p><a href="/app/site/${site.id}/scaffold/${scaff.id}/detailsscaffold/delete/confirm">Yes</a></p>
            <p><a href="/app/site/${site.id}/scaffold/showscaffolds">No, back to list</a></p>
            <hr>
        </div>
    </div>


    <footer class="text-center tm-mb-1">
        <p><a href="https://github.com/kkski">github.com/kkski</a></p>
    </footer>
</div>



</body>
</html>
