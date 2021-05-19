package com.tangent.ums.controller;

import com.tangent.ums.dto.CourseDto;
import com.tangent.ums.dto.StudentDto;
import com.tangent.ums.model.Student;
import com.tangent.ums.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/students")
public class StudentController {

    private final StudentService studentService;
    private final ModelMapper modelMapper;

    @Autowired
    public StudentController(StudentService studentService, ModelMapper modelMapper) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<StudentDto> getallStudents() {
        List<Student> students = studentService.getAllStudents();
        return students.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping(path="{studentId}")
    public StudentDto getStudentById(@PathVariable Long studentId) {


        return convertToDto(studentService.getStudentById(studentId));
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

    @PutMapping(path="{studentId}/courses")
    public void registerCourse(@PathVariable("studentId") Long studentId,
                               @RequestParam Long courseId) {
        studentService.registerCourse(studentId, courseId);
    }

    private StudentDto convertToDto(Student student) {
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        studentDto.setRegisteredCourses(student.getRegisteredCourses().stream().
                map(course -> course.getName()).collect(Collectors.toList()));
        return studentDto;
    }
}
