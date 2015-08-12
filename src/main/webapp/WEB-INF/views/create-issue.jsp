<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Create Issue</title>
  <%@include file="fragments/imports.html" %>
</head>
<body>
<div class="main">
  <div class="container">
    <div class="content">
      <sf:form method="POST" modelAttribute="task">
        <div class="header">
          Create Issue
        </div>
        <div class="column">
          <div class="row">
            <sf:label path="">Project</sf:label>
            <sf:select path="">
            </sf:select>
          </div>
          <div class="row">
            <sf:label path="">Assignee</sf:label>
            <sf:select path="">
            </sf:select>
          </div>
          <div class="row">
            <sf:label path="lastname">Description</sf:label>
            <sf:input type="textarea" path="lastname"/>
          </div>

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