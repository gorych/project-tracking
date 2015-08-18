package by.epamlab.projecttracking.web.controllers;

import by.epamlab.projecttracking.domain.*;
import by.epamlab.projecttracking.security.UserRoleConstants;
import by.epamlab.projecttracking.service.interfaces.*;
import by.epamlab.projecttracking.web.AttributeConstants;
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

import javax.validation.Valid;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Controller
public class UserController {

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

    @Secured(value = {UserRoleConstants.USER})
    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String showDashboard(Model model) {
        final int FROM_INDEX = 0;
        final int TO_INDEX = 5;

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Activity> activities = activityService.getActivitiesFromIndexToIndex(FROM_INDEX, TO_INDEX);
        List<Task> tasks = assignmentService.getAssigneeTasks(username);
        List<Member> members = memberService.getMembersByUsername(username);

        model.addAttribute(AttributeConstants.ACTIVITIES, activities);
        model.addAttribute(AttributeConstants.TASKS, tasks);
        model.addAttribute(AttributeConstants.MEMBERS, members);

        return "dashboard";
    }

    @Secured(value = {UserRoleConstants.USER})
    @RequestMapping(value = {"/activity"}, method = RequestMethod.POST)
    @ResponseBody
    public String loadActivity(@RequestParam(value = "fromIndex") int fromIndex) {
        final int TO_INDEX = fromIndex + 5;

        List<Activity> activities = activityService.getActivitiesFromIndexToIndex(fromIndex, TO_INDEX);
        return activityService.getJsonString(activities);
    }

    @Secured(UserRoleConstants.USER)
    @RequestMapping(value = {"/projects"}, method = RequestMethod.GET)
    public String showProjects(@RequestParam(value = "id", required = false) String id, Model model) {
        try {
            int projectId = Integer.parseInt(id);
            Project project = projectService.getProjectById(projectId);

            model.addAttribute(AttributeConstants.PROJECT, project);
            model.addAttribute(AttributeConstants.MEMBERS, project.getMembers());

            return "projects";
        } catch (NumberFormatException e) {
            return "redirect:/dashboard";
        }
    }

    @Secured({UserRoleConstants.TEAM_LEAD, UserRoleConstants.PR_MANAGER})
    @RequestMapping(value = {"/create-issue"}, method = RequestMethod.GET)
    public String showCreateIssueForm() {
        return "create-issue";
    }

    @Secured({UserRoleConstants.TEAM_LEAD, UserRoleConstants.PR_MANAGER})
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
        return "redirect:/dashboard";//TODO redirect on back page
    }

    @Secured(value = {UserRoleConstants.USER})
    @RequestMapping(value = {"/loadProjectTeam"}, method = RequestMethod.GET)
    @ResponseBody
    public String loadProjectTeam(@RequestParam(value = "projectId") int projectId) {
        List<Member> members = memberService.getMembersByProjectId(projectId);
        return memberService.getJsonString(members);
    }

    @Secured(value = {UserRoleConstants.USER})
    @RequestMapping(value = {"/issues"}, method = RequestMethod.GET)
    public String showIssues(@RequestParam(value = "id", required = false) String id, Model model) {
        try {
            int taskId = Integer.parseInt(id);

            Task task = taskService.getTaskById(taskId);
            Assignment assignment = assignmentService.getAssignmentByTaskId(taskId);

            model.addAttribute(AttributeConstants.TASK, task);
            model.addAttribute(AttributeConstants.ASSIGNMENT, assignment);

            return "issues";
        } catch (NumberFormatException e) {
            return "redirect:/dashboard";
        }
    }

    @Secured(value = {UserRoleConstants.USER})
    @RequestMapping(value = {"/report"}, method = RequestMethod.POST)
    public String createReport(@Valid Activity activity, BindingResult bindingResult) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeService.getEmployeeByUsername(username);
        Date date = new Date(Calendar.getInstance().getTime().getTime());

        activity.setFullName(employee.getFullName());
        activity.setDate(date);

        if (bindingResult.hasErrors()) {
            return "issues";
        }

        activityService.addActivity(activity);
        return "redirect:/issues";
    }

    @Secured(value = {UserRoleConstants.USER})
    @RequestMapping(value = {"/assign"}, method = RequestMethod.POST)
    public String showAssignForm(@Valid Assignment assignment) {
        try {
            Member member = memberService.getMemberById(assignment.getMember().getId());
            Task task = taskService.getTaskById(assignment.getTask().getId());

            assignment.setTask(task);
            assignment.setMember(member);

            assignmentService.updateAssignment(assignment);

            return "assign";
        } catch (NumberFormatException e) {
            return "redirect:/issues";
        }
    }

    @Secured(value = {UserRoleConstants.USER})
    @RequestMapping(value = {"/assign"}, method = RequestMethod.GET)
    public String createAssignment(@RequestParam(value = "id", required = false) String id, Model model) {
        try {
            int taskId = Integer.parseInt(id);
            Task task = taskService.getTaskById(taskId);
            model.addAttribute(AttributeConstants.TASK, task);

            return "assign";
        } catch (NumberFormatException e) {
            return "redirect:/issues";
        }
    }

}
