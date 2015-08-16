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
            <sf:form class="create-issue-form" method="POST" modelAttribute="task">
                <div class="header">
                    Create Issue
                </div>
                <div class="column">
                    <div class="row">
                        <label class="required" for="project.id">Project</label>
                        <sf:select path="project.id" items="${projects}" itemValue="id" itemLabel="name"/>
                    </div>
                    <div class="row">
                        <label class="required" for="psd">Start date</label>
                        <sf:input path="psd" id="psd" onfocus="datepicker('#psdDrop','#psd')"/>
                        <div id="psdDrop"></div>
                        <div class="row error">
                            <sf:errors path="psd"/>
                        </div>
                    </div>
                    <div class="row">
                        <label class="required" for="pdd">End date</label>
                        <sf:input path="pdd" id="pdd" onfocus="datepicker('#pddDrop','#pdd')"/>
                        <div id="pddDrop"></div>
                        <div class="row error">
                            <sf:errors path="pdd"/>
                        </div>
                    </div>
                    <div class="row">
                        <label for="description">Description</label>
                        <sf:textarea path="description"/>
                    </div>
                    <c:if test="${not empty input_date_error}">
                        <div class="row error">
                            <c:out value="${input_date_error}"/>
                        </div>
                    </c:if>
                </div>
                <input type="submit" value="Create"/>
            </sf:form>
        </div>
    </div>
</div>
<%@include file="fragments/footer.html" %>
</body>
</html>