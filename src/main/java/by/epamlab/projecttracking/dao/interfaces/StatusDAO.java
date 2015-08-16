package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Status;

import java.util.List;

public interface StatusDAO {

    Status getStatusById(int id);

    List<Status> getAllStatuses();

}
