package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.ActivityDAO;
import by.epamlab.projecttracking.domain.Activity;
import by.epamlab.projecttracking.service.interfaces.ActivityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDAO activityDAO;

    @Transactional
    public Activity getActivityById(int id) {
        return activityDAO.getActivityById(id);
    }

    @Transactional
    public List<Activity> getAllActivities() {
        return activityDAO.getAllActivities();
    }

    @Transactional
    public List<Activity> getActivitiesFromIndexToIndex(int fromIndex, int toIndex) {
        return activityDAO.getActivitiesFromIndexToIndex(fromIndex, toIndex);
    }

    public String getJsonString(List<Activity> activities) {
        List<Map<String, String>> jsonList = new ArrayList<Map<String, String>>();
        for (Activity activity : activities) {
            Map<String, String> jsonObject = new HashMap<String, String>();
            jsonObject.put("fullName", activity.getFullName());
            jsonObject.put("date", activity.getDate().toString());
            jsonObject.put("duration", "" + activity.getDuration());
            jsonObject.put("comment", activity.getComment());
            jsonList.add(jsonObject);
        }

        String jsonString = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = mapper.writeValueAsString(jsonList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @Transactional
    public void addActivity(Activity activity) {
        activityDAO.insertActivity(activity);
    }
}
