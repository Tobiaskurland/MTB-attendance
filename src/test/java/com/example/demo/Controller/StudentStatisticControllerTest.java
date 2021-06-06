package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.AttendanceServiceImpl;
import com.example.demo.Service.ICourseService;
import com.example.demo.Service.LectureServiceImpl;
import com.example.demo.Service.UserServiceImpl;
import com.example.demo.Service.View.AttendanceOverviewImpl;
import com.example.demo.Service.View.AttendanceViewServiceImpl;
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
@WebMvcTest(StudentStatisticController.class)
class StudentStatisticControllerTest
{
    @MockBean
    AttendanceServiceImpl attendanceService;

    @MockBean
    UserServiceImpl userService;

    @MockBean
    ICourseService courseService;

    @MockBean
    LectureServiceImpl lectureService;

    @MockBean
    AttendanceViewServiceImpl attendanceViewService;

    @MockBean
    AttendanceOverviewImpl attendanceOverview;

    @Autowired
    private MockMvc mvc;

    private HashMap<String, Object> session;

    @BeforeEach
    void setup()
    {
        User user = new User();
        user.setUserId(1);
        user.setRole_id(2);
        session = new HashMap<>();
        session.put("login", user);

        when(userService.findById(1)).thenReturn(user);
    }

    @Test
    void getStudentStatistics() throws Exception
    {
        //not logged in
        mvc.perform(MockMvcRequestBuilders.get("/student/statistics"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        //logged in
        mvc.perform(MockMvcRequestBuilders.get("/student/statistics")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getStudentStatisticsResult() throws Exception
    {
        //not logged in
        mvc.perform(MockMvcRequestBuilders.get("/student/statistics/result?course=1"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        //logged in
        mvc.perform(MockMvcRequestBuilders.get("/student/statistics/result?course=1")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}