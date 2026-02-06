package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    // LAZY: Posts are only fetched when user.getPosts() is called
    // @JsonManagedReference: Prevents infinite recursion but allows posts to show in JSON
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference 
    private List<Post> posts;
    
    // Getters and Setters (or @Data with Lombok)
    public List<Post> getPosts() { return posts; }
    public void setPosts(List<Post> posts) { this.posts = posts; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
