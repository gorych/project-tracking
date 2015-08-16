package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.PositionDAO;
import by.epamlab.projecttracking.domain.Position;
import by.epamlab.projecttracking.service.interfaces.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDAO positionDAO;

    @Transactional
    public Position getPositionById(int id) {
        return positionDAO.getPositionById(id);
    }

    @Transactional
    public List<Position> getAllPositions() {
        return positionDAO.getAllPositions();
    }

}
