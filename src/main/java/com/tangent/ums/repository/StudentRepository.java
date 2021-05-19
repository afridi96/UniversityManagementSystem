package com.tangent.ums.repository;

import com.tangent.ums.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
