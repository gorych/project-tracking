package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Project;

import java.util.List;

public interface ProjectDAO {

    Project getById(int id);

    List<Project> getAll();

    void add(Project project);
}
