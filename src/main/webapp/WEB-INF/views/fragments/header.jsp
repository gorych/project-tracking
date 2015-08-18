<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="header">
    <div class="container">
        <div class="nav">
            <ul>
                <li><a href="<c:url value="/user/dashboard"/>">Dashboard</a></li>
                <li>Projects
                    <ul>
                        <c:forEach var="member" items="${user_members}">
                            <li>
                                <a href="<c:url value="/user/projects?id=${member.project.id}"/>">${member.project.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <li><a href="#">Issues</a></li>
                <sec:authorize access="!isAuthenticated()">
                    <li class="login-logout"><a href="<c:url value="/login"/>">Log In</a></li>
                </sec:authorize>
                <sec:authorize
                        access="hasAnyRole('ROLE_PR_MANAGER','ROLE_TEAM_LEAD')">
                    <li class="btn"><a href="<c:url value="/create-issue"/>">Create Issue</a></li>
                </sec:authorize>
            </ul>
            <ul class="login-logout">
                <sec:authorize access="isAuthenticated()">
                    <li>${user_full_name}
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
