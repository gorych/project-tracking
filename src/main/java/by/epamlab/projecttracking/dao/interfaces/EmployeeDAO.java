package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Employee;

import java.util.List;

public interface EmployeeDAO {

    Employee getEmployeeById(int id);

    Employee getEmployeeByUsername(String username);

    List<Employee> getAllEmployees();

    void add(Employee employee);
}
