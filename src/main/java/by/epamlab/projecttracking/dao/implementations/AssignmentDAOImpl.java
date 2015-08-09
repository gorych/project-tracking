package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.dao.interfaces.AssignmentDAO;
import by.epamlab.projecttracking.domain.Assignment;
import by.epamlab.projecttracking.domain.Member;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssignmentDAOImpl implements AssignmentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Assignment getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Assignment) session.get(Assignment.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Assignment> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Assignment")
                .list();
    }

    public List<Assignment> getByMember(Member member) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Assignment WHERE mamber_id = :mamber_id");
        query.setInteger("mamber_id", member.getId());
        return query.list();
    }

}
