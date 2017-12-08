package com.yura.registration.controller;

import com.yura.registration.entity.User;
import com.yura.registration.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String create(
            @RequestParam (value = "name") String firstName,
            @RequestParam (value = "email") String email,
            @RequestParam (value = "username") String name,
            @RequestParam (value = "password") String password
    ) {
        String encryptedPassword = RegistrationController.md5Apache(password);
        User user = new User(name, firstName, email, encryptedPassword);
        userService.addUser(user);
        return "index";
    }

    @RequestMapping(value = "/{n}", method = RequestMethod.GET)
    public @ResponseBody String checkDBUser(
            @PathVariable (value = "n") String name
    ) {
        System.out.println("Name is " + name);
        List<User> users = userService.getAllUsers();

        List<String> usersNames = new ArrayList<>();
        users.forEach(u -> usersNames.add(u.getName()));

        String message = "good";
        for (String s : usersNames) {
            if (s.equals(name)) {
                message = "bad";
                break;
            }
        }
        return message;
    }

    public static String md5Apache(String st) {
        return DigestUtils.md5Hex(st);
    }
}
