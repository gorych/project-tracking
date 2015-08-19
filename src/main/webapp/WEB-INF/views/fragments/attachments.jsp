<div class="modal">
    <div class="header">Attachments</div>
    <div class="body">
        <div class="attachments">
            <table>
                <tr>
                    <th>File Name</th>
                    <th>Size</th>
                    <th>Description</th>
                </tr>
                <c:forEach var="attachment" items="${task.project.attachments}">
                    <tr>
                        <td><a href="/user/download?id=${attachment.id}">${attachment.name}</a></td>
                        <td>${attachment.size}</td>
                        <td>${attachment.description}</td>
                    </tr>
                </c:forEach>
            </table>
            <c:if test="${(assignment.member.employee.id eq employee.id) ||
            (employee.position.id eq '2') || (employee.position.id eq '3')}">
                <div class="attachment-btn">
                    <a href="<c:url value="/upload?taskId=${task.id}&projectId=${task.project.id}"/>">Attach</a>
                </div>
            </c:if>
        </div>
    </div>
</div>