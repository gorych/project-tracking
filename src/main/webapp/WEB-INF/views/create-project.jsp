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
            <sf:form method="POST" modelAttribute="project">
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
            </sf:form>
        </div>
    </div>
</div>
<div class="footer">
    <div class="text">Copyright &copy; August, 2015 Yahor Semianchenia</div>
</div>
</body>
</html>