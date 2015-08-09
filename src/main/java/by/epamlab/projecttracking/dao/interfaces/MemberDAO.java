package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Member;

import java.util.List;

public interface MemberDAO {

    Member get(int id);

    List<Member> getAll();

    List<Member> getByEmployee(Employee employee);

    List<Member> getGroupByProject(Employee employee);

}
