package com.tangent.ums.config;

import com.tangent.ums.model.Student;
import com.tangent.ums.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunnerStudent(StudentRepository studentRepository) {
        return args -> {
            Student abdullah = new Student("Abdullah",
                    LocalDate.of(1996, Month.FEBRUARY, 24));
            Student ali = new Student(  "Ali",
                    LocalDate.of(1995,Month.JANUARY,12));
            studentRepository.saveAll(List.of(abdullah, ali));
        };
    }
}
