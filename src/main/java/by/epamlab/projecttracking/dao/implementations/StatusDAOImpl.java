package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.dao.interfaces.StatusDAO;
import by.epamlab.projecttracking.domain.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusDAOImpl implements StatusDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Status getStatusById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Status) session.get(Status.class, id);
    }

    @Override
    public Status getDefaultStatus() {
        final int STATUS_ID = 1;
        return getStatusById(STATUS_ID);
    }

}
