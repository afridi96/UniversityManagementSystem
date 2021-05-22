package com.tangent.ums.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tangent.ums.model.Course;
import com.tangent.ums.model.Semester;
import com.tangent.ums.model.Student;
import com.tangent.ums.model.Teacher;
import com.tangent.ums.repository.CourseRepository;
import com.tangent.ums.repository.SemesterRepository;

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

@ContextConfiguration(classes = {CourseService.class})
@ExtendWith(SpringExtension.class)
public class CourseServiceTest {
    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @MockBean
    private SemesterRepository semesterRepository;

    @Test
    public void testGetAllCourses() {
        ArrayList<Course> courseList = new ArrayList<Course>();
        when(this.courseRepository.findAll()).thenReturn(courseList);
        List<Course> actualAllCourses = this.courseService.getAllCourses();
        assertSame(courseList, actualAllCourses);
        assertTrue(actualAllCourses.isEmpty());
        verify(this.courseRepository).findAll();
    }

    @Test
    public void testGetCourseById() {
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
        assertSame(course, this.courseService.getCourseById(123L));
        verify(this.courseRepository).findById((Long) any());
    }

    @Test
    public void testGetCourseById2() {
        when(this.courseRepository.findById((Long) any())).thenReturn(Optional.<Course>empty());
        assertThrows(IllegalStateException.class, () -> this.courseService.getCourseById(123L));
        verify(this.courseRepository).findById((Long) any());
    }

    @Test
    public void testCreateNewCourse() {
        Semester semester = new Semester();
        semester.setId(123L);
        semester.setName("Name");
        semester.setOfferedCourses(new ArrayList<Course>());
        Optional<Semester> ofResult = Optional.<Semester>of(semester);
        when(this.semesterRepository.findSemesterByName(anyString())).thenReturn(ofResult);

        Semester semester1 = new Semester();
        semester1.setId(123L);
        semester1.setName("Name");
        semester1.setOfferedCourses(new ArrayList<Course>());

        Teacher teacher = new Teacher();
        teacher.setDob(LocalDate.ofEpochDay(1L));
        teacher.setId(123L);
        teacher.setCourses(new ArrayList<Course>());
        teacher.setName("Name");

        Course course = new Course();
        course.setSemester(semester1);
        course.setId(123L);
        course.setTeacher(teacher);
        course.setName("Name");
        course.setStudentList(new ArrayList<Student>());
        Optional<Course> ofResult1 = Optional.<Course>of(course);
        when(this.courseRepository.findCourseByNameAndSemester(anyString(), (Semester) any())).thenReturn(ofResult1);
        assertThrows(IllegalStateException.class, () -> this.courseService.createNewCourse("Course Name", "Semester Name"));
        verify(this.courseRepository).findCourseByNameAndSemester(anyString(), (Semester) any());
        verify(this.semesterRepository).findSemesterByName(anyString());
    }

    @Test
    public void testCreateNewCourse2() {
        when(this.semesterRepository.findSemesterByName(anyString())).thenReturn(Optional.<Semester>empty());

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
        when(this.courseRepository.findCourseByNameAndSemester(anyString(), (Semester) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> this.courseService.createNewCourse("Course Name", "Semester Name"));
        verify(this.semesterRepository).findSemesterByName(anyString());
    }

    @Test
    public void testCreateNewCourse3() {
        Semester semester = new Semester();
        semester.setId(123L);
        semester.setName("Name");
        semester.setOfferedCourses(new ArrayList<Course>());
        Optional<Semester> ofResult = Optional.<Semester>of(semester);
        when(this.semesterRepository.findSemesterByName(anyString())).thenReturn(ofResult);

        Semester semester1 = new Semester();
        semester1.setId(123L);
        semester1.setName("Name");
        semester1.setOfferedCourses(new ArrayList<Course>());

        Teacher teacher = new Teacher();
        teacher.setDob(LocalDate.ofEpochDay(1L));
        teacher.setId(123L);
        teacher.setCourses(new ArrayList<Course>());
        teacher.setName("Name");

        Course course = new Course();
        course.setSemester(semester1);
        course.setId(123L);
        course.setTeacher(teacher);
        course.setName("Name");
        course.setStudentList(new ArrayList<Student>());
        when(this.courseRepository.save((Course) any())).thenReturn(course);
        when(this.courseRepository.findCourseByNameAndSemester(anyString(), (Semester) any()))
                .thenReturn(Optional.<Course>empty());
        this.courseService.createNewCourse("Course Name", "Semester Name");
        verify(this.courseRepository).save((Course) any());
        verify(this.courseRepository).findCourseByNameAndSemester(anyString(), (Semester) any());
        verify(this.semesterRepository).findSemesterByName(anyString());
    }
}

