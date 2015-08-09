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
                        <c:forEach  var="member" items="${members}">
                            <li><a href="#">${member.project.name}</a></li>
                        </c:forEach>
                    </ul>
                </li>
                <li>
                    <a href="<c:url value="#"/>">Issues</a>
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
