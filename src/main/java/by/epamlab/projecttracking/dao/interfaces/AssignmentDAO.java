package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Assignment;
import by.epamlab.projecttracking.domain.Member;

import java.util.List;

public interface AssignmentDAO {

    Assignment getAssignmentById(int id);

    List<Assignment> getAllAssignments();

    List<Assignment> getAssignmentsByMember(Member member);
}
