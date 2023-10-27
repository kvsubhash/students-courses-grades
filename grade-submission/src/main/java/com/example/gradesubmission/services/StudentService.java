package com.example.gradesubmission.services;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gradesubmission.exceptions.StudentNotFoundException;
import com.example.gradesubmission.models.Student;
import com.example.gradesubmission.repos.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    public Student getStudent(Long id) {
        Student referenceById = studentRepo.findById(id).orElseThrow(studentNotFoundException(id));
        return referenceById;
    }

    public List<Student> getStudents() {
        List<Student> students = studentRepo.findAll();
        return students;
    }

    public Student saveStudent(Student student) {
        Student savedStudent = studentRepo.save(student);
        return savedStudent;
    }

    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }

    private Supplier<StudentNotFoundException> studentNotFoundException(Long id) {
        return () -> new StudentNotFoundException(id);
    }

    
}
