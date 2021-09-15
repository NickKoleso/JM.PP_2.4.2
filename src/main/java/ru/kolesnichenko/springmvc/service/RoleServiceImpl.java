package ru.kolesnichenko.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kolesnichenko.springmvc.dao.RoleDao;
import ru.kolesnichenko.springmvc.model.Role;

import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    @Transactional
    public Role getByIdRole(int id) {
        return roleDao.getByIdRole(id);
    }
}
