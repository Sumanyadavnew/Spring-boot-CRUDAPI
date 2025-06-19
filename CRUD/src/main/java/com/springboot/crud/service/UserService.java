package com.springboot.crud.service;

import com.springboot.crud.entity.User;
import com.springboot.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    @Autowired
    UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    public User CreateUser(User user) {
        return repository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        return repository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return repository.save(user);

        }).orElse(null);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
