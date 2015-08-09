package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Activity;

import java.util.List;

public interface ActivityService {

    Activity getById(int id);

    List<Activity> getAll();

    List<Activity> getFiveElementsFromIndex(int fromIndex);

    String getJsonString(List<Activity> activities);

}
