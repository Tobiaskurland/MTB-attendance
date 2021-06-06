package com.example.demo.Controller;

import com.example.demo.Model.Lecture;
import com.example.demo.Model.User;
import com.example.demo.Service.AttendanceServiceImpl;
import com.example.demo.Service.ILectureService;
import com.example.demo.Utility.GenerateCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CodeController.class)
class CodeControllerTest
{
    @MockBean
    ILectureService lectureService;

    @MockBean
    AttendanceServiceImpl attendanceService;

    private HashMap<String, Object> session;

    @Autowired
    MockMvc mvc;

    @BeforeEach
    void setup()
    {
        User user = new User();
        user.setUserId(1);
        user.setRole_id(2);
        session = new HashMap<>();
        session.put("login", user);

        when(lectureService.findById(1)).thenReturn(new Lecture());
        Lecture l = new Lecture();
        l.setLectureId(2);
        l.setCodeExpire(Timestamp.from(Instant.now()));
        when(lectureService.findById(2)).thenReturn(l);
    }

    @Test
    void teacherAttendance() throws Exception
    {
        //no login
        mvc.perform(MockMvcRequestBuilders.get("/lecture/1/attendance/createcode"))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in lecture without timestamp
        mvc.perform(MockMvcRequestBuilders.get("/lecture/1/attendance/createcode")
        .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //logged in lecture with timestamp
        mvc.perform(MockMvcRequestBuilders.get("/lecture/2/attendance/createcode")
        .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void studentAttendance() throws Exception
    {
        //no login
        mvc.perform(MockMvcRequestBuilders.get("/lecture/1/attendance"))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in lecture without timestamp
        mvc.perform(MockMvcRequestBuilders.get("/lecture/1/attendance")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //logged in lecture with timestamp
        mvc.perform(MockMvcRequestBuilders.get("/lecture/2/attendance")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void generateCode() throws Exception
    {
        //no login
        mvc.perform(MockMvcRequestBuilders.get("/GenerateCode/1"))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in
        mvc.perform(MockMvcRequestBuilders.get("/GenerateCode/2")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/lecture/2/attendance/createcode"));
    }

    @Test
    void studentCode() throws Exception
    {
        //no login
        mvc.perform(MockMvcRequestBuilders.post("/StudentCode/1"))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in
        mvc.perform(MockMvcRequestBuilders.post("/StudentCode/1")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/incorrect/1"));

    }

    @Test
    void success() throws Exception
    {
        //no login
        mvc.perform(MockMvcRequestBuilders.get("/success/1"))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in
        mvc.perform(MockMvcRequestBuilders.get("/success/1")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.view().name("success"));
    }

    @Test
    void incorrect() throws Exception
    {
        //no login
        mvc.perform(MockMvcRequestBuilders.get("/incorrect/1"))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in
        mvc.perform(MockMvcRequestBuilders.get("/incorrect/1")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.view().name("incorrect"));
    }
}