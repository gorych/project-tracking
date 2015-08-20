package by.epamlab.projecttracking.web.controllers;

import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Member;
import by.epamlab.projecttracking.domain.Position;
import by.epamlab.projecttracking.domain.Project;
import by.epamlab.projecttracking.security.UserRoleConstants;
import by.epamlab.projecttracking.service.interfaces.*;
import by.epamlab.projecttracking.web.AttributeConstants;
import by.epamlab.projecttracking.web.PageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@Secured(UserRoleConstants.ADMIN)
@RequestMapping(PageConstants.ADMIN)
public class AdminController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PositionService positionService;

    @Autowired
    ProjectService projectService;

    @Autowired
    RoleService roleService;

    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/" + PageConstants.REGISTER, method = RequestMethod.GET)
    public String showRegisterForm(Model model) {
        model.addAttribute(AttributeConstants.EMPLOYEE, new Employee());
        return PageConstants.REGISTER;
    }

    @RequestMapping(value = "/" + PageConstants.REGISTER, method = RequestMethod.POST)
    public String addNewEmployee(@Valid Employee employee,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return PageConstants.REGISTER;
        }

        synchronized (AdminController.class) {
            String login = employee.getLogin().toLowerCase();
            Employee user = employeeService.getEmployeeByUsername(login);

            if (user != null) {
                model.addAttribute(AttributeConstants.REGISTER_ERROR, "This username already exists.");
                return PageConstants.REGISTER;
            }

            Position position = positionService.getPositionById(employee.getPosition().getId());
            employee.setPosition(position);
            employee.setLogin(login);
            employeeService.addEmployee(employee);
        }
        return "redirect:/" + PageConstants.ADMIN_PANEL;
    }

    @RequestMapping(value = {"/" + PageConstants.CREATE_PROJECT}, method = RequestMethod.GET)
    public String showCreateProjectForm(Model model) {
        model.addAttribute(AttributeConstants.PROJECT, new Project());
        return "create-project";
    }

    @RequestMapping(value = {"/" + PageConstants.CREATE_PROJECT}, method = RequestMethod.POST)
    public String createProject(@Valid Project project,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return PageConstants.CREATE_PROJECT;
        }

        projectService.add(project);
        return "redirect:/" + PageConstants.ADMIN_PANEL;
    }

    @RequestMapping(value = {"/" + PageConstants.ADD_EMPLOYEE_TO_PROJECT}, method = RequestMethod.GET)
    public String showAddEmployeeToProjectForm(Model model) {
        model.addAttribute(AttributeConstants.MEMBER, new Member());
        return PageConstants.ADD_EMPLOYEE_TO_PROJECT;
    }

    @RequestMapping(value = {"/" + PageConstants.ADD_EMPLOYEE_TO_PROJECT}, method = RequestMethod.POST)
    public String createProject(@Valid Member member, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return PageConstants.ADD_EMPLOYEE_TO_PROJECT;
        }

        int employeeId = member.getEmployee().getId();
        int projectId = member.getProject().getId();
        int roleId = member.getRole().getId();

        Member existMember = memberService.getMemberByProjectAndEmployeeId(projectId, employeeId);
        if (existMember != null) {
            model.addAttribute(AttributeConstants.ADD_EMPLOYEE_TO_PROJECT_ERROR,
                    "The member already assigned to this project.");
            return PageConstants.ADD_EMPLOYEE_TO_PROJECT;
        }

        memberService.addMember(employeeId, projectId, roleId);
        return "redirect:/admin/" + PageConstants.ADD_EMPLOYEE_TO_PROJECT;
    }

}