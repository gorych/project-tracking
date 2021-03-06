package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.dao.interfaces.RoleDAO;
import by.epamlab.projecttracking.domain.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Role getRoleById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Role) session.get(Role.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Role> getAllRoles() {
        return sessionFactory.getCurrentSession().createQuery("from Role")
                .list();
    }
}
