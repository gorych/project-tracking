package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Position;

import java.util.List;

public interface PositionService {

    Position getById(int id);

    List<Position> getAll();

}
