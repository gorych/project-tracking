package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Role;

import java.util.List;

public interface RoleDAO {

    Role getRoleById(int id);

    List<Role> getAllRoles();

}
