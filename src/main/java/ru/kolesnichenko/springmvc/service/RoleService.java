package ru.kolesnichenko.springmvc.service;

import ru.kolesnichenko.springmvc.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();

    Set<Role> getByIdRoles(List<Integer> ids);
}
