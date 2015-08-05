package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.StatusDAO;
import by.epamlab.projecttracking.domain.Status;
import by.epamlab.projecttracking.service.interfaces.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusDAO statusDAO;

    @Transactional
    public Status get(int id) {
        return statusDAO.get(id);
    }

    @Transactional
    public List<Status> getAll() {
        return statusDAO.getAll();
    }

}
