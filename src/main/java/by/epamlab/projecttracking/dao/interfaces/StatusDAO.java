package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusDAO {

    Status getStatusById(int id);

    List<Status> getAllStatuses();

    Status getStatusDone();

}
