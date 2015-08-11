package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Member;

import java.util.List;

public interface MemberService {

    Member get(int id);

    List<Member> getAll();

    List<Member> getByUsername(String username);
}
