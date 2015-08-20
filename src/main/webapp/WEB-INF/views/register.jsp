<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <%@include file="fragments/imports.html" %>
</head>
<body>
<div class="main">
    <div class="container">
        <div class="content">
            <sf:form class="register-form" method="POST" modelAttribute="employee">
                <div class="header">
                    Registration
                </div>
                <div class="column">
                    <div class="row">
                        <label class="required" for="firstname">Firstname</label>
                        <sf:input path="firstname" size="20"/>
                        <div class="row error">
                            <sf:errors path="firstname"/>
                        </div>
                    </div>
                    <div class="row">
                        <label class="required" for="lastname">Lastname</label>
                        <sf:input path="lastname" size="20"/>
                        <div class="row error">
                            <sf:errors path="lastname"/>
                        </div>
                    </div>
                    <div class="row">
                        <label class="required" for="login">Username</label>
                        <sf:input path="login" size="15" maxlength="15"/>
                        <div class="row error">
                            <sf:errors path="login"/>
                        </div>
                    </div>
                    <div class="row">
                        <label class="required" for="password">Password</label>
                        <sf:input path="password" size="15" showPassword="flase"/>
                        <div class="row error">
                            <sf:errors path="password"/>
                        </div>
                    </div>
                    <div class="row">
                        <label for="position.id">Position</label>
                        <sf:select path="position.id" items="${positions}" itemValue="id" itemLabel="name"/>
                    </div>
                    <c:if test="${not empty register_error}">
                        <div class="row error">
                            <c:out value="${register_error}"/>
                        </div>
                    </c:if>
                </div>
                <input type="submit" value="Sign Up"/>
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