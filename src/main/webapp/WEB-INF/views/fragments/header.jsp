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
                <li class="btn-login">
                    <sec:authorize access="!isAuthenticated()">
                        <a href="<c:url value="/login"/>">Log In</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authentication property="${principal.username}"/>
                    </sec:authorize>
                </li>
            </ul>
            <div class="btn">
                <a href="">Create Issue</a>
            </div>
        </div>
    </div>
</div>
