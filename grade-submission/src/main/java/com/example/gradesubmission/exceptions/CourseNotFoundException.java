package com.example.gradesubmission.exceptions;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException(Long id) {
        super("Course with id : " + id + " not found!");
    }
    
}
