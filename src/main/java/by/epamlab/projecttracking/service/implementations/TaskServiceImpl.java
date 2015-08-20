package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.AssignmentDAO;
import by.epamlab.projecttracking.dao.interfaces.StatusDAO;
import by.epamlab.projecttracking.dao.interfaces.TaskDAO;
import by.epamlab.projecttracking.domain.Assignment;
import by.epamlab.projecttracking.domain.Status;
import by.epamlab.projecttracking.domain.Task;
import by.epamlab.projecttracking.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    StatusDAO statusDAO;

    @Autowired
    AssignmentDAO assignmentDAO;

    @Transactional
    public Task getTaskById(int id) {
        return taskDAO.getTaskById(id);
    }

    @Transactional
    public void insertTask(Task task) {
        Status status = statusDAO.getDefaultStatus();
        task.setStatus(status);
        taskDAO.insertTask(task);
    }

    @Transactional
    public void addTask(Assignment assignment) {
        Status status = statusDAO.getDefaultStatus();

        Task task = assignment.getTask();
        task.setStatus(status);

        taskDAO.insertTask(task);
        assignmentDAO.insertAssignment(assignment);
    }

    @Transactional
    public void updateTaskStatus(int taskId, int statusId) {
        Task task = taskDAO.getTaskById(taskId);
        Status status = statusDAO.getStatusById(statusId);
        task.setStatus(status);
    }
}
