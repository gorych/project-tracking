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
                            <c:forEach var="activity" items="${activities}">
                                <div class="row">
                                    <div class="date-and-time">
                                        ${activity.date}
                                    </div>
                                    <span class="employee">
                                        ${activity.member.employee.firstname}&nbsp;
                                        ${activity.member.employee.lastname}
                                    </span>
                                    <c:out value="${activity.comment}"/>
                                </div>
                            </c:forEach>
                        </div>
                        <input id="more" class="btn" type="button" value="Show more..."/>
                    </div>
                </div>
                <div class="grid-col">
                    <div class="half-column">
                        <div class="modal">
                            <div class="header">Assigned to Me</div>
                            <table>
                                <tr>
                                    <th>Column1</th>
                                    <th>Column2</th>
                                    <th>Column3</th>
                                <tr>
                                    <td>This</td>
                                    <td>I will have a text in this column</td>
                                    <td>Small text</td>
                                </tr>
                                <tr>
                                    <td>This</td>
                                    <td>I will have a text in this column</td>
                                    <td>Small text</td>
                                </tr>
                                <tr>
                                    <td>This</td>
                                    <td>I will have a text in this column</td>
                                    <td>Small text</td>
                                </tr>
                                <tr>
                                    <td>This</td>
                                    <td>I will have a text in this column</td>
                                    <td>Small text</td>
                                </tr>
                                <tr>
                                    <td>This</td>
                                    <td>I will have a text in this column</td>
                                    <td>Small text</td>
                                </tr>
                                <tr>
                                    <td>This</td>
                                    <td>I will have a text in this column</td>
                                    <td>Small text</td>
                                </tr>
                                <tr>
                                    <td>This</td>
                                    <td>I will have a text in this column</td>
                                    <td>Small text</td>
                                </tr>
                                <tr>
                                    <td>This</td>
                                    <td>I will have a text in this column</td>
                                    <td>Small text</td>
                                </tr>
                                <tr>
                                    <td>This</td>
                                    <td>I will have a text in this column</td>
                                    <td>Small text</td>
                                </tr>
                                <tr>
                                    <td>This</td>
                                    <td>I will have a text in this column</td>
                                    <td>Small text</td>
                                </tr>
                                <tr>
                                    <td>This</td>
                                    <td>I will have a text in this column</td>
                                    <td>Small text</td>
                                </tr>
                                <tr>
                                    <td>This</td>
                                    <td>I will have a text in this column</td>
                                    <td>Small text</td>
                                </tr>
                                <tr>
                                    <td>This</td>
                                    <td>I will have a text in this column</td>
                                    <td>Small text</td>
                                </tr>
                                <tr>
                                    <td>This</td>
                                    <td>I will have a text in this column</td>
                                    <td>Small text</td>
                                </tr>
                            </table>
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
