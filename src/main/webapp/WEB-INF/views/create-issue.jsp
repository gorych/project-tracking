<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Issue</title>
    <%@include file="fragments/imports.html" %>
</head>
<body>
<div class="main">
    <div class="container">
        <div class="content">
            <sf:form method="POST" modelAttribute="assignment">
                <div class="header">
                    Create Issue
                </div>
                <div class="column">
                    <div class="row">
                        <label for="project">Project</label>
                        <sf:select path="project" items="${projects}" itemValue="id" itemLabel="name"/>
                    </div>
                    <div class="row">
                        <label for="member.employee.id">Assignee</label>
                        <sf:select path="member.employee.id">
                        </sf:select>
                    </div>
                    <div class="row">
                        <label for="description">Description</label>
                        <sf:textarea path="description"/>
                    </div>
                </div>
                <input type="submit" value="Create"/>
            </sf:form>
        </div>
    </div>
</div>
<%@include file="fragments/footer.html" %>
</body>
</html>