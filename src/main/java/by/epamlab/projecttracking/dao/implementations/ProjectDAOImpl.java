package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.dao.interfaces.ProjectDAO;
import by.epamlab.projecttracking.domain.Project;
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

    public Project getProjectById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Project) session.get(Project.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getAllProjects() {
        return sessionFactory.getCurrentSession().createQuery("from Project")
                .list();
    }

    @Override
    public void insertProject(Project project) {
        sessionFactory.getCurrentSession().save(project);
    }

}
