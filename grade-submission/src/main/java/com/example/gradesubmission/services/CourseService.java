package com.example.gradesubmission.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gradesubmission.exceptions.CourseNotFoundException;
import com.example.gradesubmission.models.Course;
import com.example.gradesubmission.models.Student;
import com.example.gradesubmission.repos.CourseRepository;
import com.example.gradesubmission.utils.Utility;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private StudentService studentService;

    public Course getCourse(Long courseId) {
        return courseRepo.findById(courseId).orElseThrow(Utility.idNotFoundExceptionSupplier(courseId, CourseNotFoundException.class));
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course saveCourse(Course course) {
        return courseRepo.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }

    public Course enrollStudentToCourse(Long courseId, Long studentId) {
        Course course = getCourse(courseId);
        Student student = studentService.getStudent(studentId);
        course.getStudents().add(student);
        return courseRepo.save(course);
    }

    public List<Student> getEnrolledStudents(Long courseId) {
        Course course = getCourse(courseId);
        return course.getStudents();
    }
    
}
