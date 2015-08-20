package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Project;

import java.util.List;

public interface ProjectService {

    Project getProjectById(int id);

    List<Project> getAllProjects();

    void add(Project project);
}
