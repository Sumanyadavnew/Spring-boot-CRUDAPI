package com.springboot.crud.controller;

import com.springboot.crud.entity.User;
import com.springboot.crud.service.impl.UserServiceImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<User>>  getAllUsers() {
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<User> getUserByPagination(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity(userService.getAllUsersByPage(page,size,sortBy), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User> addUsers(@RequestBody User user) {
        return new ResponseEntity(userService.CreateUser(user),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updated = userService.updateUser(id, userDetails);
        if (updated != null) return ResponseEntity.ok(updated);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
