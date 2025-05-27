package com.demo.service;

import java.util.List;

import com.demo.entity.User;
import com.demo.exception.UserNotFoundException;

public interface UserService {
    User getUserById(long id) throws UserNotFoundException;

    List<User> getUsers();

    User updateUser(long id, User user) throws UserNotFoundException;

    User saveUser(User user);

    void deleteUser(long id) throws UserNotFoundException;

}
