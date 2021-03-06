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
    <title>Dashboard</title>
</head>
<body>

<div class="container-fluid">
    <div class="tm-site-header tm-mb-1">
        <div class="tm-em-box tm-bg-color-1">
            <h2>Site: ${chosenSite.name}</h2>
        </div>
        <div class="tm-nav-container tm-bg-color-8">

               <div class="nav-item">
                   <a href="/app/site">
                   <span>Start page</span>
                   </a>
               </div>

                <div class="nav-item">
                    <a href="/app/site/${chosenSite.id}/scaffold/manage">
                    <span>Manage scaffolds</span>
                    </a>
                </div>

                   <div class="nav-item">
                       <a href="/app/site/${chosenSite.id}/area/showareas">
                       <span>Manage areas</span>
                        </a>
                   </div>


        </div>
        <div class="tm-em-box tm-bg-color-8">
            <div>
                <p>There are ${scaffoldListCount} scaffolds on this site.</p>
                <p>There are ${unapprovedScaffoldsCount} unapproved scaffolds on this site. <a href="/app/site/${chosenSite.id}/scaffold/showunapproved">See unapproved scaffolds</a></p>
                <p>There are ${approvedScaffoldsCount} approved scaffolds on this site.</p>
            </div>
            <c:if test="${empty lastAddedInspection}">
                There is no inspection yet! Add one to see info on dashboard.
            </c:if>
            <c:if test="${not empty lastAddedInspection}">
                <div class="text-center">
                    <p>Last added inspection was on ${lastAddedInspection.dateOfInspection}, on ${lastInspectedArea.name} zone.</p>
                    <p>Inspected scaffold: ${lastInspectedScaffold.name}, Id: ${lastInspectedScaffold.scaffoldId}</p>
                    <p>Note: ${lastAddedInspection.inspectionMessage}</p>
                    <p>Approval: ${lastAddedInspection.approved}</p>
                </div>
            </c:if>
        </div>
        <footer class="text-center tm-mb-1">
            <p><a href="https://github.com/kkski">github.com/kkski</a></p>
        </footer>
    </div>



</div>


</body>
</html>

