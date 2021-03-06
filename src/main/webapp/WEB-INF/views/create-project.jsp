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
            <sf:form class="create-project" method="POST" modelAttribute="project">
                <div class="header">
                    Create Project
                </div>
                <div class="column">
                    <div class="row">
                        <sf:label cssClass="required" path="name">Name</sf:label>
                        <sf:input path="name"/>
                        <div class="row error">
                            <sf:errors path="name"/>
                        </div>
                    </div>
                    <div class="row">
                        <sf:label path="description">Description</sf:label>
                        <sf:textarea path="description"/>
                    </div>
                </div>
                <input type="submit" value="Create"/>
                <div class="footer">
                    <a href="<c:url value="/admin-panel"/>">Go to admin panel.</a>
                </div>
            </sf:form>
        </div>
    </div>
</div>
<%@include file="fragments/footer.html" %>
</body>
</html>