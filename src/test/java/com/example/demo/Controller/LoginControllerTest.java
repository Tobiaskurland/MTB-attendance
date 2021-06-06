package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.LoginService;
import com.example.demo.Service.SignupService;
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
@WebMvcTest(LoginController.class)
class LoginControllerTest
{
    @MockBean
    private LoginService loginService;

    @MockBean
    private SignupService signupService;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setup()
    {
        when(loginService.findLogin("userthatexists", "1234")).thenReturn(new User());
    }

    @Test
    void getLoginPage() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void logout() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/logout")).andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @Test
    void login() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.post("/login?email=hacker&password=hackthemainframe"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        mvc.perform(MockMvcRequestBuilders.post("/login?email=userthatexists&password=1234"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/overview"));

        mvc.perform(MockMvcRequestBuilders.post("/login"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}