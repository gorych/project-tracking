package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getById(int id);

    Employee getByUsername(String username);

    List<Employee> getAll();

}
