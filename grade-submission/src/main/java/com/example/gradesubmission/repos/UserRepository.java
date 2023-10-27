package com.example.gradesubmission.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gradesubmission.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);
    
}
