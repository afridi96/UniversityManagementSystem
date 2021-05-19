package com.tangent.ums.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Semester {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "semester")
    private List<Course> offeredCourses;

    public Semester() {
    }

    public Semester(String name) {
        this.name = name;
    }

    public Semester(Long id, String name, List<Course> offeredCourses) {
        this.id = id;
        this.name = name;
        this.offeredCourses = offeredCourses;
    }

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

    public List<Course> getOfferedCourses() {
        return offeredCourses;
    }

    public void setOfferedCourses(List<Course> offeredCourses) {
        this.offeredCourses = offeredCourses;
    }
}
