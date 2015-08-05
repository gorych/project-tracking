package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.EmployeeDAO;
import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.enums.UserRole;
import by.epamlab.projecttracking.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional
    public Employee getById(int id) {
        return employeeDAO.getById(id);
    }

    @Transactional
    public Employee getByUsername(String username) {
        return employeeDAO.getByUsername(username);
    }

    @Transactional
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = getByUsername(username);
        Set<SimpleGrantedAuthority> roles = new HashSet<SimpleGrantedAuthority>();

        System.out.println(employee);

        if (employee == null) {
            roles.add(new SimpleGrantedAuthority(UserRole.ANONYMOUS.name()));
            return new User("anonymous", "anonymous", roles);
        }

        if (UserRole.ADMIN.name().equals(employee.getPosition().getName())) {
            roles.add(new SimpleGrantedAuthority(UserRole.ADMIN.name()));
        } else {
            roles.add(new SimpleGrantedAuthority(UserRole.USER.name()));
        }
        return new User(username, employee.getPassword(), roles);
    }
}
