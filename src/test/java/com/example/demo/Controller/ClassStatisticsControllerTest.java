package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.IAttendanceService;
import com.example.demo.Service.IClazzService;
import com.example.demo.Service.ILectureService;
import com.example.demo.Service.IUserClassService;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClassStatisticsController.class)
class ClassStatisticsControllerTest
{

    @MockBean
    IClazzService clazzService;

    @MockBean
    ILectureService lectureService;

    @MockBean
    IUserClassService userClassService;

    @MockBean
    IAttendanceService attendanceService;

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
    }

    @Test
    void classStatistics() throws Exception
    {
        //not logged in
        mvc.perform(MockMvcRequestBuilders.get("/class/statistics"))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in
        mvc.perform(MockMvcRequestBuilders.get("/class/statistics")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void getClassStatisticsResult() throws Exception
    {
        //not logged in
        mvc.perform(MockMvcRequestBuilders.get("/class/statistics/result?class=1"))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in
        mvc.perform(MockMvcRequestBuilders.get("/class/statistics/result?class=1")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}