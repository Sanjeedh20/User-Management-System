package com.example.Project1.controller;
import java.util.*;

import com.example.Project1.exception.UserNotFound;
import com.example.Project1.model.User;
import com.example.Project1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepo userrepo;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userrepo.save(newUser);
    }

    @GetMapping("/users")
     List<User> getUsers(){
        return userrepo.findAll();
     }

     @GetMapping("/user/{id}")
    User getUserById(@PathVariable Integer id){
        return userrepo.findById(id).orElseThrow(()-> new UserNotFound(id));
     }

     @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Integer id) {
        return userrepo.findById(id)
                .map(user -> {
            //user.setId(newUser.getId());
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setAge(newUser.getAge());
            return userrepo.save(user);
        }).orElseThrow(()->new UserNotFound(id));
     }

     @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Integer id){
        if(!userrepo.existsById(id)){
            throw new UserNotFound(id);
        }
        userrepo.deleteById(id);
        return "User "+id+" deleted";
     }

}
