package by.epamlab.projecttracking.web.controllers;

import by.epamlab.projecttracking.domain.*;
import by.epamlab.projecttracking.service.interfaces.*;
import by.epamlab.projecttracking.web.AttributeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    ActivityService activityService;

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PositionService positionService;

    @Autowired
    RoleService roleService;

    @ModelAttribute("newEmployee")
    public Employee newEmployee() {
        return new Employee();
    }

    @ModelAttribute("newTask")
    public Task newTask() {
        return new Task();
    }

    @ModelAttribute("newActivity")
    public Activity newActivity() {
        return new Activity();
    }

    @ModelAttribute("newAssignment")
    public Assignment newAssignment() {
        return new Assignment();
    }

    @ModelAttribute("newProject")
    public Project newProject() {
        return new Project();
    }

    @ModelAttribute("newMember")
    public Member newMember() {
        return new Member();
    }

    @ModelAttribute("default_status")
    public String defaultStatus() {
        return "To do";
    }

    @ModelAttribute(AttributeConstants.USERNAME)
    public String username() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @ModelAttribute(AttributeConstants.USER_FULL_NAME)
    public String userFullName() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeService.getEmployeeByUsername(username);
        return (employee != null) ? employee.getFullName() : "not";
    }

    @ModelAttribute(AttributeConstants.PROJECTS)
    public List<Project> allProjects() {
        return projectService.getAllProjects();
    }

    @ModelAttribute(AttributeConstants.EMPLOYEES)
    public List<Employee> allEmployees() {
        return employeeService.getAllEmployees();
    }

    @ModelAttribute(AttributeConstants.ROLES)
    public List<Role> allRoles() {
        return roleService.getAllRoles();
    }

    @ModelAttribute(AttributeConstants.ACTIVITIES)
    public List<Activity> allActivities() {
        return activityService.getAllActivities();
    }

    @ModelAttribute(AttributeConstants.POSITIONS)
    public List<Position> allPositions() {
        return positionService.getAllPositions();
    }

}