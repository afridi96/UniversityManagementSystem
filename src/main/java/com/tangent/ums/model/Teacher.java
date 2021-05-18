package com.tangent.ums.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@SequenceGenerator(
        name = "user_gen",
        sequenceName = "teacherSequence",
        allocationSize = 1
)
public class Teacher extends User{

    @OneToMany
    private List<Course> courses;

    public Teacher() {
    }

    public Teacher(Long id, String name, LocalDate dob, List<Course> courses) {
        super(id, name, dob);
        this.courses = courses;
    }

    public Teacher(String name, LocalDate dob) {
        super(name, dob);
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public LocalDate getDob() {
        return super.getDob();
    }

    @Override
    public void setDob(LocalDate dob) {
        super.setDob(dob);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
