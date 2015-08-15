package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.EmployeeDAO;
import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional
    public Employee getById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    @Transactional
    public Employee getByUsername(String username) {
        return employeeDAO.getEmployeeByUsername(username);
    }

    @Transactional
    public List<Employee> getAll() {
        return employeeDAO.getAllEmployees();
    }

    @Transactional
    public void add(Employee employee) {
        employeeDAO.add(employee);
    }

}
