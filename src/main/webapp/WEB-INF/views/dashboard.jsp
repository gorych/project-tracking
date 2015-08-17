<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <%@include file="fragments/imports.html" %>
</head>
<body>
<div class="main">
    <%@include file="fragments/header.jsp" %>
    <div class="container">
        <div class="content">
            <div class="container">
                <h1>System Dashboard</h1>
                <sec:authorize access="!isAuthenticated()">
                    <h4>Please <a href="<c:url value="/login"/>">log in.</a></h4>
                </sec:authorize>
            </div>
            <sec:authorize access="isAuthenticated()">
                <div class="grid-col">
                    <div class="modal">
                        <%@include file="fragments/activity.jsp"%>
                        <input id="more" class="btn" type="button" value="Show more..."/>
                    </div>
                </div>
                <div class="grid-col">
                    <div class="modal">
                        <div class="header">Assigned to Me</div>
                        <table>
                            <tr>
                                <th>Project</th>
                                <th>Summary</th>
                            </tr>
                            <c:forEach var="task" items="${tasks}">
                                <tr>
                                    <td>${task.project.name}</td>
                                    <td>
                                        <a href="<c:url value="/issues?id=${task.id}"/>">
                                                ${task.description}
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </sec:authorize>
        </div>
    </div>
</div>
<%@include file="fragments/footer.html" %>
</body>
</html>
