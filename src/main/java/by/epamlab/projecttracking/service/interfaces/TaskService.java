package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Task;

import java.util.List;

public interface TaskService {

    Task get(int id);

    List<Task> getAll();


}
