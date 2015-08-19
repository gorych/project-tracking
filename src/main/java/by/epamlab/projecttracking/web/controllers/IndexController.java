package by.epamlab.projecttracking.web.controllers;


import by.epamlab.projecttracking.security.UserRoleConstants;
import by.epamlab.projecttracking.service.interfaces.EmployeeService;
import by.epamlab.projecttracking.service.interfaces.MemberService;
import by.epamlab.projecttracking.web.AttributeConstants;
import by.epamlab.projecttracking.web.PageConstants;
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

    @Autowired
    MemberService memberService;

    @RequestMapping(value = PageConstants.PAGE_SWITCH, method = RequestMethod.GET)
    public String pageSwitcher() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Collection roles = authentication.getAuthorities();

        Iterator itr = roles.iterator();
        if (itr.hasNext()) {
            SimpleGrantedAuthority simpleRole = (SimpleGrantedAuthority) itr.next();
            String role = simpleRole.getAuthority();
            return UserRoleConstants.ADMIN.equals(role) ?
                    "redirect:/" + PageConstants.ADMIN_PANEL :
                    "redirect:/" + PageConstants.USER_DASHBOARD;
        }

        return PageConstants.LOGIN;
    }

    @RequestMapping(value = PageConstants.LOGIN, method = RequestMethod.GET)
    public String goToLogin(@RequestParam(value = "auth_error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute(AttributeConstants.AUTHENTICATION_ERROR, "Wrong username or password.");
        }

        return PageConstants.LOGIN;
    }

    @Secured(UserRoleConstants.ANONYMOUS)
    @RequestMapping(value = PageConstants.ROOT, method = RequestMethod.GET)
    public String goToDashboard() {
        return PageConstants.DASHBOARD;
    }

    @Secured(UserRoleConstants.ADMIN)
    @RequestMapping(value = {PageConstants.ADMIN_PANEL}, method = RequestMethod.GET)
    public String goToAdminPage() {
        return PageConstants.ADMIN_PAGE;
    }

    @RequestMapping(value = PageConstants.ACCESS_DENIED, method = RequestMethod.GET)
    public String goToAccessDenied() {
        return "errors/" + PageConstants.ACCESS_DENIED;
    }

    @RequestMapping(value = PageConstants.RESOURCE_NOT_FOUND, method = RequestMethod.GET)
    public String goToResourceNotFound() {
        return "errors/" + PageConstants.RESOURCE_NOT_FOUND;
    }

}
