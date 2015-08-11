package by.epamlab.projecttracking.web.controllers;

import by.epamlab.projecttracking.domain.Activity;
import by.epamlab.projecttracking.domain.Member;
import by.epamlab.projecttracking.domain.Task;
import by.epamlab.projecttracking.security.UserRoleConstants;
import by.epamlab.projecttracking.service.interfaces.ActivityService;
import by.epamlab.projecttracking.service.interfaces.UserService;
import by.epamlab.projecttracking.web.AttributeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    ActivityService activityService;

    @Autowired
    UserService userService;

    @Secured(value = {UserRoleConstants.USER, UserRoleConstants.ADMIN})
    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public ModelAndView setModelParams() {
        final int FROM_INDEX = 0;
        final int TO_INDEX = 5;

        List<Activity> activities = activityService.getFromIndexToIndex(FROM_INDEX, TO_INDEX);
        List<Task> tasks = userService.getUserTasks();
        List<Member> members = userService.getMembers();

        ModelAndView model = new ModelAndView();
        model.addObject(AttributeConstants.ACTIVITIES, activities);
        model.addObject(AttributeConstants.TASKS, tasks);
        model.addObject(AttributeConstants.MEMBERS, members);
        model.setViewName("dashboard");

        return model;
    }

    @Secured(value = {UserRoleConstants.USER, UserRoleConstants.ADMIN})
    @RequestMapping(value = {"/activity"}, method = RequestMethod.POST)
    @ResponseBody
    public String loadActivity(@RequestParam(value = "fromIndex", required = false) Integer fromIndex) {
        final int TO_INDEX = fromIndex + 5;
        List<Activity> activities = activityService.getFromIndexToIndex(fromIndex, TO_INDEX);
        return activityService.getJsonString(activities);
    }
}
