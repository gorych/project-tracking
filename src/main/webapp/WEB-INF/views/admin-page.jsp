<!DOCTYPE html>
<html>
<head>
    <title>Admin page</title>
    <%@include file="fragments/imports.html" %>
</head>
<body>
<div class="main">
    <%@include file="fragments/header.jsp" %>
    <div class="container">
        <div class="content">
            <div class="modal admin-panel">
                <div class="header">Admin Panel</div>
                <div class="column">
                    <a class="btn" href="<c:url value="/register"/>">Add new user</a>
                    <a class="btn" href="#">Create new project</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="fragments/footer.html" %>
</body>
</html>
