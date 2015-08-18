<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Assign</title>
    <%@include file="fragments/imports.html" %>
</head>
<body>
<div class="main">
    <div class="container">
        <div class="content">
            <sf:form method="POST" modelAttribute="new_assignment">
                <div class="header">
                    Assign
                </div>
                <div class="column">
                    <div class="row">
                        <label class="required" for="member.id">Employee</label>
                        <sf:select path="member.id">
                            <c:forEach var="member" items="${task.project.members}">
                                <sf:option
                                        value="${member.id}">${member.employee.fullName}
                                </sf:option>
                            </c:forEach>
                        </sf:select>
                        <sf:input path="task.id" type="hidden" value="${task.id}"/>
                    </div>
                    <div class="row">
                        <label for="description">Description</label>
                        <sf:textarea path="description"/>
                    </div>
                </div>
                <input type="submit" value="Assign"/>
                <div class="footer">
                    <a href="<c:url value="/user/issues?id=2"/>">Go to issues.</a>
                </div>
            </sf:form>
        </div>
    </div>
</div>
<%@include file="fragments/footer.html" %>
</body>
</html>