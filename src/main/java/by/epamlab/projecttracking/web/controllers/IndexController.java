package by.epamlab.projecttracking.web.controllers;


import by.epamlab.projecttracking.web.AttributeConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class IndexController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView goToLogin(@RequestParam(value = "auth_error", required = false) String error) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject(AttributeConstants.AUTHENTICATION_ERROR, "Wrong username or password.");
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

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Principal user, Model model) {
        if (user != null) {
            model.addAttribute(AttributeConstants.ACCESS_DENIED_ERROR, "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addAttribute(AttributeConstants.ACCESS_DENIED_ERROR,
                    "You do not have permission to access this page!");
        }
        return "403";

    }


}
