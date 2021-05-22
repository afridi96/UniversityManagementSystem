package com.tangent.ums.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tangent.ums.model.Course;
import com.tangent.ums.model.Semester;
import com.tangent.ums.repository.SemesterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SemesterService.class})
@ExtendWith(SpringExtension.class)
public class SemesterServiceTest {
    @MockBean
    private SemesterRepository semesterRepository;

    @Autowired
    private SemesterService semesterService;

    @Test
    public void testGetAllSemesters() {
        ArrayList<Semester> semesterList = new ArrayList<Semester>();
        when(this.semesterRepository.findAll()).thenReturn(semesterList);
        List<Semester> actualAllSemesters = this.semesterService.getAllSemesters();
        assertSame(semesterList, actualAllSemesters);
        assertTrue(actualAllSemesters.isEmpty());
        verify(this.semesterRepository).findAll();
    }

    @Test
    public void testCreateNewSemester() {
        Semester semester = new Semester();
        semester.setId(123L);
        semester.setName("Name");
        semester.setOfferedCourses(new ArrayList<Course>());
        Optional<Semester> ofResult = Optional.<Semester>of(semester);
        when(this.semesterRepository.findSemesterByName(anyString())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> this.semesterService.createNewSemester("Name"));
        verify(this.semesterRepository).findSemesterByName(anyString());
    }

    @Test
    public void testCreateNewSemester2() {
        Semester semester = new Semester();
        semester.setId(123L);
        semester.setName("Name");
        semester.setOfferedCourses(new ArrayList<Course>());
        when(this.semesterRepository.save((Semester) any())).thenReturn(semester);
        when(this.semesterRepository.findSemesterByName(anyString())).thenReturn(Optional.<Semester>empty());
        this.semesterService.createNewSemester("Name");
        verify(this.semesterRepository).findSemesterByName(anyString());
        verify(this.semesterRepository).save((Semester) any());
    }
}

