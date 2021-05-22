package com.tangent.ums.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tangent.ums.model.Course;
import com.tangent.ums.model.Semester;
import com.tangent.ums.model.Student;
import com.tangent.ums.model.Teacher;
import com.tangent.ums.repository.CourseRepository;
import com.tangent.ums.repository.StudentRepository;

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

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
public class StudentServiceTest {
    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Test
    public void testGetAllStudents() {
        ArrayList<Student> studentList = new ArrayList<Student>();
        when(this.studentRepository.findAll()).thenReturn(studentList);
        List<Student> actualAllStudents = this.studentService.getAllStudents();
        assertSame(studentList, actualAllStudents);
        assertTrue(actualAllStudents.isEmpty());
        verify(this.studentRepository).findAll();
    }

    @Test
    public void testGetStudentById() {
        Student student = new Student();
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setRegisteredCourses(new ArrayList<Course>());
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.<Student>of(student);
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(student, this.studentService.getStudentById(123L));
        verify(this.studentRepository).findById((Long) any());
    }

    @Test
    public void testGetStudentById2() {
        when(this.studentRepository.findById((Long) any())).thenReturn(Optional.<Student>empty());
        assertThrows(IllegalStateException.class, () -> this.studentService.getStudentById(123L));
        verify(this.studentRepository).findById((Long) any());
    }

    @Test
    public void testAddNewStudent() {
        Student student = new Student();
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setRegisteredCourses(new ArrayList<Course>());
        student.setId(123L);
        student.setName("Name");
        when(this.studentRepository.save((Student) any())).thenReturn(student);
        this.studentService.addNewStudent(new Student());
        verify(this.studentRepository).save((Student) any());
    }

    @Test
    public void testDeleteStudent() {
        doNothing().when(this.studentRepository).deleteById((Long) any());
        when(this.studentRepository.existsById((Long) any())).thenReturn(true);
        this.studentService.deleteStudent(123L);
        verify(this.studentRepository).deleteById((Long) any());
        verify(this.studentRepository).existsById((Long) any());
    }

    @Test
    public void testDeleteStudent2() {
        doNothing().when(this.studentRepository).deleteById((Long) any());
        when(this.studentRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> this.studentService.deleteStudent(123L));
        verify(this.studentRepository).existsById((Long) any());
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student();
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setRegisteredCourses(new ArrayList<Course>());
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.<Student>of(student);
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        this.studentService.updateStudent(123L, "Name");
        verify(this.studentRepository).findById((Long) any());
    }

    @Test
    public void testUpdateStudent2() {
        Student student = new Student();
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setRegisteredCourses(new ArrayList<Course>());
        student.setId(123L);
        student.setName(null);
        Optional<Student> ofResult = Optional.<Student>of(student);
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        this.studentService.updateStudent(123L, "Name");
        verify(this.studentRepository).findById((Long) any());
    }

    @Test
    public void testUpdateStudent3() {
        when(this.studentRepository.findById((Long) any())).thenReturn(Optional.<Student>empty());
        assertThrows(IllegalStateException.class, () -> this.studentService.updateStudent(123L, "Name"));
        verify(this.studentRepository).findById((Long) any());
    }

    @Test
    public void testUpdateStudent4() {
        Student student = new Student();
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setRegisteredCourses(new ArrayList<Course>());
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.<Student>of(student);
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        this.studentService.updateStudent(123L, null);
        verify(this.studentRepository).findById((Long) any());
    }

    @Test
    public void testRegisterCourse() {
        Student student = new Student();
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setRegisteredCourses(new ArrayList<Course>());
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.<Student>of(student);
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);

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
        Optional<Course> ofResult1 = Optional.<Course>of(course);
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult1);
        this.studentService.registerCourse(123L, 123L);
        verify(this.courseRepository).findById((Long) any());
        verify(this.studentRepository).findById((Long) any());
    }

    @Test
    public void testRegisterCourse2() {
        when(this.studentRepository.findById((Long) any())).thenReturn(Optional.<Student>empty());

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
        assertThrows(IllegalStateException.class, () -> this.studentService.registerCourse(123L, 123L));
        verify(this.studentRepository).findById((Long) any());
    }

    @Test
    public void testRegisterCourse3() {
        Student student = new Student();
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setRegisteredCourses(new ArrayList<Course>());
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.<Student>of(student);
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.courseRepository.findById((Long) any())).thenReturn(Optional.<Course>empty());
        assertThrows(IllegalStateException.class, () -> this.studentService.registerCourse(123L, 123L));
        verify(this.courseRepository).findById((Long) any());
        verify(this.studentRepository).findById((Long) any());
    }
}

