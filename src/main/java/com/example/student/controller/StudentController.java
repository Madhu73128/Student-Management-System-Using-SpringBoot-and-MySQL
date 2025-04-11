package com.example.student.controller;

import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/students")
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<Student> createStudentDetails(@RequestBody Student student)
    {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Student> getStudentDetails(@PathVariable("id") Long id) {
        return studentService.getStudent(id)
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/read")
    public ResponseEntity<List<Student>> getAllStudentDetails()
    {
        return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudentDetails(@PathVariable("id") Long id, @RequestBody Student student)
    {
        Optional<Student> existingStudent = studentService.getStudent(id);

        if (existingStudent.isPresent())
        {
            Student updatedStudent = existingStudent.get();
            updatedStudent.setStudentName(student.getStudentName());
            updatedStudent.setStudentEmail(student.getStudentEmail());
            updatedStudent.setStudentAddress(student.getStudentAddress());

            return new ResponseEntity<>(studentService.updateStudent(updatedStudent), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentDetails(@PathVariable("id") Long id)
    {
        studentService.deleteStudent(id);
        return "Student Deleted Successfully.";
    }
}
