package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Project;

import java.util.List;

public interface ProjectDAO {

    Project getProjectById(int id);

    List<Project> getAllProjects();

    void insertProject(Project project);
}
