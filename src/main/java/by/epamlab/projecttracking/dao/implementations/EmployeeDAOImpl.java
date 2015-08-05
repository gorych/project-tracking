package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.dao.interfaces.EmployeeDAO;
import by.epamlab.projecttracking.domain.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Employee getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Employee) session.get(Employee.class, id);
    }

    @SuppressWarnings("unchecked")
    public Employee getByUsername(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Employee WHERE login = :login");
        query.setString("login", login);
        List<Employee> employees = query.list();
        return employees.size() > 0 ? employees.get(0) : null;
    }

    @SuppressWarnings("unchecked")
    public List<Employee> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Employee")
                .list();
    }

}
