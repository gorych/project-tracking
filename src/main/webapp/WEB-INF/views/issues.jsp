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
                <c:if test="${username eq assignment.member.employee.login}">
                    <c:if test="${task.status.name eq 'To do'}">
                        <div class="issue-btn">
                            <a href="">Start Progress</a>
                        </div>
                    </c:if>
                    <div class="issue-btn">
                        <a href="#">Delivery</a>
                    </div>
                    <div class="issue-btn">
                        <a href="#">Resolve Issue</a>
                    </div>
                </c:if>
                <div class="issue-btn issue-right-btn ">
                    <a href="<c:url value="/user/showAsXML?id=${task.id}"/>">Export to XML</a>
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