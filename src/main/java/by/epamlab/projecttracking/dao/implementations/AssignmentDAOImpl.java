package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.domain.Assignment;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssignmentDAOImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public Assignment get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Assignment) session.get(Assignment.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Assignment> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Assignment")
                .list();
    }

}
