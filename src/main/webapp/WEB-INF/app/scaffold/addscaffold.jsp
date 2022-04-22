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
    <title>Create a scaffold</title>
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


            <form:form method="POST" modelAttribute="scaffoldForm" class="form-signin">
                <h2 class="form-signin-heading">Provide scaffold details</h2>
                <spring:bind path="scaffoldId">
                    <p>Scaffold identification:</p>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="scaffoldId" class="form-control inputLogin" placeholder="Scaffold identification:"
                                    autofocus="true"></form:input>
                        <form:errors path="scaffoldId"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="name">
                    <p>Name of scaffold:</p>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="name" class="form-control inputLogin" placeholder="Name of scaffold:"
                                    autofocus="true"></form:input>
                        <form:errors path="name"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="erectorName">
                    <p>Builder:</p>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="erectorName" class="form-control inputLogin" placeholder="Name of builder:"
                                    autofocus="true"></form:input>
                        <form:errors path="erectorName"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="foremanName">
                    <p>Responsible foreman:</p>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="foremanName" class="form-control inputLogin" placeholder="Name of foreman:"
                                    autofocus="true"></form:input>
                        <form:errors path="foremanName"></form:errors>
                    </div>
                </spring:bind>
                <spring:bind path="scaffoldGrade">
                    <p>Scaffolding grade (1-4):</p>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="scaffoldGrade" class="form-control inputLogin" placeholder="Inspector grade (1-4)"
                                    autofocus="true"></form:input>
                        <form:errors path="scaffoldGrade"></form:errors>
                    </div>
                </spring:bind>


                <spring:bind path="area">
                    <p>Choose area:</p>
                    <div class="inputLogin form-group ${status.error ? 'has-error' : ''}">
                        <form:select path="area" class="inputLogin">
                            <form:option value="NONE">--SELECT--</form:option>
                            <c:forEach var="area" items="${areaList}">
                                <form:option value="${area.name}">${area.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </spring:bind>

                <spring:bind path="dateOfErection">
                    <p>Date of release:</p>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="date" path="dateOfErection" class="form-control" placeholder="Date of erection (DD/MMYYYY)"
                                    autofocus="true"></form:input>
                        <form:errors path="dateOfErection"></form:errors>
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
