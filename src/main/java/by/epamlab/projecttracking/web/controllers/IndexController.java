package by.epamlab.projecttracking.web.controllers;


import by.epamlab.projecttracking.security.UserRoleConstants;
import by.epamlab.projecttracking.web.AttributeConstants;
import org.springframework.security.access.annotation.Secured;
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
            model.addObject(AttributeConstants.AUTHENTICATION_ERROR, "Wrong username or password.");
        }
        model.setViewName("login");
        return model;
    }

    @Secured(UserRoleConstants.ANONYMOUS)
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String goToDashboard() {
        return "dashboard";
    }

    @Secured(UserRoleConstants.ADMIN)
    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String goToAdminPage() {
        return "admin-page";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return "403";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String resourceNotFound() {
        return "404";
    }

}
