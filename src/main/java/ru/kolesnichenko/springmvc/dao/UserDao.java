package ru.kolesnichenko.springmvc.dao;

import ru.kolesnichenko.springmvc.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    void updateUser(User user);

    void deleteUserById(int id);

    User getUserById(int id);

    List<User> getUsers();
}
