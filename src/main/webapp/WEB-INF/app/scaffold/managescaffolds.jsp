<%--
  Created by IntelliJ IDEA.
  User: kkski
  Date: 08.04.22
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Manage scaffolds</title>
</head>
<body>

<div class="container-fluid">
    <div class="tm-site-header tm-mb-1">
        <div class="tm-em-box tm-bg-color-1">
            <h2>Manage scaffolds</h2>
        </div>
        <div class="tm-nav-container tm-bg-color-8">

            <div class="nav-item">
                <a href="/app/site/${site.id}/scaffold/add">
                    <span>Add a scaffold</span>
                </a>
            </div>

            <div class="nav-item">
                <a href="/app/site/${site.id}/">
                    <span>Back to dashboard</span>
                </a>
            </div>


        </div>
        <div class="tm-em-box tm-bg-color-8">
            <div>
                <a href="/app/site/${site.id}/scaffold/find">
                    <span>Find a scaffold by Scaffold Id</span>
                </a>
            </div>
            <div>
                <a href="/app/site/${site.id}/scaffold/group">
                    <span>Find scaffolds by an area</span>
                </a>
            </div>
            <div>
                <a href="/app/site/${site.id}/scaffold/showscaffolds">
                    <span>Show all scaffolds</span>
                </a>
            </div>
        </div>
    </div>


    <footer class="text-center tm-mb-1">
        <p><a href="https://github.com/kkski">github.com/kkski</a></p>
    </footer>
</div>
</body>
</html>
