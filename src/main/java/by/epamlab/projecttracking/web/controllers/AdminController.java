package by.epamlab.projecttracking.web.controllers;

import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Position;
import by.epamlab.projecttracking.service.interfaces.EmployeeService;
import by.epamlab.projecttracking.service.interfaces.PositionService;
import by.epamlab.projecttracking.web.AttributeConstants;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String createEmployee(Model model) {
        List<Position> positions = positionService.getAll();
        model.addAttribute(AttributeConstants.EMPLOYEE, new Employee());
        model.addAttribute(AttributeConstants.POSITIONS, positions);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addNewEmployee(@Valid Employee employee, BindingResult bindingResult, Model model) {
        List<Position> positions = positionService.getAll();
        model.addAttribute(AttributeConstants.POSITIONS, positions);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        Employee user = employeeService.getByUsername(employee.getLogin());
        if (user != null) {
            model.addAttribute(AttributeConstants.USER_EXIST_ERROR, "This username already exists.");
            return "register";
        }
        Position position = positionService.get(employee.getPosition().getId());
        employee.setPosition(position);
        employeeService.add(employee);

        return "redirect:/admin";
    }

}