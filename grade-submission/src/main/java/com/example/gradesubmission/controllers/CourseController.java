package com.example.gradesubmission.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gradesubmission.models.Course;
import com.example.gradesubmission.models.Student;
import com.example.gradesubmission.services.CourseService;

@RestController
@RequestMapping(path = "/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        Course course = courseService.getCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }


    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
        Course savedcourse = courseService.saveCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedcourse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> allCourses = courseService.getAllCourses();
        return ResponseEntity.status(HttpStatus.OK).body(allCourses);
    }

    @GetMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Course> enrollStudentToCourse(@PathVariable("courseId") Long courseId, @PathVariable("studentId") Long studentId) {
        Course enrolledCourse = courseService.enrollStudentToCourse(courseId, studentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(enrolledCourse);
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<List<Student>> getEnrolledStudents(@PathVariable("courseId") Long courseId) {
        List<Student> enrolledStudents =  courseService.getEnrolledStudents(courseId);
        return ResponseEntity.status(HttpStatus.OK).body(enrolledStudents);
    }

}
