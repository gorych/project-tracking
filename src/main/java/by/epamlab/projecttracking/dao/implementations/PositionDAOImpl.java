package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.dao.interfaces.PositionDAO;
import by.epamlab.projecttracking.domain.Position;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionDAOImpl implements PositionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Position getPositionById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Position) session.get(Position.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Position> getAllPositions() {
        final int ADMIN_POSITION_ID = 1;

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Position WHERE id != :id");
        query.setInteger("id", ADMIN_POSITION_ID);

        return query.list();
    }

}
