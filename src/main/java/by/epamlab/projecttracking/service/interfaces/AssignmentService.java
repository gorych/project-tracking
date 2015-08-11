package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Assignment;
import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Task;

import java.util.List;

public interface AssignmentService {

    Assignment get(int id);

    List<Assignment> getAll();

    List<Task> getAssigneeTasks(String username);

}
