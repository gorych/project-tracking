package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.TaskDAO;
import by.epamlab.projecttracking.domain.Task;
import by.epamlab.projecttracking.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Transactional
    public Task get(int id) {
        return taskDAO.getById(id);
    }

    @Transactional
    public List<Task> getAll() {
        return taskDAO.getAll();
    }

}
