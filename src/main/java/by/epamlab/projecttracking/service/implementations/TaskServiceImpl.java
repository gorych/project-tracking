package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.TaskDAO;
import by.epamlab.projecttracking.domain.Task;
import by.epamlab.projecttracking.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Transactional
    public Task get(int id) {
        return taskDAO.get(id);
    }

    @Transactional
    public List<Task> getAll() {
        return taskDAO.getAll();
    }

}
