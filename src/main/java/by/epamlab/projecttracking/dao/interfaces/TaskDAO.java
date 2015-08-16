package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TaskDAO {

    Task getTaskById(int id);

    List<Task> getAllTasks();

    void addTask(Task task);
}
