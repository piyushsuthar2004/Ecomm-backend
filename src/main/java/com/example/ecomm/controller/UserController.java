package com.example.ecomm.controller;



import com.example.ecomm.model.User;
import com.example.ecomm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }
    @PostMapping("/login")
    public User loginUser(@RequestBody User user){
        return (User) userService.loginUser(user.getEmail(),user.getPassword());
    }
}
