package by.epamlab.projecttracking.web.controllers;

import by.epamlab.projecttracking.domain.*;
import by.epamlab.projecttracking.security.UserRoleConstants;
import by.epamlab.projecttracking.service.interfaces.*;
import by.epamlab.projecttracking.web.AttributeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
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

    @Secured(UserRoleConstants.ADMIN)
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String createEmployee(Model model) {
        List<Position> positions = positionService.getAll();
        model.addAttribute(AttributeConstants.EMPLOYEE, new Employee());
        model.addAttribute(AttributeConstants.POSITIONS, positions);

        return "register";
    }

    @Secured(UserRoleConstants.ADMIN)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addNewEmployee(@Valid Employee employee,
                                 BindingResult bindingResult, Model model) {
        List<Position> positions = positionService.getAll();
        model.addAttribute(AttributeConstants.POSITIONS, positions);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        synchronized (AdminController.class) {
            Employee user = employeeService.getByUsername(employee.getLogin());
            if (user != null) {
                model.addAttribute(AttributeConstants.USER_EXIST_ERROR, "This username already exists.");
                return "register";
            }
            Position position = positionService.getById(employee.getPosition().getId());
            employee.setPosition(position);
            employeeService.add(employee);
        }
        return "redirect:/admin";
    }

    @Secured(value = {UserRoleConstants.ADMIN})
    @RequestMapping(value = {"/create-project"}, method = RequestMethod.GET)
    public String goToCreateProject(Model model) {
        model.addAttribute(AttributeConstants.PROJECT, new Project());
        return "create-project";
    }

    @Secured(value = {UserRoleConstants.ADMIN})
    @RequestMapping(value = {"/create-project"}, method = RequestMethod.POST)
    public String createProject(@Valid Project project,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-project";
        }
        projectService.add(project);
        return "redirect:/admin";
    }

    @Secured(value = {UserRoleConstants.ADMIN})
    @RequestMapping(value = {"/add-employee-to-project"}, method = RequestMethod.GET)
    public String goToAddEmployeeToProject(Model model) {
        List<Project> projects = projectService.getAll();
        List<Employee> employees = employeeService.getAll();
        List<Role> roles = roleService.getAll();

        model.addAttribute(AttributeConstants.MEMBER, new Member());
        model.addAttribute(AttributeConstants.PROJECTS, projects);
        model.addAttribute(AttributeConstants.EMPLOYEES, employees);
        model.addAttribute(AttributeConstants.ROLES, roles);

        return "add-employee-to-project";
    }

    @Secured(value = {UserRoleConstants.ADMIN})
    @RequestMapping(value = {"/add-employee-to-project"}, method = RequestMethod.POST)
    public String createProject(@Valid Member member,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "add-employee-to-project";
        }

        //TODO на проект не может быть назначен один и тот же человек и админ
        int employeeId = member.getEmployee().getId();
        int projectId = member.getProject().getId();
        int roleId = member.getRole().getId();
        memberService.add(employeeId, projectId, roleId);

        return "redirect:/admin";
    }

}