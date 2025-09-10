package com.example.ecomm.service;

import com.example.ecomm.model.User;
import com.example.ecomm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public User registerUser(User user) {
        return userRepo.save(user);
    }


    public User loginUser(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
