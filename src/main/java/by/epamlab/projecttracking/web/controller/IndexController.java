package by.epamlab.projecttracking.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView goToLogin(@RequestParam(value = "auth_error", required = false) String error) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("auth_error", "Wrong username or password.");
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String goToDashboard() {
        return "dashboard";
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String goToAdminPage() {
        return "admin-page";
    }

}
