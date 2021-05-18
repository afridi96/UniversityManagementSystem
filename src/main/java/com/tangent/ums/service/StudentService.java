package com.tangent.ums.service;

import com.tangent.ums.model.Course;
import com.tangent.ums.model.Student;
import com.tangent.ums.repository.CourseRepository;
import com.tangent.ums.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).
                orElseThrow(() -> new IllegalStateException("Student does not exist"));
    }

    public void addNewStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("Student Does Not Exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name) {
        Student student = studentRepository.findById(studentId).
                orElseThrow(() -> new IllegalStateException("Student does not exist"));
        if (name != null && name.length() >= 0 &&
        !Objects.equals(name, student.getName())) {
            student.setName(name);
        }
    }

    @Transactional
    public void registerCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).
                orElseThrow(() -> new IllegalStateException("Student does not exist"));
        Course course = courseRepository.findById(courseId).
                orElseThrow(() -> new IllegalStateException("Course not available"));
        if (student.getRegisteredCourses().size() == 5)
            throw new IllegalStateException("Maximum number of courses taken");
        student.getRegisteredCourses().add(course);
    }

}
