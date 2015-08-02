package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.EmployeeDAO;
import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional
    public Employee get(int id) {
        return employeeDAO.get(id);
    }

    @Transactional
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }
}
