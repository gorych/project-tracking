package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Employee;

import java.util.List;

public interface EmployeeDAO {

    public Employee getById(int id);

    public Employee getByUsername(String id);

    public List<Employee> getAll();

}
