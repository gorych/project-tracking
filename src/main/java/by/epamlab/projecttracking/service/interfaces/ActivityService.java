package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Activity;

import java.util.List;

public interface ActivityService {

    Activity get(int id);

    List<Activity> getAll();

}
