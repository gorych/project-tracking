package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Project;
import by.epamlab.projecttracking.domain.Role;

import java.util.List;

public interface ProjectService {

    Project get(int id);

    List<Project> getAll();

    void add(Project project);

    void addEmployeeToProject(int employeeId, int projectId, int roleId);
}
