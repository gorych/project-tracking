package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.dao.interfaces.MemberDAO;
import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Member;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {

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

    public List<Member> getByEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Member WHERE employee_id = :employee_id");
        query.setInteger("employee_id", employee.getId());
        return query.list();
    }

    public List<Member> getGroupByProject(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Member WHERE employee_id = :employee_id GROUP BY project");
        query.setInteger("employee_id", employee.getId());
        return query.list();
    }

}
