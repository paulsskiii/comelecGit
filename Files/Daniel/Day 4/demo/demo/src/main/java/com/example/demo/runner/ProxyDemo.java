package com.example.demo.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Component
public class ProxyDemo implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;

    @Override
    @Transactional
    public void run(String... args) {
        // Ensure you have data in data.sql or create it here before fetching

        System.out.println("--- Loading User ---");
        // Assuming ID 1 exists
        User user = userRepo.findById(1L).orElse(null);

        if (user != null) {
            // At this point, no SQL has run for Posts. The 'posts' list is a Proxy.
            System.out.println("User object loaded. Posts are still 'ghosts'.");

            // TRIGGER: This forces the Proxy to fetch real data
            System.out.println("Fetching post count: " + user.getPosts().size());
        }
    }

    // Imports assumed from context (List, User, Autowired, etc.)
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
