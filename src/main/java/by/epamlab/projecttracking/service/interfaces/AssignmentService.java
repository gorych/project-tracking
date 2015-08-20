package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Assignment;
import by.epamlab.projecttracking.domain.Task;

import java.util.List;

public interface AssignmentService {

    Assignment getAssignmentById(int id);

    List<Assignment> getAllAssignments();

    List<Task> getAssigneeTasks(String username);

    Assignment getAssignmentByTaskId(int taskId);

    void updateAssignment(Assignment assignment);
}
