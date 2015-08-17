<div class="modal">
    <div class="header">Details</div>
    <div class="body">
        <div class="details">
            <table>
                <tr>
                    <th>Assignee</th>
                    <th>Status</th>
                    <th>Plan Start Date</th>
                    <th>Plan End Date</th>
                </tr>
                <tr>`
                    <c:choose>
                        <c:when test="${not empty assignment}">
                            <td>${assignment.member.employee.fullName}</td>
                        </c:when>
                        <c:otherwise>
                            <td>NOBODY</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${task.status.name}</td>
                    <td>${task.psd}</td>
                    <td>${task.pdd}</td>
                </tr>
            </table>
        </div>
    </div>
</div>