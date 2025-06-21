package com.springboot.crud.service.impl;

import com.springboot.crud.entity.User;
import com.springboot.crud.exception.NoSuchUserExistException;
import com.springboot.crud.repository.UserRepository;
import com.springboot.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new NoSuchUserExistException("User Not Found with id :" + id)));
    }

    @Override
    public User CreateUser(User user) {
        return repository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        return repository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return repository.save(user);
        }).orElseThrow(()-> new NoSuchUserExistException("User is not Exist with Id : "+id));
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> existingUser = repository.findById(id);
        if(existingUser.isPresent()){
            repository.deleteById(id);
        }else{
           throw new NoSuchUserExistException("User Not Found!");
        }
//        existingUser.ifPresentOrElse((i) -> repository.deleteById(id), () ->  new NoSuchUserExistException("User Not Found!"));
    }
}
