package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Member;

import java.util.List;

public interface MemberDAO {

    public Member get(int id);

    public List<Member> getAll();

}
