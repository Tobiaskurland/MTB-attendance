package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest
{
    @MockBean
    private IUserService userService;

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
    void addUsers() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/users/add").sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.status().isOk());

        ((User) session.get("login")).setRole_id(1);

        mvc.perform(MockMvcRequestBuilders.get("/users/add").sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/overview"));
    }

    @Test
    void createUsers() throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setUserId(1);
        user.setFirstName("bob");
        user.setLastName("dylan");

        List<User> users = new ArrayList<>();
        users.add(user);

        //no user session
        mvc.perform(MockMvcRequestBuilders.post("/users/submit")
                .content(objectMapper.writeValueAsString(users))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        ((User) session.get("login")).setRole_id(2);

        mvc.perform(MockMvcRequestBuilders.post("/users/submit")
                .sessionAttrs(session)
                .content(objectMapper.writeValueAsString(users))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/users/add/success"));

        mvc.perform(MockMvcRequestBuilders.post("/users/submit")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(null)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}