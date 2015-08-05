package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.dao.interfaces.ProjectDAO;
import by.epamlab.projecttracking.domain.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDAOImpl implements ProjectDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public Project get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Project) session.get(Project.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Project")
                .list();
    }

}
