package com.tangent.ums.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    private Semester semester;
    @ManyToOne
    private Teacher teacher;
    @ManyToMany(mappedBy = "registeredCourses")
    private List<Student> studentList;

    public Course() {
    }

    public Course(String name, Semester semester) {
        this.name = name;
        this.semester = semester;
    }

    public Course(Long id, String name, Semester semester, Teacher teacher, List<Student> studentList) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.teacher = teacher;
        this.studentList = studentList;
    }

    public Course(String name) {
        this.name = name;
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

    public Semester getSemester() { return semester; }

    public void setSemester(Semester semester) { this.semester = semester; }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return getName().equals(course.getName()) && getSemester().equals(course.getSemester());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),getSemester());
    }
}
