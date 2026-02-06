package com.example.relationship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.relationship.model.User;
import com.example.relationship.repository.UserRepository;

public class UserService {

    @Autowired
    private UserRepository userRepo;

    public void simulateNPlusOne() {
        // Query 1: Fetch all users
        List<User> users = userRepo.findAll(); 

        for (User user : users) {
            // N Queries: This runs a NEW query for every single user!
            // If 1000 users, that's 1001 database calls.
            System.out.println(user.getUsername() + " has " + user.getPosts().size() + " posts.");
        }
    }


}
