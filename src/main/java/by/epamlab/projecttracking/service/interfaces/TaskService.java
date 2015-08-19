package by.epamlab.projecttracking.service.interfaces;


import by.epamlab.projecttracking.domain.Assignment;
import by.epamlab.projecttracking.domain.Task;

public interface TaskService {

    Task getTaskById(int id);

    void insertTask(Task task);

    void insertTask(Assignment assignment);

    void updateTaskStatus(int taskId, int statusId);
}
