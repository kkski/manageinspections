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
    <title>Edit a scaffold</title>
</head>
<body>

<div class="container-fluid">
    <div class="tm-site-header tm-mb-1">
        <div class="tm-em-box tm-bg-color-1">
            <h2>Edit a scaffold</h2>
        </div>
        <div class="tm-nav-container tm-bg-color-8">
            <div class="nav-item">
                <a href="/app/site/${chosenSite.id}/scaffold/showscaffolds">
                    <span>Back to scaffold list.</span>
                </a>
            </div>
        </div>
        <div class="tm-em-box tm-bg-color-8">

            <form:form method="POST" modelAttribute="scaffoldForm" class="form-signin">
                <h2 class="form-signin-heading">Provide scaffold details</h2>
                Scaffold identification:
                <hr>
                Current: ${scaff.scaffoldId}
                <spring:bind path="scaffoldId">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="scaffoldId" class="form-control inputLogin" placeholder="${scaff.scaffoldId}"
                                    autofocus="true"></form:input>
                        <form:errors path="scaffoldId"></form:errors>
                    </div>
                </spring:bind>
                Name of scaffold:
                <hr>
                Current: ${scaff.name}
                <spring:bind path="name">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="name" class="form-control inputLogin" placeholder="${scaff.name}"
                                    autofocus="true"></form:input>
                        <form:errors path="name"></form:errors>
                    </div>
                </spring:bind>
                Name of builder:
                <hr>
                Current: ${scaff.erectorName}
                <spring:bind path="erectorName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="erectorName" class="form-control inputLogin" placeholder="${scaff.erectorName}"
                                    autofocus="true"></form:input>
                        <form:errors path="erectorName"></form:errors>
                    </div>
                </spring:bind>
                Name of foreman:
                <hr>
                Current: ${scaff.foremanName}
                <spring:bind path="foremanName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="foremanName" class="form-control inputLogin" placeholder="${scaff.foremanName}"
                                    autofocus="true"></form:input>
                        <form:errors path="foremanName"></form:errors>
                    </div>
                </spring:bind>
                Inspector grade (1-4):
                <hr>
                Current: ${scaff.scaffoldGrade}
                <spring:bind path="scaffoldGrade">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="scaffoldGrade" class="form-control inputLogin" placeholder="${scaff.scaffoldGrade}"
                                    autofocus="true"></form:input>
                        <form:errors path="scaffoldGrade"></form:errors>
                    </div>
                </spring:bind>

                Choose zone:
                <hr>
                Current: ${scaff.area.name}
                <spring:bind path="area">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:select path="area">
                            <form:option value="NONE">--SELECT--</form:option>
                            <c:forEach var="area" items="${areaList}">
                                <form:option value="${area.name}">${area.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </spring:bind>
                Choose date of erection:
                <hr>
                Current: ${scaff.dateOfErection}
                <spring:bind path="dateOfErection">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="date" path="dateOfErection" class="form-control inputLogin" placeholder="Date of erection (DD/MMYYYY)"
                                    autofocus="true"></form:input>
                        <form:errors path="dateOfErection"></form:errors>
                    </div>
                </spring:bind>



                <button type="submit" class="inputLogin">Change</button>
            </form:form>
        </div>
    </div>


    <footer class="text-center tm-mb-1">
        <p><a href="github.com/kkski">github.com/kkski</a></p>
    </footer>
</div>

</body>
</html>
