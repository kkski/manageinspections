<%--
  Created by IntelliJ IDEA.
  User: kkski
  Date: 06.04.22
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Login</title>
</head>
<body>
<div class="container-fluid">
    <div class="tm-site-header tm-mb-1">
        <div class="tm-site-name-container tm-bg-color-1">
            <h1 class="tm-text-white">Manage Inspections v0.0.1</h1>
        </div>

        <div class="tm-em-box tm-bg-color-8">
            <h2 class="tm-mb-2 tm-title-color">Login</h2>
            <form method="post">
                <div><label> User Name : <input class="inputLogin" type="text" name="username"/> </label></div>
                <div><label> Password: <input class="inputLogin"type="password" name="password"/> </label></div>
                <div><input class="inputLogin" type="submit" value="Sign In"/></div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>

        <footer class="text-center tm-mb-1">
            <p>github.com/kkski</p>
        </footer>

    </div>


</div>


</body>
</html>
