package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Employee;

import java.util.List;

public interface EmployeeDAO {

    Employee getById(int id);

    Employee getByUsername(String login);

    List<Employee> getAll();

    void add(Employee employee);
}
