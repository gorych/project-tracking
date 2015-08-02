package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Position;

import java.util.List;

public interface PositionDAO {

    public Position get(int id);

    public List<Position> getAll();

}
