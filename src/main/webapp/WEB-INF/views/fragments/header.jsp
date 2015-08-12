<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <div class="container">
        <div class="nav">
            <ul>
                <li><a href="<c:url value="/dashboard"/>">Dashboard</a></li>
                <li>Projects
                    <ul>
                        <c:forEach var="member" items="${members}">
                            <li><a href="#">${member.project.name}</a></li>
                        </c:forEach>
                    </ul>
                </li>
                <li>Issues
                    <ul>
                        <c:forEach var="member" items="${members}">
                            <li><a href="#">${member.project.name}</a></li>
                        </c:forEach>
                    </ul>
                </li>
                <sec:authorize access="!isAuthenticated()">
                    <li class="login-logout"><a href="<c:url value="/login"/>">Log In</a></li>
                </sec:authorize>
            </ul>
            <ul class="login-logout">
                <sec:authorize access="isAuthenticated()">
                    <li>Yahor Semianchenia
                        <ul>
                            <li><a href="<c:url value="/j_spring_security_logout"/>">Log out</a>
                            </li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</div>
