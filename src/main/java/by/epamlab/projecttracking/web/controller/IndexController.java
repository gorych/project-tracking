package by.epamlab.projecttracking.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String goToLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String sendLoginForm() {
        return "dashboard";
    }

    @RequestMapping(value = {"/", "/dashboard"}, method = RequestMethod.GET)
    public String goToDashboard() {
        return "dashboard";
    }

}
