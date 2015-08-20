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

    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Employee) session.get(Employee.class, id);
    }

    @SuppressWarnings("unchecked")
    public Employee getEmployeeByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Employee WHERE login = :login");
        query.setString("login", username);

        return (Employee) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Employee> getAllEmployees() {
        final int ADMIN_POSITION_ID = 1;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Employee WHERE position.id!=:position");
        query.setInteger("position", ADMIN_POSITION_ID);

        return query.list();
    }

    public void insertEmployee(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }

}
