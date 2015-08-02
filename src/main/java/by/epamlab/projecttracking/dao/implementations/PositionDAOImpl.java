package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.domain.Position;
import by.epamlab.projecttracking.domain.Role;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionDAOImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public Position get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Position) session.get(Position.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Role> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Position")
                .list();
    }

}
