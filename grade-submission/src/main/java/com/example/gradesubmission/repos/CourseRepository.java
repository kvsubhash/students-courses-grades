package com.example.gradesubmission.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gradesubmission.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    
}
