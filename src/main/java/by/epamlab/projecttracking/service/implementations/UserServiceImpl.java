package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.security.UserPosition;
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

    public UserDetails loadUserByUsername(String j_username) throws UsernameNotFoundException {
        Employee employee = employeeService.getByUsername(j_username);
        if (employee == null) {
            throw new UsernameNotFoundException("User with the login " + j_username + " not found!");
        }

        int roleIndex = employee.getPosition().getId() - 1;
        String[] roles = UserPosition.values()[roleIndex].getRole();

        Set<SimpleGrantedAuthority> userRoles = new HashSet<>();
        for (String role : roles) {
            userRoles.add(new SimpleGrantedAuthority(role));
        }

        return new User(j_username, employee.getPassword(), userRoles);
    }

}
