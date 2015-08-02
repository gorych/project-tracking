package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Assignment;

import java.util.List;

public interface AssignmentService {

    Assignment get(int id);

    List<Assignment> getAll();

}
