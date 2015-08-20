package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Activity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ActivityDAO {

    Activity getActivityById(int id);

    List<Activity> getAllActivities();

    List<Activity> getActivitiesFromIndexToIndex(int fromIndex, int toIndex);

    void insertActivity(Activity activity);
}
