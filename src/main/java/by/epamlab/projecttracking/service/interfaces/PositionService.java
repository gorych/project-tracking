package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Position;

import java.util.List;

public interface PositionService {

    Position get(int id);

    List<Position> getAll();

}
