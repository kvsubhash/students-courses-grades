package com.example.gradesubmission.exceptions;

public class GradeNotFoundException extends RuntimeException {
    
    public GradeNotFoundException(Long id) {
        super("Grade with id: " + id + " not found!");
    }

    public GradeNotFoundException(Long studentId, Long courseId) {
        super("Grade with studentId: " + studentId + " and courseId: " + courseId +  " not found!");
    }
}
