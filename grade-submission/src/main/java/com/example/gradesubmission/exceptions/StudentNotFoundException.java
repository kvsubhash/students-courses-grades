package com.example.gradesubmission.exceptions;

public class StudentNotFoundException extends RuntimeException {
    
    public StudentNotFoundException(Long id) {
        super("Student with id : " + id + " not found!");
    }
}
