package by.epamlab.projecttracking.web.controllers;

import by.epamlab.projecttracking.domain.Activity;
import by.epamlab.projecttracking.domain.Member;
import by.epamlab.projecttracking.domain.Project;
import by.epamlab.projecttracking.domain.Task;
import by.epamlab.projecttracking.security.UserRoleConstants;
import by.epamlab.projecttracking.service.interfaces.ActivityService;
import by.epamlab.projecttracking.service.interfaces.AssignmentService;
import by.epamlab.projecttracking.service.interfaces.MemberService;
import by.epamlab.projecttracking.service.interfaces.ProjectService;
import by.epamlab.projecttracking.web.AttributeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    ActivityService activityService;

    @Autowired
    ProjectService projectService;

    @Autowired
    MemberService memberService;

    @Secured(value = {UserRoleConstants.USER})
    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String setModelParams(Model model) {
        final int FROM_INDEX = 0;
        final int TO_INDEX = 5;

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Activity> activities = activityService.getFromIndexToIndex(FROM_INDEX, TO_INDEX);
        List<Task> tasks = assignmentService.getAssigneeTasks(username);
        List<Member> members = memberService.getMembersByUsername(username);

        model.addAttribute(AttributeConstants.ACTIVITIES, activities);
        model.addAttribute(AttributeConstants.TASKS, tasks);
        model.addAttribute(AttributeConstants.MEMBERS, members);

        return "dashboard";
    }

    @Secured(value = {UserRoleConstants.USER, UserRoleConstants.ADMIN})
    @RequestMapping(value = {"/activity"}, method = RequestMethod.POST)
    @ResponseBody
    public String loadActivity(@RequestParam(value = "fromIndex") int fromIndex) {
        final int TO_INDEX = fromIndex + 5;
        List<Activity> activities = activityService.getFromIndexToIndex(fromIndex, TO_INDEX);
        return activityService.getJsonString(activities);
    }

    @Secured(UserRoleConstants.USER)
    @RequestMapping(value = {"/projects"}, method = RequestMethod.GET)
    public String showProjects(@RequestParam(value = "id") String id, Model model) {
        int projectId;
        try {
            projectId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return "redirect:/dashboard";
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Project project = projectService.getById(projectId);
        List<Member> members = memberService.getMembersByUsername(username);

        model.addAttribute(AttributeConstants.PROJECT, project);
        model.addAttribute(AttributeConstants.MEMBERS, members);

        return "projects";
    }

    @Secured({UserRoleConstants.TEAM_LEAD, UserRoleConstants.PR_MANAGER})
    @RequestMapping(value = {"/create-issue"}, method = RequestMethod.GET)
    public String showCreateIssueForm(Model model) {
        List<Project> projects = projectService.getAll();

        model.addAttribute(AttributeConstants.ASSIGNMENT, new Member());
        model.addAttribute(AttributeConstants.PROJECTS, projects);

        return "create-issue";
    }
}
