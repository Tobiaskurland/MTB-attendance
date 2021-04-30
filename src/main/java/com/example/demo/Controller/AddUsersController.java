package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AddUsersController
{
    @Autowired
    private IUserService userService;

    @GetMapping("/users/add")
    public String addUsersView(HttpSession session, Model model)
    {
        User u = getLoggedInUser(session);

        if(u != null && u.getRole_id() > 1)
        {
            return "addusers";
        }
        return "redirect:/overview";
    }

    @PostMapping("/users/submit")
    public String createUsers(@RequestBody List<User> users, HttpSession session)
    {
        if(getLoggedInUser(session) != null)
        {
            for(User u : users)
            {
                userService.save(u);
            }

            return "redirect:/users/add/success";
        }
        return "redirect:/";
    }

    @GetMapping("/users/add/success")
    public String addUsersSuccess()
    {
        return "adduserssuccess";
    }

    private User getLoggedInUser(HttpSession session)
    {
        try
        {
            User user = (User) session.getAttribute("login");
            return user;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
