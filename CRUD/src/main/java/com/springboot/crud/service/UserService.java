package com.springboot.crud.service;

import com.springboot.crud.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User CreateUser(User user);

    User updateUser(Long id, User userDetails);

    void deleteUser(Long id);
}
