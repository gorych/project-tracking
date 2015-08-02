package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Task;

import java.util.List;

public interface TaskDAO {

    public Task get(int id);

    public List<Task> getAll();

}
