package by.epamlab.projecttracking.web.controllers;


import by.epamlab.projecttracking.domain.Assignment;
import by.epamlab.projecttracking.domain.Member;
import by.epamlab.projecttracking.domain.Project;
import by.epamlab.projecttracking.domain.Task;
import by.epamlab.projecttracking.security.UserRoleConstants;
import by.epamlab.projecttracking.service.interfaces.AssignmentService;
import by.epamlab.projecttracking.service.interfaces.MemberService;
import by.epamlab.projecttracking.service.interfaces.ProjectService;
import by.epamlab.projecttracking.service.interfaces.TaskService;
import by.epamlab.projecttracking.web.AttributeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Secured({UserRoleConstants.TEAM_LEAD, UserRoleConstants.PR_MANAGER})
public class ProjectLeaderController {

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    MemberService memberService;

    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;

    @RequestMapping(value = {"/create-issue"}, method = RequestMethod.GET)
    public String showCreateIssueForm() {
        return "create-issue";
    }

    @RequestMapping(value = {"/create-issue"}, method = RequestMethod.POST)
    public String createIssue(@Valid Task task, BindingResult bindingResult, Model model) {
        Project project = projectService.getProjectById(task.getProject().getId());
        task.setProject(project);

        if (bindingResult.hasErrors()) {
            return "create-issue";
        }

        long startDate = task.getPsd().getTime();
        long endDate = task.getPdd().getTime();
        if (startDate > endDate) {
            model.addAttribute(AttributeConstants.INPUT_DATE_ERROR, "End date must be larger than start date.");
            return "create-issue";
        }

        taskService.insertTask(task);
        return "success";
    }

    @RequestMapping(value = {"/assign"}, method = RequestMethod.GET)
    @ExceptionHandler({NumberFormatException.class})
    public String createAssignment(@RequestParam(value = "id", required = false) String id, Model model) {
        int taskId = Integer.parseInt(id);
        Task task = taskService.getTaskById(taskId);

        model.addAttribute(AttributeConstants.TASK, task);
        return "assign";
    }

    @RequestMapping(value = {"/assign"}, method = RequestMethod.POST)
    public String showAssignForm(@Valid Assignment assignment, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String previousPage = "redirect:" + session.getAttribute(AttributeConstants.PREVIOUS_PAGE);
        try {
            Member member = memberService.getMemberById(assignment.getMember().getId());
            Task task = taskService.getTaskById(assignment.getTask().getId());

            assignment.setTask(task);
            assignment.setMember(member);

            assignmentService.updateAssignment(assignment);

            return previousPage;
        } catch (NumberFormatException e) {
            return previousPage;
        }
    }

}
