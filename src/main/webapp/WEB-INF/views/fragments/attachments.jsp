<div class="modal">
    <div class="header">Attachments</div>
    <div class="body">
        <div class="attachments">
            <table>
                <tr>
                    <th>File Name</th>
                    <th>Size, Kb</th>
                    <th>Description</th>
                </tr>
                <c:forEach var="attachment" items="${task.attachments}">
                    <tr>
                        <td><a href="/download?id=${attachment.id}">${attachment.name}</a></td>
                        <td>${attachment.size}</td>
                        <td>${attachment.description}</td>
                    </tr>
                </c:forEach>
            </table>
            <sec:authorize access="hasAnyRole('ROLE_PR_MANAGER', 'ROLE_TEAM_LEAD')
                                                OR ${employee.id eq assignment.member.employee.id}">
                <div class="attachment-btn">
                    <a href="<c:url value="/upload?taskId=${task.id}&projectId=${task.project.id}"/>">Attach</a>
                </div>
            </sec:authorize>
        </div>
    </div>
</div>