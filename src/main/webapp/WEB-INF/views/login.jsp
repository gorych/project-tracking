<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <%@include file="fragments/imports.html" %>
</head>
<body>
<div class="main">
    <div class="container">
        <div class="content">
            <c:url value="/j_spring_security_check" var="login_url"/>
            <form class="login-form" action="${login_url}" method="POST">
                <div class="header">
                    Login
                </div>qee
                <div class="column">
                    <div class="row">
                        <label for="username">Username</label>
                        <input id="username" name="j_username" type="text">
                    </div>
                    <div class="row">
                        <label for="password">Password</label>
                        <input id="password" name="j_password" type="password">
                    </div>
                </div>
                <input type="submit" value="Log In">

                <div class="footer">
                    Need an account?
                    <a href="#">Sign up.</a>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="footer">
    <div class="text">Copyright &copy; August, 2015 Yahor Semianchenia</div>
</div>
</body>
</html>