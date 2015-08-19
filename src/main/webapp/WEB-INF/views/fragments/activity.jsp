<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">Activity Stream</div>
<div class="body">
    <div class="activity">
        <c:forEach var="activity" items="${activities}">
            <div class="row">
                <div class="date-and-time">
                        ${activity.date} - ${activity.duration} min
                </div>
            <span class="employee">
                    ${activity.fullName}
            </span>
                <c:out value="${activity.comment}"/>
            </div>
        </c:forEach>
    </div>
</div>