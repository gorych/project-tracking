package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.dao.interfaces.ActivityDAO;
import by.epamlab.projecttracking.domain.Activity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityDAOImpl implements ActivityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Activity getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Activity) session.get(Activity.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Activity> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Activity")
                .list();
    }

    public List<Activity> getFromIndexToIndex(int fromIndex, int toIndex) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Activity ORDER BY date DESC");
        query.setFirstResult(fromIndex);
        query.setMaxResults(toIndex);
        return query.list();
    }

}
