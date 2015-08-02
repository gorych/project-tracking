package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Status;

import java.util.List;

public interface StatusDAO {

    public Status get(int id);

    public List<Status> getAll();

}
