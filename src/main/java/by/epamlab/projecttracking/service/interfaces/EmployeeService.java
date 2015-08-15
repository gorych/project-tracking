package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeById(int id);

    Employee getEmployeeByUsername(String username);

    List<Employee> getAllEmployees();

    void add(Employee employee);
}
