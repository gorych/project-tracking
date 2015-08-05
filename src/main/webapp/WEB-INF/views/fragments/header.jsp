<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <div class="container">
        <div class="main-nav">
            <ul>
                <li>
                    <a href="<c:url value="/dashboard"/>">Dashboard</a>
                </li>
                <li class="dropdown">
                    <a href="#">Projects</a>
                    <ul class="drop-nav">
                        <li><a href="#">Project 1</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#">Issues</a>
                    <ul class="drop-nav">
                        <li><a href="#">I I I</a></li>
                        <li><a href="#">Issue 2</a></li>
                    </ul>
                </li>
                <sec:authorize access="!isAuthenticated()">
                    <li class="btn-login">
                        <a href="<c:url value="/login"/>">Log In</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="btn-login dropdown">
                        <a href="#"> <sec:authentication property="principal.username"/></a>
                        <ul class="drop-nav">
                            <li><a href="<c:url value="/j_spring_security_logout"/>">Log out</a></li>
                        </ul>
                    </li>
                </sec:authorize>

            </ul>
            <div class="btn">
                <a href="">Create Issue</a>
            </div>
        </div>
    </div>
</div>
