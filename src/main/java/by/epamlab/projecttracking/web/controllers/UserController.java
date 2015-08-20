package by.epamlab.projecttracking.web.controllers;

import by.epamlab.projecttracking.domain.*;
import by.epamlab.projecttracking.security.UserPosition;
import by.epamlab.projecttracking.security.UserRoleConstants;
import by.epamlab.projecttracking.service.interfaces.*;
import by.epamlab.projecttracking.web.AttributeConstants;
import by.epamlab.projecttracking.web.PageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Controller
@Secured(value = {UserRoleConstants.USER})
@RequestMapping(PageConstants.USER)
public class UserController {

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ActivityService activityService;

    @Autowired
    ProjectService projectService;

    @Autowired
    MemberService memberService;

    @Autowired
    TaskService taskService;

    private String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @RequestMapping(value = {"/" + PageConstants.DASHBOARD}, method = RequestMethod.GET)
    public String showDashboard(Model model) {
        final int FROM_INDEX = 0;
        final int TO_INDEX = 5;

        List<Activity> activities = activityService.getActivitiesFromIndexToIndex(FROM_INDEX, TO_INDEX);
        List<Member> members = memberService.getMembersByUsername(getUsername());
        List<Task> tasks = assignmentService.getAssigneeTasks(getUsername());

        model.addAttribute(AttributeConstants.ACTIVITIES, activities);
        model.addAttribute(AttributeConstants.USER_MEMBERS, members);
        model.addAttribute(AttributeConstants.TASKS, tasks);

        return PageConstants.DASHBOARD;
    }

    @RequestMapping(value = {"/" + PageConstants.ACTIVITY}, method = RequestMethod.POST)
    @ResponseBody
    public String loadActivity(@RequestParam(value = "fromIndex") int fromIndex) {
        final int TO_INDEX = fromIndex + 5;

        List<Activity> activities = activityService.getActivitiesFromIndexToIndex(fromIndex, TO_INDEX);
        return activityService.getJsonString(activities);
    }

    @RequestMapping(value = {"/" + PageConstants.PROJECTS}, method = RequestMethod.GET)
    public String showProjects(@RequestParam(value = "id", required = true) int projectId, Model model) {
        Project project = projectService.getProjectById(projectId);
        List<Member> members = memberService.getMembersByUsername(getUsername());

        model.addAttribute(AttributeConstants.PROJECT, project);
        model.addAttribute(AttributeConstants.USER_MEMBERS, members);
        model.addAttribute(AttributeConstants.PROJECT_MEMBERS, project.getMembers());

        return PageConstants.PROJECTS;
    }

    @RequestMapping(value = {"/" + PageConstants.ISSUES}, method = RequestMethod.GET)
    public String showIssues(@RequestParam(value = "id", required = false) int taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        Assignment assignment = assignmentService.getAssignmentByTaskId(taskId);
        Employee employee = employeeService.getEmployeeByUsername(getUsername());
        List<Member> members = memberService.getMembersByUsername(getUsername());

        model.addAttribute(AttributeConstants.TASK, task);
        model.addAttribute(AttributeConstants.ASSIGNMENT, assignment);
        model.addAttribute(AttributeConstants.EMPLOYEE, employee);
        model.addAttribute(AttributeConstants.USER_MEMBERS, members);
        model.addAttribute(AttributeConstants.ACTIVITY, new Activity());
        model.addAttribute(AttributeConstants.ACTIVITIES, task.getActivities());

        return PageConstants.ISSUES;
    }

    @RequestMapping(value = {"/" + PageConstants.REPORT}, method = RequestMethod.POST)
    public String createReport(@Valid Activity activity, BindingResult bindingResult,
                               HttpServletRequest request) {
        Employee employee = employeeService.getEmployeeByUsername(getUsername());
        Task task = taskService.getTaskById(activity.getTask().getId());
        Date date = new Date(Calendar.getInstance().getTime().getTime());

        activity.setFullName(employee.getFullName());
        activity.setTask(task);
        activity.setDate(date);

        if (!bindingResult.hasErrors()) {
            activityService.addActivity(activity);
        }

        return "redirect:" + request.getSession().getAttribute(AttributeConstants.PREVIOUS_PAGE);
    }

    @RequestMapping(value = {"/" + PageConstants.EXPORT}, method = RequestMethod.GET)
    @ResponseBody
    public Task exportToXML(@RequestParam(value = "id", required = false) int id) {
        return taskService.getTaskById(id);
    }

    @RequestMapping(value = "/" + PageConstants.STATUS_SWITCHER, method = RequestMethod.GET)
    public String statusSwitcher(@RequestParam(value = "statusCode") int newStatusCode,
                                 @RequestParam(value = "assignmentId") int assignmentId, HttpServletRequest request) {
        final int STATUS_DONE_CODE = 4;
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);

        int employeePosition = assignment.getMember().getEmployee().getPosition().getId();
        int oldStatusCode = assignment.getTask().getStatus().getId();
        int different = newStatusCode - oldStatusCode;

        if (different > 1 && different != -3) {
            return "redirect:" + request.getSession().getAttribute(AttributeConstants.PREVIOUS_PAGE);
        }

        int prManagerPos = UserPosition.PROJECT_MANAGER.ordinal();
        int teamLeadPos = UserPosition.TEAM_LEAD.ordinal();

        if (newStatusCode == STATUS_DONE_CODE && (employeePosition == prManagerPos
                || employeePosition == teamLeadPos)) {
            taskService.updateTaskStatus(assignment.getTask().getId(), STATUS_DONE_CODE);
            return "redirect:" + request.getSession().getAttribute(AttributeConstants.PREVIOUS_PAGE);
        }

        taskService.updateTaskStatus(assignment.getTask().getId(), newStatusCode);
        return "redirect:" + request.getSession().getAttribute(AttributeConstants.PREVIOUS_PAGE);
    }

}
