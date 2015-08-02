package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Project;

import java.util.List;

public interface ProjectDAO {

    public Project get(int id);

    public List<Project> getAll();

}
