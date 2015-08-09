package by.epamlab.projecttracking.web.controller;

import by.epamlab.projecttracking.domain.Activity;
import by.epamlab.projecttracking.domain.Member;
import by.epamlab.projecttracking.domain.Task;
import by.epamlab.projecttracking.service.interfaces.ActivityService;
import by.epamlab.projecttracking.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public ModelAndView setModelParams() {
        final int FROM_INDEX = 0;
        final int TO_INDEX = 5;

        List<Activity> activities = activityService.getFromIndexToIndex(FROM_INDEX, TO_INDEX);
        List<Task> tasks = userService.getUserTasks();
        List<Member> members = userService.getMembers();

        ModelAndView model = new ModelAndView();
        model.addObject("activities", activities);
        model.addObject("tasks", tasks);
        model.addObject("members", members);
        model.setViewName("dashboard");

        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public String loadActivity(@RequestParam(value = "fromIndex", required = false) Integer fromIndex) {
        final int TO_INDEX = fromIndex + 5;

        String jsonString = "";
        if (fromIndex != null) {
            List<Activity> activities = activityService.getFromIndexToIndex(fromIndex, TO_INDEX);
            jsonString = activityService.getJsonString(activities);
        }
        return jsonString;
    }
}
