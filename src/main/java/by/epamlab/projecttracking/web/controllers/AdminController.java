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
@Secured(UserRoleConstants.ADMIN)
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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm(Model model) {
        List<Position> positions = positionService.getAllPositions();

        model.addAttribute(AttributeConstants.POSITIONS, positions);
        model.addAttribute(AttributeConstants.EMPLOYEE, new Employee());

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addNewEmployee(@Valid Employee employee,
                                 BindingResult bindingResult, Model model) {
        List<Position> positions = positionService.getAllPositions();
        model.addAttribute(AttributeConstants.POSITIONS, positions);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        synchronized (AdminController.class) {
            Employee user = employeeService.getEmployeeByUsername(employee.getLogin());
            if (user != null) {
                model.addAttribute(AttributeConstants.REGISTER_ERROR, "This username already exists.");
                return "register";
            }
            Position position = positionService.getPositionById(employee.getPosition().getId());
            employee.setPosition(position);
            employeeService.add(employee);
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = {"/create-project"}, method = RequestMethod.GET)
    public String goToCreateProject(Model model) {
        model.addAttribute(AttributeConstants.PROJECT, new Project());
        return "create-project";
    }

    @RequestMapping(value = {"/create-project"}, method = RequestMethod.POST)
    public String createProject(@Valid Project project,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-project";
        }

        projectService.add(project);
        return "redirect:/admin";
    }

    @RequestMapping(value = {"/add-employee-to-project"}, method = RequestMethod.GET)
    public String showAddEmployeeToProjectForm(Model model) {
        List<Project> projects = projectService.getAllProjects();
        List<Employee> employees = employeeService.getAllEmployees();
        List<Role> roles = roleService.getAllRoles();

        model.addAttribute(AttributeConstants.MEMBER, new Member());
        model.addAttribute(AttributeConstants.PROJECTS, projects);
        model.addAttribute(AttributeConstants.EMPLOYEES, employees);
        model.addAttribute(AttributeConstants.ROLES, roles);

        return "add-employee-to-project";
    }

    @RequestMapping(value = {"/add-employee-to-project"}, method = RequestMethod.POST)
    public String createProject(@Valid Member member,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-employee-to-project";
        }

        int employeeId = member.getEmployee().getId();
        int projectId = member.getProject().getId();
        int roleId = member.getRole().getId();

        Member existMember = memberService.getMemberByProjectAndEmployeeId(projectId, employeeId);
        if (existMember != null) {
            model.addAttribute(AttributeConstants.ADD_EMPLOYEE_TO_PROJECT_ERROR,
                    "The member already assigned to this project.");
            return showAddEmployeeToProjectForm(model);
        }

        memberService.add(employeeId, projectId, roleId);
        return "redirect:/add-employee-to-project";
    }

}