package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.dao.interfaces.TaskDAO;
import by.epamlab.projecttracking.domain.Assignment;
import by.epamlab.projecttracking.domain.Task;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public Task getTaskById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Task) session.get(Task.class, id);
    }

    @Override
    public void insertTask(Task task) {
        sessionFactory.getCurrentSession().save(task);
    }

}
