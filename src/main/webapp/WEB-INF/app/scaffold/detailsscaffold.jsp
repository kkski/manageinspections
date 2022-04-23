<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Manage inspections of scaffold</title>
</head>
<body>

<div class="container-fluid">
    <div class="tm-site-header tm-mb-1">
        <div class="tm-em-box tm-bg-color-1">
            <h2>Manage scaffolds</h2>
        </div>
        <div class="tm-nav-container tm-bg-color-8">

            <div class="nav-item text-center">
                <a href="/app/site/${siteId}/scaffold/${scaff.id}/detailsscaffold/delete">
                    <span>Delete scaffold</span>
                </a>
            </div>

            <div class="nav-item text-center">
                <a href="/app/site/${siteId}/scaffold/${scaff.id}/detailsscaffold/edit">
                    <span>Edit scaffold</span>
                </a>
            </div>

            <div class="nav-item text-center">
                <a href="/app/site/${siteId}/scaffold/showscaffolds">
                    <span>Back to scaffold list.</span>
                </a>
            </div>

        </div>
        <div class="tm-em-box tm-bg-color-8">
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

            <div>
                <a href="/app/site/${siteId}/scaffold/${scaff.id}/inspection/add/">
                    <span>Add new inspection</span>
                </a>
            </div>
            <hr>
            <c:if test="${fn:length(inspectionList) < 1}">
                <p class="text-center"> There are no inspections yet! Add one to see info. </p>
            </c:if>
            <c:if test="${fn:length(inspectionList) > 0}">
            <h2>Inspections</h2>
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
                        <td><a href="/app/site/${siteId}/scaffold/${scaff.id}/inspection/${inspection.id}/delete">Delete inspection</a></td>
                        <td><a href="/app/site/${siteId}/scaffold/${scaff.id}/inspection/${inspection.id}/edit">Edit inspection</a></td>
                    </tr>
                </c:forEach>
                </c:if>


            </table>
        </div>
        <footer class="text-center tm-mb-1">
            <p><a href="https://github.com/kkski">github.com/kkski</a></p>
        </footer>
    </div>



</div>


</body>
</html>