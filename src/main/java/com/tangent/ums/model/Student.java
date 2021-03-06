package com.tangent.ums.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@SequenceGenerator(
        name = "user_gen",
        sequenceName = "studentSequence",
        allocationSize = 1
)
public class Student extends User{

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Course> registeredCourses;

    public Student() {
    }

    public Student(Long id, String name, LocalDate dob) {
        super(id,name,dob);
    }

    public Student(String name, LocalDate dob) {
        super(name, dob);
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public LocalDate getDob() {
        return super.getDob();
    }

    public void setDob(LocalDate dob) {
        super.setDob(dob);
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(List<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }
}
