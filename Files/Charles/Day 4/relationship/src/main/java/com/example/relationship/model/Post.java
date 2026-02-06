package com.example.relationship.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "posts") // Explicit naming to match SQL convention
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    // Standard Getters and Setters
    public Long getId() { return id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
