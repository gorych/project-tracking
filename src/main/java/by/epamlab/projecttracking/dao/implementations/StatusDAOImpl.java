package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.dao.interfaces.StatusDAO;
import by.epamlab.projecttracking.domain.Member;
import by.epamlab.projecttracking.domain.Status;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusDAOImpl implements StatusDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public Status getStatusById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Status) session.get(Status.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Status> getAllStatuses() {
        return sessionFactory.getCurrentSession().createQuery("from Status")
                .list();
    }

    @Override
    public Status getStatusDone() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Status WHERE name = :name");

        query.setString("name", "To do");
        return (Status) query.uniqueResult();
    }

}
