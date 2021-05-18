package com.tangent.ums.dto;

import com.tangent.ums.model.Course;

import java.time.LocalDate;
import java.util.List;

public class StudentDto {

    private Long id;
    private String name;
    private LocalDate dob;
    private List<Course> registeredCourses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(List<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }
}
