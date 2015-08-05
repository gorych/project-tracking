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

    public UserDetails loadUserByUsername(String j_username) throws UsernameNotFoundException {
        Employee employee = employeeService.getByUsername(j_username);
        Set<SimpleGrantedAuthority> roles = new HashSet<SimpleGrantedAuthority>();

        String anonymous = UserRole.ANONYMOUS.name();
        String admin = UserRole.ADMIN.name();
        String user = UserRole.USER.name();
        
        if (employee == null) {
            roles.add(new SimpleGrantedAuthority(anonymous));
            return new User(anonymous, anonymous, roles);
        }

        if (admin.equals(employee.getPosition().getName())) {
            roles.add(new SimpleGrantedAuthority(admin));
        } else {
            roles.add(new SimpleGrantedAuthority(user));
        }
        return new User(j_username, employee.getPassword(), roles);
    }
}
