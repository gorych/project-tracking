package by.epamlab.projecttracking.service.implementations;


import by.epamlab.projecttracking.dao.interfaces.AssignmentDAO;
import by.epamlab.projecttracking.dao.interfaces.EmployeeDAO;
import by.epamlab.projecttracking.dao.interfaces.MemberDAO;
import by.epamlab.projecttracking.dao.interfaces.TaskDAO;
import by.epamlab.projecttracking.domain.Assignment;
import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Member;
import by.epamlab.projecttracking.domain.Task;
import by.epamlab.projecttracking.service.interfaces.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentDAO assignmentDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private TaskDAO taskDAO;

    @Transactional
    public Assignment get(int id) {
        return assignmentDAO.getById(id);
    }

    @Transactional
    public List<Assignment> getAll() {
        return assignmentDAO.getAll();
    }

    @Transactional
    public List<Task> getAssigneeTasks(String username) {
        Employee employee = employeeDAO.getByUsername(username);
        if (employee == null) {
            return new ArrayList<>();
        }

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

}
