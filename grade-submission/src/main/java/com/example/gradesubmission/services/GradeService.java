package com.example.gradesubmission.services;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gradesubmission.exceptions.GradeNotFoundException;
import com.example.gradesubmission.models.Course;
import com.example.gradesubmission.models.Grade;
import com.example.gradesubmission.models.Student;
import com.example.gradesubmission.repos.GradeRepository;
import com.example.gradesubmission.utils.Utility;

@Service
public class GradeService {
    
    @Autowired
    private GradeRepository gradeRepo;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    public List<Grade> getAllGrades() {
        List<Grade> allGrades = gradeRepo.findAll();
        return allGrades;
    }

    public Grade getGrade(Long id) {
        Grade grade = gradeRepo.findById(id).orElseThrow(Utility.idNotFoundExceptionSupplier(id, GradeNotFoundException.class));
        return grade;
    }

    public Grade saveGrade(Grade grade) {
        Grade savedGrade = gradeRepo.save(grade);
        return savedGrade;
    }

    public void deleteGrade(Long id) {
        gradeRepo.deleteById(id);
    }

    public Grade getGrade(Long studentId, Long courseId) {
        return gradeRepo.findByStudentIdAndCourseId(studentId, courseId).orElseThrow(gradeNotFound(studentId, courseId));
    }

    private Supplier<GradeNotFoundException> gradeNotFound(Long studentId, Long courseId) {
        return () -> new GradeNotFoundException(studentId, courseId);
    }

    public Grade saveGrade(Long studentId, Long courseId, Grade grade) {
        Student aStudent = studentService.getStudent(studentId);
        Course aCourse = courseService.getCourse(courseId);
        grade.setStudent(aStudent);
        grade.setCourse(aCourse);
        return gradeRepo.save(grade);
    }

    public Grade updateGrade(Long studentId, Long courseId, Grade grade) {
        Grade aGrade = getGrade(studentId, courseId);
        aGrade.setScore(grade.getScore());
        return gradeRepo.save(aGrade);
    }

    public void deleteGrade(Long studentId, Long courseId) {
        Grade aGrade = getGrade(studentId, courseId);
        gradeRepo.delete(aGrade);
    }

    public List<Grade> getGradesByStudent(Long studentId) {
        List<Grade> grades = gradeRepo.findByStudentId(studentId);
        return grades;
    }

    public List<Grade> getGradesByCourse(Long courseId) {
        List<Grade> grades = gradeRepo.findByCourseId(courseId);
        return grades;
    }
}
