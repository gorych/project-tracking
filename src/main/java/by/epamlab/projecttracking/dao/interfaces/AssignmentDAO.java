package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Assignment;
import by.epamlab.projecttracking.domain.Member;

import java.util.List;

public interface AssignmentDAO {

    Assignment getById(int id);

    List<Assignment> getAll();

    List<Assignment> getByMember(Member member);
}
