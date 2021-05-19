package com.tangent.ums.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangent.ums.model.Course;
import com.tangent.ums.model.Student;
import com.tangent.ums.service.StudentService;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
public class StudentControllerTest {
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentService studentService;

    @Test
    public void testDeleteStudent() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/{studentId}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetStudentById() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{studentId}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetallStudents() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testRegisterCourse() throws Exception {
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/{studentId}/courses", 1L);
        MockHttpServletRequestBuilder requestBuilder = putResult.param("courseId", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testRegisterStudent() throws Exception {
        Student student = new Student();
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setRegisteredCourses(new ArrayList<Course>());
        student.setId(123L);
        student.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(student);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testUpdateStudent() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/{studentId}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

