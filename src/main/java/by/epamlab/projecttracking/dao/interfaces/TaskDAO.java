package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Assignment;
import by.epamlab.projecttracking.domain.Task;

import java.util.List;

public interface TaskDAO {

    Task getById(int id);

    List<Task> getAll();
}
