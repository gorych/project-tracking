<!DOCTYPE html>
<html>
<head>
    <title>Issues</title>
    <%@include file="fragments/imports.html" %>
</head>
<body>
<div class="main">
    <%@include file="fragments/header.jsp" %>
    <div class="title">
        <div class="container">
            <h1>${task.description}</h1>
        </div>
    </div>
    <div class="container">
        <div class="content">
            <div class="container">
                <sec:authorize access="hasAnyRole('ROLE_PR_MANAGER','ROLE_TEAM_LEAD')">
                    <div class="issue-btn">
                        <a href="<c:url value="/assign?id=${task.id}"/>">Assign</a>
                    </div>
                </sec:authorize>
                <c:choose>
                    <c:when test="${(task.status.id eq '1') || (task.status.id eq '4')}">
                        <c:if test="${assignment.member.employee.id eq employee.id}">
                            <div class="issue-btn ">
                                <a href="/status-switcher?statusCode=2&assignmentId=${assignment.id}">Start
                                    Progress</a>
                            </div>
                        </c:if>
                    </c:when>
                    <c:when test="${task.status.id eq '2'}">
                        <div class="issue-btn ">
                            <a href="/status-switcher?statusCode=3&assignmentId=${assignment.id}">Delivery</a>
                        </div>
                    </c:when>
                    <c:when test="${task.status.id eq '3'}">
                        <div class="issue-btn ">
                            <sec:authorize access="hasAnyRole('ROLE_PR_MANAGER', 'ROLE_TEAM_LEAD')">
                                <a href="/status-switcher?statusCode=4&assignmentId=${assignment.id}">Resolve
                                    Issue</a>
                            </sec:authorize>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="hide-block"></div>
                    </c:otherwise>
                </c:choose>
                <div class="issue-btn issue-right-btn ">
                    <a href="/user/export?id=${task.id}">Export to XML</a>
                </div>
            </div>
            <div class="grid-col">
                <%@include file="fragments/details.jsp" %>
                <%@include file="fragments/attachments.jsp" %>
                <%@include file="fragments/report.jsp" %>
            </div>
            <div class="grid-col">
                <div class="modal">
                    <%@include file="fragments/activity.jsp" %>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="fragments/footer.html" %>
</body>
</html>