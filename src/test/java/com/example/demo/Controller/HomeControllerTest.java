package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.IUserService;
import com.example.demo.Service.UserServiceImpl;
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
@WebMvcTest(HomeController.class)
class HomeControllerTest
{
    @MockBean
    private UserServiceImpl userService;

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
    void getHeader() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/header")
        .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}