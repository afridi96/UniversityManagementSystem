package com.tangent.ums.dto;

import java.util.List;

public class SemesterDto {

    private Long id;
    private String name;
    private List<String> offeredCourses;

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

    public List<String> getOfferedCourses() {
        return offeredCourses;
    }

    public void setOfferedCourses(List<String> offeredCourses) {
        this.offeredCourses = offeredCourses;
    }
}
