package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.StatusDAO;
import by.epamlab.projecttracking.dao.interfaces.TaskDAO;
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

    @Transactional
    public void insertTask(Task task) {
        Status status = statusDAO.getStatusDone();
        task.setStatus(status);
        taskDAO.addTask(task);
    }
}
