package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.ActivityDAO;
import by.epamlab.projecttracking.domain.Activity;
import by.epamlab.projecttracking.service.interfaces.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDAO activityDAO;

    @Transactional
    public Activity get(int id) {
        return activityDAO.get(id);
    }

    @Transactional
    public List<Activity> getAll() {
        return activityDAO.getAll();
    }
}
