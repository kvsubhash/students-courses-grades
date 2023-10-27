package com.example.gradesubmission.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gradesubmission.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    
}
