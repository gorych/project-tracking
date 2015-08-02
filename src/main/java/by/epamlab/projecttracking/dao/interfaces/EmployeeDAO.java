package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Employee;

import java.util.List;

public interface EmployeeDAO {

    public Employee get(int id);

    public List<Employee> getAll();

}
