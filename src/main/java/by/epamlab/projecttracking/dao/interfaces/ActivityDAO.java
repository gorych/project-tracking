package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Activity;

import java.util.List;

public interface ActivityDAO {

    Activity get(int id);

    List<Activity> getAll();

}
