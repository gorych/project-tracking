package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Task;

import java.util.List;

public interface TaskDAO {

    Task getTaskById(int id);

    List<Task> getAllTasks();
}
