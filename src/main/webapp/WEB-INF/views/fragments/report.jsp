<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="modal">
    <div class="header">Report</div>
    <div class="report">
        <sf:form method="POST" modelAttribute="activity" action="/report">
            <div class="column">
                <div class="row">
                    <label class="required" for="comment">Comment</label>
                    <sf:textarea path="comment"/>
                </div>
                <div class="row">
                    <label class="required" for="duration">Duration</label>
                    <sf:input path="duration"/>
                </div>
                <div class="row error">
                    <sf:errors path="comment"/>
                    <sf:errors path="duration"/>
                </div>
                <input type="submit" value="Report">
            </div>
        </sf:form>
    </div>
</div>
