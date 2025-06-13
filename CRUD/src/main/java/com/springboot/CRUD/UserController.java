package com.springboot.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return  userService.getAllUsers();
    }
@PostMapping
    public User AddUsers(@RequestBody User user){
       return userService.CreateUser(user);
    }
    @PutMapping("/{id}")

    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
        User updated=userService.updateUser(id,userDetails);
if(updated !=null)return ResponseEntity.ok(updated);
else return ResponseEntity.notFound().build();
    }

@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable  Long id){
    userService.deleteUser(id);
    return  ResponseEntity.noContent().build();
}


}
