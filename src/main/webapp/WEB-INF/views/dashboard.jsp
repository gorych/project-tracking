<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <%@include file="fragments/imports.html" %>
</head>
<body>
<div class="main">
    <%@include file="fragments/header.jsp" %>
    <div class="container">
        <div class="content">
            <h1>System Dashboard</h1>
            <sec:authorize access="!isAuthenticated()">
                <h4>Please <a href="<c:url value="/login"/>">log in.</a></h4>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <div class="grid-col">
                    <div class="modal">
                        <div class="header">Activity Stream</div>
                        <div class="activity">
                            <div class="row">
                                <div class="date-and-time">
                                    06.08.2015
                                    at 14.00 PM
                                </div>
                                <span class="employee">Yahor Semianchenia</span>
                                changed the Assignee to comment your dream and another themes.
                            </div>
                            <div class="row">
                                <div class="date-and-time">
                                    06.08.2015
                                    at 15.00 PM
                                </div>
                                <span class="employee">Maxim Patapenka</span>
                                changed the Assignee to comment your dream and another themes.
                            </div>
                            <div class="row">
                                <div class="date-and-time">
                                    31.12.2015
                                    at 23.00 PM
                                </div>
                                <span class="employee">Raman Novikau</span>
                                changed the Assignee to comment your dream and another themes because this is your dream
                                and
                                I have a lot of money. I'm a student and I don't have money. Changed the Assignee to
                                comment
                                your dream and another themes because this is your dream and I have a lot of money. I'm
                                a
                                student and I don't have money.
                            </div>
                            <div class="row">
                                <div class="date-and-time">
                                    06.08.2015
                                    at 15.00 PM
                                </div>
                                <span class="employee">Yahor Semianchenia</span>
                                changed the Assignee to comment your dream and another themes.
                            </div>
                            <div class="row">
                                <div class="date-and-time">
                                    06.08.2015
                                    at 15.00 PM
                                </div>
                                <span class="employee">Yahor Semianchenia</span>
                                changed the Assignee to comment your dream and another themes.
                            </div>
                        </div>
                        <input class="btn" type="submit" value="Show more...">
                    </div>
                </div>
                <div class="grid-col">
                    <div class="half-column">
                        <div class="modal">
                            <div class="header">Assigned to Me</div>
                            <div class="activity">
                            </div>
                        </div>
                    </div>
                </div>
            </sec:authorize>
        </div>
    </div>
</div>
<%@include file="fragments/footer.html" %>
</body>
</html>
