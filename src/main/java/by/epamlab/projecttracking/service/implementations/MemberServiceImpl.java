package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.EmployeeDAO;
import by.epamlab.projecttracking.dao.interfaces.MemberDAO;
import by.epamlab.projecttracking.dao.interfaces.ProjectDAO;
import by.epamlab.projecttracking.dao.interfaces.RoleDAO;
import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Member;
import by.epamlab.projecttracking.domain.Project;
import by.epamlab.projecttracking.domain.Role;
import by.epamlab.projecttracking.service.interfaces.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private ProjectDAO projectDAO;

    @Transactional
    public Member getMemberById(int id) {
        return memberDAO.getMemberById(id);
    }

    @Transactional
    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    @Transactional
    public List<Member> getMembersByUsername(String username) {
        Employee employee = employeeDAO.getEmployeeByUsername(username);
        if (employee == null) {
            return new ArrayList<>();
        }
        return memberDAO.getMembersGroupByProject(employee);
    }

    @Transactional
    public Member getMemberByProjectAndEmployeeId(int projectId, int employeeId) {
        return memberDAO.getMemberByProjectAndEmployeeId(projectId, employeeId);
    }

    @Transactional
    public void add(int employeeId, int projectId, int roleId) {
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        Project project = projectDAO.getById(projectId);
        Role role = roleDAO.getById(roleId);
        memberDAO.add(new Member(project, employee, role));
    }

}
