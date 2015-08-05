package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.enums.UserRole;
import by.epamlab.projecttracking.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    EmployeeService employeeService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeService.getByUsername(username);
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
