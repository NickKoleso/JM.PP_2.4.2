package ru.kolesnichenko.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kolesnichenko.springmvc.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r FROM Role r", Role.class).getResultList();
    }

    @Override
    public Set<Role> getByIdRoles(List<Integer> ids) {
        TypedQuery<Role> query = entityManager.createQuery("select r FROM Role r WHERE r.id in :ids", Role.class);
        return new HashSet<>(query.setParameter("ids", ids).getResultList());
    }



}
