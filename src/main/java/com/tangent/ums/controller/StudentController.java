package com.tangent.ums.controller;

import com.tangent.ums.model.Student;
import com.tangent.ums.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getallStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path="{studentId}")
    public Student getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PostMapping
    public void registerStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path="{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path="{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name) {
        studentService.updateStudent(studentId, name);
    }

    @PutMapping(path="{studentId}/addCourse")
    public void registerCourse(@PathVariable("studentId") Long studentId,
                               @RequestParam Long courseId) {
        studentService.registerCourse(studentId, courseId);
    }

}
