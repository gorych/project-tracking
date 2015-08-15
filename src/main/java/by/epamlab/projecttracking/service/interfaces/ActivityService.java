package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Activity;

import java.util.List;

public interface ActivityService {

    Activity getActivityById(int id);

    List<Activity> getAllActivities();

    List<Activity> getActivitiesFromIndexToIndex(int fromIndex, int toIndex);

    String getJsonString(List<Activity> activities);

}
