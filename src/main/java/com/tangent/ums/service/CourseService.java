package com.tangent.ums.service;

import com.tangent.ums.model.Course;
import com.tangent.ums.model.Semester;
import com.tangent.ums.repository.CourseRepository;
import com.tangent.ums.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final SemesterRepository semesterRepository;

    @Autowired
    CourseService(CourseRepository courseRepository, SemesterRepository semesterRepository) {
        this.courseRepository = courseRepository;
        this.semesterRepository = semesterRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).
                orElseThrow(() -> new IllegalStateException("Course does not exist"));
    }

    public void createNewCourse(String courseName, String semesterName) {
        Optional<Semester> semester = semesterRepository.findSemesterByName(semesterName);
        if (!semester.isPresent())
            throw new IllegalStateException("Semester does not exist");
        Optional<Course> courseByNameAndSemester = courseRepository.
                findCourseByNameAndSemester(courseName, semester.get());
        if (courseByNameAndSemester.isPresent())
            throw new IllegalStateException("Course Already Exists");
        Course course = new Course(courseName, semester.get());
        courseRepository.save(course);
    }
}
