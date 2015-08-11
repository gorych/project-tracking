package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.AssignmentDAO;
import by.epamlab.projecttracking.dao.interfaces.EmployeeDAO;
import by.epamlab.projecttracking.dao.interfaces.MemberDAO;
import by.epamlab.projecttracking.dao.interfaces.TaskDAO;
import by.epamlab.projecttracking.domain.Assignment;
import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Member;
import by.epamlab.projecttracking.domain.Task;
import by.epamlab.projecttracking.security.UserRoleConstants;
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
        if (employee == null) {
            throw new UsernameNotFoundException("User with the login " + j_username + " not found!");
        }

        Set<SimpleGrantedAuthority> roles = new HashSet<>();
        if (UserRoleConstants.ADMIN_ROLE_NAME.equals(employee.getPosition().getName())) {
            roles.add(new SimpleGrantedAuthority(UserRoleConstants.ADMIN));
        } else {
            roles.add(new SimpleGrantedAuthority(UserRoleConstants.USER));
        }
        return new User(j_username, employee.getPassword(), roles);
    }

    @Transactional
    public List<Task> getUserTasks() {
        Employee employee = employeeDAO.getByUsername(getUsername());
        List<Member> members = memberDAO.getByEmployee(employee);

        List<Assignment> assignments = new ArrayList<>();
        for (Member member : members) {
            assignments.addAll(assignmentDAO.getByMember(member));
        }

        List<Task> tasks = new ArrayList<>();
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
