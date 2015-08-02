package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Role;

import java.util.List;

public interface RoleDAO {

    public Role get(int id);

    public List<Role> getAll();

}
