package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Position;

import java.util.List;

public interface PositionDAO {

    Position getPositionById(int id);

    List<Position> getAllPositions();

}
