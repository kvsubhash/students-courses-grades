package com.example.gradesubmission.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gradesubmission.models.Grade;
import com.example.gradesubmission.services.GradeService;

@RestController
@RequestMapping(path = "/grades")
public class GradeController {
    
    @Autowired
    private GradeService gradeService;

    @GetMapping(path = "/all")
    public List<Grade> getAllGrades() {
        return gradeService.getAllGrades();
    }

    @GetMapping(path = "/student/{studentId}/course/{courseId}")
    public Grade getGrade(@PathVariable(name = "studentId") Long studentId, @PathVariable(name = "courseId") Long courseId) {
        return gradeService.getGrade(studentId, courseId);
    }

    @PostMapping(path = "/student/{studentId}/course/{courseId}")
    public Grade saveGrade(@PathVariable(name = "studentId") Long studentId, 
                            @PathVariable(name = "courseId") Long courseId,
                            @RequestBody Grade grade) {
        return gradeService.saveGrade(studentId, courseId, grade);
    }

    @PutMapping(path = "/student/{studentId}/course/{courseId}")
    public Grade updateGrade(@PathVariable(name = "studentId") Long studentId, 
                            @PathVariable(name = "courseId") Long courseId,
                            @RequestBody Grade grade) {
        return gradeService.updateGrade(studentId, courseId, grade);
    }

    @DeleteMapping(path = "/student/{studentId}/course/{courseId}")
    public void deleteGrade(@PathVariable(name = "studentId") Long studentId, 
                            @PathVariable(name = "courseId") Long courseId) {
        ResponseEntity<Object> build = ResponseEntity.noContent().build();
        gradeService.deleteGrade(studentId, courseId);
    }

    @GetMapping(path = "/student/{studentId}")
    public List<Grade> getStudentGrades(@PathVariable(name = "studentId") Long studentId) {
        return gradeService.getGradesByStudent(studentId);
    }

    @GetMapping(path = "/course/{courseId}")
    public List<Grade> getCourseGrades(@PathVariable(name = "courseId") Long courseId) {
        return gradeService.getGradesByCourse(courseId);
    }


}
