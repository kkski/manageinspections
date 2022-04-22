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
    <title>Create an inspection</title>
</head>
<body>

<div class="container-fluid">
    <div class="tm-site-header tm-mb-1">
        <div class="tm-em-box tm-bg-color-1">
            <h2>Create a new scaffold</h2>
        </div>
        <div class="tm-nav-container tm-bg-color-8">
            <div class="nav-item">
                <a href="/app/site/${siteId}/">
                    <span>Back to dashboard</span>
                </a>
            </div>
        </div>
        <div class="tm-em-box tm-bg-color-8">


            <h2>Inspection for scaffold:</h2>
            <table>
                <tr>
                    <th>ScaffId</th>
                    <th>Name</th>
                    <th>Area</th>
                    <th>Date of erection:</th>
                    <th>Approval</th>
                </tr>
                    <tr>
                        <td>${scaff.scaffoldId}</td>
                        <td>${scaff.name}</td>
                        <td>${scaff.area.name}</td>
                        <td>${scaff.dateOfErection}</td>
                        <td>${scaff.approval}</td>
                    </tr>

            </table>



            <form:form method="POST" modelAttribute="inspectionForm" class="form-signin" action="">
                <h2 class="form-signin-heading">Provide inspection details</h2>
                <hr>
                <h4>Date of inspection:</h4>
                <spring:bind path="dateOfInspection">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="date" path="dateOfInspection" class="form-control inputLogin" placeholder="Date of inspection:"
                                    autofocus="true"></form:input>
                        <form:errors path="dateOfInspection"></form:errors>
                    </div>
                </spring:bind>
                <h4>Inspection remarks:</h4>
                <spring:bind path="inspectionMessage">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="inspectionMessage" class="form-control inputLogin" placeholder="Inspection remarks:"
                                    autofocus="true"></form:input>
                        <form:errors path="inspectionMessage"></form:errors>
                    </div>
                </spring:bind>
                <spring:bind path="approved">
                    <h4>Approved?</h4>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:checkbox path="approved" class="form-control inputLogin" placeholder="Approved?"
                        ></form:checkbox>
                        <form:errors path="approved"></form:errors>
                    </div>
                </spring:bind>


                <button type="submit" class="inputLogin">Submit</button>
            </form:form>
        </div>
    </div>


    <footer class="text-center tm-mb-1">
        <p><a href="github.com/kkski">github.com/kkski</a></p>
    </footer>
</div>


</body>
</html>
