package com.example.student.service;

import com.example.student.entity.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService
{
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student)
    {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudent(Long id)
    {
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student)
    {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id)
    {
        studentRepository.deleteById(id);
    }

}
