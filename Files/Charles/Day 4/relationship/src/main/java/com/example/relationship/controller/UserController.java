package com.example.relationship.controller;

import com.example.relationship.model.User;
import com.example.relationship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {
    @Autowired private UserRepository userRepo;

    @GetMapping("/users/{id}/broken")
    public int getPostCountBroken(@PathVariable Long id) {
        // Transaction opens... and closes immediately after this line
        User user = userRepo.findById(id).orElseThrow();
        
        // CRASH: The session is closed. The proxy cannot fetch the posts.
        // This throws LazyInitializationException.
        return user.getPosts().size(); 
    }

    @GetMapping("/users/fixed")
    public List<User> getAllUsersFixed() {
        // This uses our JOIN FETCH query. The data is initialized inside the query.
        // Even though the session closes, the data is already in memory.
        return userRepo.findAllUsersWithPostsOptimized();
    }
}
