package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Member;
import by.epamlab.projecttracking.domain.Project;
import by.epamlab.projecttracking.domain.Role;

import java.util.List;

public interface ProjectService {

    Project getById(int id);

    List<Project> getAll();

    void add(Project project);

}
