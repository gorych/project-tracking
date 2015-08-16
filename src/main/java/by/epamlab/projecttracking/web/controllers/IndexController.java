package by.epamlab.projecttracking.web.controllers;


import by.epamlab.projecttracking.security.UserRoleConstants;
import by.epamlab.projecttracking.service.interfaces.EmployeeService;
import by.epamlab.projecttracking.web.AttributeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class IndexController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/switch", method = RequestMethod.GET)
    public String pageSwitcher() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Collection roles = authentication.getAuthorities();

        Iterator itr = roles.iterator();
        if (itr.hasNext()) {
            SimpleGrantedAuthority simpleRole = (SimpleGrantedAuthority) itr.next();
            String role = simpleRole.getAuthority();
            return UserRoleConstants.ADMIN.equals(role) ? "redirect:/admin" : "redirect:/dashboard";
        }

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String goToLogin(@RequestParam(value = "auth_error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute(AttributeConstants.AUTHENTICATION_ERROR, "Wrong username or password.");
        }

        return "login";
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
    public String goToAccessDenied() {
        return "403";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String goToResourceNotFound() {
        return "404";
    }

}
