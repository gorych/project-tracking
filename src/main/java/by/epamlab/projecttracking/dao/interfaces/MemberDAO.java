package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Member;

import java.util.List;

public interface MemberDAO {

    Member getMemberById(int id);

    List<Member> getAllMembers();

    List<Member> getMembersByEmployee(Employee employee);

    List<Member> getMembersGroupByProject(Employee employee);

    List<Member> getMembersByProjectId(int projectId);

    Member getMemberByProjectAndEmployeeId(int projectId, int employeeId);

    void insertMember(Member member);

}
