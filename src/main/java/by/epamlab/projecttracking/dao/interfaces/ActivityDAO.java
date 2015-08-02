package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Activity;

import java.util.List;

public interface ActivityDAO {

    public Activity get(int id);

    public List<Activity> getAll();

}
