<!DOCTYPE html>
<html>
<head>
    <title>Projects</title>
    <%@include file="fragments/imports.html" %>
</head>
<body>
<div class="main">
    <%@include file="fragments/header.jsp" %>
    <div class="title">
        <div class="container">
            <h1>${project.name}</h1>
        </div>
    </div>
    <div class="container">
        <div class="content">
            <div class="summary">
                <span>Summary</span>

                <div class="description">
                    ${project.description}
                </div>
            </div>
            <div class="grid-col">
                <div class="modal">
                    <div class="header">Report</div>
                    <table>
                        <tr>
                            <th>Firstname</th>
                            <th>Lastname</th>
                            <th>Project role</th>
                        </tr>
                        <c:forEach var="member" items="${members}">
                            <tr>
                                <td>${member.employee.firstname}</td>
                                <td>${member.employee.lastname}</td>
                                <td>${member.role.name}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="grid-col">
                <div class="modal">
                    <div class="header">Project Tasks</div>
                    <table>
                        <tr>
                            <th>Summary</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach var="task" items="${project.tasks}">
                            <tr>
                                <td><a href="<c:url value="/issues?id=${task.id}"/>">
                                        ${task.description}
                                </a></td>
                                <td>${task.status.name}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="fragments/footer.html" %>
</body>
</html>