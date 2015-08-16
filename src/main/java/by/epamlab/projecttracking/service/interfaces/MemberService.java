package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Member;

import java.util.List;

public interface MemberService {

    Member getMemberById(int id);

    List<Member> getAllMembers();

    List<Member> getMembersByUsername(String username);

    List<Member> getMembersByProjectId(int projectId);

    Member getMemberByProjectAndEmployeeId(int projectId, int employeeId);

    void add(int employeeId, int projectId, int roleId);

    String getJsonString(List<Member> members);
}
