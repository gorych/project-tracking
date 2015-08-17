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
    public Assignment getAssignmentById(int id) {
        return assignmentDAO.getAssignmentById(id);
    }

    @Transactional
    public List<Assignment> getAllAssignments() {
        return assignmentDAO.getAllAssignments();
    }

    @Transactional
    public List<Task> getAssigneeTasks(String username) {
        Employee employee = employeeDAO.getEmployeeByUsername(username);
        List<Member> members = memberDAO.getMembersByEmployee(employee);

        List<Assignment> assignments = new ArrayList<>();
        for (Member member : members) {
            assignments.addAll(assignmentDAO.getAssignmentsByMember(member));
        }

        List<Task> tasks = new ArrayList<>();
        for (Assignment assignment : assignments) {
            int taskId = assignment.getTask().getId();
            tasks.add(taskDAO.getTaskById(taskId));
        }

        return tasks;
    }

    @Transactional
    public Assignment getAssignmentByTaskId(int taskId) {
        return assignmentDAO.getAssignmentsByTaskId(taskId);
    }

    @Transactional
    public void insertAssignment(Assignment assignment) {
        taskDAO.addTask(assignment.getTask());
    }

    @Transactional
    public void updateAssignment(Assignment newAssignment) {
        Assignment assignment = assignmentDAO.getAssignmentsByTaskId(newAssignment.getTask().getId());
        if (assignment == null) {
            assignmentDAO.addAssignment(newAssignment);
        } else {
            assignment.setMember(newAssignment.getMember());
            assignment.setTask(newAssignment.getTask());
            assignment.setDescription(newAssignment.getDescription());
        }
    }

}
