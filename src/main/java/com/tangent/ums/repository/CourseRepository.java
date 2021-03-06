package com.tangent.ums.repository;

import com.tangent.ums.model.Course;
import com.tangent.ums.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findCourseByNameAndSemester(String name, Semester semester);
}
