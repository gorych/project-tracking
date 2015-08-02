package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.PositionDAO;
import by.epamlab.projecttracking.domain.Position;
import by.epamlab.projecttracking.service.interfaces.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDAO positionDAO;

    @Transactional
    public Position get(int id) {
        return positionDAO.get(id);
    }

    @Transactional
    public List<Position> getAll() {
        return positionDAO.getAll();
    }

}
