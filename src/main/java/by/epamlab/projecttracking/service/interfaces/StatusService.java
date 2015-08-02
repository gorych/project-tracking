package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Status;

import java.util.List;

public interface StatusService {

    Status get(int id);

    List<Status> getAll();

}
