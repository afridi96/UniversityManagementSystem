package com.tangent.ums.config;

import com.tangent.ums.model.Teacher;
import com.tangent.ums.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class TeacherConfig {

    @Bean
    CommandLineRunner commandLineRunnerTeacher(TeacherRepository teacherRepository) {
        return args -> {
            Teacher omer = new Teacher("Omer", LocalDate.of(1985,Month.DECEMBER,16));
            Teacher mazhar = new Teacher("Mazhar",LocalDate.of(1980,Month.APRIL, 1));
            teacherRepository.saveAll(List.of(omer,mazhar));
        };
    }
}
