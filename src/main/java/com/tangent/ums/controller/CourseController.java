package com.tangent.ums.controller;

import com.tangent.ums.dto.CourseDto;
import com.tangent.ums.dto.StudentDto;
import com.tangent.ums.model.Course;
import com.tangent.ums.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/courses")
public class CourseController {

    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @Autowired
    public CourseController(CourseService courseService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<CourseDto> getAllCourses() {
        List<Course> courses =  courseService.getAllCourses();
        return courses.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping(path = "{courseId}")
    public Course getCourseById(@PathVariable("courseId") Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @PostMapping
    public void createNewCourse(@RequestParam String courseName,
                                @RequestParam String semesterName) {
        courseService.createNewCourse(courseName, semesterName);
    }

    private CourseDto convertToDto (Course course) {
        CourseDto courseDto = modelMapper.map(course, CourseDto.class);
        courseDto.setSemester(course.getSemester().getName());
        courseDto.setStudentList(course.getStudentList().stream().
                map(student -> modelMapper.map(student, StudentDto.class)).collect(Collectors.toList()));
        return courseDto;
    }
}
