<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Attach file</title>
    <%@include file="fragments/imports.html" %>
</head>
<body>
<div class="main">
    <div class="container">
        <div class="content">
            <sf:form method="post" enctype="multipart/form-data" modelAttribute="attachment">
                <div class="header">
                    Attaching file
                </div>
                <div class="column">
                    <div class="row">
                        <label class="required" for="file">File</label>
                        <input type="file" class="file" id="file" name="file"/>
                    </div>
                    <div class="row">
                        <label for="description">Description</label>
                        <sf:textarea path="description"/>
                    </div>
                    <sf:input path="task.id" type="hidden" value="${task_id}"/>
                    <sf:input path="project.id" type="hidden" value="${project_id}"/>
                </div>
                <c:if test="${not empty upload_error}">
                    <div class="row error">
                        <span>${upload_error}</span>
                    </div>
                </c:if>
                <input type="submit" value="Attach"/>
            </sf:form>
        </div>
    </div>
</div>
<%@include file="fragments/footer.html" %>
</body>
</html>