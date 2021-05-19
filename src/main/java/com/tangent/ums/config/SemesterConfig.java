package com.tangent.ums.config;

import com.tangent.ums.model.Semester;
import com.tangent.ums.repository.SemesterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SemesterConfig {

    @Bean
    CommandLineRunner commandLineRunnerSemester(SemesterRepository semesterRepository) {
        return args -> {
            Semester fall2020 = new Semester("FALL2020");
            Semester spring2020 = new Semester("SPRING2020");
            semesterRepository.saveAll(List.of(fall2020, spring2020));
        };
    }
}
