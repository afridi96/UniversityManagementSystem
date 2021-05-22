package com.tangent.ums.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tangent.ums.model.Course;
import com.tangent.ums.model.Semester;
import com.tangent.ums.model.Student;
import com.tangent.ums.model.Teacher;
import com.tangent.ums.repository.CourseRepository;
import com.tangent.ums.repository.TeacherRepository;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TeacherService.class})
@ExtendWith(SpringExtension.class)
public class TeacherServiceTest {
    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    @Test
    public void testGetAllTeachers() {
        ArrayList<Teacher> teacherList = new ArrayList<Teacher>();
        when(this.teacherRepository.findAll()).thenReturn(teacherList);
        List<Teacher> actualAllTeachers = this.teacherService.getAllTeachers();
        assertSame(teacherList, actualAllTeachers);
        assertTrue(actualAllTeachers.isEmpty());
        verify(this.teacherRepository).findAll();
    }

    @Test
    public void testGetTeacherById() {
        Teacher teacher = new Teacher();
        teacher.setDob(LocalDate.ofEpochDay(1L));
        teacher.setId(123L);
        teacher.setCourses(new ArrayList<Course>());
        teacher.setName("Name");
        Optional<Teacher> ofResult = Optional.<Teacher>of(teacher);
        when(this.teacherRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(teacher, this.teacherService.getTeacherById(123L));
        verify(this.teacherRepository).findById((Long) any());
    }

    @Test
    public void testGetTeacherById2() {
        when(this.teacherRepository.findById((Long) any())).thenReturn(Optional.<Teacher>empty());
        assertThrows(IllegalStateException.class, () -> this.teacherService.getTeacherById(123L));
        verify(this.teacherRepository).findById((Long) any());
    }

    @Test
    public void testTeachCourse() {
        Teacher teacher = new Teacher();
        teacher.setDob(LocalDate.ofEpochDay(1L));
        teacher.setId(123L);
        teacher.setCourses(new ArrayList<Course>());
        teacher.setName("Name");
        Optional<Teacher> ofResult = Optional.<Teacher>of(teacher);
        when(this.teacherRepository.findById((Long) any())).thenReturn(ofResult);

        Semester semester = new Semester();
        semester.setId(123L);
        semester.setName("Name");
        semester.setOfferedCourses(new ArrayList<Course>());

        Teacher teacher1 = new Teacher();
        teacher1.setDob(LocalDate.ofEpochDay(1L));
        teacher1.setId(123L);
        teacher1.setCourses(new ArrayList<Course>());
        teacher1.setName("Name");

        Course course = new Course();
        course.setSemester(semester);
        course.setId(123L);
        course.setTeacher(teacher1);
        course.setName("Name");
        course.setStudentList(new ArrayList<Student>());
        Optional<Course> ofResult1 = Optional.<Course>of(course);
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult1);
        this.teacherService.teachCourse(123L, 123L);
        verify(this.courseRepository).findById((Long) any());
        verify(this.teacherRepository).findById((Long) any());
    }

    @Test
    public void testTeachCourse2() {
        when(this.teacherRepository.findById((Long) any())).thenReturn(Optional.<Teacher>empty());

        Semester semester = new Semester();
        semester.setId(123L);
        semester.setName("Name");
        semester.setOfferedCourses(new ArrayList<Course>());

        Teacher teacher = new Teacher();
        teacher.setDob(LocalDate.ofEpochDay(1L));
        teacher.setId(123L);
        teacher.setCourses(new ArrayList<Course>());
        teacher.setName("Name");

        Course course = new Course();
        course.setSemester(semester);
        course.setId(123L);
        course.setTeacher(teacher);
        course.setName("Name");
        course.setStudentList(new ArrayList<Student>());
        Optional<Course> ofResult = Optional.<Course>of(course);
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> this.teacherService.teachCourse(123L, 123L));
        verify(this.teacherRepository).findById((Long) any());
    }

    @Test
    public void testTeachCourse3() {
        Teacher teacher = new Teacher();
        teacher.setDob(LocalDate.ofEpochDay(1L));
        teacher.setId(123L);
        teacher.setCourses(new ArrayList<Course>());
        teacher.setName("Name");
        Optional<Teacher> ofResult = Optional.<Teacher>of(teacher);
        when(this.teacherRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.courseRepository.findById((Long) any())).thenReturn(Optional.<Course>empty());
        assertThrows(IllegalStateException.class, () -> this.teacherService.teachCourse(123L, 123L));
        verify(this.courseRepository).findById((Long) any());
        verify(this.teacherRepository).findById((Long) any());
    }
}

