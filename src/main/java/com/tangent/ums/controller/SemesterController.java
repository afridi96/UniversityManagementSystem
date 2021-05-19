package com.tangent.ums.controller;

import com.tangent.ums.dto.CourseDto;
import com.tangent.ums.dto.SemesterDto;
import com.tangent.ums.model.Semester;
import com.tangent.ums.service.SemesterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/semesters")
public class SemesterController {

    private final SemesterService semesterService;
    private final ModelMapper modelMapper;

    @Autowired
    public SemesterController(SemesterService semesterService, ModelMapper modelMapper) {
        this.semesterService = semesterService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<SemesterDto> getAllSemesters() {
        List<Semester> semesters= semesterService.getAllSemesters();
        return semesters.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping
    public void createNewSemester(@RequestParam String name) {
        semesterService.createNewSemester(name);
    }

    private SemesterDto convertToDto(Semester semester) {
        SemesterDto semesterDto = modelMapper.map(semester, SemesterDto.class);
        semesterDto.setOfferedCourses(semester.getOfferedCourses().stream().
                map(course -> course.getName()).collect(Collectors.toList()));
        return semesterDto;
    }
}
