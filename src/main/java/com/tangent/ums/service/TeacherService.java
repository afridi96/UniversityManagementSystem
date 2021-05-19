package com.tangent.ums.service;

import com.tangent.ums.model.Course;
import com.tangent.ums.model.Teacher;
import com.tangent.ums.repository.CourseRepository;
import com.tangent.ums.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).
                orElseThrow(() -> new IllegalStateException("Teacher does not exist"));
    }

    @Transactional
    public void teachCourse(Long teacherId, Long courseId) {
        Teacher teacher = teacherRepository.findById(teacherId).
                orElseThrow(() -> new IllegalStateException("Teacher does not exist"));
        Course course = courseRepository.findById(courseId).
                orElseThrow(() -> new IllegalStateException("Course does not exist"));
        if (teacher.getCourses().size() == 3)
            throw new IllegalStateException("Maximum number of courses taken");
        teacher.getCourses().add(course);
    }
}
