package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.RoleDAO;
import by.epamlab.projecttracking.domain.Role;
import by.epamlab.projecttracking.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO roleDAO;

    @Override
    @Transactional
    public List<Role> getAll() {
        return roleDAO.getAll();
    }
}
