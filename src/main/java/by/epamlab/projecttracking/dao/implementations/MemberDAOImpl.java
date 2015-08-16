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

    @Override
    public Member getMemberById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Member) session.get(Member.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Member> getAllMembers() {
        return sessionFactory.getCurrentSession().createQuery("from Member")
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Member> getMembersByEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Member WHERE employee.id = :employee_id");
        query.setInteger("employee_id", employee.getId());
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Member> getMembersGroupByProject(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Member WHERE employee.id = :employee_id GROUP BY project");
        query.setInteger("employee_id", employee.getId());
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Member> getMembersByProjectId(int projectId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Member WHERE project.id = :project_id");

        query.setInteger("project_id", projectId);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Member getMemberByProjectAndEmployeeId(int projectId, int employeeId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Member WHERE project.id = :project_id AND employee.id = :employee_id");

        query.setInteger("project_id", projectId);
        query.setInteger("employee_id", employeeId);

        return (Member) query.uniqueResult();
    }

    @Override
    public void add(Member member) {
        sessionFactory.getCurrentSession().save(member);
    }

}
