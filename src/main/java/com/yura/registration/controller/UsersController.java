package com.yura.registration.controller;

import com.yura.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users")
    public String getUsers(ModelMap modelMap) {
        modelMap.put("users", userService.getAllUsers());
        return "users";
    }
}
