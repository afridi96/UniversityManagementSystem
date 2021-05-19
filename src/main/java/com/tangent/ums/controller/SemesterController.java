package com.tangent.ums.controller;

import com.tangent.ums.model.Semester;
import com.tangent.ums.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/semesters")
public class SemesterController {

    private final SemesterService semesterService;

    @Autowired
    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @GetMapping
    public List<Semester> getAllSemesters() {
        return semesterService.getAllSemesters();
    }

    @PostMapping
    public void createNewSemester(@RequestParam String name) {
        semesterService.createNewSemester(name);
    }
}
