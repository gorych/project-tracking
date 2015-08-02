package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Assignment;

import java.util.List;

public interface AssignmentDAO {

    public Assignment get(int id);

    public List<Assignment> getAll();

}
