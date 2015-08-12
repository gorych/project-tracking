package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.MemberDAO;
import by.epamlab.projecttracking.dao.interfaces.ProjectDAO;
import by.epamlab.projecttracking.domain.Employee;
import by.epamlab.projecttracking.domain.Project;
import by.epamlab.projecttracking.service.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private MemberDAO memberDAO;

    @Transactional
    public Project get(int id) {
        return projectDAO.getById(id);
    }

    @Transactional
    public List<Project> getAll() {
        return projectDAO.getAll();
    }

    @Transactional
    public void add(Project project) {
        projectDAO.add(project);
    }

    @Transactional
    public void addEmployeeToProject(int employeeId, int projectId, int roleId) {
    }

}
