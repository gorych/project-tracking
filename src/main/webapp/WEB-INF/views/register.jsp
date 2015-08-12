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
                        <sf:label cssClass="required" path="firstname">Firstname</sf:label>
                        <sf:input path="firstname" size="20"/>
                        <div class="row error">
                            <sf:errors path="firstname"/>
                        </div>
                    </div>
                    <div class="row">
                        <sf:label cssClass="required" path="lastname">Lastname</sf:label>
                        <sf:input path="lastname" size="20"/>
                        <div class="row error">
                            <sf:errors path="lastname"/>
                        </div>
                    </div>
                    <div class="row">
                        <sf:label cssClass="required" path="login">Username</sf:label>
                        <sf:input path="login" size="15" maxlength="15"/>
                        <div class="row error">
                            <sf:errors path="login"/>
                        </div>
                    </div>
                    <div class="row">
                        <sf:label cssClass="required" path="password">Password</sf:label>
                        <sf:input path="password" size="15" showPassword="flase"/>
                        <div class="row error">
                            <sf:errors path="password"/>
                        </div>
                    </div>
                    <div class="row">
                        <sf:label path="position.id">Position</sf:label>
                        <sf:select path="position.id">
                            <c:forEach var="pos" items="${positions}">
                                <sf:option value="${pos.id}">${pos.name}</sf:option>
                            </c:forEach>
                        </sf:select>
                    </div>
                    <c:if test="${not empty user_exist_error}">
                        <div class="row error">
                            <c:out value="${user_exist_error}"/>
                        </div>
                    </c:if>
                </div>
                <input type="submit" value="Sign Up"/>
            </sf:form>
        </div>
    </div>
</div>
<div class="footer">
    <div class="text">Copyright &copy; August, 2015 Yahor Semianchenia</div>
</div>
</body>
</html>