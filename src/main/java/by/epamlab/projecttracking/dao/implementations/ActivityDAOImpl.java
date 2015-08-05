package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.dao.interfaces.ActivityDAO;
import by.epamlab.projecttracking.domain.Activity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityDAOImpl implements ActivityDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public Activity get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Activity) session.get(Activity.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Activity> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Activity")
                .list();
    }

}
