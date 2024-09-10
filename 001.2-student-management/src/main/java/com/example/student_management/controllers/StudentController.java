package com.example.student_management.controllers;

import com.example.student_management.entities.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Laura Smith", "laura@mail.com", 21, "Ciencias de la Computación"),
            new Student(2, "John Doe", "jhon@mail.com", 22, "Ingeniería Civil"),
            new Student(3, "Jane Doe", "jane@mail.com", 23, "Ingeniería Industrial"),
            new Student(4 , "Alice Smith", "alice@mail.com", 24, "Ingeniería de Sistemas")
    ));

    @GetMapping()
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/{studentEmail}")
    public Student getStudentByName(@PathVariable String studentEmail) {
        return students.stream()
                .filter(student -> student.getStudentEmail().equals(studentEmail))
                .findFirst()
                .orElse(null);
    }

    @PostMapping()
    public Student createStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }
    // Modificación completa
    @PutMapping()
    public Student updateStudent(@RequestBody Student student) {
        Student studentToUpdate = students.stream()
                .filter(s -> s.getStudentId() == student.getStudentId())
                .findFirst()
                .orElse(null);

        if (studentToUpdate != null) {
            studentToUpdate.setStudentName(student.getStudentName());
            studentToUpdate.setStudentEmail(student.getStudentEmail());
            studentToUpdate.setStudentAge(student.getStudentAge());
            studentToUpdate.setStudentCourse(student.getStudentCourse());
        }
        return studentToUpdate;
    }

    // Modificación parcial
    @PatchMapping()
    public Student updateStudentPartial(@RequestBody Student student) {
        Student studentToUpdate = students.stream()
                .filter(s -> s.getStudentId() == student.getStudentId())
                .findFirst()
                .orElse(null);

        if (studentToUpdate != null) {
            if (student.getStudentName() != null) {
                studentToUpdate.setStudentName(student.getStudentName());
            }
            if (student.getStudentEmail() != null) {
                studentToUpdate.setStudentEmail(student.getStudentEmail());
            }
            if (student.getStudentAge() > 0) {
                studentToUpdate.setStudentAge(student.getStudentAge());
            }
            if (student.getStudentCourse() != null) {
                studentToUpdate.setStudentCourse(student.getStudentCourse());
            }
        }
        return studentToUpdate;
    }

    @DeleteMapping("/{studentId}")
    public Student deleteStudent(@PathVariable int studentId) {
        Student studentToDelete = students.stream()
                .filter(s -> s.getStudentId() == studentId)
                .findFirst()
                .orElse(null);

        if (studentToDelete != null) {
            students.remove(studentToDelete);
        }
        return studentToDelete;
    }
}
