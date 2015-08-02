package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Project;

import java.util.List;

public interface ProjectService {

    Project get(int id);

    List<Project> getAll();


}
