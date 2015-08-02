package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Role;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public Employee get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Employee) session.get(Employee.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Employee> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Employee")
                .list();
    }

}
