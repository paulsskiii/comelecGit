package com.example.relationship.repository;

import com.example.relationship.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // The 'JOIN FETCH' keyword grabs posts immediately.
    // 'DISTINCT' ensures we don't get duplicate User objects (e.g. User A twice if they have 2 posts).
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.posts")
    List<User> findAllUsersWithPostsOptimized();
}
