package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.ProjectDAO;
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

    @Transactional
    public Project getProjectById(int id) {
        return projectDAO.getById(id);
    }

    @Transactional
    public List<Project> getAllProjects() {
        return projectDAO.getAll();
    }

    @Transactional
    public void add(Project project) {
        projectDAO.add(project);
    }

}
