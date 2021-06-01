package com.example.demo.Controller;

import com.example.demo.Model.Course;
import com.example.demo.Model.User;
import com.example.demo.Service.IClazzService;
import com.example.demo.Service.ICourseService;
import com.example.demo.Service.ILectureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LectureController.class)
class LectureControllerTest
{
    @MockBean
    private IClazzService clazzService;

    @MockBean
    private ILectureService lectureService;

    @MockBean
    private ICourseService courseService;

    @Autowired
    private MockMvc mvc;

    private HashMap<String, Object> studentSession;
    private HashMap<String, Object> session;



    @BeforeEach
    void setup()
    {
        User teacher = new User();
        teacher.setUserId(1);
        teacher.setRole_id(2);
        teacher.setFirstName("bob");
        teacher.setLastName("marley");
        teacher.setEmail("b@marley.com");
        session = new HashMap<>();
        session.put("login", teacher);

        User student = new User();
        student.setUserId(2);
        student.setRole_id(1);
        student.setFirstName("bob");
        student.setLastName("marley");
        student.setEmail("e@mail.dk");
        studentSession = new HashMap<>();
        studentSession.put("login", student);

        when(courseService.findById(1)).thenReturn(new Course());
    }

    @Test
    void testAddLecture() throws Exception
    {
        //not logged in
        mvc.perform(MockMvcRequestBuilders.get("/course/1/addLecture"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        // logged in teacher
        mvc.perform(MockMvcRequestBuilders.get("/course/1/addLecture")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // logged in student
        mvc.perform(MockMvcRequestBuilders.get("/course/1/addLecture")
                .sessionAttrs(studentSession))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/overview"));

    }
}