package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.MemberDAO;
import by.epamlab.projecttracking.domain.Member;
import by.epamlab.projecttracking.service.interfaces.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Transactional
    public Member get(int id) {
        return memberDAO.get(id);
    }

    @Transactional
    public List<Member> getAll() {
        return memberDAO.getAll();
    }

}
