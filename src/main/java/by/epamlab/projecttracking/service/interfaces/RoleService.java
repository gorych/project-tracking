package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Role;

import java.util.List;

public interface RoleService {

    Role get(int id);

    List<Role> getAll();

}
