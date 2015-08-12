<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Project</title>
    <%@include file="fragments/imports.html" %>
</head>
<body>
<div class="main">
    <div class="container">
        <div class="content">
            <sf:form method="POST" modelAttribute="member">
                <div class="header">
                    Add employee to project
                </div>
                <div class="column">
                    <div class="row">
                        <sf:label path="employee.id">Employee</sf:label>
                        <sf:select path="employee.id">
                            <c:forEach var="employee" items="${employees}">
                                <sf:option
                                        value="${employee.id}">${employee.firstname} ${employee.lastname}</sf:option>
                            </c:forEach>
                        </sf:select>
                    </div>
                    <div class="row">
                        <sf:label path="project.id">Project</sf:label>
                        <sf:select path="project.id">
                            <c:forEach var="project" items="${projects}">
                                <sf:option value="${project.id}">${project.name}</sf:option>
                            </c:forEach>
                        </sf:select>
                    </div>
                    <div class="row">
                        <sf:label path="role.id">Role</sf:label>
                        <sf:select path="role.id">
                            <c:forEach var="role" items="${roles}">
                                <sf:option value="${role.id}">${role.name}</sf:option>
                            </c:forEach>
                        </sf:select>
                    </div>
                </div>
                <input type="submit" value="Add"/>
            </sf:form>
        </div>
    </div>
</div>
<div class="footer">
    <div class="text">Copyright &copy; August, 2015 Yahor Semianchenia</div>
</div>
</body>
</html>