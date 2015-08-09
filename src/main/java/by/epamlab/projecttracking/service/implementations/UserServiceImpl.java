package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.AssignmentDAO;
import by.epamlab.projecttracking.dao.interfaces.EmployeeDAO;
import by.epamlab.projecttracking.dao.interfaces.MemberDAO;
import by.epamlab.projecttracking.dao.interfaces.TaskDAO;
import by.epamlab.projecttracking.domain.*;
import by.epamlab.projecttracking.domain.enums.UserRole;
import by.epamlab.projecttracking.service.interfaces.EmployeeService;
import by.epamlab.projecttracking.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    MemberDAO memberDAO;

    @Autowired
    AssignmentDAO assignmentDAO;

    @Autowired
    TaskDAO taskDAO;

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

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

    @Transactional
    public List<Task> getUserTasks() {
        Employee employee = employeeDAO.getByUsername(getUsername());
        List<Member> members = memberDAO.getByEmployee(employee);

        List<Assignment> assignments = new ArrayList<Assignment>();
        for (Member member : members) {
            assignments.addAll(assignmentDAO.getByMember(member));
        }

        List<Task> tasks = new ArrayList<Task>();
        for (Assignment assignment : assignments) {
            int taskId = assignment.getTask().getId();
            tasks.add(taskDAO.getById(taskId));
        }

        return tasks;
    }

    @Transactional
    public List<Member> getMembers() {
        Employee employee = employeeDAO.getByUsername(getUsername());
        List<Member> members = memberDAO.getGroupByProject(employee);
        return members;
    }
}
