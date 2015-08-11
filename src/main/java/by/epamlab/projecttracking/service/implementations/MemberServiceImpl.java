package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.EmployeeDAO;
import by.epamlab.projecttracking.dao.interfaces.MemberDAO;
import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Member;
import by.epamlab.projecttracking.service.interfaces.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional
    public Member get(int id) {
        return memberDAO.get(id);
    }

    @Transactional
    public List<Member> getAll() {
        return memberDAO.getAll();
    }

    @Transactional
    public List<Member> getByUsername(String username) {
        Employee employee = employeeDAO.getByUsername(username);
        if (employee == null) {
            return new ArrayList<>();
        }
        List<Member> members = memberDAO.getGroupByProject(employee);
        return members;
    }

}
