package com.example.demo.Controller;

import com.example.demo.Model.Clazz;
import com.example.demo.Model.ClazzViewModel;
import com.example.demo.Model.User;
import com.example.demo.Service.IClazzService;
import com.example.demo.Service.IEducationService;
import com.example.demo.Service.IUserClassService;
import com.example.demo.Service.IUserService;
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
@WebMvcTest(AdminClassController.class)
class AdminClassControllerTest
{
    @MockBean
    IClazzService clazzService;

    @MockBean
    IEducationService educationService;

    @MockBean
    IUserService userService;

    @MockBean
    IUserClassService userClassService;

    @Autowired
    MockMvc mvc;

    private HashMap<String, Object> session;
    private HashMap<String, Object> adminSession;

    @BeforeEach
    void setup()
    {
        User teacher = new User();
        teacher.setUserId(1);
        teacher.setRole_id(2);

        User admin = new User();
        admin.setUserId(2);
        admin.setRole_id(3);

        session = new HashMap<>();
        session.put("login", teacher);

        adminSession = new HashMap<>();
        adminSession.put("login", admin);

        when(clazzService.findById(1)).thenReturn(new Clazz());
    }

    @Test
    void adminClass() throws Exception
    {
        //not logged in
        mvc.perform(MockMvcRequestBuilders.get("/admin/class"))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in not admin
        mvc.perform(MockMvcRequestBuilders.get("/admin/class")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in admin
        mvc.perform(MockMvcRequestBuilders.get("/admin/class")
                .sessionAttrs(adminSession))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void addClass() throws Exception
    {
        //not logged in
        mvc.perform(MockMvcRequestBuilders.get("/admin/addClass"))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in not admin
        mvc.perform(MockMvcRequestBuilders.get("/admin/addClass")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in admin
        mvc.perform(MockMvcRequestBuilders.get("/admin/addClass")
                .sessionAttrs(adminSession))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void addClassSuccess() throws Exception
    {

        //not logged in
        mvc.perform(MockMvcRequestBuilders.get("/admin/addClass/success"))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in not admin
        mvc.perform(MockMvcRequestBuilders.get("/admin/addClass/success")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in admin
        mvc.perform(MockMvcRequestBuilders.get("/admin/addClass/success")
                .sessionAttrs(adminSession))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateClass() throws Exception
    {
        //not logged in
        mvc.perform(MockMvcRequestBuilders.get("/admin/1/updateClass"))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in not admin
        mvc.perform(MockMvcRequestBuilders.get("/admin/1/updateClass")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in admin
        mvc.perform(MockMvcRequestBuilders.get("/admin/1/updateClass")
                .sessionAttrs(adminSession))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //POST

        ClazzViewModel model = new ClazzViewModel();
        model.setClazz(new Clazz());

        //not logged in
        mvc.perform(MockMvcRequestBuilders.post("/admin/1/updateClass"))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in not admin
        mvc.perform(MockMvcRequestBuilders.post("/admin/1/updateClass")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in admin
        mvc.perform(MockMvcRequestBuilders.post("/admin/1/updateClass")
                .flashAttr("updatedClazz", model)
                .sessionAttrs(adminSession))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/addClass/success"));
    }

    @Test
    void deleteClass() throws Exception
    {
        //not logged in
        mvc.perform(MockMvcRequestBuilders.get("/admin/1/deleteClass"))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in not admin
        mvc.perform(MockMvcRequestBuilders.get("/admin/1/deleteClass")
                .sessionAttrs(session))
                .andExpect(MockMvcResultMatchers.view().name("error"));

        //logged in admin
        //mvc.perform(MockMvcRequestBuilders.get("/admin/1/deleteClass")
        //        .sessionAttrs(adminSession))
        //        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteClassConfirm() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/admin/delete/1/confirm")
                .sessionAttrs(adminSession))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}