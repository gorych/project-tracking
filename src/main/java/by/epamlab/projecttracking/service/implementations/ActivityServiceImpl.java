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
    public Activity getById(int id) {
        return activityDAO.getById(id);
    }

    @Transactional
    public List<Activity> getAll() {
        return activityDAO.getAll();
    }

    @Transactional
    public List<Activity> getFiveElementsFromIndex(int fromIndex) {
        final int INDEX_TO = fromIndex + 5;
        List<Activity> activities = getAll();
        System.out.println(activities);
        if (fromIndex < activities.size()) {
            return INDEX_TO < activities.size() ?
                    activities.subList(fromIndex, INDEX_TO) :
                    activities.subList(fromIndex, activities.size());
        }
        return new ArrayList<Activity>();
    }

    public String getJsonString(List<Activity> activities) {
        List<Map<String, String>> jsonList = new ArrayList<Map<String, String>>();
        for (Activity activity : activities) {
            Map<String, String> jsonObject = new HashMap<String, String>();
            jsonObject.put("firstname", activity.getMember().getEmployee().getFirstname());
            jsonObject.put("lastname", activity.getMember().getEmployee().getLastname());
            jsonObject.put("date", activity.getDate().toString());
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
}
