package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // The 'JOIN FETCH' keyword grabs posts immediately.
    // 'DISTINCT' ensures we don't get duplicate User objects (e.g. User A twice if they have 2 posts).
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.posts")
    List<User> findAllUsersWithPostsOptimized();
}
