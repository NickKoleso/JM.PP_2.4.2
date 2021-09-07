package ru.kolesnichenko.springmvc.dao;

import org.springframework.stereotype.Repository;
import ru.kolesnichenko.springmvc.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUserById(int id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u FROM User u", User.class).getResultList();
    }

    @Override
    public User getUserByName(String name) {
        Query query = entityManager.createQuery("select u from User u where u.name =: name");
        query.setParameter("name", name);
        User user = (User) query.getSingleResult();
        return entityManager.find(User.class, user.getId());
    }
}
