package com.tangent.ums.controller;

import com.tangent.ums.dto.CourseDto;
import com.tangent.ums.dto.TeacherDto;
import com.tangent.ums.model.Teacher;
import com.tangent.ums.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final ModelMapper modelMapper;

    @Autowired
    public TeacherController(TeacherService teacherService, ModelMapper modelMapper) {
        this.teacherService = teacherService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return teachers.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping(path = "{teacherId}")
    public TeacherDto getTeacherById(@PathVariable("teacherId") Long id) {
        return convertToDto(teacherService.getTeacherById(id));
    }

    @PutMapping(path = "{teacherId}/courses")
    public void teachCourse(@PathVariable("teacherId") Long teacherId, @RequestParam Long courseId) {
        teacherService.teachCourse(teacherId, courseId);
    }

    private TeacherDto convertToDto(Teacher teacher) {
        TeacherDto teacherDto = modelMapper.map(teacher, TeacherDto.class);
        teacherDto.setCourses(teacher.getCourses().stream().
                map(course -> {
                    CourseDto courseDto = modelMapper.map(course, CourseDto.class);
                    courseDto.setSemester(course.getSemester().getName());
//                    courseDto.setTeacher(course.getTeacher().getName());
                    courseDto.setStudentList(course.getStudentList().stream().
                            map(student -> student.getName()).collect(Collectors.toList()));
                    return courseDto;
                }).collect(Collectors.toList()));
        return teacherDto;
    }
}
