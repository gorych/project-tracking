package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.dao.interfaces.MemberDAO;
import by.epamlab.projecttracking.domain.Member;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public Member get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Member) session.get(Member.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Member> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Member")
                .list();
    }

}
