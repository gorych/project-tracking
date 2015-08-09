package by.epamlab.projecttracking.web.controller;

import by.epamlab.projecttracking.domain.Activity;
import by.epamlab.projecttracking.service.interfaces.ActivityService;
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

    @RequestMapping(value = {"/", "/dashboard"}, method = RequestMethod.GET)
    public ModelAndView goToDashboard() {
        final int START_INDEX = 0;

        ModelAndView model = new ModelAndView();
        List<Activity> activities = activityService.getFiveElementsFromIndex(START_INDEX);
        model.addObject("activities", activities);
        model.setViewName("dashboard");

        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public String loadActivity(@RequestParam(value = "fromIndex", required = false) Integer fromIndex) {
        String jsonString = "";
        if (fromIndex != null) {
            List<Activity> activities = activityService.getFiveElementsFromIndex(fromIndex);
            jsonString = activityService.getJsonString(activities);
        }
        return jsonString;
    }
}
