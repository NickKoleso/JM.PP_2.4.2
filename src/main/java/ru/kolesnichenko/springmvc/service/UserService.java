package ru.kolesnichenko.springmvc.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kolesnichenko.springmvc.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);

    void updateUser(User user);

    void deleteUserById(int id);

    User getUserById(int id);

    List<User> getUsers();

    User getUserByName(String name);


}
