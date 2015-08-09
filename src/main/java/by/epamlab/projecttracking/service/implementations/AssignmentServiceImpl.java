package by.epamlab.projecttracking.service.implementations;


import by.epamlab.projecttracking.dao.interfaces.AssignmentDAO;
import by.epamlab.projecttracking.domain.Assignment;
import by.epamlab.projecttracking.service.interfaces.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentDAO assignmentDAO;

    @Transactional
    public Assignment get(int id) {
        return assignmentDAO.getById(id);
    }

    @Transactional
    public List<Assignment> getAll() {
        return assignmentDAO.getAll();
    }

}
