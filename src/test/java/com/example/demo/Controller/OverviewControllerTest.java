package com.example.demo.Controller;

import com.example.demo.Model.User;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OverviewController.class)
class OverviewControllerTest
{
    @MockBean
    private ICourseService courseService;

    @MockBean
    private ILectureService lectureService;

    @Autowired
    private MockMvc mvc;

    private HashMap<String, Object> session;

    @BeforeEach
    void setup()
    {
        User user = new User();
        user.setUserId(1);
        user.setRole_id(1);
        session = new HashMap<>();
        session.put("login", user);
    }

    @Test
    void getOverviewPage() throws Exception
    {
        //no user
        mvc.perform(MockMvcRequestBuilders.get("/overview"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        //Student
        mvc.perform(MockMvcRequestBuilders.get("/overview").sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.view().name("Overview"));

        ((User) session.get("login")).setRole_id(2);

        //Teacher
        mvc.perform(MockMvcRequestBuilders.get("/overview").sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.view().name("Overview"));

        ((User) session.get("login")).setRole_id(3);

        //admin
        mvc.perform(MockMvcRequestBuilders.get("/overview").sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.view().name("admin"));

    }
}