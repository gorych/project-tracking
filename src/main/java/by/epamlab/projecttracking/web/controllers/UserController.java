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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Controller
@Secured(value = {UserRoleConstants.USER})
@RequestMapping("/user")
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

    private String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String showDashboard(Model model) {
        final int FROM_INDEX = 0;
        final int TO_INDEX = 5;

        List<Activity> activities = activityService.getActivitiesFromIndexToIndex(FROM_INDEX, TO_INDEX);
        List<Member> members = memberService.getMembersByUsername(getUsername());
        List<Task> tasks = assignmentService.getAssigneeTasks(getUsername());

        model.addAttribute(AttributeConstants.ACTIVITIES, activities);
        model.addAttribute(AttributeConstants.USER_MEMBERS, members);
        model.addAttribute(AttributeConstants.TASKS, tasks);

        return "dashboard";
    }

    @RequestMapping(value = {"/activity"}, method = RequestMethod.POST)
    @ResponseBody
    public String loadActivity(@RequestParam(value = "fromIndex") int fromIndex) {
        final int TO_INDEX = fromIndex + 5;

        List<Activity> activities = activityService.getActivitiesFromIndexToIndex(fromIndex, TO_INDEX);
        return activityService.getJsonString(activities);
    }

    @RequestMapping(value = {"/projects"}, method = RequestMethod.GET)
    @ExceptionHandler({NumberFormatException.class})
    public String showProjects(@RequestParam(value = "id", required = false) String id, Model model) {
        int projectId = Integer.parseInt(id);

        Project project = projectService.getProjectById(projectId);
        List<Member> members = memberService.getMembersByUsername(getUsername());

        model.addAttribute(AttributeConstants.PROJECT, project);
        model.addAttribute(AttributeConstants.USER_MEMBERS, members);
        model.addAttribute(AttributeConstants.PROJECT_MEMBERS, project.getMembers());

        return "projects";
    }

    @RequestMapping(value = {"/issues"}, method = RequestMethod.GET)
    @ExceptionHandler({NumberFormatException.class})
    public String showIssues(@RequestParam(value = "id", required = false) String id, Model model) {
        int taskId = Integer.parseInt(id);

        Task task = taskService.getTaskById(taskId);
        Assignment assignment = assignmentService.getAssignmentByTaskId(taskId);
        List<Member> members = memberService.getMembersByUsername(getUsername());

        model.addAttribute(AttributeConstants.TASK, task);
        model.addAttribute(AttributeConstants.ASSIGNMENT, assignment);
        model.addAttribute(AttributeConstants.USER_MEMBERS, members);

        return "issues";
    }

    @RequestMapping(value = {"/report"}, method = RequestMethod.POST)
    public String createReport(@Valid Activity activity, BindingResult bindingResult) {
        Employee employee = employeeService.getEmployeeByUsername(getUsername());
        Date date = new Date(Calendar.getInstance().getTime().getTime());

        activity.setFullName(employee.getFullName());
        activity.setDate(date);

        if (bindingResult.hasErrors()) {
            return "issues";
        }

        activityService.addActivity(activity);
        return "redirect:/issues";
    }

    @RequestMapping(value = {"/showAsXML"}, method = RequestMethod.GET)
    @ExceptionHandler({NumberFormatException.class})
    @ResponseBody
    public Task showAsXML(@RequestParam(value = "id", required = false) int id) {
        System.out.println(taskService.getTaskById(id));
        return taskService.getTaskById(id);
    }

}
