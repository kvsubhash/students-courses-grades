package com.example.gradesubmission;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.gradesubmission.models.Course;
import com.example.gradesubmission.models.Student;
import com.example.gradesubmission.repos.CourseRepository;
import com.example.gradesubmission.repos.StudentRepository;

@SpringBootApplication
public class GradeSubmissionApplication implements CommandLineRunner{

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(GradeSubmissionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Student[] students = new Student[] {
            new Student(1L, "Harry Potter", LocalDate.parse(("1980-07-31"))),
            new Student(2L, "Ron Weasley", LocalDate.parse(("1980-03-01"))),
            new Student(3L, "Hermione Granger", LocalDate.parse(("1979-09-19"))),
            new Student(4L, "Neville Longbottom", LocalDate.parse(("1980-07-30"))) 
        };

		studentRepo.saveAll(Arrays.asList(students));

		Course[] courses = new Course[] {
            new Course("Charms", "CH104", "In this class, you will learn spells concerned with giving an object new and unexpected properties."),
            new Course("Defence Against the Dark Arts", "DADA", "In this class, you will learn defensive techniques against the dark arts."),
            new Course("Herbology", "HB311", "In this class, you will learn the study of magical plants and how to take care of, utilise and combat them."),
            new Course("History of Magic", "HIS393", "In this class, you will learn about significant events in wizarding history."),
            new Course("Potions", "POT102", "In this class, you will learn correct mixing and stirring of ingredients to create mixtures with magical effects."),
            new Course("Transfiguration", "TR442", "In this class, you will learn the art of changing the form or appearance of an object.")
        };

		courseRepository.saveAll(Arrays.asList(courses));
		
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
