package com.tangent.ums.service;

import com.tangent.ums.model.Semester;
import com.tangent.ums.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {
    
    private final SemesterRepository semesterRepository;
    
    @Autowired
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    public void createNewSemester(String name) {
        Optional<Semester> semesterByName = semesterRepository.findSemesterByName(name);
        if (semesterByName.isPresent())
            throw new IllegalStateException("Semester already exists");
        Semester semester = new Semester(name);
        semesterRepository.save(semester);
    }
}
