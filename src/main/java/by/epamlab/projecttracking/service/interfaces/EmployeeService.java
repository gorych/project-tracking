package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Employee;

import java.util.List;

public interface EmployeeService {

    Employee get(int id);

    List<Employee> getAll();


}
