package com.example.relationship.runner;

import com.example.relationship.model.User;
import com.example.relationship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProxyDemo implements CommandLineRunner {
    @Autowired private UserRepository userRepo;

    @Override
    @Transactional
    public void run(String... args) {
        // Ensure you have data in data.sql or create it here before fetching
        
        System.out.println("--- Loading User ---");
        // Assuming ID 1 exists
        User user = userRepo.findById(1L).orElse(null); 
        
        if(user != null) {
            // At this point, no SQL has run for Posts. The 'posts' list is a Proxy.
            System.out.println("User object loaded. Posts are still 'ghosts'.");
    
            // TRIGGER: This forces the Proxy to fetch real data
            System.out.println("Fetching post count: " + user.getPosts().size()); 
        }

        
    }



}
